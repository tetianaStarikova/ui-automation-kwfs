package com.kwri.auto.enums;

import static java.lang.System.getProperties;

/**
 * List of used API keys.
 */
public enum ApiKeys {
    AMS_KEY("AMS", false),
    COMMON_KEY("CommonKey", false),
    CONNECT_GROUPS_KEY("ConnectGroups", false),
    KW_EMAIL_UPDATES_KEY("KwEmailUpdates", true),
    MC_RECRUITS_KEY("McRecruits", false),
    ONBOARDING_KEY("Onboarding", false),
    POST_PEOPLE_KEY("PostPeople", true),
    TRANSACTIONS_API_KEY("Transactions", false),
    TRUSTED_KEY("TrustedApiKey", false),
    MLS_KEY("Mls", false);
    private final String apiKey;
    private final boolean isFeatureKey;

    ApiKeys(final String apiKey, final boolean isFeatureKey) {
        this.apiKey = apiKey;
        this.isFeatureKey = isFeatureKey;
    }

    /**
     * This method will return api key value from property file.
     *
     * @return {@link String} string value of api key.
     */
    public String getApiKey() {
        return getProperties().getProperty(apiKey + (isFeatureKey ? ".featureApiKey" : ".serviceApiKey"));
    }
}
