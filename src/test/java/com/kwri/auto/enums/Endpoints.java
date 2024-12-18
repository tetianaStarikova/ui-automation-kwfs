package com.kwri.auto.enums;

/**
 * List of end-points used in this repository.
 */
public enum Endpoints {

    LOGIN("login"),
    AMS_BASE_URL("ams/api/{version}"),
    KW_FS_BASE_URL("financial-statement/api/{version}"),
    COMMAND_MC_TRANSACTIONS("commandmc-transactions/{version}"),
    ORGS(AMS_BASE_URL.getUrl() + "/orgs"),
    KW_FS_ACCOUNTS(KW_FS_BASE_URL.getUrl() + "/accounts"),
    KW_FS_ACCOUNTS_BALANCE_SHEET(KW_FS_ACCOUNTS.getUrl() + "/orgs", "/balance-sheet"),
    KW_FS_ACCOUNTS_INCOME_STATEMENT(KW_FS_ACCOUNTS.getUrl() + "/orgs", "/income-statement"),
    KW_FS_ORGANIZATION(KW_FS_BASE_URL.getUrl() + "/organizations"),
    KW_FS_ACCOUNTS_API_KEY(KW_FS_ORGANIZATION.getUrl(), "/api-key"),
    KW_FS_ACCOUNT_HISTORY(KW_FS_BASE_URL.getUrl() + "/account-history/orgs"),
    COMMAND_MC_TRANSACTIONS_PERIODS(COMMAND_MC_TRANSACTIONS.getUrl() + "/periods/current/org");

    private final String url;
    private String url2;
    private String url3;
    private String url4;

    Endpoints(final String url) {
        this.url = url;
    }

    Endpoints(final String url, final String url2) {
        this.url = url;
        this.url2 = url2;
    }

    Endpoints(final String url, final String url2, final String url3) {
        this.url = url;
        this.url2 = url2;
        this.url3 = url3;
    }

    Endpoints(final String url, final String url2, final String url3, final String url4) {
        this.url = url;
        this.url2 = url2;
        this.url3 = url3;
        this.url4 = url4;
    }

    /**
     * Gets url.
     *
     * @param id the id
     * @return the url
     */
    public String getUrl(String... id) {
        if (id == null || id.length == 0) {
            return url;
        } else if (id.length == 1) {
            if (url2 == null) {
                return url + "/" + id[0];
            } else {
                return url + "/" + id[0] + url2;
            }
        } else if (id.length == 2) {
            if (url3 == null) {
                return url + "/" + id[0] + url2 + "/" + id[1];
            } else {
                return url + "/" + id[0] + url2 + "/" + id[1] + url3;
            }
        } else if (id.length == 3) {
            if (url4 == null) {
                return url + "/" + id[0] + url2 + "/" + id[1] + url3 + "/" + id[2];
            } else {
                return url + "/" + id[0] + url2 + "/" + id[1] + url3 + "/" + id[2] + url4;
            }
        } else {
            return url + "/" + id[0] + url2 + "/" + id[1] + url3 + "/" + id[2] + url4 + "/" + id[3];
        }
    }
}
