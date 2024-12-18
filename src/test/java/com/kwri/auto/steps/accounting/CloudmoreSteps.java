package com.kwri.auto.steps.accounting;

import com.google.inject.Inject;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.pages.accounting.CloudmorePage;
import com.kwri.auto.utils.DriverUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.TimeoutException;

/**
 * The type Cloudmore steps.
 */
@InflateContext
public class CloudmoreSteps {
    @Inject
    private CloudmorePage cloudmorePage;
    @Inject
    private DriverUtils driverUtils;

    /**
     * User is on Command MC.
     */
    @When("user open \"CommandMC\" main page")
    public void userOpenCommandMcPage() {
        try {
            this.cloudmorePage.getCloudmoreTab().isDisplayed();
        } catch (TimeoutException ex) {
            this.driverUtils.refreshTab();
            this.cloudmorePage.getCloudmoreTab().isDisplayed();
        }
        this.cloudmorePage.clickOnCloudmoreTab();
    }

    /**
     * User sees accounting card.
     *
     * @param isEnabled the is enabled
     */
    @Then("user sees enabled {} \"Accounting\" card located between \"Reports\" and \"Tasks\" cards")
    public void userSeesAccountingCard(boolean isEnabled) {
        this.cloudmorePage.verifyAccountingCardIsEnabled(isEnabled);
    }

    /**
     * User sees accounting card with wording.
     *
     * @param accountingWording the accounting wording
     */
    @Then("user sees \"Accounting\" card with 'Accounting' header and with the following wording {string}")
    public void userSeesAccountingCardWithWording(String accountingWording) {
        this.cloudmorePage.verifyAccountingWording(accountingWording);
        this.cloudmorePage.verifyAccountingHeader();
    }

    /**
     * User clicks on accounting card in the side bar menu.
     */
    @Then("user clicks on \"Accounting\" card in the sidebar menu")
    public void userClicksOnAccountingCardInTheSideBarMenu() {
        this.cloudmorePage.clickOnAccountingCardInTheSidebarMenu();
    }

    /**
     * User clicks on accounting card in the list of aplets.
     */
    @Then("user clicks on \"Accounting\" card in the list of Applets")
    public void userClicksOnAccountingCardInTheListOfAplets() {
        this.cloudmorePage.clickOnBtnAccountingCloudmore();
    }

    /**
     * User sees accounting cars is selected in sidebar menu.
     */
    @Then("user sees \"Accounting\" card is selected and highlighted in sidebar menu")
    public void userSeesAccountingCarsIsSelectedInSidebarMenu() {
        this.cloudmorePage.verifyAccountingCardIsSelectedInSideBarMenu();
    }

    /**
     * User sees accounting cars is disabled in sidebar menu.
     */
    @Then("user sees disabled \"Accounting\" applet icon located between \"Report\" and \"Tasks\" applet icons")
    public void userSeesAccountingCarsIsDisabledInSidebarMenu() {
        this.cloudmorePage.verifyAccountingCardIsDisabledInSideBarMenu();
    }

    /**
     * User sees accounting header is displayed in sidebar menu.
     */
    @Then("user sees \"Accounting\" header is displayed on applet")
    public void userSeesAccountingHeaderIsDisplayedInSidebarMenu() {
        this.cloudmorePage.verifyAccountingTextIsDisplayedInSideBarMenu();
    }
}
