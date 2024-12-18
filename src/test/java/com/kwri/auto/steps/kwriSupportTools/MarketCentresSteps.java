package com.kwri.auto.steps.kwriSupportTools;

import com.google.inject.Inject;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.User;
import com.kwri.auto.pages.kwriSupportTools.MarketCentresPage;
import com.kwri.auto.ui.methods.Common;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.kwri.auto.enums.Urls.*;
import static org.junit.Assert.assertTrue;

/**
 * The type Market Centres tab steps.
 */
@InflateContext
public class MarketCentresSteps {
    @Inject
    private MarketCentresPage marketCentresPage;
    @Inject
    private Common common;

    /**
     * Open chart of accounts page.
     */
    @And("user opens \"Chart of Accounts\" page")
    public void openChartOfAccountsPage() {
        this.marketCentresPage.userOpensChartOfAccountsSubMenu();
    }

    /**
     * User is on Support Tools Financial Statement> Chart of Accounts page.
     */
    @Then("user is on Support Tools>'Financial Statement'> 'Chart of Accounts' page")
    public void userIsOnSupportToolFinancialStatementChartOfAccountsPage() {
        assertTrue("Error - current url should contain"
                        + " '/kwri-support-tools/financial-statement/chart-of-accounts', but was "
                        + this.marketCentresPage.getCurrentUrlForPage(),
                this.marketCentresPage.getCurrentUrlForPage()
                        .contains(SUPPORT_TOOLS_FINANCIAL_STATEMENT_CHART_OF_ACCOUNTS_PAGE.getExpectedUrl()));
    }

    /**
     * User is on Support Tools>Directory>"Associates" page (.../kwri-support-tools/directory/associates).
     */
    @Then("user is on Support Tools>Directory>Associates page")
    public void userIsOnSupportToolsDirectoryAssociatesPage() {
        assertTrue("Error - current url should contain '/kwri-support-tools/directory/associates', but was "
                        + this.marketCentresPage.getCurrentUrlForPage(),
                this.marketCentresPage.getCurrentUrlForPage()
                        .contains(SUPPORT_TOOLS_ASSOCIATES_PAGE.getExpectedUrl()));
    }

    /**
     * User sees {MC number}-{MC name} label in top left side of page.
     *
     * @param user  the user
     * @param orgId the org id
     * @throws Exception the exception
     */
    @Then("user {user} sees org number - org name label in top left side of page by {defaultOrg} orgId")
    public void userSeesOrgNumberOrgNameLabel(User user, String orgId) throws Exception {
        this.common.waitForPageToLoad();
        this.marketCentresPage.verifyOrgNameAndNumber(user, orgId);
    }

    /**
     * User sees account edge tab.
     *
     * @param shouldBeDisplayed should be displayed
     */
    @Then("user sees {} \"AccountEdge\" tab after \"MLS IDs\" tab in the general info of NA MC")
    public void userSeesAccountEdgeTab(boolean shouldBeDisplayed) {
        this.common.waitForPageToLoad();
        this.marketCentresPage.verifyAccountEdgeTabIsPresent(shouldBeDisplayed);
    }

    /**
     * User sees change vendor type.
     *
     * @param shouldBeDisabled should be disabled
     */
    @Then("user sees disabled {} \"Change Vendor Type\" button in right upper corner")
    public void userSeesChangeVendorType(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        this.marketCentresPage.verifyChangeVendorTypeBtnIsDisabled(shouldBeDisabled);
    }

    /**
     * User clicks on change vendor type.
     */
    @Then("user clicks on enabled 'Change Vendor Type' button for active organization")
    public void userClicksOnChangeVendorType() {
        this.common.waitForPageToLoad();
        this.marketCentresPage.clickOnChangeVendorTypeBtn();
    }

    /**
     * User sees generate new api key is enabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    @Then("user sees disabled {} 'Generate New API Key' button")
    public void userSeesGenerateNewApiKeyIsEnabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        this.marketCentresPage.verifyGenerateNewApiKeyBtnIsDisabled(shouldBeDisabled);
    }

    /**
     * User sees api key information.
     *
     * @param user            the user
     * @param shouldBePresent should be present
     * @param orgId           the org id
     * @throws Exception the exception
     */
    @Then("user {user} sees \"API Key\" label and api key value and \"copy\" icon is present {} for NA MC {defaultOrg}")
    public void userSeesApiKeyInformation(User user, boolean shouldBePresent, String orgId) throws Exception {
        this.common.waitForPageToLoad();
        this.marketCentresPage.verifyApiKeyValueForOrganization(user, orgId);
        this.marketCentresPage.verifyCopyIconIsPresent(shouldBePresent);
    }

    /**
     * User sees kwfs information for organization.
     *
     * @param user  the user
     * @param orgId the org id
     * @throws Exception the exception
     */
    @Then("user {user} sees next MC details info for 'Vendor', 'Status', 'Current Account Date' and 'Currency' "
            + "for orgId {defaultOrg}")
    public void userSeesKwfsInformationForOrganization(User user, String orgId) throws Exception {
        this.common.waitForPageToLoad();
        this.marketCentresPage.verifyKwfsInfoForOrganization(user, orgId);
    }

    /**
     * User opens account edge tab.
     */
    @Then("user open 'AccountEdge' tab for MC")
    public void userOpensAccountEdgeTab() {
        this.common.waitForPageToLoad();
        this.marketCentresPage.openAccountEdgeTab();
    }

    /**
     * User clicks on generate new api key button.
     */
    @When("user clicks on enabled 'Generate New API Key' button")
    public void userClicksOnGenerateNewApiKeyButton() {
        this.common.waitForPageToLoad();
        this.marketCentresPage.clickOnGenerateNewApiKeyBtn();
    }


    /**
     * User is redirected to Command home page (.../command).
     */
    @Then("user is redirected to Command home page")
    public void userIsOnCommandPage() {
        assertTrue("Error - current url should contain '/command', but was "
                        + this.marketCentresPage.getCurrentUrlForPage(),
                this.marketCentresPage.getCurrentUrlForPage().contains(COMMAND_PAGE.getExpectedUrl()));
    }

}
