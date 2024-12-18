@KWRISupportTools @ui-kwri-directory
Feature: KWRI Support Tools Market Centres page 'AccountEdge' tab

  Background:
    Given user navigates to home page

  @TRX-158641 @tmsLink=TRX-158641
  Scenario Outline: Verification of an ability to see 'AccountEdge' tab in Support Tools>Directory>Market Centers/Organizations>MC details by KWRI user with permissions for NA/Canadian MC
    Given user logs in as <user> user
    When user opens url SUPPORT_TOOLS_MARKET_CENTERS_PAGE for DEMO_MARKET_CENTER_ID orgId
    Then user <user> sees org number - org name label in top left side of page by DEMO_MARKET_CENTER_ID orgId
    And user sees true "AccountEdge" tab after "MLS IDs" tab in the general info of NA MC
   @QA
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
      | TEAMSBRAVOLEGAL       |
      | TEAMSBRAVOSUPPORT     |
    @PROD
    Examples:
      | user          |
      | DOP5000       |
      | DMCBROKER5000 |

  @TRX-158643 @tmsLink=TRX-158643
  Scenario Outline: Verification of inability to see 'AccountEdge' tab in Support Tools>Directory>Market Centers/Organizations>MC details by KWRI user with permissions for WW MC
    Given user logs in as <user> user
    When user opens url SUPPORT_TOOLS_MARKET_CENTERS_PAGE for KWW_2TEST_MC_GS_ID orgId
    Then user <user> sees org number - org name label in top left side of page by KWW_2TEST_MC_GS_ID orgId
    And user sees false "AccountEdge" tab after "MLS IDs" tab in the general info of NA MC
   @QA
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
      | TEAMSBRAVOLEGAL       |
      | TEAMSBRAVOSUPPORT     |
    @PROD
    Examples:
      | user          |
      | DOP5000       |
      | DMCBROKER5000 |

  @TRX-158652 @tmsLink=TRX-158652 @QA
  Scenario Outline: Verification of an ability to see 'AccountEdge' tab default state in Support Tools>Directory>Market Centers/Organizations>MC details by KWRI user with permissions for active NA MC with api key
    Given user logs in as <user> user
    When user opens url SUPPORT_TOOLS_MARKET_CENTERS_PAGE for DEMO_MARKET_CENTER_ID orgId
    Then user <user> sees org number - org name label in top left side of page by DEMO_MARKET_CENTER_ID orgId
    And user sees true "AccountEdge" tab after "MLS IDs" tab in the general info of NA MC
    When user open 'AccountEdge' tab for MC
    Then user sees disabled false "Change Vendor Type" button in right upper corner
    And user sees disabled false 'Generate New API Key' button
    And user <user> sees "API Key" label and api key value and "copy" icon is present true for NA MC DEMO_MARKET_CENTER_ID
    And user <user> sees next MC details info for 'Vendor', 'Status', 'Current Account Date' and 'Currency' for orgId DEMO_MARKET_CENTER_ID
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
      | TEAMSBRAVOLEGAL       |

  @TRX-158653 @tmsLink=TRX-158653 @QA
  Scenario Outline: Verification of an ability to see 'AccountEdge' tab default state in Support Tools>Directory>Market Centers/Organizations>MC details by KWRI user with permissions for active Canadian MC
    Given user logs in as <user> user
    When user opens url SUPPORT_TOOLS_MARKET_CENTERS_PAGE for TEST_CANADIAN_MC orgId
    Then user <user> sees org number - org name label in top left side of page by TEST_CANADIAN_MC orgId
    And user sees true "AccountEdge" tab after "MLS IDs" tab in the general info of NA MC
    When user open 'AccountEdge' tab for MC
    Then user sees disabled false "Change Vendor Type" button in right upper corner
    And user <user> sees next MC details info for 'Vendor', 'Status', 'Current Account Date' and 'Currency' for orgId TEST_CANADIAN_MC
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
      | TEAMSBRAVOLEGAL       |

  @TRX-158659 @tmsLink=TRX-158659 @e2e @QA
  Scenario Outline: Verification of an ability to generate new api key in Support Tools>Directory>Market Centers/Organizations>MC details> 'AccountEdge' tab by KWRI user with permissions for active NA MC with api key
    Given user logs in as <user> user
    When user opens url SUPPORT_TOOLS_MARKET_CENTERS_PAGE for DEMO_MARKET_CENTER_ID orgId
    Then user <user> sees org number - org name label in top left side of page by DEMO_MARKET_CENTER_ID orgId
    And user sees true "AccountEdge" tab after "MLS IDs" tab in the general info of NA MC
    When user open 'AccountEdge' tab for MC
    Then user sees disabled false "Change Vendor Type" button in right upper corner
    And user sees disabled false 'Generate New API Key' button
    And user <user> sees "API Key" label and api key value and "copy" icon is present true for NA MC DEMO_MARKET_CENTER_ID
    And user <user> sees next MC details info for 'Vendor', 'Status', 'Current Account Date' and 'Currency' for orgId DEMO_MARKET_CENTER_ID
    When user clicks on enabled 'Generate New API Key' button
    Then user <user> sees "API Key" label and api key value and "copy" icon is present true for NA MC DEMO_MARKET_CENTER_ID
    And user sees disabled false 'Generate New API Key' button
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
      | TEAMSBRAVOLEGAL       |

  @TRX-158743 @tmsLink=TRX-158743 @QA
  Scenario Outline: Verification of an ability to open 'Edit Account Edge' modal in Support Tools>Directory>Market Centers/Organizations>MC details>'AccountEdge' tab by KWRI user with permissions for NA/Canadian MC
    Given user logs in as <user> user
    When user opens url SUPPORT_TOOLS_MARKET_CENTERS_PAGE for DEMO_MARKET_CENTER_ID orgId
    Then user <user> sees org number - org name label in top left side of page by DEMO_MARKET_CENTER_ID orgId
    And user sees true "AccountEdge" tab after "MLS IDs" tab in the general info of NA MC
    When user open 'AccountEdge' tab for MC
    Then user sees disabled false "Change Vendor Type" button in right upper corner
    When user clicks on enabled 'Change Vendor Type' button for active organization
    Then user sees 'Edit Account Edge' modal is open
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
      | TEAMSBRAVOLEGAL       |

  @TRX-158745 @tmsLink=TRX-158745 @QA
  Scenario Outline:Verification of an ability to see 'Edit Account Edge' modal default state in Support Tools>Directory>Market Centers/Organizations>MC details>'AccountEdge' tab by KWRI user with permissions for NA/Canadian MC
    Given user logs in as <user> user
    When user opens url SUPPORT_TOOLS_MARKET_CENTERS_PAGE for DEMO_MARKET_CENTER_ID orgId
    Then user <user> sees org number - org name label in top left side of page by DEMO_MARKET_CENTER_ID orgId
    And user sees true "AccountEdge" tab after "MLS IDs" tab in the general info of NA MC
    When user open 'AccountEdge' tab for MC
    Then user sees disabled false "Change Vendor Type" button in right upper corner
    When user clicks on enabled 'Change Vendor Type' button for active organization
    Then user sees 'Edit Account Edge' modal is open
    And user sees enabled "Close" button in right upper corner of Edit Account Edge modal
    And user <user> sees next MC info for organization DEMO_MARKET_CENTER_ID Edit Account Edge modal
    And user sees disabled false 'Cancel' button in the bottom of Edit Account Edge modal
    And user sees disabled true 'Save' button in the bottom of Edit Account Edge modal
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
      | TEAMSBRAVOLEGAL       |

  @TRX-158749 @tmsLink=TRX-158749 @TRX-158750 @tmsLink=TRX-158750 @e2e @QA
  Scenario Outline: Verification of an ability to change vendor for MC with AccountEdgeInt vendor in Support Tools>Directory>Market Centers/Organizations>MC details>'AccountEdge' tab by KWRI user with permissions
    Given user logs in as <user> user
    When user opens url SUPPORT_TOOLS_MARKET_CENTERS_PAGE for DEMO_MARKET_CENTER_ID orgId
    Then user <user> sees org number - org name label in top left side of page by DEMO_MARKET_CENTER_ID orgId
    And user sees true "AccountEdge" tab after "MLS IDs" tab in the general info of NA MC
    When user open 'AccountEdge' tab for MC
    Then user sees disabled false "Change Vendor Type" button in right upper corner
    When user clicks on enabled 'Change Vendor Type' button for active organization
    Then user sees 'Edit Account Edge' modal is open
    And user sees enabled "Close" button in right upper corner of Edit Account Edge modal
    And user <user> sees next MC info for organization DEMO_MARKET_CENTER_ID Edit Account Edge modal
    And user sees disabled false 'Cancel' button in the bottom of Edit Account Edge modal
    And user sees disabled true 'Save' button in the bottom of Edit Account Edge modal
    When user selects "AccountEdge International" vendor value Edit Account Edge modal
    Then user sees disabled false 'Cancel' button in the bottom of Edit Account Edge modal
    And user sees disabled false 'Save' button in the bottom of Edit Account Edge modal
    When user clicks on enabled "Save" button for Edit Account Edge modal
    Then user <user> sees next MC details info for 'Vendor', 'Status', 'Current Account Date' and 'Currency' for orgId DEMO_MARKET_CENTER_ID
    When user clicks on enabled 'Change Vendor Type' button for active organization
    Then user sees 'Edit Account Edge' modal is open
    And user sees enabled "Close" button in right upper corner of Edit Account Edge modal
    And user <user> sees next MC info for organization DEMO_MARKET_CENTER_ID Edit Account Edge modal
    And user sees disabled false 'Cancel' button in the bottom of Edit Account Edge modal
    And user sees disabled true 'Save' button in the bottom of Edit Account Edge modal
    When user selects "AccountEdge" vendor value Edit Account Edge modal
    Then user sees disabled false 'Cancel' button in the bottom of Edit Account Edge modal
    And user sees disabled false 'Save' button in the bottom of Edit Account Edge modal
    When user clicks on enabled "Save" button for Edit Account Edge modal
    Then user <user> sees "API Key" label and api key value and "copy" icon is present true for NA MC DEMO_MARKET_CENTER_ID
    And user <user> sees next MC details info for 'Vendor', 'Status', 'Current Account Date' and 'Currency' for orgId DEMO_MARKET_CENTER_ID
    Examples:
      | user                  |
      | TEAMSBRAVOSYSTEMADMIN |
      | TEAMSBRAVOLEGAL       |
