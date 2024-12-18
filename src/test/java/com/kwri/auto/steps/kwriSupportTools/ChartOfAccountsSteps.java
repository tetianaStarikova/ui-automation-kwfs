package com.kwri.auto.steps.kwriSupportTools;

import com.google.inject.Inject;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.User;
import com.kwri.auto.pages.kwriSupportTools.ChartOfAccountsPage;
import com.kwri.auto.ui.methods.Common;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

/**
 * The type Chart of Accounts tab steps.
 */
@InflateContext
public class ChartOfAccountsSteps {
    @Inject
    private ChartOfAccountsPage chartOfAccountsPage;
    @Inject
    private Common common;

    /**
     * User sees chart of accounts label.
     */
    @Then("user sees header with 'Chart of Accounts' label in the left upper side of Chart of Accounts page")
    public void userSeesChartOfAccountsLabel() {
        this.common.waitForPageToLoad();
        this.chartOfAccountsPage.verifyChartOfAccountsLabel();
    }

    /**
     * User sees search by account name input.
     */
    @Then("user sees search box is shown from the right side of \"Chart of Accounts\" label")
    public void userSeesSearchByAccountNameInput() {
        this.chartOfAccountsPage.verifyInputSearchByAccountNameIsDisplayed();
    }

    /**
     * User sees magnifying glass icon in the field.
     */
    @And("user sees magnifying glass icon in the 'Search' field")
    public void userSeesMagnifyingGlassIconInTheSearchField() {
        this.chartOfAccountsPage.verifyMagnifyingGlass();
    }

    /**
     * User add data in search field.
     *
     * @param searchCriteria the search criteria
     */
    @And("user adds in \"Search\" field data {string} of existing Account Names")
    public void userAddDataInSearchField(String searchCriteria) {
        this.chartOfAccountsPage.userEntersSearchCriteriaInSearchByAccountNameField(searchCriteria);
    }

    /**
     * User sees search for account names starts from first character.
     */
    @And("user sees search for Account names starts from the first entered character")
    public void userSeesSearchForAccountNamesStartsFromFirstCharacter() {
        this.chartOfAccountsPage.userSeesSearchStartsFromTheFirstEnteredCharacterForAccountNames();
    }

    /**
     * User verifies search result for account name.
     *
     * @param accountName the account name
     */
    @And("user sees list of accounts that matches entered account name {string} is shown")
    public void userVerifiesSearchResultForAccountName(String accountName) {
        this.chartOfAccountsPage.userSeesListOfAccountNamesThatMatchEnteredAccountName(accountName);
    }

    /**
     * User verifies no results found disclaimer.
     *
     * @param expectedMessage the expected message
     */
    @And("user sees in the middle of page {string} disclaimer for Account List")
    public void userVerifiesNoResultsFoundDisclaimer(String expectedMessage) {
        this.chartOfAccountsPage.userSeesNoResultsFoundMessage(expectedMessage);
    }

    /**
     * User verifies try different search disclaimer.
     */
    @And("user sees 'Please try a different search.' disclaimer for Account List")
    public void userVerifiesTryDifferentSearchDisclaimer() {
        this.chartOfAccountsPage.userSeesTryDifferentSearchDisclaimer();
    }

    /**
     * User verifies search by account name search placeholder.
     *
     * @param placeholder the placeholder
     */
    @And("user sees {string} placeholder is shown inside Chart of Accounts searchbox")
    public void userVerifiesSearchByAccountNameSearchPlaceholder(String placeholder) {
        this.chartOfAccountsPage.verifyPlaceholderForSearchByAccountName(placeholder);
    }

    /**
     * User verifies grid with list of all accounts.
     */
    @And("user sees grid with the list of all Accounts in KWRI with next columns")
    public void userVerifiesGridWithListOfAllAccounts() {
        this.chartOfAccountsPage.verifyListOfAccountsTableColumnHeaders();
    }

    /**
     * User verifies list accounts.
     *
     * @param user the user
     */
    @And("user {user} verifies list of Accounts in KWRI")
    public void userVerifiesListAccounts(User user) throws Exception {
        this.chartOfAccountsPage.verifyListOfAccounts(user);
    }
}
