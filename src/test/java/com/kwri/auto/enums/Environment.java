package com.kwri.auto.enums;

/**
 * List of base URL for environments.
 *

 */
public enum Environment {
    DEV("https://console-dev.command.kw.com/", "https://dev-kong.command-api.kw.com/", "DEV"),
    QA("https://console-qa.command.kw.com/", "https://qa-kong.command-api.kw.com/", "QA"),
    PROD("https://console.command.kw.com/", "https://kong.command-api.kw.com/", "PROD");

    private final String baseUri;
    private final String baseUriApi;
    private final String environmentName;

    Environment(final String baseUri, final String baseUriApi, final String environmentName) {
        this.baseUri = baseUri;
        this.baseUriApi = baseUriApi;
        this.environmentName = environmentName;
    }

    /**
     * Gets base uri.
     *
     * @return the base uri
     */
    public String getBaseUri() {
        return baseUri;
    }

    /**
     * Gets base uri api.
     *
     * @return the base uri api
     */
    public String getBaseUriApi() {
        return baseUriApi;
    }

    /**
     * Gets environment name.
     *
     * @return the environment name
     */
    public String getEnvironmentName() {
        return environmentName;
    }
}
