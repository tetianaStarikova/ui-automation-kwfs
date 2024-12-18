package com.kwri.auto.steps.accounting;

import com.google.inject.Inject;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.pages.accounting.AdministrationPage;
import com.kwri.auto.pages.accounting.CloudmorePage;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.utils.DriverUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.TimeoutException;

import static com.kwri.auto.enums.Urls.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The type Administration steps.
 */
@InflateContext
public class AdministrationSteps {
    @Inject
    private AdministrationPage administrationPage;
    @Inject
    private CloudmorePage cloudmorePage;
    @Inject
    private DriverUtils driverUtils;
    @Inject
    private Common common;

    /**
     * User is on Command MC > Administration applet.
     */
    @When("user is on Command MC > Administration applet")
    public void userIsOnAdministrationApplet() {
        try {
            this.cloudmorePage.getCloudmoreTab().isDisplayed();
        } catch (TimeoutException ex) {
            this.driverUtils.refreshTab();
            this.cloudmorePage.getCloudmoreTab().isDisplayed();
        }
        this.cloudmorePage.clickOnCloudmoreTab();
        this.driverUtils.moveToElement(this.cloudmorePage.getBtnAdministrationCloudmore());
        this.cloudmorePage.clickOnBtnAdministrationCloudmore();
    }

    /**
     * User is redirected to accounting page.
     */
    @Then("user is redirected to \"Accounting\" page")
    public void userIsRedirectedToAccountingPage() {
        this.common.waitForPageToLoad();
        assertTrue("Error - current url should contain '/commandmc/accounting'!",
                this.administrationPage.getCurrentUrlForPage().contains(ACCOUNTING_APPLET.getExpectedUrl()));
    }

    /**
     * User is redirected to cloudmore page.
     */
    @Then("user is redirected to CommandMC main page")
    public void userIsRedirectedToCommandMCPage() {
        this.driverUtils.waitLoaderDisappear();
        assertTrue("Error - current url should contain '/cloudmore/'!",
                this.administrationPage.getCurrentUrlForPage().contains(CLOUDMORE_PAGE.getExpectedUrl()));
    }
}
