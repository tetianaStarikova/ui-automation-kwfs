package com.kwri.auto.enums;

import lombok.Getter;

/**
 * List of waiters used in automation testing in this repository.
 */
@Getter
public enum WaiterValues {
    TIMEOUT_5_SECONDS(5),
    TIMEOUT_10_SECONDS(10),
    TIMEOUT_15_SECONDS(15),
    TIMEOUT_30_SECONDS(30),
    TIMEOUT_45_SECONDS(45),
    TIMEOUT_60_SECONDS(60),
    TIMEOUT_1000_MILLI_SECONDS(1000);

    private final int value;

    WaiterValues(final int value) {
        this.value = value;
    }
}
