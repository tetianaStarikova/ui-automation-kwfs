package com.kwri.auto.enums;

import lombok.Getter;

/**
 * The enum Urls.
 */
@Getter
public enum Urls {
    ADMINISTRATION_APPLET("commandmc/administration/", "/commandmc/administration"),
    ACCOUNTING_APPLET("commandmc/accounting/", "/commandmc/accounting"),
    CLOUDMORE_PAGE("cloudmore", "/cloudmore"),
    COMMAND_PAGE("command", "/command"),
    SUPPORT_TOOLS_PAGE("kwri-support-tools", "/kwri-support-tools"),
    SUPPORT_TOOLS_DIRECTORY_PAGE(SUPPORT_TOOLS_PAGE.getNavigationUrl() + "/directory",
            SUPPORT_TOOLS_PAGE.getExpectedUrl() + "/directory"),
    SUPPORT_TOOLS_MARKET_CENTERS_PAGE(SUPPORT_TOOLS_DIRECTORY_PAGE.getNavigationUrl() + "/market-centers/",
            SUPPORT_TOOLS_DIRECTORY_PAGE.getExpectedUrl() + "/market-centers"),
    SUPPORT_TOOLS_REGIONS_PAGE(SUPPORT_TOOLS_DIRECTORY_PAGE.getNavigationUrl() + "/regions/",
            SUPPORT_TOOLS_DIRECTORY_PAGE.getExpectedUrl() + "/regions"),
    SUPPORT_TOOLS_FINANCIAL_STATEMENT_PAGE(SUPPORT_TOOLS_PAGE.getNavigationUrl()
            + "/financial-statement", SUPPORT_TOOLS_PAGE.getExpectedUrl() + "/financial-statement"),
    SUPPORT_TOOLS_FINANCIAL_STATEMENT_CHART_OF_ACCOUNTS_PAGE(SUPPORT_TOOLS_FINANCIAL_STATEMENT_PAGE.getNavigationUrl()
            + "/chart-of-accounts", SUPPORT_TOOLS_FINANCIAL_STATEMENT_PAGE.getExpectedUrl() + "/chart-of-accounts"),
    SUPPORT_TOOLS_ASSOCIATES_PAGE(SUPPORT_TOOLS_DIRECTORY_PAGE.getNavigationUrl()
            + "/associates", SUPPORT_TOOLS_DIRECTORY_PAGE.getExpectedUrl() + "/associates"),
    LOGIN_PAGE("login", "/login");

    /**
     * -- GETTER --
     *  Gets navigation url.
     *
     * @return the navigation url
     */
    private final String navigationUrl;
    /**
     * -- GETTER --
     *  Gets expected url.
     *
     * @return the expected url
     */
    private final String expectedUrl;

    Urls(final String navigationUrl, final String expectedUrl) {
        this.navigationUrl = navigationUrl;
        this.expectedUrl = expectedUrl;
    }

}
