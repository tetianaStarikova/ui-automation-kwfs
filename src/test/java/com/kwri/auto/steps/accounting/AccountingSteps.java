package com.kwri.auto.steps.accounting;

import com.google.inject.Inject;
import com.kwri.auto.core.facade.VariableFacade;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.User;
import com.kwri.auto.pages.accounting.AccountingPage;
import com.kwri.auto.ui.methods.Common;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.time.Month;

/**
 * The type Accounting steps.
 */
@InflateContext
public class AccountingSteps {
    @Inject
    private AccountingPage accountingPage;
    @Inject
    private VariableFacade variableFacade;
    @Inject
    private Common common;

    /**
     * User sees accounting header.
     */
    @When("user sees \"Accounting\" header in top left side of page")
    public void userSeesAccountingHeader() {
        this.accountingPage.verifyAccountingHeader();
    }

    /**
     * User sees "Accounting" page has three tabs.
     * * "Balance Sheet"
     * * "Income Statement"
     * * "Account History"
     */
    @Then("user sees \"Accounting\" page has three tabs \"Balance Sheet\", \"Income Statement\", \"Account History\"")
    public void userSeesAccountingTabs() {
        this.accountingPage.verifyAccountingTabsArePresent();
    }

    /**
     * User sees balance sheet page is opened by default.
     */
    @Then("user sees \"Balance Sheet\" page is open by default")
    public void userSeesBalanceSheetPageIsOpenedByDefault() {
        this.accountingPage.verifyBalanceSheetPageIsOpenedByDefault();
    }

    /**
     * User sees print button for current month for Balance Sheet and Income Statement tabs.
     *
     * @param shouldBeDisabled should be disabled
     */
    @Then("user sees disabled {} button to print out Balance Sheet for current financial month")
    @Then("user sees disabled {} button to print out Income Statement for current financial month")
    public void userSeesBPrintButtonForCurrentMonth(boolean shouldBeDisabled) {
        this.accountingPage.verifyPrintButtonIsEnabled(shouldBeDisabled);
    }

    /**
     * User select income statement tab.
     */
    @Then("user selects \"Income Statement\" tab")
    public void userSelectIncomeStatementTab() {
        this.accountingPage.selectIncomeStatementTab();
    }

    /**
     * User select Account History tab.
     */
    @Then("user selects \"Account History\" tab")
    public void userSelectAccountHistoryTab() {
        this.accountingPage.selectAccountHistoryTab();
    }

    /**
     * User sees two section on balance sheet page.
     */
    @Then("user sees Balance Sheet data is displayed in next two sections: \"Assets\" section from the left side"
            + " \"Liabilities + Equity\" section from the right side")
    public void userSeesTwoSectionOnBalanceSheetPage() {
        this.accountingPage.verifyAssetsSectionIsDisplayed();
        this.accountingPage.verifyLiabilitiesEquitySectionIsDisplayed();
    }

    /**
     * User sees current month label on balance sheet page.
     */
    @Then("user sees \"Current Month\" label in the header of every section")
    public void userSeesCurrentMonthLabelOnBalanceSheetPage() {
        this.accountingPage.verifyCurrentMonthLabelForAssetsSectionIsDisplayed();
        this.accountingPage.verifyCurrentMonthLabelForLiabilitiesEquitySectionIsDisplayed();
    }

    /**
     * User sees full list of accounts on balance sheet page.
     *
     * @param user   the user
     * @param orgId  the org id
     * @param period the period
     * @throws Exception the exception
     */
    @Then("user {user} sees full list of accounts for every section for orgId {defaultOrg} for period {string}")
    public void userSeesFullListOfAccountsOnBalanceSheetPage(User user, String orgId, String period)
            throws Exception {
        this.accountingPage.verifyFullListOfAccountsForBalanceSheetForPeriod(user, orgId, Integer.parseInt(period));
    }

    /**
     * User sees full list of accounts for income statement.
     *
     * @param user   the user
     * @param orgId  the org id
     * @param period the period
     * @throws Exception the exception
     */
    @Then("user {user} sees full list of accounts for Income Statement for orgId {defaultOrg} for period {string}")
    public void userSeesFullListOfAccountsForIncomeStatement(User user, String orgId, String period)
            throws Exception {
        this.accountingPage.verifyFullListOfAccountsForIncomeStatement(user, orgId, Integer.parseInt(period));
    }

    /**
     * User sees full list of accounts values on balance sheet page.
     *
     * @param user   the user
     * @param orgId  the org id
     * @param period the period
     * @throws Exception the exception
     */
    @Then("user {user} sees full list of accounts value for every section for orgId {defaultOrg} for period {string}")
    public void userSeesFullListOfAccountsValuesOnBalanceSheetPage(User user, String orgId, String period)
            throws Exception {
        this.accountingPage.verifyFullListOfAccountsTotalForBalanceSheet(user, orgId, Integer.parseInt(period));

    }

    /**
     * User sees full list of accounts ranges on balance sheet page.
     *
     * @param user   the user
     * @param orgId  the org id
     * @param period the period
     * @throws Exception the exception
     */
    @Then("user {user} sees full list of accounts range for every section for orgId {defaultOrg} for period {string}")
    public void userSeesFullListOfAccountsRangesOnBalanceSheetPage(User user, String orgId, String period)
            throws Exception {
        this.accountingPage.verifyFullListOfAccountsRangeForBalanceSheet(user, orgId, Integer.parseInt(period));
    }

    /**
     * User sees total cash in current assets group on balance sheet page.
     *
     * @param assetsName the assets name
     * @param groupName  the group name
     */
    @Then("user sees next calculated formula for current month {string} in {string} group")
    public void userSeesTotalCashInCurrentAssetsGroupOnBalanceSheetPage(String assetsName, String groupName) {
        this.accountingPage.verifyTotalLabelIsDisplayedInAssetsGroup(assetsName, groupName);
    }

    /**
     * Assign to variable current transaction period.
     *
     * @param user  the user
     * @param orgId the org id
     * @param key   the key
     * @throws Exception the exception
     */
    @Given("user {user} get current transaction period for organization {defaultOrg} and assign to {string} variable")
    public void assignToVariableCurrentTransactionPeriod(User user, String orgId, String key) throws Exception {
        this.variableFacade.setVariable(key,
                String.valueOf(this.accountingPage.getCurrentTransactionPeriod(user, orgId)));
    }

    /**
     * User sees total income data.
     *
     * @param user      the user
     * @param totalName the total name
     * @param orgId     the org id
     * @param period    the period
     * @throws Exception the exception
     */
    @Given("user {user} sees next calculated formula for current month: {string} in group for org {defaultOrg} "
            + "in {string}")
    public void userSeesTotalIncomeData(User user, String totalName, String orgId, String period) throws Exception {
        this.accountingPage.verifyTotalIncomeForIncomeGroup(user, orgId, Integer.parseInt(period), totalName);
    }

    /**
     * User assign to variable next month period.
     *
     * @param key  the key
     * @param var1 the var 1
     */
    @Then("user assign to {string} variable next month period from {string}")
    public void userAssignToVariableNextMonthPeriod(String key, String var1) {
        this.variableFacade.setVariable(key,
                String.valueOf(LocalDate.now().withMonth(Integer.parseInt(var1))
                        .plusMonths(1).getMonth().getValue()));
    }

    /**
     * User assign to variable previous month period.
     *
     * @param key  the key
     * @param var1 the var 1
     */
    @Then("user assign to {string} variable previous month period from {string}")
    public void userAssignToVariablePreviousMonthPeriod(String key, String var1) {
        this.variableFacade.setVariable(key,
                String.valueOf(LocalDate.now().withMonth(Integer.parseInt(var1))
                        .minusMonths(1).getMonth().getValue()));
    }

    /**
     * User select month in select month dropdown.
     *
     * @param month the month
     */
    @Then("user selects month {string} in \"Select Month\" dropdown")
    public void userSelectMonthInSelectMonthDropdown(String month) {
        this.accountingPage.selectMonth(StringUtils.capitalize(Month.of(Integer.parseInt(month)).name().toLowerCase()));
    }

    /**
     * User sees clock icon for future month.
     */
    @Then("user sees in the middle of page clock icon")
    public void userSeesClockIconForFutureMonth() {
        this.accountingPage.verifyFutureMonthClockIconIsDisplayed();
    }

    /**
     * User sees future month label.
     */
    @Then("user sees in the middle of page \"Future Month\" label")
    public void userSeesFutureMonthLabel() {
        this.accountingPage.verifyFutureMonthLabelIsDisplayed();
    }

    /**
     * User sees No data available yet disclaimer.
     */
    @Then("user sees 'No data is available yet' disclaimer on Accounting page")
    public void userSeesNoDataAvailableYetDisclaimer() {
        this.accountingPage.verifyNoDataAvailableDisclaimer();
    }

    /**
     * User sees Please, upload financial data to proceed.
     */
    @Then("user sees 'Please upload financial data to proceed.' disclaimer on the Accounting page")
    public void userSeesPleaseUploadFinancialDataToProceed() {
        this.accountingPage.verifyPleaseUploadFinancialDataDisclaimer();
    }

    /**
     * User sees select month dropdown.
     */
    @Then("user sees \"Select Month\" dropdown in top right side of page with downward arrow")
    public void userSeesSelectMonthDropdown() {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        this.accountingPage.verifySelectMonthDropdownIsDisplayed();
    }

    /**
     * User sees disclaimer for future month.
     *
     * @param disclaimer the disclaimer
     */
    @Then("user sees in the middle of page {string} disclaimer")
    public void userSeesDisclaimerForFutureMonth(String disclaimer) {
        this.accountingPage.verifyMessageForFutureMonth(disclaimer);
    }

    /**
     * User sees account history list.
     */
    @Then("user sees list of all accounts from bookkeeping system with the following info")
    public void userSeesAccountHistoryList() {
        this.accountingPage.verifyAccountHistoryHeaders();
    }

    /**
     * User sees list of account history is sorted in asc order.
     */
    @Then("user sees list of accounts is sorted by \"ACCT #\" in ASC order by default")
    public void userSeesListOfAccountHistoryIsSortedInAscOrder() {
        this.accountingPage.verifyAccountsInAccountHistoryAreSortedAlphabetically();
    }

    /**
     * User sees list of account history data.
     *
     * @param user  the user
     * @param orgId the org id
     * @throws Exception the exception
     */
    @Then("user {user} sees list of all accounts history for an org {defaultOrg}")
    public void userSeesListOfAccountHistoryData(User user, String orgId) throws Exception {
        this.accountingPage.verifyAccountHistoryData(user, orgId);
    }

    /**
     * User sees Income Statement data is displayed in table with columns.
     * | ACCT | ACCOUNT NAME | CURRENT ACTUAL | CURRENT BUDGET | CURRENT VARIANCE | YTD ACTUAL |
     * | YTD BUDGET | YTD VARIANCE | ANNUAL BUDGET |
     */
    @Then("user sees Income Statement data is displayed in table with columns")
    public void userSeesIncomeStatementDataIsDisplayedInNextColumns() {
        this.accountingPage.verifyIncomeStatementHeaders();
    }

    /**
     * User sees disabled view security key.
     *
     * @param isDisabled is disabled
     */
    @Then("user sees disabled {} \"View Security Key\" button in the top right corner of page")
    public void userSeesDisabledViewSecurityKey(boolean isDisabled) {
        this.accountingPage.verifyViewSecurityKeyButtonIsEnabled(isDisabled);
    }

    /**
     * User sees view security key.
     *
     * @param isPresent is presented
     */
    @Then("user sees \"View Security Key\" button is displayed {} in the top right corner of page")
    public void userSeesViewSecurityKey(boolean isPresent) {
        this.accountingPage.verifyViewSecurityKeyButtonIsPresent(isPresent);
    }

    /**
     * User sees upload files button.
     *
     * @param isPresent is presented
     */
    @Then("user sees 'Upload Files' button is displayed {} in the top right corner of page")
    public void userSeesUploadFilesButton(boolean isPresent) {
        this.accountingPage.verifyUploadFilesButtonIsPresent(isPresent);
    }

    /**
     * User clicks on view security key.
     */
    @When("user clicks on enabled \"View Security Key\" button in the top right corner of page")
    public void userClicksOnViewSecurityKey() {
        this.accountingPage.clickOnViewSecurityKeyButton();
    }

    /**
     * User clicks on upload files button.
     */
    @When("user clicks on enabled 'Upload Files' button in the top right corner of page")
    public void userClicksOnUploadFilesButton() {
        this.accountingPage.clickOnUploadFilesButton();
    }

    /**
     * User verify footer in the bottom of the page.
     */
    @Then("user sees footer in the bottom of page displayed with: \"Total Assets\", \"Total Liabilities + Equity\"")
    public void userVerifyFooterInTheBottomOfThePage() {
        this.accountingPage.verifyFooterForTheBalanceSheet();
    }

    /**
     * User does not see footer.
     */
    @Then("footer is not displayed for any past month")
    public void userDoesNotSeeFooter() {
        this.accountingPage.verifyFooterIsNotPresent();
    }

    /**
     * User clicks on Save New Data button.
     */
    @When("user clicks on enabled \"Save New Data\" button")
    public void userClicksOnSaveNewDataButton() {
        this.common.waitForPageToLoad();
        this.accountingPage.clickOnSaveNewDataButton();
    }

    /**
     * User sees Save New Data button is disabled.
     *
     * @param isDisabled the is disabled
     */
    @When("user sees \"Save New Data\" button is disabled {}")
    public void userSeesSaveNewDataButtonIsDisabled(boolean isDisabled) {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        this.accountingPage.verifySaveNewDataButtonIsEnabled(isDisabled);
    }

    /**
     * User sees notification banner.
     */
    @When("user sees \"YTD Net Profit Loss on the Income Statement does not match Current Period Profit Loss "
            + "on the Balance Sheet\" banner notification is displayed")
    public void userSeesNotificationBanner() {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        this.accountingPage.verifyBannerNotification();
    }

    /**
     * User sees banner with forbidden and close icons.
     */
    @When("user sees a red banner displayed notifying about YTD Net Profit does not match Current Period Profit"
            + " with forbidden icon and with Close icon")
    public void userSeesBannerWithForbiddenAndCloseIcons() {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        this.accountingPage.verifyForbiddenIconIsPresent();
        this.accountingPage.verifyCloseIconInNetProfitErrorBannerIsPresent();
    }

    /**
     * User sees notification banner.
     *
     * @param shouldBeDisplayed should be displayed
     */
    @When("user sees {} Net Profit Banner")
    public void userDoesNotSeeNetProfitNotificationBanner(boolean shouldBeDisplayed) {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        this.accountingPage.verifyNetProfitBannerIsPresent(shouldBeDisplayed);
    }
}
