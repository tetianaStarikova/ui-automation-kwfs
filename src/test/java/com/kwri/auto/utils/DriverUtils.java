package com.kwri.auto.utils;

import com.google.inject.Inject;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.methods.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.kwri.auto.enums.WaiterValues.TIMEOUT_1000_MILLI_SECONDS;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.cssSelector;

/**
 * The type Driver utils.
 */
public class DriverUtils implements Waits {
    private static final int WAIT_MILLISECONDS = 5000;
    private static final int GLOBAL_WAITING_TIMEOUT = 2;

    @Inject
    private World world;
    @Inject
    private Common common;

    /**
     * Scroll up.
     */
    public void scrollUp() {
        getJSExecutor().executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    /**
     * Scroll down.
     */
    public void scrollDown() {
        getJSExecutor().executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Scroll down till the end of page.
     *
     * @param element the element
     */
    public void scrollDownTillTheEndOfPage(WebElement element) {
        scrollDown();
        waitLoaderDisappear();
        try {
            Thread.sleep(WAIT_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getJSExecutor().executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Scroll to element.
     *
     * @param element the web element
     */
    public void scrollToElement(WebElement element) {
        getJSExecutor().executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Get JS Executor.
     *
     * @return JSExecutor
     */
    private JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) this.world.driver;
    }

    /**
     * Move to element.
     *
     * @param webElement the web element
     */
    public void moveToElement(WebElement webElement) {
        Actions actions = new Actions(this.world.driver);
        actions.moveToElement(webElement).build().perform();
        try {
            Thread.sleep(WAIT_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wait loader disappear.
     */
    public void waitLoaderDisappear() {
        By loaderCss = cssSelector(".loader");
        try {
            getWait(this.world.driver, GLOBAL_WAITING_TIMEOUT)
                    .until(ExpectedConditions.visibilityOfElementLocated(loaderCss));
            getWait(this.world.driver, GLOBAL_WAITING_TIMEOUT)
                    .until(ExpectedConditions.invisibilityOfElementLocated(loaderCss));
        } catch (TimeoutException ex) {
            getWait(this.world.driver, GLOBAL_WAITING_TIMEOUT)
                    .until(ExpectedConditions.invisibilityOfElementLocated(loaderCss));
        }
    }

    /**
     * Clear field using backspace.
     *
     * @param fieldInput the field input
     */
    public void clearFieldUsingBackspace(WebElement fieldInput) {
        String value = fieldInput.getAttribute("value");
        IntStream.range(0, value.length()).forEach(element -> fieldInput.sendKeys(Keys.BACK_SPACE));
    }

    /**
     * Clear field.
     *
     * @param fieldInput the field input
     */
    public void clearField(WebElement fieldInput) {
        this.common.waitUntilElementIsVisible(fieldInput);
        fieldInput.click();
        fieldInput.sendKeys(Keys.chord(Keys.END));
        fieldInput.clear();
    }

    /**
     * Switch to tab.
     *
     * @param tabIndex      the tab index
     * @param timeoutMillis the timeout millis
     */
    public void switchToTab(int tabIndex, long timeoutMillis) {
        Object lock = new Object();
        try {
            synchronized (lock) {
                lock.wait(timeoutMillis * TIMEOUT_1000_MILLI_SECONDS.getValue());
            }
            ArrayList<String> tabs = new ArrayList<>(this.world.driver.getWindowHandles());
            this.world.driver.switchTo().window(tabs.get(tabIndex));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Close opened tab.
     */
    public void closeOpenedTab() {
        List<String> windows = new ArrayList<>(this.world.driver.getWindowHandles());
        this.world.driver.switchTo().window(windows.get(1));
        this.world.driver.close();
        this.world.driver.switchTo().window(windows.get(0));
    }

    /**
     * Switch close opened tab.
     *
     * @param tabToClose  the index of a tab to close
     * @param tabToSwitch the index of a tab to switch to
     */
    public void closeOpenedTab(int tabToClose, int tabToSwitch) {
        List<String> windows = new ArrayList<>(this.world.driver.getWindowHandles());
        this.world.driver.switchTo().window(windows.get(tabToClose));
        this.world.driver.close();
        this.world.driver.switchTo().window(windows.get(tabToSwitch));
    }

    /**
     * Refresh opened tab.
     */
    public void refreshTab() {
        this.world.driver.navigate().refresh();
    }

    /**
     * Gets opened windows count.
     *
     * @return the opened windows count
     */
    public int getOpenedWindowsCount() {
        List<String> windows = new ArrayList<>(this.world.driver.getWindowHandles());
        return windows.size();
    }

    /**
     * Click on element with js.
     *
     * @param webElement the web element
     */
    public void clickOnElementJS(WebElement webElement) {
        getJSExecutor().executeScript("arguments[0].click();", webElement);
    }

    /**
     * Open current url in new tab.
     */
    public void openCurrentUrlInNewTab() {
        String currentUrl = this.world.driver.getCurrentUrl();
        getJSExecutor().executeScript("window.open('','_blank');");
        switchToLastTab();
        this.world.driver.get(currentUrl);
    }

    /**
     * Verify element is present.
     *
     * @param element         the element
     * @param shouldBePresent should be present
     */
    public void verifyElementIsPresent(WebElement element, boolean shouldBePresent) {
        this.common.waitForPageToLoad();
        boolean isPresent;
        try {
            element.isDisplayed();
            isPresent = true;
        } catch (NoSuchElementException e) {
            isPresent = false;
        }
        assertEquals("Error - " + element + " should be presented " + shouldBePresent + ", but found " + isPresent,
                shouldBePresent, isPresent);
    }

    /**
     * Verify element is enabled.
     *
     * @param element         the element
     * @param shouldBeEnabled should be enabled
     */
    public void verifyElementIsEnabled(WebElement element, boolean shouldBeEnabled) {
        this.common.waitForPageToLoad();
        boolean isPresent;
        try {
            element.isEnabled();
            isPresent = true;
        } catch (NoSuchElementException e) {
            isPresent = false;
        }
        assertEquals("Error - " + element + " should be enabled " + shouldBeEnabled + ", but found " + isPresent,
                shouldBeEnabled, isPresent);
    }

    /**
     * Verify element is disabled.
     *
     * @param element          the element
     * @param shouldBeDisabled should be disabled
     */
    public void verifyElementIsDisabled(WebElement element, boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        assertEquals("Error - " + element + " button should be disabled " + shouldBeDisabled + ", but is not!",
                Boolean.valueOf(element.getAttribute("aria-disabled")), shouldBeDisabled);
    }

    /**
     * Switch to the last tab.
     */
    private void switchToLastTab() {
        ArrayList<String> tabs = new ArrayList<>(this.world.driver.getWindowHandles());
        int indexOfLastTab = tabs.size() - 1;
        this.world.driver.switchTo().window(tabs.get(indexOfLastTab));
    }
}
