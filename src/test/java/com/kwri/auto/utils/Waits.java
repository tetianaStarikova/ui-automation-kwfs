package com.kwri.auto.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * The interface Waits.
 */
public interface Waits {
    /**
     * Gets wait.
     *
     * @param webDriver        the web driver
     * @param timeOutInSeconds the time out in seconds
     * @return the wait
     */
    default WebDriverWait getWait(WebDriver webDriver, long timeOutInSeconds) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(timeOutInSeconds));
    }
}
