@KWFSAccountingApplet @ui-financial-statement
Feature: Financial Statement Accounting applet - Income Statement page

  Background:
    Given user navigates to home page

  @TRX-157358 @tmsLink=TRX-157358 @TRX-157359 @tmsLink=TRX-157359
  Scenario Outline: Verification of an ability to see "Income Statement" page default state in CommandMC>Accounting by users with permissions
    Given user logs in as <user> user
    And user <user> get current transaction period for organization <orgId> and assign to "transactionPeriodForMC7" variable
    And  user is on Command MC > Administration applet
    And user select "<accountName>" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees "Select Month" dropdown in top right side of page with downward arrow
    And user sees Income Statement data is displayed in table with columns
    And user <user> sees full list of accounts for Income Statement for orgId <orgId> for period "{(transactionPeriodForMC7)}"
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

  @TRX-157361 @tmsLink=TRX-157361 @QA
  Scenario: Verification of an ability to see "Income Statement" page in CommandMC>Accounting by users with permissions (several accounts)
    Given user logs in as TEAMSBRAVOOP user
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_3 and assign to "transactionPeriodForMC3" variable
    And user assign to "previousMonthPeriodMC7" variable previous month period from "{(transactionPeriodForMC7)}"
    And user assign to "previousMonthPeriodMC3" variable previous month period from "{(transactionPeriodForMC3)}"
    When user open 'Accounting' page using url
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user selects "Income Statement" tab
    Then user sees "Select Month" dropdown in top right side of page with downward arrow
    And user sees Income Statement data is displayed in table with columns
     # Current month
    And user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user select "KWFS Test TeamsBravo 3" account in shell
    When user selects "Income Statement" tab
    Then user sees "Select Month" dropdown in top right side of page with downward arrow
    And user sees Income Statement data is displayed in table with columns
     # Current month
    And user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(transactionPeriodForMC3)}"
    # Past/Future month
    When user selects month "{(previousMonthPeriodMC3)}" in "Select Month" dropdown
    Then user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(previousMonthPeriodMC3)}"
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user selects "Income Statement" tab
    Then user sees "Select Month" dropdown in top right side of page with downward arrow
    And user sees Income Statement data is displayed in table with columns
    And user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"

  @TRX-157362 @tmsLink=TRX-157362 @QA
  Scenario:Verification of inability to see "Income Statement" page in CommandMC>Accounting (account w/o permissions)
    Given user logs in as TEAMSBRAVOAMCA user
    And user TEAMSBRAVOAMCA get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    And  user is on Command MC > Administration applet
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees "Select Month" dropdown in top right side of page with downward arrow
    And user sees Income Statement data is displayed in table with columns
    And user TEAMSBRAVOAMCA sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user select "Aruba Market Center for Automation Testing" account in shell
    Then user is redirected to CommandMC main page

  @TRX-158535 @tmsLink=TRX-158535 @QA
  Scenario: Verification of an ability to see Income Statement data for past month in CommandMC>Accounting>"Income Statement" page by users with permissions
    Given user logs in as TEAMSBRAVOOP user
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    And user assign to "previousMonthPeriodMC7" variable previous month period from "{(transactionPeriodForMC7)}"
    When user open 'Accounting' page using url
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees "Select Month" dropdown in top right side of page with downward arrow
    When user selects month "{(previousMonthPeriodMC7)}" in "Select Month" dropdown
    And user sees Income Statement data is displayed in table with columns
    Then user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(previousMonthPeriodMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(previousMonthPeriodMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Commission Paid Out" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(previousMonthPeriodMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Company Dollar" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(previousMonthPeriodMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Grand Total Operating Expenses" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(previousMonthPeriodMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Inside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(previousMonthPeriodMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Outside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(previousMonthPeriodMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Other Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(previousMonthPeriodMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total KW Approved Costs" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(previousMonthPeriodMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "KW Owner Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(previousMonthPeriodMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Net Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(previousMonthPeriodMC7)}"

  @TRX-157363 @tmsLink=TRX-157363 @QA
  Scenario: Verification of an ability to see Income Statement data for current month in CommandMC>Accounting>"Income Statement" page by users with permissions
    Given user logs in as TEAMSBRAVOOP user
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    When user open 'Accounting' page using url
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees "Select Month" dropdown in top right side of page with downward arrow
    And user sees Income Statement data is displayed in table with columns
    Then user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Commission Paid Out" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Company Dollar" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Grand Total Operating Expenses" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Inside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Outside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Other Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total KW Approved Costs" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "KW Owner Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Net Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"

  @TRX-158537 @tmsLink=TRX-158537 @QA
  Scenario: Verification of inability to see Income Statement data for future month in CommandMC>Accounting>"Income Statement" page by users with permissions
    Given user logs in as TEAMSBRAVOOP user
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_3 and assign to "transactionPeriod" variable
    And user assign to "futureMonthPeriod" variable next month period from "{(transactionPeriod)}"
    When user open 'Accounting' page using url
    And user select "KWFS Test TeamsBravo 3" account in shell
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees "Select Month" dropdown in top right side of page with downward arrow
    When user selects month "{(futureMonthPeriod)}" in "Select Month" dropdown
    Then user sees in the middle of page clock icon
    And user sees in the middle of page "Future Month" label
    And user sees in the middle of page "No data is available yet. Select a current or past month to see figures." disclaimer

  @TRX-158733 @tmsLink=TRX-158733 @TRX-158736 @tmsLink=TRX-158736 @e2e @QA
  Scenario: Verification of an ability to submit Income Statement data for current month in CommandMC>Accounting>"Income Statement" page by users with permissions
    Given user logs in as TEAMSBRAVOOP user
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    When user open 'Accounting' page using url
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts2/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees "Save New Data" button is disabled false
    When user clicks on enabled "Save New Data" button
    Then user sees "Save New Data" button is disabled true
    And user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Commission Paid Out" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Company Dollar" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Grand Total Operating Expenses" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Inside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Outside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Other Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total KW Approved Costs" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "KW Owner Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Net Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    #resubmit data
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts2/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees "Save New Data" button is disabled false
    When user clicks on enabled "Save New Data" button
    Then user sees "Save New Data" button is disabled true
    And user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Commission Paid Out" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Company Dollar" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Grand Total Operating Expenses" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Inside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Outside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Other Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total KW Approved Costs" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "KW Owner Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Net Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"

  @TRX-158734 @tmsLink=TRX-158734 @e2e @QA
  Scenario: Verification of inability to submit Income Statement data for current month in CommandMC>Accounting>"Income Statement" page by users with permissions when data is out of balance
    Given user logs in as TEAMSBRAVOOP user
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    When user open 'Accounting' page using url
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "outOfBalanceBudget/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees "Save New Data" button is disabled true
    And user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Commission Paid Out" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Company Dollar" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Grand Total Operating Expenses" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Inside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Outside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Other Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total KW Approved Costs" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "KW Owner Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Net Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"

    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts2/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees "Save New Data" button is disabled false
    And user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Commission Paid Out" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Company Dollar" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Grand Total Operating Expenses" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Inside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Outside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Other Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total KW Approved Costs" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "KW Owner Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Net Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForMC7)}"
    When user clicks on enabled "Save New Data" button
    Then user sees "Save New Data" button is disabled true

  @TRX-160409 @tmsLink=TRX-160409 @QA
  Scenario: Verification of inability to see Income Statement data for current month in CommandMC>Accounting>"Income Statement" page when no data uploaded from bookkeeping system yet
    Given user logs in as TEAMSBRAVONAMCA user
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user select "KWFS Test TeamsBravo 1" account in shell
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees in the middle of page clock icon
    And user sees 'No data is available yet' disclaimer on Accounting page
    And user sees 'Please upload financial data to proceed.' disclaimer on the Accounting page
    And user sees disabled true button to print out Balance Sheet for current financial month

  @TRX-165556 @tmsLink=TRX-165556 @e2e @QA
  Scenario: Verification of inability to submit Income Statement data for current month in CommandMC>Accounting>"Income Statement" page when YTD Net Profit (Loss) does not match Current Period Profit
    Given user logs in as TEAMSBRAVOOP user
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForAustinSW" variable
    When user open 'Accounting' page using url
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "validationFailedForYTDActualNetProfit/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees "Save New Data" button is disabled true
    And user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Commission Paid Out" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Company Dollar" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Grand Total Operating Expenses" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Inside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Outside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Other Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total KW Approved Costs" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "KW Owner Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Net Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user sees true Net Profit Banner
    And user sees "YTD Net Profit Loss on the Income Statement does not match Current Period Profit Loss on the Balance Sheet" banner notification is displayed
    And user sees a red banner displayed notifying about YTD Net Profit does not match Current Period Profit with forbidden icon and with Close icon
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts2/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees "Save New Data" button is disabled false
    And user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Commission Paid Out" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Company Dollar" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Grand Total Operating Expenses" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Inside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Outside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Other Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total KW Approved Costs" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "KW Owner Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Net Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    When user clicks on enabled "Save New Data" button
    Then user sees "Save New Data" button is disabled true
    And user sees false Net Profit Banner

  @TRX-165558 @tmsLink=TRX-165558 @e2e @QA
  Scenario: Verification of inability to submit Income Statement data for current month in CommandMC>Accounting>"Income Statement" page when YTD Net Profit (Loss) does not match Current Period Profit and data is out of balance
    Given user logs in as TEAMSBRAVOOP user
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForAustinSW" variable
    When user open 'Accounting' page using url
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "bothValidationFailed/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees "Save New Data" button is disabled true
    And user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Commission Paid Out" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Company Dollar" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Grand Total Operating Expenses" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Inside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Outside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Other Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total KW Approved Costs" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "KW Owner Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Net Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user sees true Net Profit Banner
    And user sees "YTD Net Profit Loss on the Income Statement does not match Current Period Profit Loss on the Balance Sheet" banner notification is displayed
    And user sees a red banner displayed notifying about YTD Net Profit does not match Current Period Profit with forbidden icon and with Close icon
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts2/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees "Save New Data" button is disabled false
    And user TEAMSBRAVOOP sees full list of accounts for Income Statement for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Commission Paid Out" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Company Dollar" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Grand Total Operating Expenses" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Inside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Outside Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total Other Income" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Total KW Approved Costs" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "KW Owner Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    And user TEAMSBRAVOOP sees next calculated formula for current month: "Net Profit (Loss)" in group for org KWFS_TEST_TEAMS_BRAVO_7 in "{(transactionPeriodForAustinSW)}"
    When user clicks on enabled "Save New Data" button
    Then user sees "Save New Data" button is disabled true
    And user sees false Net Profit Banner

  @TRX-166005 @tmsLink=TRX-166005 @QA
  Scenario: Verification of inability to see banner on Income Statement for past month in CommandMC>Accounting>"Income Statement" page when YTD Net Profit (Loss) does not match Current Period Profit
    Given user logs in as TEAMSBRAVOOP user
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodMC7" variable
    And user assign to "previousMonthPeriodMC7" variable previous month period from "{(transactionPeriodMC7)}"
    When user open 'Accounting' page using url
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "validationFailedForYTDActualNetProfit/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees "Save New Data" button is disabled true
    And user sees true Net Profit Banner
    When user selects month "{(previousMonthPeriodMC7)}" in "Select Month" dropdown
    And user sees Income Statement data is displayed in table with columns
    And user sees false Net Profit Banner
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    And user sees false Net Profit Banner

  @TRX-166006 @tmsLink=TRX-166006 @QA
  Scenario: Verification of inability to see banner on Income Statement for past month in CommandMC>Accounting>"Income Statement" page when YTD Net Profit (Loss) does not match Current Period Profit and data is out of balance
    Given user logs in as TEAMSBRAVOOP user
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    And user assign to "previousMonthPeriodMC7" variable previous month period from "{(transactionPeriodForMC7)}"
    When user open 'Accounting' page using url
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    And user sees "Balance Sheet" page is open by default
    When user selects "Income Statement" tab
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "bothValidationFailed/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees "Save New Data" button is disabled true
    And user sees true Net Profit Banner
    When user selects month "{(previousMonthPeriodMC7)}" in "Select Month" dropdown
    And user sees Income Statement data is displayed in table with columns
    And user sees false Net Profit Banner
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    And user sees false Net Profit Banner
