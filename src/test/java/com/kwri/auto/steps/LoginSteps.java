package com.kwri.auto.steps;

import com.google.inject.Inject;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.User;
import com.kwri.auto.pages.LoginPage;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.utils.DriverUtils;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

import static com.kwri.auto.config.Config.PROPERTIES;
import static com.kwri.auto.enums.WaiterValues.TIMEOUT_45_SECONDS;

/**
 * The type Login steps.
 */
@InflateContext
@Slf4j
public class LoginSteps {
    @Inject
    private LoginPage loginPage;
    @Inject
    private Common common;
    @Inject
    private DriverUtils driverUtils;
    @Inject
    private GeneralGivenSteps generalGivenSteps;
    @Inject
    private NavigationSteps navigationSteps;

    /**
     * Login with username and password.
     *
     * @param name     the username
     * @param password the password
     */
    @Given("user logs in with name {string} and password {string}")
    public void login(String name, String password) {
        this.common.waitForPageToLoad();
        this.loginPage.getUsernameInput().sendKeys(name);
        this.loginPage.getPasswordInput().sendKeys(PROPERTIES.getProperty(password));
        this.loginPage.getSubmitBtn().click();
        this.common.waitForPageToLoad();
        this.driverUtils.waitLoaderDisappear();
        this.navigationSteps.waitUntilAvatarIsVisible();
//        this.loginPage.acceptOnboarding();
        this.loginPage.closeSmartTestAdds();
    }

    /**
     * Login.
     *
     * @param user the user
     */
    @Given("user logs in as {user} user")
    public void login(User user) throws Exception {
        this.generalGivenSteps.getAuthForUser(user);
        this.common.waitForPageToLoad();
        this.common.waitUntilElementIsVisible(TIMEOUT_45_SECONDS.getValue(), this.loginPage.getUsernameInput());
        this.loginPage.getUsernameInput().sendKeys(user.getLogin());
        this.loginPage.getPasswordInput().sendKeys(user.getPassword());
        this.loginPage.getSubmitBtn().click();
//        Note: this line is commented because onboarding feature flag was turned off.
//        It may be turned on at any moment, so we are targeting to keep it commented. Further,
//        if ff will be turned on and tests with auth by newly created user will be failing - we will create separate
//        login method to use in tests where auth is performed by new user.
//        In this way we are improving performance of UI automation
//        this.loginPage.acceptOnboarding();
        this.common.waitForPageToLoad();
        this.driverUtils.waitLoaderDisappear();
        this.common.waitForPageToLoad();

        this.navigationSteps.waitUntilAvatarIsVisible();
        this.common.waitForPageToLoad();
        this.loginPage.closeSmartTestAdds();
    }

    /**
     * Sets language cookie.
     *
     * @param language the language
     */
    @Given("user set language {string}")
    public void setLanguageCookie(String language) {
        this.common.waitForPageToLoad();
        this.loginPage.setCookiesWithLanguage(language);
    }
}
