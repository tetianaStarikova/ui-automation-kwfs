@KWRISupportToolsFinancialStatement @ui-financial-statement
Feature: KWRI Support Tools Financial Statement > Chart of Accounts

  Background:
    Given user navigates to home page
    And assign to "DemoMCAgent5001Kwuid" variable "573385" value

  @TRX-157006 @tmsLink=TRX-157006
  Scenario Outline: Verification of an ability to see 'Chart of Accounts' page default view by KWRI user with permissions
    Given user logs in as <user> user
    When user open "Support Tools" page using url
    And user opens "Chart of Accounts" page
    Then user sees header with 'Chart of Accounts' label in the left upper side of Chart of Accounts page
    And user sees search box is shown from the right side of "Chart of Accounts" label
    And user sees "Search by Account Name..." placeholder is shown inside Chart of Accounts searchbox
    And user sees grid with the list of all Accounts in KWRI with next columns
    And user <user> verifies list of Accounts in KWRI
    @QA
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
      | TEAMSBRAVOLEGAL       |
    @PROD
    Examples:
      | user          |
      | DOP5000       |
      | DMCBROKER5000 |

  @TRX-157072 @tmsLink=TRX-157072 @QA
  Scenario Outline: Verification of inability to see Support Tools> 'Financial Statement'>'Chart of Accounts' page by user w/o permissions
    Given user logs in as <user> user
    When user open Support Tools> 'Financial Statement'>'Chart of Accounts' page using url
    Then <step>
    Examples:
      | user                             | step                                               |
      | AVENGERS_MC_AGENT                | user is redirected to Command home page            |
      | AVENGERS_MC_MCA                  | user is redirected to Command home page            |
      | AVENGERSMCMARKETCENTERLEADER     | user is redirected to Command home page            |
      | AVENGERSREGOPERATIONSMANAGER     | user is redirected to Command home page            |
      | TEAMSBRAVOEXECUTIVE              | user is on Support Tools>Directory>Associates page |
      | TEAMSBRAVOANALYST                | user is on Support Tools>Directory>Associates page |
      | TEAMSBRAVOREGIONALDIVISIONLEADER | user is on Support Tools>Directory>Associates page |
      | TEAMSBRAVOMCANGEL                | user is on Support Tools>Directory>Associates page |

  @TRX-157071 @tmsLink=TRX-157071
  Scenario Outline: Verification of an ability to open Support Tools>'Financial Statement'>'Chart of Accounts' page by using direct link by KWRI user with permissions
    Given user logs in as <user> user
    When user open Support Tools> 'Financial Statement'>'Chart of Accounts' page using url
    Then user is on Support Tools>'Financial Statement'> 'Chart of Accounts' page
    @QA
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
      | TEAMSBRAVOLEGAL       |
    @PROD
    Examples:
      | user          |
      | DOP5000       |
      | DMCBROKER5000 |

  @TRX-156996 @tmsLink=TRX-156996 @TRX-156997 @tmsLink=TRX-156997 @issue=TRX-157079
  Scenario Outline: Verification of ability to search by Account Name in Support Tools>Financial Statement>Chart of Accounts page when matches found by user with KWRI role
    Given user logs in as <user> user
    When user open "Support Tools" page using url
    And user opens "Chart of Accounts" page
    Then user sees header with 'Chart of Accounts' label in the left upper side of Chart of Accounts page
    And user sees search box is shown from the right side of "Chart of Accounts" label
    And user sees "Search by Account Name..." placeholder is shown inside Chart of Accounts searchbox
    And user sees magnifying glass icon in the 'Search' field
    When user adds in "Search" field data "C" of existing Account Names
    Then user sees search for Account names starts from the first entered character
    And user sees list of accounts that matches entered account name "C" is shown
    When user adds in "Search" field data "Contribution/Gifts" of existing Account Names
    Then user sees search for Account names starts from the first entered character
    And user sees list of accounts that matches entered account name "Contribution/Gifts" is shown
    When user adds in "Search" field data "Salaries" of existing Account Names
    Then user sees search for Account names starts from the first entered character
    And user sees list of accounts that matches entered account name "Salaries" is shown
    When user adds in "Search" field data "RENT" of existing Account Names
    Then user sees search for Account names starts from the first entered character
    And user sees list of accounts that matches entered account name "Rent" is shown
    When user adds in "Search" field data "cash" of existing Account Names
    Then user sees search for Account names starts from the first entered character
    And user sees list of accounts that matches entered account name "cash" is shown
    When user adds in "Search" field data "   Supplies   " of existing Account Names
    Then user sees search for Account names starts from the first entered character
    And user sees list of accounts that matches entered account name "Supplies" is shown
    When user adds in "Search" field data "-" of existing Account Names
    Then user sees search for Account names starts from the first entered character
    And user sees list of accounts that matches entered account name "-" is shown
    When user adds in "Search" field data "(" of existing Account Names
    Then user sees search for Account names starts from the first entered character
    And user sees list of accounts that matches entered account name "(" is shown
    @QA
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
    @PROD
    Examples:
      | user          |
      | DMCBROKER5000 |

  @TRX-156999 @tmsLink=TRX-156999 @issue=TRX-157079
  Scenario Outline: Verification of ability to search by Account Name in Support Tools>Financial Statement>Chart of Accounts page when no matches found by user with KWRI role
    Given user logs in as <user> user
    When user open "Support Tools" page using url
    And user opens "Chart of Accounts" page
    Then user sees header with 'Chart of Accounts' label in the left upper side of Chart of Accounts page
    And user sees search box is shown from the right side of "Chart of Accounts" label
    And user sees "Search by Account Name..." placeholder is shown inside Chart of Accounts searchbox
    And user sees magnifying glass icon in the 'Search' field
    When user adds in "Search" field data "cv" of existing Account Names
    Then user sees in the middle of page "No Results Found" disclaimer for Account List
    And user sees 'Please try a different search.' disclaimer for Account List
    When user adds in "Search" field data "Franchise Costs" of existing Account Names
    Then user sees in the middle of page "No Results Found" disclaimer for Account List
    And user sees 'Please try a different search.' disclaimer for Account List
    When user adds in "Search" field data "2222 Costs" of existing Account Names
    Then user sees in the middle of page "No Results Found" disclaimer for Account List
    And user sees 'Please try a different search.' disclaimer for Account List
    When user adds in "Search" field data "88" of existing Account Names
    Then user sees in the middle of page "No Results Found" disclaimer for Account List
    And user sees 'Please try a different search.' disclaimer for Account List
    When user adds in "Search" field data "?!" of existing Account Names
    Then user sees in the middle of page "No Results Found" disclaimer for Account List
    And user sees 'Please try a different search.' disclaimer for Account List
    @QA
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
    @PROD
    Examples:
      | user          |
      | DMCBROKER5000 |