package com.kwri.auto.enums;

/**
 * Version manager.
 */
public enum Versions {
    V1("v1"),
    V2("v2"),
    V3("v3");

    private final String version;

    Versions(final String version) {
        this.version = version;
    }

    /**
     * Getter for version value.
     *
     * @return {@link String} version
     */
    public String getVersion() {
        return version;
    }
}
