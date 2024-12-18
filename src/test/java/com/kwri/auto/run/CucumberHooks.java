package com.kwri.auto.run;

import com.google.inject.Inject;
import com.kwri.auto.config.CustomLogFilter;
import com.kwri.auto.enums.User;
import com.kwri.auto.steps.GeneralGivenSteps;
import com.kwri.auto.steps.GeneralWhenSteps;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.webdriver.BrowserOptions;
import io.cucumber.java.*;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.exception.JsonPathException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.AssumptionViolatedException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.kwri.auto.config.Config.ENV;

/**
 * The type Cucumber hooks.
 */
@Slf4j
public class CucumberHooks {

    static {
        ENV_URL = ENV.getBaseUri();
    }

    /**
     * The constant ENV_URL.
     */
    public static final String ENV_URL;
    /**
     * The constant JIRA_USERNAME.
     */
    public static final String JIRA_USERNAME = System.getProperty("jiraUsername", "KWRI Automation User");
    /**
     * The constant WORLD_THREAD_LOCAL.
     */
    public static final ThreadLocal<World> WORLD_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * The constant XRAY_JSON_REPORT_FILEPATH.
     */
    public static final String XRAY_JSON_REPORT_FILEPATH =
            System.getProperty("jsonReportFilepath", "target/xray-report/xrayReport.json");

    private static final HashMap<User, String> TOKENS = new HashMap<>();

    @Inject
    private World world;
    @Inject
    private GeneralWhenSteps generalWhenSteps;
    @Inject
    private GeneralGivenSteps generalGivenSteps;
    private String ongoingJiraTag;
    @Inject
    private JiraReportHooks jiraReportHooks;

    /**
     * Sets world.
     */
    @Before
    public void setWorld() {
        WORLD_THREAD_LOCAL.set(world);
    }

    /**
     * Steps to be done before scenario.
     *
     * @param scenario ongoing scenario
     */
    @Before
    public void before(final Scenario scenario) {
        //        Collect list of all tags of scenario
        final ArrayList<String> scenarioTags = new ArrayList<>(scenario.getSourceTagNames());
        //        Set JIRA tag of ongoing scenario
        this.ongoingJiraTag = this.jiraReportHooks.getJiraTicketNumber(scenario).toString();
        if (skipScenarioNotApplicableForCurrentEnv(scenarioTags)) {
            throw new AssumptionViolatedException("Test scenario " + ongoingJiraTag
                    + " is not supported for current environment."
                    + " Selected environment is: " + ENV.getEnvironmentName());
        }
    }

    /**
     * This method will verify if scenario can be executed in given env.
     * If scenario contains any of env tag (or all of them) then if it will verify if it matches with current env value.
     * For example: You have test with Example 1 tagged as @QA and Example 2 tagged as @DEV @PROD
     * if you will execute this test in QA env - Example 1 will be executed and Example 2 will be skipped.
     *
     * @param scenarioTags list of tags for ongoing scenario
     * @return decision whether to skip scenario or not
     */
    private boolean skipScenarioNotApplicableForCurrentEnv(final ArrayList<String> scenarioTags) {
        return !scenarioTags.contains("@" + ENV.getEnvironmentName());
    }

    /**
     * Sets up headless mode.
     */
    @BeforeAll
    public static void setUpHeadlessMode() {
        ChromeOptions chromeOptions = BrowserOptions.getDefaultChromeOptions();
        chromeOptions.setHeadless(true);
        BrowserOptions.setChromeOptions(chromeOptions);
    }

    /**
     * After each scenario.
     *
     * @param scenario the scenario
     */
    @After(order = 1)
    public void shutDownDriver(Scenario scenario) {
        log.info("Shutting down browser and making screenshot if test failed");
        String screenshotName = scenario.getName();
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.BYTES);
            File scrFile = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.FILE);
            File destinationPath = new File(
                    System.getProperty("user.dir") + "/target/cucumber-html-reports/screenshots/"
                            + screenshotName + ".png");
            try {
                FileUtils.copyFile(scrFile, destinationPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            scenario.attach(screenshot, "image/png", "test");
        }
        world.driver.quit();
    }

    /**
     * Generate allure env.
     */
    @AfterAll(order = 1)
    public static void generateAllureEnv() {
        File allureEnvFile = new File("target/allure-results/environment.properties");
        try {
            FileWriter writer = new FileWriter(allureEnvFile);
            writer.write("ENVIRONMENT: " + ENV.name());
            writer.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Gets authorization token.
     *
     * @param userName the username
     * @param password the password
     * @return the authorization token
     */
    @Step("Get authorization token")
    public static String getAuthorizationToken(String userName, String password) {
        try {
            return RestAssured.given()
                    .formParam("username", userName)
                    .formParam("password", password)
                    .filter(new CustomLogFilter()
                            .setRequestTemplate("http-request.ftl")
                            .setResponseAttachmentName("Response"))
                    .when()
                    .post(ENV.getBaseUriApi() + "login")
                    .then()
                    .extract()
                    .jsonPath()
                    .get("access_token");
        } catch (JsonPathException exception) {
            log.info("RETRY for user " + userName);
            return RestAssured.given()
                    .formParam("username", userName)
                    .formParam("password", password)
                    .filter(new CustomLogFilter()
                            .setRequestTemplate("http-request.ftl")
                            .setResponseAttachmentName("Response"))
                    .when()
                    .post(ENV.getBaseUriApi() + "login")
                    .then()
                    .extract()
                    .jsonPath()
                    .get("access_token");
        }
    }

    /**
     * Gets tokens.
     *
     * @return the tokens
     */
    public static Map<User, String> getTokenMap() {
        return TOKENS;
    }

    /**
     * This method will set generated token into tokens map in order to reuse it.
     *
     * @param user  user will be used as key
     * @param token value of token
     */
    public static void setTokens(User user, String token) {
        TOKENS.put(user, token);
    }
}
