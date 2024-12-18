package com.kwri.auto.pages.accounting;

import com.google.inject.Inject;
import com.kwri.auto.enums.User;
import com.kwri.auto.model.kwfs.AccountGroupsModel;
import com.kwri.auto.model.kwfs.AccountsHistoryModel;
import com.kwri.auto.model.kwfs.AssetsModel;
import com.kwri.auto.model.kwfs.IncomeStatementTotalsModel;
import com.kwri.auto.model.kwfs.LiabilitiesAndEquityModel;
import com.kwri.auto.steps.GeneralWhenSteps;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.ui.pages.BasePage;
import com.kwri.auto.utils.DriverUtils;
import com.kwri.auto.utils.Waits;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kwri.auto.enums.Endpoints.COMMAND_MC_TRANSACTIONS_PERIODS;
import static com.kwri.auto.enums.Endpoints.KW_FS_ACCOUNTS_BALANCE_SHEET;
import static com.kwri.auto.enums.Endpoints.KW_FS_ACCOUNTS_INCOME_STATEMENT;
import static com.kwri.auto.enums.Endpoints.KW_FS_ACCOUNT_HISTORY;
import static com.kwri.auto.enums.Versions.V1;
import static com.kwri.auto.enums.Versions.V2;
import static com.kwri.auto.enums.WaiterValues.TIMEOUT_30_SECONDS;
import static io.restassured.http.Method.GET;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The type Accounting page.
 */
@Getter
@Slf4j
public class AccountingPage extends BasePage implements Waits {

    @FindBy(xpath = "//div[@data-testId='header']//h1")
    private WebElement accountingHeader;

    @FindBy(xpath = "//div[@data-testId='tab-bar']//div[text()='Balance Sheet']")
    private WebElement balanceSheetTab;

    @FindBy(xpath = "//div[@data-testId='tab-bar']//div[text()='Balance Sheet']//parent::div[@role='button']")
    private WebElement balanceSheetTabSelected;

    @FindBy(xpath = "//div[@data-testId='tab-bar']//div[text()='Income Statement']")
    private WebElement incomeStatementTab;

    @FindBy(xpath = "//div[@data-testId='tab-bar']//div[text()='Account History']")
    private WebElement accountHistoryTab;

    @FindBy(xpath = "//button[@data-testId='print-button']")
    private WebElement printBtn;

    @FindBy(xpath = "//p[text()='Assets']")
    private WebElement assetsHeader;

    @FindBy(xpath = "//p[text()='Assets']//following-sibling::p")
    private WebElement assetsCurrentMonthLabel;

    @FindBy(xpath = "//p[text()='Liabilities + Equity']")
    private WebElement liabilitiesEquityHeader;

    @FindBy(xpath = "//p[text()='Liabilities + Equity']//following-sibling::p")
    private WebElement liabilitiesEquityCurrentMonthLabel;

    @FindBy(xpath = "//th//ancestor::thead//following-sibling::tbody//td[@colspan='0'][2]")
    private List<WebElement> lstAssetsAccountsName;

    @FindBy(xpath = "//th//ancestor::thead//following-sibling::tbody//td[@colspan='2']")
    private List<WebElement> lstAssetsAccountsNamesWithoutRange;

    @FindBy(xpath = "//th//ancestor::thead//following-sibling::tbody//td[@colspan='0'][3]")
    private List<WebElement> lstAccountsTotal;

    @FindBy(xpath = "//th//ancestor::thead//following-sibling::tbody//td[@colspan='0'][2]//preceding-sibling::td")
    private List<WebElement> lstAccountsRange;

    @FindBy(xpath = "//th//ancestor::thead//following-sibling::tbody//td[@colspan='2']//following-sibling::td")
    private List<WebElement> lstAccountsTotalWithoutRange;

    @FindBy(xpath = "//div[contains(@class, 'indicatorContainer')]//span")
    private WebElement selectMonthDropdown;

    @FindBy(xpath = "//div[contains(@id, '-option-')]//span")
    private List<WebElement> lstMonth;

    @FindBy(xpath = "//h3[text()='Future Month']")
    private WebElement futureMonthLbl;

    @FindBy(xpath = "//div[contains(@data-testid, 'error')]//h3")
    private WebElement noDataAvailableError;

    @FindBy(xpath = "//div[contains(@data-testid, 'error')]//p")
    private WebElement pleaseUploadFinancialDataError;

    @FindBy(xpath = "//div[contains(@data-testid, 'error')]//div/span")
    private WebElement clockIcon;

    @FindBy(xpath = "//h3[text()='Future Month']//following-sibling::p")
    private WebElement futureMonthMessage;

    @FindBy(xpath = "//td[contains(@data-testid, 'accountNumber')]")
    private List<WebElement> lstAccountHistoryAccounts;

    @FindBy(xpath = "//td[contains(@data-testid, 'name')]")
    private List<WebElement> lstAccountHistoryNames;

    @FindBy(xpath = "//table[contains(@class, 'sc')]//th//parent::tr//following-sibling::tr//td[2]")
    private List<WebElement> lstAccountNamesForIncomeStatement;

    @FindBy(xpath = "//span[text()='View Security Key']//parent::button")
    private WebElement btnViewSecurityKey;

    @FindBy(xpath = "//span[text()='Upload Files']//parent::button")
    private WebElement btnUploadFiles;

    @FindBy(xpath = "//div[@data-testid='submit-footer-total-assets']")
    private WebElement lblTotalAssetsFooter;

    @FindBy(xpath = "//div[@data-testid='submit-footer-total-liabilities-and-equity']")
    private WebElement lblTotalLiabilitiesAndEquityFooter;

    @FindBy(xpath = "//div[@data-testid='submit-footer-balance-status']")
    private WebElement lblBalanceStatusFooter;

    @FindBy(xpath = "//button[@data-testid='submit-footer-save-new-data-button']")
    private WebElement btnSaveNewData;

    @FindBy(xpath = "//div[@class='notification-title']")
    private WebElement lblNotificationTitle;

    @FindBy(xpath = "//div[@class='notification-subtitle']")
    private WebElement lblNotificationSubTitle;

    @FindBy(xpath = "//div[@data-testid='netProfitErrorBanner']")
    private WebElement netProfitErrorBanner;

    @FindBy(xpath = "//div[@class='dismiss-icon']")
    private WebElement dismissIconInNetProfitErrorBanner;

    @FindBy(xpath = "//div[@data-testid='netProfitErrorBanner']//div/span[@color='red']")
    private WebElement forbiddenIconInNetProfitErrorBanner;

    @Inject
    private Common common;
    @Inject
    private GeneralWhenSteps generalWhenSteps;
    @Inject
    private DriverUtils driverUtils;

    /**
     * Instantiates a new Accounting page.
     *
     * @param world the world
     */
    @Inject
    public AccountingPage(final World world) {
        super(world);
    }

    /**
     * Gets lbl for assets in group.
     *
     * @param assetsName the assets name
     * @param groupName  the group name
     * @return the label for assets in group
     */
    public WebElement getLblForAssetsInGroup(String assetsName, String groupName) {
        common.waitForPageToLoad();
        return this.world.driver.findElement(By.xpath("//th[text()='" + assetsName
                + "']//parent::tr//parent::thead//following-sibling::tbody//tr//td[text()='" + groupName + "']"));
    }

    /**
     * Gets account history headers by name.
     *
     * @param accountHeader the account header
     * @return the account history headers by name
     */
    public WebElement getAccountHistoryHeadersByName(String accountHeader) {
        common.waitForPageToLoad();
        return this.world.driver.findElement(By.xpath("//thead[@data-testid="
                + "'account-history-table-header']//th[text()='" + accountHeader + "']"));
    }

    /**
     * Gets total current actual by name.
     *
     * @param accountHeader the account header
     * @return the total current actual by name
     */
    public WebElement getTotalCurrentActualByName(String accountHeader) {
        common.waitForPageToLoad();
        return this.world.driver.findElement(By.xpath("//table[contains(@class, 'sc')]//td[text()='"
                + accountHeader + "']//following-sibling::td[1]"));
    }

    /**
     * Gets total current budget by name.
     *
     * @param accountHeader the account header
     * @return the total current budget by name
     */
    public WebElement getTotalCurrentBudgetByName(String accountHeader) {
        common.waitForPageToLoad();
        return this.world.driver.findElement(By.xpath("//table[contains(@class, 'sc')]//td[text()='"
                + accountHeader + "']//following-sibling::td[2]"));
    }

    /**
     * Gets total current variance by name.
     *
     * @param accountHeader the account header
     * @return the total current variance by name
     */
    public WebElement getTotalCurrentVarianceByName(String accountHeader) {
        common.waitForPageToLoad();
        return this.world.driver.findElement(By.xpath("//table[contains(@class, 'sc')]//td[text()='"
                + accountHeader + "']//following-sibling::td[3]"));
    }

    /**
     * Gets total ytd actual by name.
     *
     * @param accountHeader the account header
     * @return the total ytd actual by name
     */
    public WebElement getTotalYtdActualByName(String accountHeader) {
        common.waitForPageToLoad();
        return this.world.driver.findElement(By.xpath("//table[contains(@class, 'sc')]//td[text()='"
                + accountHeader + "']//following-sibling::td[4]"));
    }

    /**
     * Gets total ytd budget by name.
     *
     * @param accountHeader the account header
     * @return the total ytd budget by name
     */
    public WebElement getTotalYtdBudgetByName(String accountHeader) {
        common.waitForPageToLoad();
        return this.world.driver.findElement(By.xpath("//table[contains(@class, 'sc')]//td[text()='"
                + accountHeader + "']//following-sibling::td[5]"));
    }

    /**
     * Gets total ytd variance by name.
     *
     * @param accountHeader the account header
     * @return the total ytd variance by name
     */
    public WebElement getTotalYtdVarianceByName(String accountHeader) {
        common.waitForPageToLoad();
        return this.world.driver.findElement(By.xpath("//table[contains(@class, 'sc')]//td[text()='"
                + accountHeader + "']//following-sibling::td[6]"));
    }

    /**
     * Gets total annual budget by name.
     *
     * @param accountHeader the account header
     * @return the total annual budget by name
     */
    public WebElement getTotalAnnualBudgetByName(String accountHeader) {
        common.waitForPageToLoad();
        return this.world.driver.findElement(By.xpath("//table[contains(@class, 'sc')]//td[text()='"
                + accountHeader + "']//following-sibling::td[7]"));
    }

    /**
     * Gets Income Statement headers by name.
     *
     * @param header the header
     * @return the Income Statement headers by name
     */
    public WebElement getIncomeStatementHeadersByName(String header) {
        common.waitForPageToLoad();
        return this.world.driver.findElement(By.xpath("//table//th[text()='" + header + "']"));
    }

    /**
     * Gets account history columns by name.
     *
     * @param accountColumnName the account column name
     * @return the account history columns by name
     */
    public List<WebElement> getAccountHistoryColumnsByName(String accountColumnName) {
        common.waitForPageToLoad();
        return this.world.driver.findElements(By.xpath("//td[contains(@data-testid, '"
                + accountColumnName + "')]"));
    }

    /**
     * Verify accounting header.
     */
    public void verifyAccountingHeader() {
        this.common.waitForPageToLoad();
        assertEquals("Error - Accounting header is not present!", "Accounting",
                getAccountingHeader().getText());
    }

    /**
     * Verify accounting tabs are present.
     */
    public void verifyAccountingTabsArePresent() {
        this.common.waitForPageToLoad();
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(getBalanceSheetTab().isDisplayed())
                .as("Balance Sheet tab should be displayed!").isTrue();
        assertions.assertThat(getIncomeStatementTab().isDisplayed())
                .as("Income Statement tab should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryTab().isDisplayed())
                .as("Account History tab should be displayed!").isTrue();
        assertions.assertAll();
    }

    /**
     * Verify balance sheet page is opened by default.
     */
    public void verifyBalanceSheetPageIsOpenedByDefault() {
        this.common.waitForPageToLoad();
        assertEquals("Balance Sheet Tab should be selected by default!",
                "system", getBalanceSheetTabSelected().getAttribute("color"));
    }

    /**
     * Verify print button is enabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    public void verifyPrintButtonIsEnabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsDisabled(getPrintBtn(), shouldBeDisabled);
    }

    /**
     * Select Income Statement tab.
     */
    public void selectIncomeStatementTab() {
        this.common.waitForPageToLoad();
        getIncomeStatementTab().click();
    }

    /**
     * Select Account History tab.
     */
    public void selectAccountHistoryTab() {
        this.common.waitForPageToLoad();
        getAccountHistoryTab().click();
    }

    /**
     * Verify assets section is displayed.
     */
    public void verifyAssetsSectionIsDisplayed() {
        this.common.waitForPageToLoad();
        assertTrue("Assets section is not displayed!", getAssetsHeader().isDisplayed());
    }

    /**
     * Verify liabilities equity section is displayed.
     */
    public void verifyLiabilitiesEquitySectionIsDisplayed() {
        this.common.waitForPageToLoad();
        assertTrue("Liabilities Equity section is not displayed!", getLiabilitiesEquityHeader().isDisplayed());
    }

    /**
     * Verify current month label for assets section is displayed.
     */
    public void verifyCurrentMonthLabelForAssetsSectionIsDisplayed() {
        this.common.waitForPageToLoad();
        assertTrue("Current Month Label is not displayed on Assets section!",
                getAssetsCurrentMonthLabel().isDisplayed());
    }

    /**
     * Verify current month label for liabilities equity section is displayed.
     */
    public void verifyCurrentMonthLabelForLiabilitiesEquitySectionIsDisplayed() {
        this.common.waitForPageToLoad();
        assertTrue("Current Month Label is not displayed forLiabilities Equity section!",
                getLiabilitiesEquityCurrentMonthLabel().isDisplayed());
    }

    /**
     * Verify total label is displayed in assets group.
     *
     * @param assetsName the assets name
     * @param groupName  the group name
     */
    public void verifyTotalLabelIsDisplayedInAssetsGroup(String assetsName, String groupName) {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        this.common.waitUntilElementIsVisible(TIMEOUT_30_SECONDS.getValue(),
                getLblForAssetsInGroup(groupName, assetsName));
        assertTrue(assetsName + " should be displayed in " + groupName + " group!",
                getLblForAssetsInGroup(groupName, assetsName).isDisplayed());
    }

    /**
     * Verify full list of accounts for balance sheet.
     *
     * @param user   the user
     * @param orgId  the org id
     * @param period the period
     * @throws Exception the exception
     */
    public void verifyFullListOfAccountsForBalanceSheetForPeriod(User user, String orgId, int period)
            throws Exception {
        this.common.waitForPageToLoad();
        this.generalWhenSteps.queryParam("month", String.valueOf(period));
        Response balanceSheetResponse = this.generalWhenSteps.makeRequest(GET, KW_FS_ACCOUNTS_BALANCE_SHEET, V2,
                new String[]{orgId}, user);
        balanceSheetResponse.then().assertThat().statusCode(SC_OK);

        List<AssetsModel> assetsModelList = balanceSheetResponse.jsonPath()
                .getList("assets", AssetsModel.class);
        List<LiabilitiesAndEquityModel> liabilitiesEquityModelList = balanceSheetResponse.jsonPath()
                .getList("liabilitiesAndEquity", LiabilitiesAndEquityModel.class);
        List<String> assetsNamesUi = getAssetsNamesUi();
        List<String> assetsNamesApi = getAssetsNamesApi(assetsModelList, liabilitiesEquityModelList);
        Collections.sort(assetsNamesUi);
        Collections.sort(assetsNamesApi);
        assertEquals("Assets names is not equal!", assetsNamesUi, assetsNamesApi);
    }

    /**
     * Verify account history data.
     *
     * @param user  the user
     * @param orgId the org id
     * @throws Exception the exception
     */
    public void verifyAccountHistoryData(User user, String orgId) throws Exception {
        this.generalWhenSteps.queryParam("limit", "1000");
        Response accountHistoryModels = this.generalWhenSteps.makeRequest(GET, KW_FS_ACCOUNT_HISTORY, V1,
                new String[]{orgId}, user);
        accountHistoryModels.then().assertThat().statusCode(SC_OK);

        AccountsHistoryModel accountsHistoryModelUi = createAccountHistoryModelUi();
        AccountsHistoryModel accountsHistoryModelApi = createAccountHistoryModelApi(accountHistoryModels);
        assertTrue(accountsHistoryModelUi.equals(accountsHistoryModelApi));
    }

    /**
     * Create account history model from UI.
     *
     * @return AccountsHistoryModel
     */
    private AccountsHistoryModel createAccountHistoryModelUi() {
        AccountsHistoryModel accountsHistoryModel = new AccountsHistoryModel();
        accountsHistoryModel.setAccountNumber(getAccountHistoryColumnsByName("accountNumber").stream()
                .map(WebElement::getText).map(String::toLowerCase).map(String::trim)
                .map(s -> s.replace(",", "").replace(".00", "")).toList());
        accountsHistoryModel.setName(getAccountHistoryColumnsByName("name").stream()
                .map(WebElement::getText).map(String::trim).toList());
        accountsHistoryModel.setJanBudget(getMonthBudget("janBudget"));
        accountsHistoryModel.setFebBudget(getMonthBudget("febBudget"));
        accountsHistoryModel.setMarBudget(getMonthBudget("marBudget"));
        accountsHistoryModel.setAprBudget(getMonthBudget("aprBudget"));
        accountsHistoryModel.setMayBudget(getMonthBudget("mayBudget"));
        accountsHistoryModel.setJunBudget(getMonthBudget("junBudget"));
        accountsHistoryModel.setJulBudget(getMonthBudget("julBudget"));
        accountsHistoryModel.setAugBudget(getMonthBudget("augBudget"));
        accountsHistoryModel.setSepBudget(getMonthBudget("sepBudget"));
        accountsHistoryModel.setOctBudget(getMonthBudget("octBudget"));
        accountsHistoryModel.setNovBudget(getMonthBudget("novBudget"));
        accountsHistoryModel.setDecBudget(getMonthBudget("decBudget"));
        accountsHistoryModel.setP13Budget(getMonthBudget("p13Budget"));
        accountsHistoryModel.setJanCurrentYear(getMonthBudget("janCurrentYear"));
        accountsHistoryModel.setFebCurrentYear(getMonthBudget("febCurrentYear"));
        accountsHistoryModel.setMarCurrentYear(getMonthBudget("marCurrentYear"));
        accountsHistoryModel.setAprCurrentYear(getMonthBudget("aprCurrentYear"));
        accountsHistoryModel.setMayCurrentYear(getMonthBudget("mayCurrentYear"));
        accountsHistoryModel.setJunCurrentYear(getMonthBudget("junCurrentYear"));
        accountsHistoryModel.setJulCurrentYear(getMonthBudget("julCurrentYear"));
        accountsHistoryModel.setAugCurrentYear(getMonthBudget("augCurrentYear"));
        accountsHistoryModel.setSepCurrentYear(getMonthBudget("sepCurrentYear"));
        accountsHistoryModel.setOctCurrentYear(getMonthBudget("octCurrentYear"));
        accountsHistoryModel.setNovCurrentYear(getMonthBudget("novCurrentYear"));
        accountsHistoryModel.setDecCurrentYear(getMonthBudget("decCurrentYear"));
        accountsHistoryModel.setP13CurrentYear(getMonthBudget("p13CurrentYear"));
        accountsHistoryModel.setReconciledDate(getReconciledDate());
        accountsHistoryModel.setLastYearAndBalance(getMonthBudget("lastYearAndBalance"));
        return accountsHistoryModel;
    }

    /**
     * Get formatted data for account history model.
     *
     * @param budgetName name of budget month
     * @return Month Budget
     */
    private List<String> getMonthBudget(String budgetName) {
        return getAccountHistoryColumnsByName(budgetName).stream()
                .map(WebElement::getText).map(String::toLowerCase).map(String::trim)
                .map(s -> s.replace(",", "").replace("(", "-").replace(")", ""))
                .map(BigDecimal::new).map(b -> b.setScale(1, RoundingMode.CEILING)).map(Object::toString)
                .map(s -> s.replace(".0", "")).toList();
    }

    /**
     * Get formatted data for reconciled date.
     *
     * @return Reconciled Date
     */
    private List<String> getReconciledDate() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return getAccountHistoryColumnsByName("reconciledDate").stream()
                .map(WebElement::getText)                 // Extract text from the WebElement
                .map(String::trim)                        // Trim any extra spaces
                .filter(date -> !date.isEmpty())          // Skip empty strings
                .map(date -> parseDate(date, inputFormat)) // Parse with error handling
                .flatMap(Optional::stream)                // Skip null results (if any parsing failed)
                .map(localDate -> localDate.format(outputFormat)) // Format to desired output
                .collect(Collectors.toList());            // Collect results as a list
    }

    /**
     * Helper method to parse dates with error handling.
     *
     * @param date      the date
     * @param formatter the formatter
     * @return the optional
     */
    private Optional<LocalDate> parseDate(String date, DateTimeFormatter formatter) {
        try {
            return Optional.of(LocalDate.parse(date, formatter));
        } catch (DateTimeParseException e) {
            System.err.println("Failed to parse date: " + date + " - " + e.getMessage());
            return Optional.empty(); // Skip this date if parsing fails
        }
    }

    /**
     * Create account history model from API.
     *
     * @param accountHistoryModels account History Model response
     * @return AccountsHistoryModel
     */
    private AccountsHistoryModel createAccountHistoryModelApi(Response accountHistoryModels) {
        AccountsHistoryModel accountsHistoryModel = new AccountsHistoryModel();
        accountsHistoryModel.setAccountNumber(accountHistoryModels.getBody().jsonPath().getList("data.accountNumber"));
        List<String> names = accountHistoryModels.getBody().jsonPath().getList("data.name");
        accountsHistoryModel.setName(names.stream().map(String::trim).toList());
        accountsHistoryModel.setJanBudget(getBudgetApi(accountHistoryModels, "data.janBudget"));
        accountsHistoryModel.setFebBudget(getBudgetApi(accountHistoryModels, "data.febBudget"));
        accountsHistoryModel.setMarBudget(getBudgetApi(accountHistoryModels, "data.marBudget"));
        accountsHistoryModel.setAprBudget(getBudgetApi(accountHistoryModels, "data.aprBudget"));
        accountsHistoryModel.setMayBudget(getBudgetApi(accountHistoryModels, "data.mayBudget"));
        accountsHistoryModel.setJunBudget(getBudgetApi(accountHistoryModels, "data.junBudget"));
        accountsHistoryModel.setJulBudget(getBudgetApi(accountHistoryModels, "data.julBudget"));
        accountsHistoryModel.setAugBudget(getBudgetApi(accountHistoryModels, "data.augBudget"));
        accountsHistoryModel.setSepBudget(getBudgetApi(accountHistoryModels, "data.sepBudget"));
        accountsHistoryModel.setOctBudget(getBudgetApi(accountHistoryModels, "data.octBudget"));
        accountsHistoryModel.setNovBudget(getBudgetApi(accountHistoryModels, "data.novBudget"));
        accountsHistoryModel.setDecBudget(getBudgetApi(accountHistoryModels, "data.decBudget"));
        accountsHistoryModel.setP13Budget(accountHistoryModels.getBody().jsonPath().getList("data.p13Budget"));
        accountsHistoryModel.setJanCurrentYear(getBudgetApi(accountHistoryModels, "data.janCurrentYear"));
        accountsHistoryModel.setFebCurrentYear(getBudgetApi(accountHistoryModels, "data.febCurrentYear"));
        accountsHistoryModel.setMarCurrentYear(getBudgetApi(accountHistoryModels, "data.marCurrentYear"));
        accountsHistoryModel.setAprCurrentYear(getBudgetApi(accountHistoryModels, "data.aprCurrentYear"));
        accountsHistoryModel.setMayCurrentYear(getBudgetApi(accountHistoryModels, "data.mayCurrentYear"));
        accountsHistoryModel.setJunCurrentYear(getBudgetApi(accountHistoryModels, "data.junCurrentYear"));
        accountsHistoryModel.setJulCurrentYear(getBudgetApi(accountHistoryModels, "data.julCurrentYear"));
        accountsHistoryModel.setAugCurrentYear(getBudgetApi(accountHistoryModels, "data.augCurrentYear"));
        accountsHistoryModel.setSepCurrentYear(getBudgetApi(accountHistoryModels, "data.sepCurrentYear"));
        accountsHistoryModel.setOctCurrentYear(getBudgetApi(accountHistoryModels, "data.octCurrentYear"));
        accountsHistoryModel.setNovCurrentYear(getBudgetApi(accountHistoryModels, "data.novCurrentYear"));
        accountsHistoryModel.setDecCurrentYear(getBudgetApi(accountHistoryModels, "data.decCurrentYear"));
        accountsHistoryModel.setP13CurrentYear(getBudgetApi(accountHistoryModels, "data.p13CurrentYear"));
        List<Object> reconciledDates = accountHistoryModels.getBody().jsonPath().getList("data.reconciledDate");
        accountsHistoryModel.setReconciledDate(
                reconciledDates.stream()
                        .filter(Objects::nonNull)         // Skip null values
                        .map(Object::toString)            // Convert to string
                        .map(String::trim)                // Trim extra spaces
                        .filter(date -> !date.isEmpty())  // Skip empty strings (if any)
                        .collect(Collectors.toList())     // Collect as a list
        );
        accountsHistoryModel.setLastYearAndBalance(getBudgetApi(accountHistoryModels, "data.lastYearAndBalance"));
        return accountsHistoryModel;
    }

    /**
     * Get formatted data for account history model.
     *
     * @param accountHistoryModels account History Models response
     * @param fieldName            name of budget month
     * @return Budget Api
     */
    private List<String> getBudgetApi(Response accountHistoryModels, String fieldName) {
        return accountHistoryModels.getBody().jsonPath().getList(fieldName)
                .stream().map(Object::toString)
                .map(BigDecimal::new)
                .map(b -> b.setScale(1, RoundingMode.CEILING))
                .map(Object::toString)
                .map(s -> s.replace(".0", ""))
                .toList();
    }

    /**
     * Gets current transaction period.
     *
     * @param user  the user
     * @param orgId the org id
     * @return the current transaction period
     * @throws Exception the exception
     */
    public int getCurrentTransactionPeriod(User user, String orgId) throws Exception {
        this.common.waitForPageToLoad();
        Response response = this.generalWhenSteps.makeRequest(GET, COMMAND_MC_TRANSACTIONS_PERIODS, V1,
                new String[]{orgId}, user);
        response.then().assertThat().statusCode(SC_OK);
        return response.jsonPath().getInt("data.periodMonth");
    }

    /**
     * Verify full list of accounts total for balance sheet.
     *
     * @param user   the user
     * @param orgId  the org id
     * @param period the period
     * @throws Exception the exception
     */
    public void verifyFullListOfAccountsTotalForBalanceSheet(User user, String orgId, int period) throws Exception {
        this.common.waitForPageToLoad();
        this.generalWhenSteps.queryParam("month", String.valueOf(period));
        Response balanceSheetResponse = this.generalWhenSteps.makeRequest(GET, KW_FS_ACCOUNTS_BALANCE_SHEET, V2,
                new String[]{orgId}, user);
        balanceSheetResponse.then().assertThat().statusCode(SC_OK);

        List<AssetsModel> assetsModelList = balanceSheetResponse.jsonPath()
                .getList("assets", AssetsModel.class);
        List<LiabilitiesAndEquityModel> liabilitiesEquityModelList = balanceSheetResponse.jsonPath()
                .getList("liabilitiesAndEquity", LiabilitiesAndEquityModel.class);
        List<Double> assetsTotalsUi = getAssetsTotalsUi();
        List<Double> assetsTotalsApi = getAssetsTotalsApi(assetsModelList, liabilitiesEquityModelList);
        Collections.sort(assetsTotalsUi);
        Collections.sort(assetsTotalsApi);
        assertEquals("Assets totals is not equal!", assetsTotalsUi, assetsTotalsApi);
    }

    /**
     * Verify full list of accounts range for balance sheet.
     *
     * @param user   the user
     * @param orgId  the org id
     * @param period the period
     * @throws Exception the exception
     */
    public void verifyFullListOfAccountsRangeForBalanceSheet(User user, String orgId, int period) throws Exception {
        this.common.waitForPageToLoad();
        this.generalWhenSteps.queryParam("month", String.valueOf(period));
        Response balanceSheetResponse = this.generalWhenSteps.makeRequest(GET, KW_FS_ACCOUNTS_BALANCE_SHEET, V2,
                new String[]{orgId}, user);
        balanceSheetResponse.then().assertThat().statusCode(SC_OK);

        List<AssetsModel> assetsModelList = balanceSheetResponse.jsonPath()
                .getList("assets", AssetsModel.class);
        List<LiabilitiesAndEquityModel> liabilitiesEquityModelList = balanceSheetResponse.jsonPath()
                .getList("liabilitiesAndEquity", LiabilitiesAndEquityModel.class);
        List<String> assetsRangeUi = getAssetsRangeUi();
        List<String> assetsRangeApi = getAssetsRangeApi(assetsModelList, liabilitiesEquityModelList);
        assertEquals("Assets Range is not equal!", assetsRangeUi, assetsRangeApi);
    }

    /**
     * Select month.
     *
     * @param month the month
     */
    public void selectMonth(String month) {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        getSelectMonthDropdown().click();
        getLstMonth().stream()
                .filter(x -> x.getText().contains(month))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Month '" + month + "' doesn't exist"))
                .click();
    }

    /**
     * Verify future month label is displayed.
     */
    public void verifyFutureMonthLabelIsDisplayed() {
        this.common.waitForPageToLoad();
        assertTrue("Future month label is not displayed!", getFutureMonthLbl().isDisplayed());
    }

    /**
     * Verify No data available disclaimer.
     */
    public void verifyNoDataAvailableDisclaimer() {
        this.common.waitForPageToLoad();
        String expectedErrorMessage = "No data is available yet";
        assertEquals("Error - error message is incorrect!", expectedErrorMessage,
                getNoDataAvailableError().getText());
    }

    /**
     * Verify please upload financial data disclaimer.
     */
    public void verifyPleaseUploadFinancialDataDisclaimer() {
        this.common.waitForPageToLoad();
        String expectedErrorMessage = "Please upload financial data to proceed.";
        assertEquals("Error - error message is incorrect!", expectedErrorMessage,
                getPleaseUploadFinancialDataError().getText());
    }

    /**
     * Verify "Select Month" dropdown in top right side of page is displayed.
     */
    public void verifySelectMonthDropdownIsDisplayed() {
        this.common.waitForPageToLoad();
        assertTrue("\"Select Month\" dropdown is not displayed!", getSelectMonthDropdown().isDisplayed());
    }

    /**
     * Verify future month clock icon is displayed.
     */
    public void verifyFutureMonthClockIconIsDisplayed() {
        this.common.waitForPageToLoad();
        assertTrue("Future month clock icon is not displayed!", getClockIcon().isDisplayed());
    }

    /**
     * Verify message for future month.
     *
     * @param expectedMessage the expected message
     */
    public void verifyMessageForFutureMonth(String expectedMessage) {
        this.common.waitForPageToLoad();
        assertEquals("Future month message is incorrect! ", expectedMessage,
                getFutureMonthMessage().getText());
    }

    /**
     * Verify View Security Key is enabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    public void verifyViewSecurityKeyButtonIsEnabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsDisabled(getBtnViewSecurityKey(), shouldBeDisabled);
    }

    /**
     * Verify view security key button is present.
     *
     * @param shouldBePresent should be present
     */
    public void verifyViewSecurityKeyButtonIsPresent(boolean shouldBePresent) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsPresent(getBtnViewSecurityKey(), shouldBePresent);
    }

    /**
     * Verify Upload files button is present.
     *
     * @param shouldBePresent should be present
     */
    public void verifyUploadFilesButtonIsPresent(boolean shouldBePresent) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsPresent(getBtnUploadFiles(), shouldBePresent);
    }

    /**
     * Click on upload files button.
     */
    public void clickOnUploadFilesButton() {
        this.common.waitForPageToLoad();
        getBtnUploadFiles().click();
    }

    /**
     * Click on view security key button.
     */
    public void clickOnViewSecurityKeyButton() {
        this.common.waitForPageToLoad();
        getBtnViewSecurityKey().click();
    }

    /**
     * Get Assets names for balance sheet.
     *
     * @param assetsModelList            assets Model List
     * @param liabilitiesAndEquityModels liabilities And Equity Models list
     * @return list of Assets Names
     */
    private static List<String> getAssetsNamesApi(List<AssetsModel> assetsModelList,
                                                  List<LiabilitiesAndEquityModel> liabilitiesAndEquityModels) {
        List<String> assetsNamesApi = new ArrayList<>();
        List<String> assetsNames = new ArrayList<>();
        for (AssetsModel assetsModel : assetsModelList) {
            for (AccountGroupsModel accountGroupsModel : assetsModel.getAccountGroups()) {
                assetsNames.add(accountGroupsModel.getName().toLowerCase());
            }
        }
        List<String> liabilitiesAndEquityNames = new ArrayList<>();
        for (LiabilitiesAndEquityModel liabilitiesAndEquityModel : liabilitiesAndEquityModels) {
            for (AccountGroupsModel accountGroupsModel : liabilitiesAndEquityModel.getAccountGroups()) {
                liabilitiesAndEquityNames.add(accountGroupsModel.getName().toLowerCase());
            }
        }
        assetsNamesApi.addAll(assetsNames);
        assetsNamesApi.addAll(liabilitiesAndEquityNames);
        return assetsNamesApi;
    }

    /**
     * Get Assets Totals for balance sheet.
     *
     * @param assetsModelList            assets Model List
     * @param liabilitiesAndEquityModels liabilities And Equity Models list
     * @return list of Assets Totals
     */
    private static List<Double> getAssetsTotalsApi(List<AssetsModel> assetsModelList,
                                                   List<LiabilitiesAndEquityModel> liabilitiesAndEquityModels) {
        List<Double> assetsTotalsApi = new ArrayList<>();
        List<Double> assetsTotal = new ArrayList<>();
        for (AssetsModel assetsModel : assetsModelList) {
            for (AccountGroupsModel accountGroupsModel : assetsModel.getAccountGroups()) {
                assetsTotal.add(accountGroupsModel.getTotal());
            }
        }
        List<Double> liabilitiesAndEquityNames = new ArrayList<>();
        for (LiabilitiesAndEquityModel liabilitiesAndEquityModel : liabilitiesAndEquityModels) {
            for (AccountGroupsModel accountGroupsModel : liabilitiesAndEquityModel.getAccountGroups()) {
                liabilitiesAndEquityNames.add(accountGroupsModel.getTotal());
            }
        }
        assetsTotalsApi.addAll(assetsTotal);
        assetsTotalsApi.addAll(liabilitiesAndEquityNames);
        return assetsTotalsApi;
    }

    /**
     * Get Assets Names fromUI for balance sheet.
     *
     * @return list of Assets Names
     */
    private List<String> getAssetsNamesUi() {
        List<String> assetsNames = getLstAssetsAccountsName().stream()
                .map(WebElement::getText).map(String::toLowerCase).toList();
        List<String> assetsNamesWithoutRange = getLstAssetsAccountsNamesWithoutRange().stream()
                .map(WebElement::getText).map(String::toLowerCase).toList();
        List<String> assetsNamesUi = new ArrayList<>();
        assetsNamesUi.addAll(assetsNames);
        assetsNamesUi.addAll(assetsNamesWithoutRange);
        return assetsNamesUi;
    }

    /**
     * Get Assets Totals from UI for balance sheet.
     *
     * @return list of Assets Totals from UI
     */
    private List<Double> getAssetsTotalsUi() {
        List<String> assetsNames = new ArrayList<>(getLstAccountsTotal().stream()
                .map(WebElement::getText).toList());
        assetsNames.replaceAll(s -> s.replace(",", "").replace("(", "-")
                .replace(")", ""));
        List<Double> assetsTotalsFormatted = assetsNames.stream()
                .map(Double::parseDouble).toList();
        List<String> assetsTotalsWithoutRange = new ArrayList<>(getLstAccountsTotalWithoutRange().stream()
                .map(WebElement::getText).toList());
        assetsTotalsWithoutRange.replaceAll(s -> s.replace(",", "").replace("(", "-")
                .replace(")", ""));
        List<Double> assetsTotalsWithoutRangeFormatted = assetsTotalsWithoutRange.stream()
                .map(Double::parseDouble).toList();
        List<Double> assetsTotalsUi = new ArrayList<>();
        assetsTotalsUi.addAll(assetsTotalsFormatted);
        assetsTotalsUi.addAll(assetsTotalsWithoutRangeFormatted);
        return assetsTotalsUi;
    }

    /**
     * Get Assets Ranges from UI for balance sheet.
     *
     * @return list of Assets Ranges
     */
    private List<String> getAssetsRangeUi() {
        return getLstAccountsRange().stream()
                .map(WebElement::getText).toList();
    }

    /**
     * Get Assets Ranges for balance sheet.
     *
     * @param assetsModelList            assets Model List
     * @param liabilitiesAndEquityModels liabilities And Equity Models list
     * @return list of Assets Ranges
     */
    private static List<String> getAssetsRangeApi(List<AssetsModel> assetsModelList,
                                                  List<LiabilitiesAndEquityModel> liabilitiesAndEquityModels) {
        List<String> assetsRangeApi = new ArrayList<>();
        List<String> assetsRange = new ArrayList<>();
        for (AssetsModel assetsModel : assetsModelList) {
            for (AccountGroupsModel accountGroupsModel : assetsModel.getAccountGroups()) {
                assetsRange.add(accountGroupsModel.getRange());
                assetsRange.removeIf(Objects::isNull);
            }
        }
        List<String> liabilitiesAndEquityNames = new ArrayList<>();
        for (LiabilitiesAndEquityModel liabilitiesAndEquityModel : liabilitiesAndEquityModels) {
            for (AccountGroupsModel accountGroupsModel : liabilitiesAndEquityModel.getAccountGroups()) {
                liabilitiesAndEquityNames.add(accountGroupsModel.getRange());
                liabilitiesAndEquityNames.removeIf(Objects::isNull);
            }
        }
        assetsRangeApi.addAll(assetsRange);
        assetsRangeApi.addAll(liabilitiesAndEquityNames);
        return assetsRangeApi;
    }

    /**
     * Verify account history headers.
     */
    public void verifyAccountHistoryHeaders() {
        this.common.waitForPageToLoad();
        this.common.waitForPageToLoad();
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(getAccountHistoryHeadersByName("Acct #").isDisplayed())
                .as("Acct # should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Name").isDisplayed())
                .as("Name should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Jan Budget").isDisplayed())
                .as("Jan Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Feb Budget").isDisplayed())
                .as("Feb Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Mar Budget").isDisplayed())
                .as("Mar Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Apr Budget").isDisplayed())
                .as("Apr Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("May Budget").isDisplayed())
                .as("May Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Jun Budget").isDisplayed())
                .as("Jun Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Jul Budget").isDisplayed())
                .as("Jul Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Aug Budget").isDisplayed())
                .as("Aug Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Sep Budget").isDisplayed())
                .as("Sep Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Oct Budget").isDisplayed())
                .as("Oct Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Nov Budget").isDisplayed())
                .as("Nov Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Dec Budget").isDisplayed())
                .as("Dec Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("P13 Budget").isDisplayed())
                .as("P13 Budget should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Jan CY").isDisplayed())
                .as("Jan CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Feb CY").isDisplayed())
                .as("Feb CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Mar CY").isDisplayed())
                .as("Mar CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Apr CY").isDisplayed())
                .as("Apr CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("May CY").isDisplayed())
                .as("May CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Jun CY").isDisplayed())
                .as("Jun CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Jul CY").isDisplayed())
                .as("Jul CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Aug CY").isDisplayed())
                .as("Aug CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Sep CY").isDisplayed())
                .as("Sep CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Oct CY").isDisplayed())
                .as("Oct CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Nov CY").isDisplayed())
                .as("Nov CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Dec CY").isDisplayed())
                .as("Dec CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("P13 CY").isDisplayed())
                .as("P13 CY should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("Reconciled Date").isDisplayed())
                .as("Reconciled Date should be displayed!").isTrue();
        assertions.assertThat(getAccountHistoryHeadersByName("LY End Bal").isDisplayed())
                .as("LY End Bal should be displayed!").isTrue();
        assertions.assertAll();
    }

    /**
     * Verify accounts in account history are sorted alphabetically.
     */
    public void verifyAccountsInAccountHistoryAreSortedAlphabetically() {
        List<String> names = new ArrayList<>();
        for (WebElement account : getLstAccountHistoryAccounts()) {
            names.add(account.getText().toLowerCase(Locale.ENGLISH));
        }
        assertThat(names).isSorted();
    }

    /**
     * Verify Income Statement headers.
     */
    public void verifyIncomeStatementHeaders() {
        this.common.waitForPageToLoad();
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(getIncomeStatementHeadersByName("ACCT #").isDisplayed())
                .as("ACCT # should be displayed!").isTrue();
        assertions.assertThat(getIncomeStatementHeadersByName("ACCOUNT NAME").isDisplayed())
                .as("ACCOUNT NAME  should be displayed!").isTrue();
        assertions.assertThat(getIncomeStatementHeadersByName("CURRENT ACTUAL").isDisplayed())
                .as("CURRENT ACTUAL should be displayed!").isTrue();
        assertions.assertThat(getIncomeStatementHeadersByName("CURRENT BUDGET").isDisplayed())
                .as("CURRENT BUDGET should be displayed!").isTrue();
        assertions.assertThat(getIncomeStatementHeadersByName("CURRENT VARIANCE").isDisplayed())
                .as("CURRENT VARIANCE should be displayed!").isTrue();
        assertions.assertThat(getIncomeStatementHeadersByName("YTD ACTUAL").isDisplayed())
                .as("YTD ACTUAL should be displayed!").isTrue();
        assertions.assertThat(getIncomeStatementHeadersByName("YTD BUDGET").isDisplayed())
                .as("YTD BUDGET should be displayed!").isTrue();
        assertions.assertThat(getIncomeStatementHeadersByName("YTD VARIANCE").isDisplayed())
                .as("YTD VARIANCE should be displayed!").isTrue();
        assertions.assertThat(getIncomeStatementHeadersByName("ANNUAL BUDGET").isDisplayed())
                .as("ANNUAL BUDGET should be displayed!").isTrue();
        assertions.assertAll();
    }

    /**
     * Verify full list of accounts for Income Statement.
     *
     * @param user   the user
     * @param orgId  the org id
     * @param period the period
     * @throws Exception the exception
     */
    public void verifyFullListOfAccountsForIncomeStatement(User user, String orgId, int period) throws Exception {
        this.common.waitForPageToLoad();
        this.generalWhenSteps.queryParam("month", String.valueOf(period));
        Response incomeStatementResponse = this.generalWhenSteps.makeRequest(GET, KW_FS_ACCOUNTS_INCOME_STATEMENT, V2,
                new String[]{String.valueOf(orgId)}, user);
        incomeStatementResponse.then().assertThat().statusCode(SC_OK);
        List<String> assetsNamesUi = getIncomeStatementNamesUi().stream().sorted().toList();
        List<String> assetsNamesApi = getIncomeStatementNamesApiByGroupName(incomeStatementResponse)
                .stream().map(String::trim).toList().stream().sorted().toList();
        assertEquals("Names is not equal!", assetsNamesUi,
                assetsNamesApi);
    }

    /**
     * Get Income Statement Names Ui.
     *
     * @return Income Statement Names
     */
    private List<String> getIncomeStatementNamesUi() {
        return getLstAccountNamesForIncomeStatement().stream()
                .map(WebElement::getText).map(String::toLowerCase).toList();
    }

    /**
     * Get Income Statement Names API.
     *
     * @param incomeStatementResponse income Statement Response
     * @return Income Statement Names By GroupName
     */
    private List<String> getIncomeStatementNamesApiByGroupName(Response incomeStatementResponse) {
        String accountNames = getIncomeStatementValues(incomeStatementResponse, "data.accountRanges.name");
        String totals = getIncomeStatementValues(incomeStatementResponse, "data.totals.name");
        String res = accountNames + ", " + totals;
        String[] strSplit = res.split(",");
        ArrayList<String> result = new ArrayList<>(
                Arrays.asList(strSplit));
        return result.stream().sorted().toList();
    }

    /**
     * Get Income Statement  values.
     *
     * @param incomeStatementResponse income Statement Response
     * @param propertyName            property name
     * @return Income Statement Values
     */
    private String getIncomeStatementValues(Response incomeStatementResponse, String propertyName) {
        return incomeStatementResponse.jsonPath().getList(propertyName)
                .stream()
                .map(Object::toString)
                .map(String::toLowerCase)
                .map(s -> s.replace("[", "").replace("]", "")).toList()
                .stream().map(Object::toString)
                .collect(Collectors.joining(","));
    }

    /**
     * Verify total income for income group.
     *
     * @param user      the user
     * @param orgId     the org id
     * @param period    the period
     * @param totalName the total name
     * @throws Exception the exception
     */
    public void verifyTotalIncomeForIncomeGroup(User user, String orgId, int period, String totalName)
            throws Exception {
        this.common.waitForPageToLoad();
        this.generalWhenSteps.queryParam("month", String.valueOf(period));
        Response incomeStatementResponse = this.generalWhenSteps.makeRequest(GET, KW_FS_ACCOUNTS_INCOME_STATEMENT, V2,
                new String[]{String.valueOf(orgId)}, user);
        incomeStatementResponse.then().assertThat().statusCode(SC_OK);
        verifyTotalIncomeInIncomeGroup(incomeStatementResponse, totalName);
    }

    /**
     * Verify total income for group.
     *
     * @param incomeStatementResponse income Statement Response
     * @param totalName               totalName
     */
    private void verifyTotalIncomeInIncomeGroup(Response incomeStatementResponse, String totalName) {
        SoftAssertions softAssertions = new SoftAssertions();
        List<List<LinkedHashMap>> incomeStatementTotalModel = incomeStatementResponse.jsonPath()
                .getList("data.totals");
        for (List<LinkedHashMap> incomeStatementTotalModel1 : incomeStatementTotalModel) {
            List<IncomeStatementTotalsModel> result = incomeStatementTotalModel1.stream()
                    .map(map -> new IncomeStatementTotalsModel(map.get("currentActual").toString(),
                            map.get("currentBudget").toString(),
                            map.get("currentVariance").toString(),
                            map.get("ytdActual").toString(), map.get("ytdBudget").toString(),
                            map.get("ytdVariance").toString(), map.get("annualBudget").toString(),
                            map.get("name").toString()))
                    .toList();
            for (IncomeStatementTotalsModel incomeStatementTotal : result) {
                if (incomeStatementTotal.getName().equals(totalName)) {
                    softAssertions.assertThat(new BigDecimal(incomeStatementTotal.getCurrentActual())
                                    .setScale(1, RoundingMode.CEILING).toString()
                                    .replace(".0", ""))
                            .as("CURRENT ACTUAL is invalid for " + totalName)
                            .isEqualTo(getTotalsFromUi(getTotalCurrentActualByName(totalName)));
                    softAssertions.assertThat(new BigDecimal(incomeStatementTotal.getCurrentBudget())
                                    .setScale(1, RoundingMode.CEILING).toString()
                                    .replace(".0", ""))
                            .as("CURRENT BUDGET is invalid for " + totalName)
                            .isEqualTo(getTotalsFromUi(getTotalCurrentBudgetByName(totalName)));
                    softAssertions.assertThat(new BigDecimal(incomeStatementTotal.getCurrentVariance())
                                    .setScale(1, RoundingMode.CEILING).toString()
                                    .replace(".0", ""))
                            .as("CURRENT VARIANCE is invalid for " + totalName)
                            .isEqualTo(getTotalsFromUi(getTotalCurrentVarianceByName(totalName)));
                    softAssertions.assertThat(new BigDecimal(incomeStatementTotal.getYtdActual())
                                    .setScale(1, RoundingMode.CEILING).toString()
                                    .replace(".0", ""))
                            .as("YTD ACTUAL is invalid for " + totalName)
                            .isEqualTo(getTotalsFromUi(getTotalYtdActualByName(totalName)));
                    softAssertions.assertThat(new BigDecimal(incomeStatementTotal.getYtdBudget())
                                    .setScale(1, RoundingMode.CEILING).toString()
                                    .replace(".0", ""))
                            .as("YTD BUDGET is invalid for " + totalName)
                            .isEqualTo(getTotalsFromUi(getTotalYtdBudgetByName(totalName)));
                    softAssertions.assertThat(new BigDecimal(incomeStatementTotal.getYtdVariance())
                                    .setScale(1, RoundingMode.CEILING).toString()
                                    .replace(".0", ""))
                            .as("YTD VARIANCE is invalid for " + totalName)
                            .isEqualTo(getTotalsFromUi(getTotalYtdVarianceByName(totalName)));
                    softAssertions.assertThat(new BigDecimal(incomeStatementTotal.getAnnualBudget())
                                    .setScale(1, RoundingMode.CEILING).toString()
                                    .replace(".0", ""))
                            .as("ANNUAL BUDGET is invalid for " + totalName)
                            .isEqualTo(getTotalsFromUi(getTotalAnnualBudgetByName(totalName)));
                    softAssertions.assertAll();
                }
            }
        }
    }

    /**
     * Get total income for group from Ui.
     *
     * @param element web element for replacement
     * @return Totals from Ui
     */
    private String getTotalsFromUi(WebElement element) {
        String result = element.getText().replace(",", "").replace("(", "-")
                .replace(")", "").replace(".00", "");
        return new BigDecimal(result).setScale(1, RoundingMode.CEILING).toString()
                .replace(".0", "");
    }

    /**
     * Verify footer for the balance sheet.
     */
    public void verifyFooterForTheBalanceSheet() {
        this.common.waitForPageToLoad();
        this.common.waitUntilElementIsVisible(TIMEOUT_30_SECONDS.getValue(), getLblTotalAssetsFooter());
        double totalAssetsValue = Double.parseDouble(getLblTotalAssetsFooter().getText()
                .replaceAll("\\D", ""));
        double totalLiabilitiesAndEquityValue = Double.parseDouble(getLblTotalLiabilitiesAndEquityFooter().getText()
                .replaceAll("\\D", ""));
        if (totalAssetsValue == totalLiabilitiesAndEquityValue) {
            assertEquals("Balance Status is invalid!", "Balanced",
                    getLblBalanceStatusFooter().getText());
            assertEquals("Save New Data button should be enabled!", "false",
                    getBtnSaveNewData().getAttribute("aria-disabled"));
        } else {
            assertEquals("Balance Status is invalid!", "Out of Balance",
                    getLblBalanceStatusFooter().getText());
            assertEquals("Save New Data button should be disabled!", "true",
                    getBtnSaveNewData().getAttribute("aria-disabled"));
        }
    }

    /**
     * Verify footer is not present.
     */
    public void verifyFooterIsNotPresent() {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsPresent(getLblBalanceStatusFooter(), false);
    }

    /**
     * Click on Save New Data button.
     */
    public void clickOnSaveNewDataButton() {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        this.common.waitUntilElementIsClickable(TIMEOUT_30_SECONDS.getValue(), getBtnSaveNewData());
        getBtnSaveNewData().click();
    }

    /**
     * Verify Save New Data button is enabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    public void verifySaveNewDataButtonIsEnabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        this.driverUtils.verifyElementIsDisabled(getBtnSaveNewData(), shouldBeDisabled);
    }

    /**
     * Verify banner notification.
     */
    public void verifyBannerNotification() {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        String expectedNotification = "YTD Net Profit (Loss) on the Income Statement does not match Current "
                + "Period Profit (Loss) on the Balance Sheet Check for any account being used that is not part"
                + " of the KW Chart of Accounts. Once corrected in Accountedge, upload data again";
        assertEquals("Banner Notification is incorrect!",
                expectedNotification,
                getLblNotificationTitle().getText() + " " + getLblNotificationSubTitle().getText());
    }

    /**
     * Verify net profit banner does not present.
     *
     * @param shouldBePresented should be presented
     */
    public void verifyNetProfitBannerIsPresent(boolean shouldBePresented) {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        this.driverUtils.verifyElementIsPresent(getNetProfitErrorBanner(), shouldBePresented);
    }

    /**
     * Verify forbidden icon is present.
     */
    public void verifyForbiddenIconIsPresent() {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        assertTrue("Error - forbidden icon is not displayed!", getForbiddenIconInNetProfitErrorBanner().isDisplayed());
    }

    /**
     * Verify close icon in net profit error banner is present.
     */
    public void verifyCloseIconInNetProfitErrorBannerIsPresent() {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();
        assertTrue("Error - forbidden icon is not displayed!", getDismissIconInNetProfitErrorBanner().isDisplayed());
    }
}
