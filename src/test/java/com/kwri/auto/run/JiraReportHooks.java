package com.kwri.auto.run;

import com.kwri.auto.hooks.JiraHelper;
import com.kwri.auto.models.TestStatus;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.kwri.auto.config.Config.ENV;
import static com.kwri.auto.run.CucumberHooks.JIRA_USERNAME;
import static com.kwri.auto.run.CucumberHooks.XRAY_JSON_REPORT_FILEPATH;

/**
 * The type Jira report hooks.
 */
public final class JiraReportHooks {
    /**
     * After all.
     *
     * @throws IOException the io exception
     */
    private static void prepareExecutionInfo() throws IOException {
        String[] environments = {ENV.getEnvironmentName()};
        JiraHelper.executionInfo
                .setSummary("[UI] [Avengers] Automation Execution:" + LocalDateTime.now())
                .setDescription("This execution is automatically created when importing execution results")
                .setUser(JIRA_USERNAME)
                .setTestEnvironments(environments);
    }

    /**
     * After scenario.
     *
     * @param scenario the scenario
     */
    @After
    public void afterScenario(Scenario scenario) {
        JiraHelper.afterScenario(getJiraTicketNumber(scenario), getTestStatus(scenario.getStatus()));
    }

    /**
     * After all.
     *
     * @throws IOException the io exception
     */
    @AfterAll
    public static void generateReportWithInfo() throws IOException {
        prepareExecutionInfo();
        File f = new File("target/xray-report");
        if (!f.exists()) {
            Files.createDirectory(Paths.get("target/xray-report"));
        }
        try {
            JiraHelper.generateXrayReportJson(XRAY_JSON_REPORT_FILEPATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get Jira Ticket Number.
     *
     * @param scenario the scenario
     * @return Jira ticket Number
     */
    public List<String> getJiraTicketNumber(Scenario scenario) {
        return scenario.getSourceTagNames()
                .stream()
                .filter(s -> s.startsWith("@COM-") | s.startsWith("@TRX-"))
                .map(s -> s.replace("@", ""))
                .collect(Collectors.toList());
    }

    /**
     * Get Test Status.
     *
     * @param status the status
     * @return Test Status
     */
    private TestStatus getTestStatus(Status status) {
        return switch (status) {
            case PASSED -> TestStatus.PASSED;
            case FAILED -> TestStatus.FAILED;
            default -> TestStatus.ABORTED;
        };
    }
}
