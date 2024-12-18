package com.kwri.auto.enums;

import static com.kwri.auto.config.Config.PROPERTIES;
import static com.kwri.auto.run.CucumberHooks.getAuthorizationToken;

/**
 * The enum User.
 */
public enum User {
    QARAINMAKER("QARainmaker", "DefaultRain"),
    QAAGENT("QAagent", "DefaultRain"),
    QAMCA("QAmca", "DefaultRain"),
    KELLE1("kelle1", "Kelle1"),
    KELLE2("kelle2", "Kelle2"),
    KELLE3("kelle3", "Kelle3"),
    DGROUPSUPERUSER("DGroupSuperUser", "DefaultRain"),       //Super user
    DASSISTANTTL5000("DAssistantTL5000", "DefaultRain"),     //Assistant Team Leader
    DOP5000("DOP5000", "DefaultRain"),                       //Legal
    DMCTECH5000("DMCTech5000", "DefaultRain"),               //MC Tech Coordinator
    DMCBROKER5000("DMCBroker5000", "DefaultRain"),           //System Administrator
    DGM5000("DGM5000", "DefaultRain"),                       //General Manager
    COMMANDRD("CommandRD", "DefaultRain"),                   //Regional Director
    COMMANDROM("CommandROM", "DefaultRain"),                 //Regional Operations Manager
    DREGIONSTAFF("DRegionStaff", "DefaultRain"),             //Regional Staff
    DASSISTANTRD("DAssistantRD", "DefaultRain"),             //Regional Director Assistant
    DASSISTANTRGMCA("DAssistantRGMCA", "DefaultRain"),       //Regional MCA assistant
    COMDEMOMCA("COMDemoMCA", "DefaultRain"),                 //MCA
    DAREADIRECTOR("DAreaDirector", "DefaultRain"),           //Area Director
    TESTMCA("testMCA@kellerwilliamsshanghai.com", "DefaultRain"), //WW MCA roleId = 6
    DREGIONOP("DRegionOP", "DefaultRain"),                   //Regional Operating Principal
    DMAPSRGCOACH("DMAPSRGCoach", "DefaultRain"),             //MAPS Regional Leadership Coach
    DTECHTRAINER("DTechTrainer", "DefaultRain"),             //Regional Tech Trainer
    DTEAMLEADER5000("DTeamLeader5000", "DefaultRain"),       //doesn't work for DEV
    DCOACH5000("DCoach5000", "DefaultRain"),                 //doesn't work for DEV
    DMCLEADER5000("DMCLeader5000", "DefaultRain"),           //doesn't work for DEV
    DMCTECHTRAINER("DMCTechTrainer5000", "DefaultRain"),   //Market Center Tech Trainer
    DUATESTER("dua.tester", "DefaultRain"),                  //doesn't work for DEV
    FIVEDOORSTESTER("fivedoors.tester", "DefaultRain"),      //doesn't work for DEV - Regional MCA
    MCINVESTOR("mc.investor", "DefaultRain"),                //doesn't work for DEV
    AVENGERS_MC_AGENT("AvengersMcAgent", "AvengersUsers"),                                      //role_id = 2
    AVENGERSTEAMRAINMAKER("AvengersTeamRainmaker", "AvengersUsers"),                          //role_Id = 71,83
    AVENGERS_MC_MCA("AvengersMcMCA", "AvengersUsers"),                                          //role_id = 3
    AVENGERS_MC2_MCA("AvengersMc2MCA", "AvengersUsers"),                                          //role_id = 3
    AVENGERS_MC_OPERATING_PRINCIPAL("AvengersMcOperatingPrincipal", "AvengersUsers"),            //role_id = 7
    AVENGERS_MC_TEAM_LEADER("AvengersMcTeamLeader", "AvengersUsers"),                            //role_id = 8
    AVENGERS_MC_MC_TECH_COORDINATOR("AvengersMcMCTechCoordinator", "AvengersUsers"),              //role_id = 9
    AVENGERSMCVESTEDRETIRED("AvengersMcVestedRetired", "AvengersUsers"),                      //role_id = 11
    AVENGERSMCADMINISTRATIVESTAFF("AvengersMcAdministrativeStaff", "AvengersUsers"),          //role_id = 12
    AVENGERS_MC_ASSISTANT_MCA("AvengersMcAssistantMCA", "AvengersUsers"),                        //role_id = 14
    AVENGERS_KWU_ADMIN("AvengersKWUAdmin", "AvengersUsers"),                                      //role_id = 17
    AVENGERSMCMCINVESTOR("AvengersMcMcInvestor", "AvengersUsers"),                            //role_id = 18
    AVENGERS_MC_ASSISTANT_TEAM_LEADER("AvengersMcAssistantTeamLeader", "AvengersUsers"),          //role_id = 19
    AVENGERS_MC_MC_BROKER("AvengersMcMcBroker", "AvengersUsers"),                                //role_id = 22
    AVENGERS_MC_GENERAL_MANAGER("AvengersMcGeneralManager", "AvengersUsers"),                    //role_id = 28
    AVENGERS_MC_PRODUCTIVITY_COACH("AvengersMcProductivityCoach", "AvengersUsers"),              //role_id = 30
    AVENGERS_LEGAL("AvengersLegal", "AvengersUsers"),                                            //role_id = 21
    AVENGERS_SUPPORT("AvengersSupport", "AvengersUsers"),                                        //role_id = 10
    AVENGERS_EXECUTIVE("AvengersExecutive", "AvengersUsers"),                                    //role_id = 16
    AVENGERS_ANALYST("AvengersAnalyst", "AvengersUsers"),                                        //role_id = 35
    AVENGERS_PS_ADMINISTRATOR("AvengersPsAdministrator", "AvengersUsers"),                       //role_id = 96
    AVENGERS_EXPANSION_USER("AvengersExpansionUser", "AvengersUsers"),
    AVENGERSMCMAPSAGENTCOACH("AvengersMcMAPSAgentCoach", "AvengersUsers"),                    //role_id = 34
    AVENGERSMCCOMPLIANCECOORDINATOR("AvengersMcComplianceCoordinator", "AvengersUsers"),      //role_id = 82
    AVENGERSMCMARKETCENTERLEADER("AvengersMcMarketCenterLeader", "AvengersUsers"),            //role_id = 85
    AVENGERSMCMARKETCENTERTECHTRAINER("AvengersMcMarketCenterTechTrainer", "AvengersUsers"),  //role_id = 87
    TESTBRAVO("test.bravo", "DefaultRain"),                     //HR role_id = 23, doesn't work for DEV
    AVENGERS_KWRI_HR("AvengersKwriHR", "AvengersUsers"),                                          //role_id = 23
    TEAMSBRAVOSUPPORT("TeamsBravoSupport", "KateSmith"),                                       //role_id = 10 - Support
    //Users in a default Region (1000000210)
    AVENGERSREGOPERATINGPRINCIPAL("AvengersRegOperatingPrincipal", "AvengersUsers"),          //role_id = 4
    AVENGERSREGDIRECTOR("AvengersRegDirector", "AvengersUsers"),                              //role_id = 5
    AVENGERSREGMCA("AvengersRegMCA", "AvengersUsers"),                                        //role_id = 6
    AVENGERSREGVESTEDRETIRED("AvengersRegVestedRetired", "AvengersUsers"),                    //role_id = 11
    AVENGERSREGINVESTOR("AvengersRegInvestor", "AvengersUsers"),                              //role_id = 15
    AVENGERSREGOPERATIONSMANAGER("AvengersRegOperationsManager", "AvengersUsers"),            //role_id = 20
    AVENGERS_SYSTEM_ADMIN("AvengersSystemAdmin", "AvengersUsers"),                  //role_id = 1, type = 3
    AVENGERS_HR_ASSISTANT("AvengersHRAssistant", "AvengersUsers"),                                //role_id = 24
    AVENGERSREGSTAFF("AvengersRegStaff", "AvengersUsers"),                                    //role_id = 25
    AVENGERSREGDIRECTORASSISTANT("AvengersRegDirectorAssistant", "AvengersUsers"),            //role_id = 26
    AVENGERSREGMCAASSISTANT("AvengersRegMCAAssistant", "AvengersUsers"),                      //role_id = 27
    AVENGERSREGAREADIRECTOR("AvengersRegAreaDirector", "AvengersUsers"),                      //role_id = 29
    AVENGERS_MC_ANGEL("AvengersMCAngel", "AvengersUsers"),                                        //role_id = 31
    AVENGERS_MAPS_LEADERSHIP_COACH("AvengersMAPSLeadershipCoach", "AvengersUsers"),               //role_id = 32
    AVENGERSREGTECHTRAINER("AvengersRegTechTrainer", "AvengersUsers"),                        //role_id = 86
    TEAMSBRAVOMCA("AutomationTeamsBravoMCA", "TestBravo"),                                    //role_id = 3
    TEAMSBRAVOOP("AutomationTeamsBravoOperationPrincipal", "TestBravo"),                      //role_id = 7
    TEAMSBRAVOTEAMLEADER("AutomationTeamsBravoTeamLeader", "TestBravo"),                      //role_id = 8
    TEAMSBRAVOAMCA("AutomationTeamsBravoAMCA", "TestBravo"),                                 //role_id = 14
    TEAMSBRAVOEXECUTIVE("TeamsBravoExecutive", "KateSmith"),                                  //role_id = 16
    TEAMSBRAVOHRASSISTANT("TeamsBravoHRAssistant", "KateSmith"),                              //role_id = 24
    TEAMSBRAVOMCANGEL("TeamsBravoMCAngel", "KateSmith"),                                      //role_id = 31
    TEAMSBRAVOMAPSLEADERSHIPCOACH("TeamsBravoMAPSLeadershipCoach", "KateSmith"),              //role_id = 32
    TEAMSBRAVOANALYST("TeamsBravoAnalyst", "KateSmith"),                                      //role_id = 35
    TEAMSBRAVOREGIONALDIVISIONLEADER("TeamsBravoRegionalDivisionLeader", "KateSmith"),        //role_id = 88
    AVENGERS_REGIONAL_DIVISION_LEADER("AvengersRegionalDivisionLeader", "AvengersUsers"),         //role_id = 88
    PSADMINISTRATOR("PSAdministrator", "DefaultRain"),                                        //role_id = 96
    TEAMSBRAVOKWUADMINISTRATOR("TeamsBravoKWUAdministrator", "KateSmith"),                    //role_id = 17
    AVENGERSSYSTEMADMIN("AvengersSystemAdmin", "AvengersUsers"),                              //role_id = 1
    TEAMSBRAVOSYSTEMADMIN("AutomationTeamsBravoSysAdmin", "TestBravo"),                      //role_id = 1
    TEAMSBRAVOLEGAL("AutomationTeamsBravoLegal", "TestBravo"),                               //role_id = 21
    TEAMSBRAVONAMCA("TeamsBravoNAMCA", "KateSmith"),                                         //role_id = 3, orgId=21
    TEAMSBRAVOWWREGIONWWMARKETCENTER("TeamsBravoRegWWRegionWWMnew", "KateSmith"),
    AVENGERS_USER_WHO_CAN_SHARE_ROYALTY("AvengersUserWhoCanShareRoyalty", "AvengersUsers");
    //DRAINMAKER5000("DRainmaker5000", "DefaultRain")        //doesn't work for DEV

    private final String login;
    private final String password;

    User(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return PROPERTIES.getProperty(password + ".password");
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return getAuthorizationToken(
                this.getLogin(),
                this.getPassword());
    }

    /**
     * Getter for user's kw_uid.
     *
     * @return {@link Integer} kw_uid
     */
    public int getKwUid() {
        return Integer.parseInt(PROPERTIES.getProperty(login + ".kwuId"));
    }
}
