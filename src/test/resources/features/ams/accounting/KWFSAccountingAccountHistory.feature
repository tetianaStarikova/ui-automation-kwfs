@KWFSAccountingApplet @ui-financial-statement
Feature: Financial Statement Accounting applet - "Account History" page

  Background:
    Given user navigates to home page

  @TRX-157721 @tmsLink=TRX-157721 @TRX-157722 @tmsLink=TRX-157722
  Scenario Outline: Verification of an ability to see "Account History" page default state in CommandMC>Accounting by users with permissions
    Given user logs in as <user> user
    And  user is on Command MC > Administration applet
    And user select "<accountName>" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    And user selects "Account History" tab
    And user sees list of all accounts from bookkeeping system with the following info
    And user sees list of accounts is sorted by "ACCT #" in ASC order by default
    And user <user> sees list of all accounts history for an org <orgId>
    @QA
    Examples:
      | user                 | accountName            | orgId                   |
      | TEAMSBRAVOMCA        | KWFS Test TeamsBravo 3 | KWFS_TEST_TEAMS_BRAVO_3 |
      | TEAMSBRAVOOP         | KWFS Test TeamsBravo 3 | KWFS_TEST_TEAMS_BRAVO_3 |
      | TEAMSBRAVOTEAMLEADER | KWFS Test TeamsBravo 3 | KWFS_TEST_TEAMS_BRAVO_3 |
      | TEAMSBRAVOAMCA       | KWFS Test TeamsBravo 3 | KWFS_TEST_TEAMS_BRAVO_3 |
    @PROD
    Examples:
      | user  | accountName        | orgId                 |
      | QAMCA | Demo Market Center | DEMO_MARKET_CENTER_ID |

  @TRX-157723 @tmsLink=TRX-157723 @QA
  Scenario: Verification of an ability to see "Account History" page in CommandMC>Accounting by users with permissions (several accounts)
    Given user logs in as TEAMSBRAVONAMCA user
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user select "Test NA MC" account in shell
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    And user selects "Account History" tab
    And user sees list of all accounts from bookkeeping system with the following info
    And user sees list of accounts is sorted by "ACCT #" in ASC order by default
    And user TEAMSBRAVONAMCA sees list of all accounts history for an org TEST_NA_MC
    When user select "Austin SW" account in shell
    Then user sees list of all accounts from bookkeeping system with the following info
    And user sees list of accounts is sorted by "ACCT #" in ASC order by default
    And user TEAMSBRAVONAMCA sees list of all accounts history for an org AUSTIN_SW

  @TRX-157724 @tmsLink=TRX-157724 @QA
  Scenario: Verification of inability to see "Account History" page in CommandMC>Accounting (account w/o permissions)
    Given user logs in as TEAMSBRAVOAMCA user
    And  user is on Command MC > Administration applet
    And user select "KWFS Test TeamsBravo 3" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    And user selects "Account History" tab
    And user sees list of all accounts from bookkeeping system with the following info
    And user sees list of accounts is sorted by "ACCT #" in ASC order by default
    And user TEAMSBRAVOAMCA sees list of all accounts history for an org KWFS_TEST_TEAMS_BRAVO_3
    And user select "Aruba Market Center for Automation Testing" account in shell
    Then user is redirected to CommandMC main page

  @TRX-160411 @tmsLink=TRX-160411 @QA
  Scenario: Verification of inability to see Account History data in CommandMC>Accounting>"Account History" tab when no data uploaded from bookkeeping system yet
    Given user logs in as TEAMSBRAVONAMCA user
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user select "KWFS Test TeamsBravo 1" account in shell
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    When user selects "Account History" tab
    Then user sees 'No data is available yet' disclaimer on Accounting page
    And user sees 'Please upload financial data to proceed.' disclaimer on the Accounting page
