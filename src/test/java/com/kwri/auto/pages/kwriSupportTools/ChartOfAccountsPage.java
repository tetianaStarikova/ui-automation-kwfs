package com.kwri.auto.pages.kwriSupportTools;

import com.google.inject.Inject;
import com.kwri.auto.enums.User;
import com.kwri.auto.steps.GeneralWhenSteps;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.ui.pages.BasePage;
import com.kwri.auto.utils.DriverUtils;
import com.kwri.auto.model.kwfs.AccountsModel;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.kwri.auto.enums.Endpoints.KW_FS_ACCOUNTS;
import static com.kwri.auto.enums.Versions.V1;
import static io.restassured.http.Method.GET;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The type Chart of Accounts Support Tools tab page.
 */
@Getter
@Slf4j
public class ChartOfAccountsPage extends BasePage {

    @FindBy(xpath = "//div[@data-testId='bar-component']//h1")
    private WebElement chartOfAccountsLbl;

    @FindBy(xpath = "//input[@id='accounts-table-search-field']")
    private WebElement inputSearchField;

    @FindBy(xpath = "//div[@data-testid='accounts-table-search-field']//span")
    private WebElement magnifyingGlassIcon;

    @FindBy(xpath = "//td[@data-testid='accounts-table-cell'][1]")
    private List<WebElement> lstAccountedgeRange;

    @FindBy(xpath = "//td[@data-testid='accounts-table-cell'][2]")
    private List<WebElement> lstAccountNames;

    @FindBy(xpath = "//td[@data-testid='accounts-table-cell'][3]")
    private List<WebElement> lstGrouping;

    @FindBy(xpath = "//h3[text()='No Results Found']")
    private WebElement lblNoResultsFound;

    @FindBy(xpath = "//p[text()='Please try a different search.']")
    private WebElement lblTryDifferentSearch;

    @Inject
    private DriverUtils driverUtils;
    @Inject
    private Common common;
    @Inject
    private GeneralWhenSteps generalWhenSteps;

    /**
     * Instantiates a newChart of Accounts tab page.
     *
     * @param world the world
     */
    @Inject
    public ChartOfAccountsPage(final World world) {
        super(world);
    }

    /**
     * Gets list of accounts table column header.
     *
     * @param column the column
     * @return the list of accounts table column header
     */
    public WebElement getListOfAccountsTableColumnHeader(String column) {
        return this.world.driver
                .findElement(By.xpath("//th[@data-testId='accounts-table-header']//div[text()='"
                        + column + "']"));
    }

    /**
     * Verify chart of accounts label.
     */
    public void verifyChartOfAccountsLabel() {
        this.common.waitForPageToLoad();
        assertEquals("Expected header is 'Chart of Accounts', but found: " + getChartOfAccountsLbl().getText(),
                "Chart of Accounts", getChartOfAccountsLbl().getText());
    }

    /**
     * Verify input search by account name is displayed.
     */
    public void verifyInputSearchByAccountNameIsDisplayed() {
        this.common.waitForPageToLoad();
        assertTrue("Error - the search field is not displayed.", getInputSearchField().isDisplayed());
    }

    /**
     * Verify placeholder for search by account name.
     *
     * @param expectedPlaceholder the expected placeholder
     */
    public void verifyPlaceholderForSearchByAccountName(String expectedPlaceholder) {
        assertEquals("Error - " + expectedPlaceholder + " placeholder is not displayed in search field.",
                expectedPlaceholder, getInputSearchField().getAttribute("placeholder"));
    }

    /**
     * Verify list of accounts table column headers.
     */
    public void verifyListOfAccountsTableColumnHeaders() {
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(getListOfAccountsTableColumnHeader("Account Name").isDisplayed())
                .as("Account Name column header is displayed.").isTrue();
        softAssert.assertThat(getListOfAccountsTableColumnHeader("Accountedge Range").isDisplayed())
                .as("Accountedge Range column header is displayed.").isTrue();
        softAssert.assertThat(getListOfAccountsTableColumnHeader("Grouping").isDisplayed())
                .as("Grouping column header is displayed.").isTrue();
        softAssert.assertAll();
    }

    /**
     * Verify list of accounts.
     *
     * @param user the user
     */
    public void verifyListOfAccounts(User user) throws Exception {
        Response response = this.generalWhenSteps.makeRequest(GET, KW_FS_ACCOUNTS, V1, user);
        response.then().assertThat().statusCode(SC_OK);

        List<AccountsModel> listOfAccountsApi = response.jsonPath()
                .getList("data", AccountsModel.class);
        List<String> listOfAccountedgeRangeApi = new ArrayList<>(listOfAccountsApi.stream()
                .map(account -> account
                        .getAccountedgeAccountNumber() == null ? "" : account.getAccountedgeAccountNumber())
                .toList());
        List<String> listOfAccountedgeRangeUi = getLstAccountedgeRange().stream().map(WebElement::getText).toList();
        List<String> listOfAccountNameApi = new ArrayList<>(listOfAccountsApi.stream()
                .map(AccountsModel::getAccountName).toList());
        List<String> listOfAccountNameUi = getLstAccountNames().stream().map(WebElement::getText).toList();
        List<String> listOfGroupingApi = new ArrayList<>(listOfAccountsApi.stream()
                .map(AccountsModel::getGrouping).toList());
        List<String> listOfGroupingUi = getLstGrouping().stream().map(WebElement::getText).toList();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(listOfAccountedgeRangeApi).as("List of Accountedge range is incorrect!")
                .isEqualTo(listOfAccountedgeRangeUi);
        softAssertions.assertThat(listOfAccountNameApi).as("List of Account Names is incorrect!")
                .isEqualTo(listOfAccountNameUi);
        softAssertions.assertThat(listOfGroupingApi).as("List of Grouping is incorrect!")
                .isEqualTo(listOfGroupingUi);
        softAssertions.assertAll();
    }

    /**
     * Verify magnifying glass.
     */
    public void verifyMagnifyingGlass() {
        assertTrue("Error - Magnifying glass icon is not displayed in search field.",
                getMagnifyingGlassIcon().isDisplayed());
    }

    /**
     * User enters search criteria in search by account name field.
     *
     * @param searchCriteria the search criteria
     */
    public void userEntersSearchCriteriaInSearchByAccountNameField(String searchCriteria) {
        this.driverUtils.clearFieldUsingBackspace(getInputSearchField());
        getInputSearchField().sendKeys(searchCriteria);
    }

    /**
     * User sees search starts from the first entered character for account names.
     */
    public void userSeesSearchStartsFromTheFirstEnteredCharacterForAccountNames() {
        this.common.waitForAjaxRequestsComplete();
        this.driverUtils.waitLoaderDisappear();
        assertFalse("Error - search for Account Names is not started", getLstAccountNames().isEmpty());
    }

    /**
     * User sees list of account names that match entered account name.
     *
     * @param accountName the account name
     */
    public void userSeesListOfAccountNamesThatMatchEnteredAccountName(String accountName) {
        this.common.waitForAjaxRequestsComplete();
        boolean isDisplayed;
        if ("-".equals(accountName)) {
            isDisplayed = getLstAccountNames()
                    .stream()
                    .anyMatch(value -> (value.getText().toLowerCase().contains("-"))
                            | (value.getText().toLowerCase().contains("–")));
        } else {
            isDisplayed = getLstAccountNames()
                    .stream()
                    .allMatch(value -> value.getText().toLowerCase().contains(accountName.toLowerCase(Locale.ENGLISH)));
        }
        assertTrue("Error - Not all displayed accounts contains entered search criteria: "
                + accountName, isDisplayed);
    }

    /**
     * User sees no results found message.
     *
     * @param expectedLabel the expected label
     */
    public void userSeesNoResultsFoundMessage(String expectedLabel) {
        this.common.waitForPageToLoad();
        this.driverUtils.waitLoaderDisappear();
        assertEquals("No Results Found", getLblNoResultsFound().getText());
    }

    /**
     * User sees try different search disclaimer.
     */
    public void userSeesTryDifferentSearchDisclaimer() {
        this.common.waitForPageToLoad();
        this.driverUtils.waitLoaderDisappear();
        String expectedLabel = "Please try a different search.";
        assertEquals("Label " + expectedLabel + " should be displayed.",
                expectedLabel, getLblTryDifferentSearch().getText());
    }
}
