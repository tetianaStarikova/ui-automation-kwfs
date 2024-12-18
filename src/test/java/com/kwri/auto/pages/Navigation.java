package com.kwri.auto.pages;

import com.google.inject.Inject;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.ui.pages.BasePage;
import com.kwri.auto.utils.DriverUtils;
import com.kwri.auto.utils.Waits;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.kwri.auto.enums.WaiterValues.TIMEOUT_10_SECONDS;
import static com.kwri.auto.enums.WaiterValues.TIMEOUT_15_SECONDS;
import static com.kwri.auto.enums.WaiterValues.TIMEOUT_30_SECONDS;
import static com.kwri.auto.enums.WaiterValues.TIMEOUT_60_SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * The type Navigation.
 */
@Getter
public class Navigation extends BasePage implements Waits {

    @FindBy(xpath = "//div//*[contains(@class, 'avatar')]")
    private WebElement btnUserActions;
    @FindBy(xpath = "//div[text()='KWRI Support Tools']")
    private WebElement btnKwriSupportTools;
    @FindBy(xpath = "//span[contains(@class, 'styles_show-all-btn')]")
    private WebElement btnSeeAllOptions;
    @FindBy(xpath = "//span[contains(@class, 'icon--logout')]/..")
    private WebElement btnLogOut;
    @Inject
    private DriverUtils driverUtils;
    @Inject
    private Common common;

    /**
     * Instantiates a new Navigation.
     *
     * @param world the world
     */
    @Inject
    public Navigation(final World world) {
        super(world);
    }

    /**
     * Gets btn user account.
     *
     * @param userAccountName the user account name
     * @return the btn user account
     */
    public List<WebElement> getBtnUserAccount(String userAccountName) {
        this.common.waitForAjaxRequestsComplete();
        this.driverUtils.scrollUp();
        return this.world.driver.findElements(By.xpath(".//*[text()='OTHER ACCOUNTS']//parent::div//"
                + "div/p[text()='" + userAccountName + "']"));
    }

    /**
     * Gets mc by name.
     *
     * @param mcName the mc name
     * @return the mc by name
     */
    public WebElement getMcByName(String mcName) {
        return this.world.driver.findElement(By.xpath("//div[contains(@class, 'flex-grow')]//p[text()='"
                + mcName + "']"));
    }

    /**
     * Gets web driver.
     *
     * @return the web driver
     */
    public WebDriver getWebDriver() {
        return this.world.driver;
    }

    /**
     * Gets user actions button.
     *
     * @return the user action button
     */
    public WebElement getBtnUserActions() {
        this.common.waitForPageToLoad();
        return getWait(this.world.driver, TIMEOUT_60_SECONDS.getValue()).until(visibilityOf(btnUserActions));
    }

    /**
     * Gets current URL for Login Page.
     *
     * @return current URL
     */
    public String getCurrentUrlForLoginPage() {
        this.common.waitForPageToLoad();
        return this.world.driver.getCurrentUrl();
    }

    /**
     * Verify kwri support tools menu is displayed.
     *
     * @param shouldBePresent should be present
     */
    public void verifyKwriSupportToolsMenuIsDisplayed(boolean shouldBePresent) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsPresent(getBtnKwriSupportTools(), shouldBePresent);
    }

    /**
     * Click on  kwri support tools menu.
     */
    public void clickOnKwriSupportToolsMenu() {
        this.common.waitForPageToLoad();
        this.common.waitAndClickElement(TIMEOUT_15_SECONDS.getValue(), getBtnKwriSupportTools());
    }

    /**
     * Logout user.
     */
    public void logoutUser() {
        this.common.waitForPageToLoad();
        if (this.driverUtils.getOpenedWindowsCount() > 1) {
            driverUtils.closeOpenedTab();
        }
        try {
            this.common.waitForPageToLoad();
            clickLogOut();
        } catch (Exception ex) {
            driverUtils.refreshTab();
            this.common.waitForPageToLoad();
            clickLogOut();
            driverUtils.openCurrentUrlInNewTab();
            driverUtils.switchToTab(1, 2);
            driverUtils.closeOpenedTab(0, 1);
        }
    }

    /**
     * Wait until user avatar is visible.
     */
    public void waitUntilUserAvatarVisible() {
        this.common.waitUntilElementIsVisible(TIMEOUT_10_SECONDS.getValue(), btnUserActions);
    }

    /**
     * Click log out.
     */
    public void clickLogOut() {
        this.common.waitForPageToLoad();
        this.common.waitUntilElementIsVisible(TIMEOUT_30_SECONDS.getValue(), getBtnUserActions());
        this.common.waitForPageToLoad();
        driverUtils.clickOnElementJS(getBtnUserActions());
        this.common.waitUntilElementIsVisible(TIMEOUT_30_SECONDS.getValue(), getBtnLogOut());
        this.common.waitUntilElementIsVisible(getBtnLogOut());
        getBtnLogOut().click();
    }
}
