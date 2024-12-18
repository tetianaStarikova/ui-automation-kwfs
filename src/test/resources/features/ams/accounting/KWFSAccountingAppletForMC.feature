@KWFSAccountingApplet @ui-financial-statement
Feature: Financial Statement Accounting applet

  Background:
    Given user navigates to home page

  @TRX-156316 @tmsLink=TRX-156316
  Scenario Outline: Verification of an ability to see "Accounting" applet in the list of Applets in CommandMC by users with permissions
    Given user logs in as <user> user
    When user open "CommandMC" main page
    And user select "KWFS Test TeamsBravo 7" account in shell
    Then user sees enabled true "Accounting" card located between "Reports" and "Tasks" cards
    And user sees "Accounting" card with 'Accounting' header and with the following wording "Import and review financial statement data."
    @QA
    Examples:
      | user                 |
      | TEAMSBRAVOMCA        |
      | TEAMSBRAVOOP         |
      | TEAMSBRAVOTEAMLEADER |
      | TEAMSBRAVOAMCA       |
    @PROD
    Examples:
      | user  |
      | QAMCA |

  @TRX-156420 @tmsLink=TRX-156420 @QA
  Scenario Outline: Verification of inability to see "Accounting" applet in the list of Applets in CommandMC by users w/o permissions
    Given user logs in as <user> user
    When user open "CommandMC" main page
    Then user sees "Accounting" card with 'Accounting' header and with the following wording "Import and review financial statement data."
    Examples:
      | user                            |
      | MCINVESTOR                      |
#      | AVENGERSREGDIRECTOR             |
#      | AVENGERS_MC_OPERATING_PRINCIPAL |
#      | AVENGERS_MC_GENERAL_MANAGER     |

  @TRX-156429 @tmsLink=TRX-156429 @TRX-156419 @tmsLink=TRX-156419
  Scenario Outline: Verification of an ability to open "Accounting" page by clicking on "Accounting" applet in the sidebar menu in CommandMC by users with permissions
    Given user logs in as <user> user
    When user open "CommandMC" main page
    And user select "<accountName>" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" card is selected and highlighted in sidebar menu
    Then user sees "Accounting" header is displayed on applet
    @QA
    Examples:
      | user                 | accountName            |
      | TEAMSBRAVOMCA        | KWFS Test TeamsBravo 3 |
      | TEAMSBRAVOOP         | KWFS Test TeamsBravo 3 |
      | TEAMSBRAVOTEAMLEADER | KWFS Test TeamsBravo 3 |
      | TEAMSBRAVOAMCA       | KWFS Test TeamsBravo 3 |
    @PROD
    Examples:
      | user  | accountName        |
      | QAMCA | Demo Market Center |

  @TRX-156428 @tmsLink=TRX-156428
  Scenario Outline: Verification of an ability to open "Accounting" page by clicking on "Accounting" applet in the list of Applets in CommandMC by users with permissions
    Given user logs in as <user> user
    When user open "CommandMC" main page
    And user clicks on "Accounting" card in the list of Applets
    Then user is redirected to "Accounting" page
    And user sees "Accounting" card is selected and highlighted in sidebar menu
    @QA
    Examples:
      | user                 |
      | TEAMSBRAVOMCA        |
      | TEAMSBRAVOOP         |
      | TEAMSBRAVOTEAMLEADER |
    @PROD
    Examples:
      | user  |
      | QAMCA |

  @TRX-156434 @tmsLink=TRX-156434
  Scenario Outline: Verification of an ability to open "Accounting" page using direct link by users with permissions
    Given user logs in as <user> user
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" card is selected and highlighted in sidebar menu
    @QA
    Examples:
      | user                 |
      | TEAMSBRAVOMCA        |
      | TEAMSBRAVOOP         |
      | TEAMSBRAVOTEAMLEADER |
    @PROD
    Examples:
      | user  |
      | QAMCA |

  @TRX-156435 @tmsLink=TRX-156435 @QA
  Scenario Outline: Verification of inability to open "Accounting" page using direct link by users w/o permissions
    Given user logs in as <user> user
    When user open 'Accounting' page using url
    Then user is redirected to CommandMC main page
    Examples:
      | user                        |
      | AVENGERSREGDIRECTOR         |
      | AVENGERS_MC_MC_BROKER       |
      | AVENGERS_MC_GENERAL_MANAGER |

  @TRX-156421 @tmsLink=TRX-156421 @QA
  Scenario Outline: Verification of inability to see "Accounting" applet in the sidebar menu in CommandMC by users w/o permissions
    Given user logs in as <user> user
    When  user is on Command MC > Administration applet
    Then user sees disabled "Accounting" applet icon located between "Report" and "Tasks" applet icons
    Examples:
      | user                        |
      | AVENGERSREGDIRECTOR         |
      | AVENGERS_MC_MC_BROKER       |
      | AVENGERS_MC_GENERAL_MANAGER |

  @TRX-156439 @tmsLink=TRX-156439
  Scenario Outline:  Verification of an ability to see "Accounting" page default state in CommandMC>Accounting by users with permissions
    Given user logs in as <user> user
    And  user is on Command MC > Administration applet
    And user select "<accountName>" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    @QA
    Examples:
      | user                 | accountName            |
      | TEAMSBRAVOMCA        | KWFS Test TeamsBravo 3 |
      | TEAMSBRAVOOP         | KWFS Test TeamsBravo 3 |
      | TEAMSBRAVOTEAMLEADER | KWFS Test TeamsBravo 3 |
    @PROD
    Examples:
      | user  | accountName        |
      | QAMCA | Demo Market Center |

  @TRX-156974 @tmsLink=TRX-156974
  Scenario Outline: Verification of an ability to see a button to print out Balance Sheet for current financial month on CommandMC>Accounting> "Balance Sheet" tab by users with permissions
    Given user logs in as <user> user
    When user open 'Accounting' page using url
    And user select "<accountName>" account in shell
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    And user sees disabled false button to print out Balance Sheet for current financial month
    @QA
    Examples:
      | user                 | accountName            |
      | TEAMSBRAVOMCA        | KWFS Test TeamsBravo 3 |
      | TEAMSBRAVOOP         | KWFS Test TeamsBravo 3 |
      | TEAMSBRAVOTEAMLEADER | KWFS Test TeamsBravo 3 |
    @PROD
    Examples:
      | user  | accountName        |
      | QAMCA | Demo Market Center |

  @TRX-156975 @tmsLink=TRX-156975
  Scenario Outline: Verification of an ability to see a button to print out Balance Sheet for past financial month on CommandMC>Accounting> "Balance Sheet" tab by users with permissions
    Given user logs in as <user> user
    And user <user> get current transaction period for organization <orgId> and assign to "transactionPeriodForMC" variable
    And user assign to "previousMonthPeriodMC" variable previous month period from "{(transactionPeriodForMC)}"
    And  user is on Command MC > Administration applet
    And user select "<accountName>" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user sees "Balance Sheet" page is open by default
    When user selects month "{(previousMonthPeriodMC)}" in "Select Month" dropdown
    And user sees disabled false button to print out Balance Sheet for current financial month
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

  @TRX-156980 @tmsLink=TRX-156980
  Scenario Outline: Verification of an ability to see a button to print out Income Statement for current financial month on CommandMC>Accounting> "Income Statement" tab by users with permissions
    Given user logs in as <user> user
    And  user is on Command MC > Administration applet
    And user select "KWFS Test TeamsBravo 3" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user selects "Income Statement" tab
    And user sees disabled false button to print out Income Statement for current financial month
    @QA
    Examples:
      | user                 |
      | TEAMSBRAVOMCA        |
      | TEAMSBRAVOOP         |
      | TEAMSBRAVOTEAMLEADER |
      | TEAMSBRAVOAMCA       |
    @PROD
    Examples:
      | user  |
      | QAMCA |

  @TRX-158309 @tmsLink=TRX-158309 @QA
  Scenario: Verification of an ability to see disabled button to print out Income Statement for future financial month on CommandMC>Accounting> "Income Statement" tab by users with permissions
    Given user logs in as TEAMSBRAVOTEAMLEADER user
    And user TEAMSBRAVOTEAMLEADER get current transaction period for organization KWFS_TEST_TEAMS_BRAVO_7 and assign to "transactionPeriod" variable
    And user assign to "futureMonthPeriod" variable next month period from "{(transactionPeriod)}"
    When user open 'Accounting' page using url
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user selects "Income Statement" tab
    When user selects month "{(futureMonthPeriod)}" in "Select Month" dropdown
    And user sees disabled true button to print out Income Statement for current financial month

  @TRX-156981 @tmsLink=TRX-156981
  Scenario Outline: Verification of an ability to see a button to print out Income Statement for past financial month on CommandMC>Accounting> "Income Statement" tab by users with permissions
    Given user logs in as <user> user
    And user <user> get current transaction period for organization <orgId> and assign to "transactionPeriodForDemoMC" variable
    And user assign to "previousMonthPeriodDemoMC" variable previous month period from "{(transactionPeriodForDemoMC)}"
    And  user is on Command MC > Administration applet
    And user select "<accountName>" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user selects "Income Statement" tab
    When user selects month "{(previousMonthPeriodDemoMC)}" in "Select Month" dropdown
    And user sees disabled false button to print out Income Statement for current financial month
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

  @TRX-158548 @tmsLink=TRX-158548 @QA
  Scenario Outline: Verification of an ability to see "View Security Key" button in CommandMC>Accounting by users with permissions in case of MC uses Accountedge as accounting vendor
    Given user logs in as <user> user
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 3" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees disabled false "View Security Key" button in the top right corner of page
    When user selects "Income Statement" tab
    Then user sees disabled false "View Security Key" button in the top right corner of page
    When user selects "Account History" tab
    Then user sees disabled false "View Security Key" button in the top right corner of page
    Examples:
      | user                 |
      | TEAMSBRAVOMCA        |
      | TEAMSBRAVOOP         |
      | TEAMSBRAVOTEAMLEADER |

  @TRX-158549 @tmsLink=TRX-158549 @QA
  Scenario: Verification of inability to see "View Security Key" button in CommandMC>Accounting by users with permissions in case of MC uses AccountedgeInt as accounting vendor
    Given user logs in as TEAMSBRAVOMCA user
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees "View Security Key" button is displayed false in the top right corner of page
    When user selects "Income Statement" tab
    Then user sees "View Security Key" button is displayed false in the top right corner of page
    When user selects "Account History" tab
    Then user sees "View Security Key" button is displayed false in the top right corner of page

  @TRX-158599 @tmsLink=TRX-158599 @QA
  Scenario: Verification of an ability to see "Upload Files" button in CommandMC>Accounting by users with permissions in case of MC uses AccountedgeInt as accounting vendor
    Given user logs in as TEAMSBRAVOOP user
    When user open 'Accounting' page using url
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    And user select "KWFS Test TeamsBravo 7" account in shell
    When user sees "Balance Sheet" page is open by default
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user selects "Income Statement" tab
    Then user sees 'Upload Files' button is displayed true in the top right corner of page
    When user selects "Account History" tab
    Then user sees 'Upload Files' button is displayed true in the top right corner of page

  @TRX-158600 @tmsLink=TRX-158600 @QA
  Scenario Outline: Verification of inability to see "Upload Files" button in CommandMC>Accounting by users with permissions in case of MC uses Accountedge as accounting vendor
    Given user logs in as <user> user
    And  user is on Command MC > Administration applet
    And user select "KWFS Test TeamsBravo 3" account in shell
    When user clicks on "Accounting" card in the sidebar menu
    Then user is redirected to "Accounting" page
    And user sees "Accounting" header in top left side of page
    When user sees "Balance Sheet" page is open by default
    Then user sees 'Upload Files' button is displayed false in the top right corner of page
    When user selects "Income Statement" tab
    Then user sees 'Upload Files' button is displayed false in the top right corner of page
    When user selects "Account History" tab
    Then user sees 'Upload Files' button is displayed false in the top right corner of page
    Examples:
      | user                 |
      | TEAMSBRAVOMCA        |
      | TEAMSBRAVOOP         |
      | TEAMSBRAVOTEAMLEADER |
      | TEAMSBRAVOAMCA       |
