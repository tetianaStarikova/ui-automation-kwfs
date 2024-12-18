@KWFSAccountingApplet @ui-financial-statement
Feature: Financial Statement Accounting applet - Balance Sheet page

  Background:
    Given user navigates to home page

  @TRX-156316 @tmsLink=TRX-156316 @TRX-157083 @tmsLink=TRX-157083 @TRX-157090 @tmsLink=TRX-157090 @TRX-157082 @tmsLink=TRX-157082 @QA
  Scenario Outline: Verification of an ability to see "Balance Sheet" page default state in CommandMC>Accounting by users with permissions
    Given user logs in as <user> user
    And user <user> get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_3 and assign to "transactionPeriodForMC" variable
    And  user is on Command MC > Administration applet
    And user select "KWFS Test TeamsBravo 3" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    And user sees Balance Sheet data is displayed in next two sections: "Assets" section from the left side "Liabilities + Equity" section from the right side
    And user sees "Current Month" label in the header of every section
    And user <user> sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(transactionPeriodForMC)}"
    And user <user> sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(transactionPeriodForMC)}"
    And user <user> sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(transactionPeriodForMC)}"
    And user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    And user sees next calculated formula for current month "Accounts Receivable - Total" in "Current Assets" group
    And user sees next calculated formula for current month "Total Current Assets" in "Current Assets" group
    And user sees next calculated formula for current month "Total Fixed Assets" in "Fixed Assets" group
    And user sees next calculated formula for current month "Fixed Assets (Less Depr, Amount)" in "Fixed Assets" group
    And user sees next calculated formula for current month "Total Other Assets" in "Other Assets" group
    And user sees next calculated formula for current month "Total Assets" in "Other Assets" group
    And user sees footer in the bottom of page displayed with: "Total Assets", "Total Liabilities + Equity"
    Examples:
      | user                 |
      | TEAMSBRAVOTEAMLEADER |
      | TEAMSBRAVOAMCA       |

  @TRX-157086 @tmsLink=TRX-157086 @QA
  Scenario Outline:  Verification of an ability to see "Liabilities + Equity" section for current month in CommandMC>Accounting>"Balance Sheet" page by users with permissions
    Given user logs in as <user> user
    And user <user> get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_3 and assign to "transactionPeriodForMC" variable
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user select "KWFS Test TeamsBravo 3" account in shell
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    And user sees Balance Sheet data is displayed in next two sections: "Assets" section from the left side "Liabilities + Equity" section from the right side
    And user sees "Current Month" label in the header of every section
    And user <user> sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(transactionPeriodForMC)}"
    And user <user> sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(transactionPeriodForMC)}"
    And user <user> sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(transactionPeriodForMC)}"
    And user sees next calculated formula for current month "Total Current Liabilities" in "Current Liabilities" group
    And user sees next calculated formula for current month "Total Long-Term Liabilities" in "Long-Term Liabilities" group
    And user sees next calculated formula for current month "Total Liabilities" in "Long-Term Liabilities" group
    And user sees next calculated formula for current month "Total Equity" in "Equity" group
    And user sees next calculated formula for current month "Total Liabilities And Equity" in "Equity" group
    And user sees footer in the bottom of page displayed with: "Total Assets", "Total Liabilities + Equity"
    Examples:
      | user          |
      | TEAMSBRAVOMCA |
      | TEAMSBRAVOOP  |

  @TRX-157713 @tmsLink=TRX-157713 @QA
  Scenario Outline: Verification of an ability to see "Assets" section for past month in CommandMC>Accounting>"Balance Sheet" page by users with permissions
    Given user logs in as <user> user
    And user <user> get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_3 and assign to "transactionPeriodForDemoMC" variable
    And user assign to "previousMonthPeriodDemoMC" variable previous month period from "{(transactionPeriodForDemoMC)}"
    And  user is on Command MC > Administration applet
    And user select "KWFS Test TeamsBravo 3" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    When user selects month "{(previousMonthPeriodDemoMC)}" in "Select Month" dropdown
    And user sees Balance Sheet data is displayed in next two sections: "Assets" section from the left side "Liabilities + Equity" section from the right side
    And user <user> sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(previousMonthPeriodDemoMC)}"
    And user <user> sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(previousMonthPeriodDemoMC)}"
    And user <user> sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(previousMonthPeriodDemoMC)}"
    And user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    And user sees next calculated formula for current month "Accounts Receivable - Total" in "Current Assets" group
    And user sees next calculated formula for current month "Total Current Assets" in "Current Assets" group
    And user sees next calculated formula for current month "Total Fixed Assets" in "Fixed Assets" group
    And user sees next calculated formula for current month "Fixed Assets (Less Depr, Amount)" in "Fixed Assets" group
    And user sees next calculated formula for current month "Total Other Assets" in "Other Assets" group
    And user sees next calculated formula for current month "Total Assets" in "Other Assets" group
    And footer is not displayed for any past month
    Examples:
      | user                 |
      | TEAMSBRAVOMCA        |
      | TEAMSBRAVOOP         |
      | TEAMSBRAVOTEAMLEADER |
      | TEAMSBRAVOAMCA       |

  @TRX-157714 @tmsLink=TRX-157714 @QA
  Scenario Outline: Verification of an ability to see "Liabilities + Equity" section for past month in CommandMC>Accounting>"Balance Sheet" page by users with permissions
    Given user logs in as <user> user
    And user <user> get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_3 and assign to "transactionPeriodForDemoMC" variable
    And user assign to "previousMonthPeriodDemoMC" variable previous month period from "{(transactionPeriodForDemoMC)}"
    And  user is on Command MC > Administration applet
    And user select "KWFS Test TeamsBravo 3" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    When user selects month "{(previousMonthPeriodDemoMC)}" in "Select Month" dropdown
    And user sees Balance Sheet data is displayed in next two sections: "Assets" section from the left side "Liabilities + Equity" section from the right side
    And user sees "Current Month" label in the header of every section
    And user <user> sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(previousMonthPeriodDemoMC)}"
    And user <user> sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(previousMonthPeriodDemoMC)}"
    And user <user> sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(previousMonthPeriodDemoMC)}"
    And user sees next calculated formula for current month "Total Current Liabilities" in "Current Liabilities" group
    And user sees next calculated formula for current month "Total Long-Term Liabilities" in "Long-Term Liabilities" group
    And user sees next calculated formula for current month "Total Liabilities" in "Long-Term Liabilities" group
    And user sees next calculated formula for current month "Total Equity" in "Equity" group
    And user sees next calculated formula for current month "Total Liabilities And Equity" in "Equity" group
    And footer is not displayed for any past month
    Examples:
      | user                 |
      | TEAMSBRAVOMCA        |
      | TEAMSBRAVOOP         |
      | TEAMSBRAVOTEAMLEADER |
      | TEAMSBRAVOAMCA       |

  @TRX-157716 @tmsLink=TRX-157716 @QA
  Scenario: Verification of inability to see "Assets" /"Liabilities + Equity" section for future month in CommandMC>Accounting>"Balance Sheet" page by users with permissions
    Given user logs in as TEAMSBRAVOAMCA user
    And user TEAMSBRAVOAMCA get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriod" variable
    And user assign to "futureMonthPeriod" variable next month period from "{(transactionPeriod)}"
    And  user is on Command MC > Administration applet
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    When user selects month "{(futureMonthPeriod)}" in "Select Month" dropdown
    Then user sees in the middle of page clock icon
    And user sees in the middle of page "Future Month" label
    And user sees in the middle of page "No data is available yet. Select a current or past month to see figures." disclaimer

  @TRX-157091 @tmsLink=TRX-157091 @QA
  Scenario: Verification of an ability to see "Balance Sheet" page in CommandMC>Accounting by users with permissions (several accounts)
    Given user logs in as TEAMSBRAVOOP user
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    And user TEAMSBRAVOOP get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_3 and assign to "transactionPeriodForMC3" variable
    And user assign to "previousMonthPeriodMC7" variable previous month period from "{(transactionPeriodForMC7)}"
    And user assign to "previousMonthPeriodMC3" variable previous month period from "{(transactionPeriodForMC3)}"
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user select "KWFS Test TeamsBravo 7" account in shell
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    And user sees Balance Sheet data is displayed in next two sections: "Assets" section from the left side "Liabilities + Equity" section from the right side
    # Current month
    And user sees "Current Month" label in the header of every section
    And user TEAMSBRAVOOP sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user select "KWFS Test TeamsBravo 3" account in shell
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    And user sees Balance Sheet data is displayed in next two sections: "Assets" section from the left side "Liabilities + Equity" section from the right side
    And user sees "Current Month" label in the header of every section
    And user TEAMSBRAVOOP sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(transactionPeriodForMC3)}"
    And user TEAMSBRAVOOP sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(transactionPeriodForMC3)}"
    And user TEAMSBRAVOOP sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(transactionPeriodForMC3)}"
    # Past/Future month
    When user selects month "{(previousMonthPeriodMC3)}" in "Select Month" dropdown
    Then user TEAMSBRAVOOP sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(previousMonthPeriodMC3)}"
    And user TEAMSBRAVOOP sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(previousMonthPeriodMC3)}"
    And user TEAMSBRAVOOP sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_3 for period "{(previousMonthPeriodMC3)}"
    And user select "KWFS Test TeamsBravo 7" account in shell
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    And user sees Balance Sheet data is displayed in next two sections: "Assets" section from the left side "Liabilities + Equity" section from the right side
    # Current month
    And user sees "Current Month" label in the header of every section
    And user TEAMSBRAVOOP sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOOP sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"

  @TRX-157093 @tmsLink=TRX-157093 @QA
  Scenario:Verification of inability to see "Balance Sheet" page in CommandMC>Accounting (account w/o permissions)
    Given user logs in as TEAMSBRAVOAMCA user
    And user TEAMSBRAVOAMCA get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC" variable
    And  user is on Command MC > Administration applet
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    And user sees Balance Sheet data is displayed in next two sections: "Assets" section from the left side "Liabilities + Equity" section from the right side
    And user sees "Current Month" label in the header of every section
    And user TEAMSBRAVOAMCA sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC)}"
    And user TEAMSBRAVOAMCA sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC)}"
    And user TEAMSBRAVOAMCA sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC)}"
    And user select "Aruba Market Center for Automation Testing" account in shell
    Then user is redirected to CommandMC main page

  @TRX-158308 @tmsLink=TRX-158308 @QA
  Scenario: Verification of an ability to see disabled button to print out Balance Sheet for future financial month on CommandMC>Accounting> "Balance Sheet" tab by users with permissions
    Given user logs in as TEAMSBRAVOMCA user
    And user TEAMSBRAVOMCA get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_3 and assign to "transactionPeriod" variable
    And user assign to "futureMonthPeriod" variable next month period from "{(transactionPeriod)}"
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user select "KWFS Test TeamsBravo 3" account in shell
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    When user selects month "{(futureMonthPeriod)}" in "Select Month" dropdown
    Then user sees in the middle of page clock icon
    And user sees disabled true button to print out Balance Sheet for current financial month

  @TRX-158551 @tmsLink=TRX-158551 @QA
  Scenario: Verification of an ability to see "Your Security Key" modal default state in CommandMC>Accounting by users with permissions
    Given user logs in as TEAMSBRAVOMCA user
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 3" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees disabled false "View Security Key" button in the top right corner of page
    When user clicks on enabled "View Security Key" button in the top right corner of page
    Then user sees "Your Security Key" modal is open
    And user TEAMSBRAVOMCA sees generated security key for MC KWFS_TEST_TEAMS_BRAVO_3 with Accountedge 1 as accounting vendor
    And user sees disabled false "Copy Key" button and disabled false "Close" in the bottom of modal

  @TRX-158554 @tmsLink=TRX-158554 @QA
  Scenario: Verification of an ability to see generated security key for MC w/o security key in CommandMC>Accounting>"Your Security Key" modal by users with permissions in case of MC uses Accountedge as accounting vendor
    Given user logs in as TEAMSBRAVOMCA user
    And user authorized as AVENGERSSYSTEMADMIN user
    And user AVENGERSSYSTEMADMIN update accounting vendor to "2" for organization KWFS_TEST_TEAMS_BRAVO_3
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 3" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees "View Security Key" button is displayed false in the top right corner of page
    When  user AVENGERSSYSTEMADMIN update accounting vendor to "1" for organization KWFS_TEST_TEAMS_BRAVO_3
    And user refresh the page
    Then user sees disabled false "View Security Key" button in the top right corner of page
    When user clicks on enabled "View Security Key" button in the top right corner of page
    Then user sees "Your Security Key" modal is open
    And user AVENGERSSYSTEMADMIN sees generated security key for MC KWFS_TEST_TEAMS_BRAVO_3 with Accountedge 1 as accounting vendor
    And user sees disabled false "Copy Key" button and disabled false "Close" in the bottom of modal

  @TRX-158605 @tmsLink=TRX-158605 @QA
  Scenario: Verification of an ability to browse file for upload in CommandMC>Accounting> "Upload Files" modal
    Given user logs in as TEAMSBRAVOMCA user
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    And user sees "Please upload the accthist1.xlsx file" disclaimer for Upload Files modal
    And user sees "Upload" label on Upload Files modal
    And user sees empty enabled "Upload" field with "Drop files to upload..." placeholder and enabled "Browse" button
    And user sees disabled false 'Cancel' button for Upload Files modal
    And user sees disabled true 'Upload' button for Upload Files modal

  @TRX-158601 @tmsLink=TRX-158601 @TRX-158603 @tmsLink=TRX-158603 @TRX-158605 @tmsLink=TRX-158605 @TRX-158606 @tmsLink=TRX-158606 @QA
  Scenario: Verification of an ability to see "Upload Files" modal default state in CommandMC>Accounting by users with permissions
    Given user logs in as TEAMSBRAVOMCA user
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    And user sees "Please upload the accthist1.xlsx file" disclaimer for Upload Files modal
    And user sees "Upload" label on Upload Files modal
    And user sees empty enabled "Upload" field with "Drop files to upload..." placeholder and enabled "Browse" button
    And user sees disabled false 'Cancel' button for Upload Files modal
    And user sees disabled true 'Upload' button for Upload Files modal
    When user selects "allAccounts/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    And user sees disabled false 'Upload' button for Upload Files modal
    When user clicks on "Delete" button on Upload Files modal
    Then user sees empty enabled "Upload" field with "Drop files to upload..." placeholder and enabled "Browse" button
    And user sees disabled false 'Cancel' button for Upload Files modal
    And user sees disabled true 'Upload' button for Upload Files modal

  @TRX-158727 @tmsLink=TRX-158727 @TRX-158732 @tmsLink=TRX-158732 @TRX-158618 @tmsLink=TRX-158618 @TRX-158612 @tmsLink=TRX-158612 @e2e @QA
  Scenario: Verification of an ability to submit Balance Sheet for current month in CommandMC>Accounting>"Balance Sheet" page by users with permissions
    Given user logs in as TEAMSBRAVOMCA user
    And user TEAMSBRAVOMCA get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts2/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    And user sees next calculated formula for current month "Accounts Receivable - Total" in "Current Assets" group
    And user sees next calculated formula for current month "Total Current Assets" in "Current Assets" group
    And user sees next calculated formula for current month "Total Fixed Assets" in "Fixed Assets" group
    And user sees next calculated formula for current month "Fixed Assets (Less Depr, Amount)" in "Fixed Assets" group
    And user sees next calculated formula for current month "Total Other Assets" in "Other Assets" group
    And user sees next calculated formula for current month "Total Assets" in "Other Assets" group
    And user TEAMSBRAVOMCA sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user sees footer in the bottom of page displayed with: "Total Assets", "Total Liabilities + Equity"
    When user clicks on enabled "Save New Data" button
    Then user sees "Save New Data" button is disabled true
    #resubmit data
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts2/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    And user sees next calculated formula for current month "Accounts Receivable - Total" in "Current Assets" group
    And user sees next calculated formula for current month "Total Current Assets" in "Current Assets" group
    And user sees next calculated formula for current month "Total Fixed Assets" in "Fixed Assets" group
    And user sees next calculated formula for current month "Fixed Assets (Less Depr, Amount)" in "Fixed Assets" group
    And user sees next calculated formula for current month "Total Other Assets" in "Other Assets" group
    And user sees next calculated formula for current month "Total Assets" in "Other Assets" group
    And user TEAMSBRAVOMCA sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user sees footer in the bottom of page displayed with: "Total Assets", "Total Liabilities + Equity"
    When user clicks on enabled "Save New Data" button
    Then user sees "Save New Data" button is disabled true

  @TRX-158730 @tmsLink=TRX-158730 @e2e @QA
  Scenario: Verification of inability to submit Balance Sheet for current month in CommandMC>Accounting>"Balance Sheet" page by users with permissions when data is out of balance
    Given user logs in as TEAMSBRAVOMCA user
    And user TEAMSBRAVOMCA get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "outOfBalanceBudget/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    And user sees next calculated formula for current month "Accounts Receivable - Total" in "Current Assets" group
    And user sees next calculated formula for current month "Total Current Assets" in "Current Assets" group
    And user sees next calculated formula for current month "Total Fixed Assets" in "Fixed Assets" group
    And user sees next calculated formula for current month "Fixed Assets (Less Depr, Amount)" in "Fixed Assets" group
    And user sees next calculated formula for current month "Total Other Assets" in "Other Assets" group
    And user sees next calculated formula for current month "Total Assets" in "Other Assets" group
    And user TEAMSBRAVOMCA sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user sees footer in the bottom of page displayed with: "Total Assets", "Total Liabilities + Equity"
    Then user sees "Save New Data" button is disabled true
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts2/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    And user sees next calculated formula for current month "Accounts Receivable - Total" in "Current Assets" group
    And user sees next calculated formula for current month "Total Current Assets" in "Current Assets" group
    And user sees next calculated formula for current month "Total Fixed Assets" in "Fixed Assets" group
    And user sees next calculated formula for current month "Fixed Assets (Less Depr, Amount)" in "Fixed Assets" group
    And user sees next calculated formula for current month "Total Other Assets" in "Other Assets" group
    And user sees next calculated formula for current month "Total Assets" in "Other Assets" group
    And user TEAMSBRAVOMCA sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user sees footer in the bottom of page displayed with: "Total Assets", "Total Liabilities + Equity"
    When user clicks on enabled "Save New Data" button
    Then user sees "Save New Data" button is disabled true

  @TRX-160408 @tmsLink=TRX-160408 @QA
  Scenario: Verification of inability to see Balance Sheet data for current month in CommandMC>Accounting>"Balance Sheet" page when no data uploaded from bookkeeping system yet
    Given user logs in as TEAMSBRAVONAMCA user
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user select "KWFS Test TeamsBravo 1" account in shell
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    Then user sees in the middle of page clock icon
    And user sees 'No data is available yet' disclaimer on Accounting page
    And user sees 'Please upload financial data to proceed.' disclaimer on the Accounting page
    And user sees disabled true button to print out Balance Sheet for current financial month

  @TRX-165557 @tmsLink=TRX-165557 @e2e @QA
  Scenario: Verification of inability to submit Balance Sheet for current month in CommandMC>Accounting>"Balance Sheet" page when YTD Net Profit (Loss) does not match Current Period Profit and data is out of balance
    Given user logs in as TEAMSBRAVOMCA user
    And user TEAMSBRAVOMCA get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "bothValidationFailed/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    And user sees next calculated formula for current month "Accounts Receivable - Total" in "Current Assets" group
    And user sees next calculated formula for current month "Total Current Assets" in "Current Assets" group
    And user sees next calculated formula for current month "Total Fixed Assets" in "Fixed Assets" group
    And user sees next calculated formula for current month "Fixed Assets (Less Depr, Amount)" in "Fixed Assets" group
    And user sees next calculated formula for current month "Total Other Assets" in "Other Assets" group
    And user sees next calculated formula for current month "Total Assets" in "Other Assets" group
    And user TEAMSBRAVOMCA sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user sees footer in the bottom of page displayed with: "Total Assets", "Total Liabilities + Equity"
    Then user sees "Save New Data" button is disabled true
    And user sees true Net Profit Banner
    And user sees "YTD Net Profit Loss on the Income Statement does not match Current Period Profit Loss on the Balance Sheet" banner notification is displayed
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts2/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    And user sees next calculated formula for current month "Accounts Receivable - Total" in "Current Assets" group
    And user sees next calculated formula for current month "Total Current Assets" in "Current Assets" group
    And user sees next calculated formula for current month "Total Fixed Assets" in "Fixed Assets" group
    And user sees next calculated formula for current month "Fixed Assets (Less Depr, Amount)" in "Fixed Assets" group
    And user sees next calculated formula for current month "Total Other Assets" in "Other Assets" group
    And user sees next calculated formula for current month "Total Assets" in "Other Assets" group
    And user TEAMSBRAVOMCA sees full list of accounts for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts value for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user TEAMSBRAVOMCA sees full list of accounts range for every section for orgId KWFS_TEST_TEAMS_BRAVO_7 for period "{(transactionPeriodForMC7)}"
    And user sees footer in the bottom of page displayed with: "Total Assets", "Total Liabilities + Equity"
    When user clicks on enabled "Save New Data" button
    Then user sees "Save New Data" button is disabled true
    And user sees false Net Profit Banner

  @TRX-165559 @tmsLink=TRX-165559 @TRX-165555 @tmsLink=TRX-165555 @e2e @QA
  Scenario: Verification of an ability to see banner in CommandMC>Accounting>Balance Sheet/Income Statement page when YTD Net Profit (Loss) does not match Current Period Profit
    Given user logs in as TEAMSBRAVOMCA user
    And user TEAMSBRAVOMCA get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForMC7" variable
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "validationFailedForYTDActualNetProfit/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    Then user sees "Save New Data" button is disabled true
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
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    And user clicks on enabled "Save New Data" button
    Then user sees "Save New Data" button is disabled true
    And user sees false Net Profit Banner

  @TRX-166002 @tmsLink=TRX-166002 @QA
  Scenario: Verification of inability to see banner on Balance Sheet for past month in CommandMC>Accounting>"Balance Sheet" page when YTD Net Profit (Loss) does not match Current Period Profit
    Given user logs in as TEAMSBRAVOMCA user
    And user TEAMSBRAVOMCA get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForAustinMC" variable
    And user assign to "previousMonthPeriodMC7" variable previous month period from "{(transactionPeriodForAustinMC)}"
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "validationFailedForYTDActualNetProfit/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    Then user sees "Save New Data" button is disabled true
    And user sees true Net Profit Banner
    And user sees "YTD Net Profit Loss on the Income Statement does not match Current Period Profit Loss on the Balance Sheet" banner notification is displayed
    And user sees a red banner displayed notifying about YTD Net Profit does not match Current Period Profit with forbidden icon and with Close icon
    When user selects month "{(previousMonthPeriodMC7)}" in "Select Month" dropdown
    And user sees false Net Profit Banner
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    And user sees false Net Profit Banner

  @TRX-166004 @tmsLink=TRX-166004 @QA
  Scenario: Verification of inability to see banner on Balance Sheet for past month in CommandMC>Accounting>"Balance Sheet" page when YTD Net Profit (Loss) does not match Current Period Profit and data is out of balance
    Given user logs in as TEAMSBRAVOMCA user
    And user TEAMSBRAVOMCA get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriodForAustinMC" variable
    And user assign to "previousMonthPeriodMC7" variable previous month period from "{(transactionPeriodForAustinMC)}"
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "bothValidationFailed/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    Then user sees "Save New Data" button is disabled true
    And user sees true Net Profit Banner
    And user sees "YTD Net Profit Loss on the Income Statement does not match Current Period Profit Loss on the Balance Sheet" banner notification is displayed
    And user sees a red banner displayed notifying about YTD Net Profit does not match Current Period Profit with forbidden icon and with Close icon
    When user selects month "{(previousMonthPeriodMC7)}" in "Select Month" dropdown
    And user sees false Net Profit Banner
    When user clicks on enabled 'Upload Files' button in the top right corner of page
    Then user sees 'Upload Files' modal is open
    And user sees enabled "Close" button in right upper corner of Upload Files modal
    When user selects "allAccounts/accthist1.xlsx" file from their device
    Then user sees trash bin icon and enabled "Delete" button
    And user sees "accthist1.xlsx" file is displayed under "Upload" field
    When user clicks on 'Upload' button on Upload Files modal
    Then user sees next calculated formula for current month "Total Cash" in "Current Assets" group
    And user sees false Net Profit Banner
