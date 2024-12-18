package com.kwri.auto.steps;

import com.google.inject.Inject;
import com.kwri.auto.enums.Urls;
import com.kwri.auto.pages.Navigation;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.utils.DriverUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;

import static com.kwri.auto.config.Config.ENV;
import static com.kwri.auto.enums.Urls.*;

/**
 * The type Navigation steps.
 */
public class NavigationSteps {
    @Inject
    private Navigation navigation;
    @Inject
    private DriverUtils driverUtils;
    @Inject
    private Common common;

    /**
     * Open home page.
     */
    @Given("user navigates to home page")
    public void openHomePage() {
        this.navigation.getWebDriver().get(ENV.getBaseUri());
    }


    /**
     * Open Support Tools using url.
     */
    @Given("user open \"Support Tools\" page using url")
    public void openSupportToolsUsingUrl() {
        this.navigation.getWebDriver().get(ENV.getBaseUri() + SUPPORT_TOOLS_DIRECTORY_PAGE.getNavigationUrl());
    }

    /**
     * User opens url.
     *
     * @param url   the url
     * @param orgId the org id
     */
    @When("user opens url {} for {defaultOrg} orgId")
    public void userOpensUrl(Urls url, String orgId) {
        this.navigation.getWebDriver().get(ENV.getBaseUri() + url.getNavigationUrl() + orgId);
    }

    /**
     * User select account in shell.
     *
     * @param accountName - name of account
     */
    @Given("user select {string} account in shell")
    public void selectUserAccount(String accountName) {
        this.common.waitForPageToLoad();
        this.navigation.getBtnUserActions().click();
        this.common.waitForPageToLoad();
        if (!this.navigation.getBtnUserAccount(accountName).isEmpty()) {
            this.navigation.getBtnUserAccount(accountName).get(0).click();
        } else {
            try {
                this.common.waitForPageToLoad();
                if (this.navigation.getBtnSeeAllOptions().isDisplayed()) {
                    this.navigation.getBtnSeeAllOptions().click();
                    this.navigation.getMcByName(accountName).click();
                }
            } catch (NoSuchElementException exception) {
                this.navigation.getBtnUserActions().click();
            }
        }
    }

    /**
     * User clicks on enabled downward arrow of Shell menu.
     */
    @Given("user clicks on enabled downward arrow of Shell menu")
    public void clickOnDownwardArrowOfShellMenu() {
        this.common.waitForPageToLoad();
        this.navigation.getBtnUserActions().click();
    }

    /**
     * User sees enabled downward arrow of Shell menu.
     */
    @Given("user sees enabled downward arrow of Shell menu")
    public void userSeesEnabledDownwardArrowOfShellMenu() {
        this.navigation.getBtnUserActions().isDisplayed();
    }

    /**
     * User refresh the page.
     */
    @When("user refresh the page")
    public void userRefreshPage() {
        this.driverUtils.refreshTab();
        this.driverUtils.waitLoaderDisappear();
    }


    /**
     * Open Support Tools > 'Financial Statement-> Chart of Accounts' page using url.
     */
    @Given("user open Support Tools> 'Financial Statement'>'Chart of Accounts' page using url")
    public void openSupportToolsFinancialStatementChartOfAccountsUsingUrl() {
        this.navigation.getWebDriver().get(ENV.getBaseUri()
                + SUPPORT_TOOLS_FINANCIAL_STATEMENT_CHART_OF_ACCOUNTS_PAGE.getNavigationUrl());
    }

    /**
     * Open accounting page using url.
     */
    @Given("user open 'Accounting' page using url")
    public void openAccountingPageUsingUrl() {
        this.navigation.getWebDriver().get(ENV.getBaseUri()
                + ACCOUNTING_APPLET.getNavigationUrl());
    }

    /**
     * User waits until avatar will be visible.
     */
    @And("wait until avatar is visible")
    public void waitUntilAvatarIsVisible() {
        this.navigation.waitUntilUserAvatarVisible();
    }

    /**
     * Log out user.
     */
    @And("log out user")
    public void logOutUser() {
        this.navigation.logoutUser();
    }
}
