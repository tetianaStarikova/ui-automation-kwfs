package com.kwri.auto.enums;

import lombok.Getter;

/**
 * List of orgs used in automation testing in this repository.
 */
@Getter
public enum DefaultOrg {
    DEMO_MARKET_CENTER_ID(5663),
    KWW_TEST_MC_GS_ID(50374),
    KWW_2TEST_MC_GS_ID(50390),
    AUSTIN_SW(21),
    TEST_CANADIAN_MC(1004095676),
    TEST_NA_MC(1004095675),
    KWFS_TEST_TEAMS_BRAVO_3(1004228892),
    KWFS_TEST_TEAMS_BRAVO_7(1004228896),
    KWRI_ORG_ID(101);


    private final int value;

    DefaultOrg(final int value) {
        this.value = value;
    }
}
