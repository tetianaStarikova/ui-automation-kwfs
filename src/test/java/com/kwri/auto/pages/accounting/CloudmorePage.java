package com.kwri.auto.pages.accounting;

import com.google.inject.Inject;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.ui.pages.BasePage;
import com.kwri.auto.utils.Waits;
import lombok.Getter;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.kwri.auto.enums.WaiterValues.TIMEOUT_30_SECONDS;
import static com.kwri.auto.enums.WaiterValues.TIMEOUT_60_SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * The type Cloudmore page.
 */
@Getter
public class CloudmorePage extends BasePage implements Waits {

    @FindBy(xpath = "//h3[contains(text(), 'Administration')]")
    private WebElement btnAdministrationCloudmore;

    @FindBy(xpath = "//h3[contains(text(), 'Accounting')]")
    private WebElement btnAccounting;

    @FindBy(xpath = "//h3[contains(text(), 'Accounting')]//following-sibling::p")
    private WebElement accountingMessage;

    @FindBy(xpath = "//img[contains(@class, 'cloudmore')]")
    private WebElement cloudmoreTab;

    @FindBy(xpath = "//img[contains(@class,' kw-logo--informal')]//parent::button")
    private WebElement sideBarMenuBtn;

    @FindBy(xpath = "//a[@data-testId='nav-item-accounting']")
    private WebElement accountingSideBarMenu;

    @FindBy(xpath = "//a[@data-testId='nav-item-administration']")
    private WebElement administrationSideBarMenu;

    @FindBy(xpath = "//a[@data-testId='nav-item-accounting']//a")
    private WebElement accountingSideBarMenuSelected;

    @FindBy(xpath = "//a[@data-testId='nav-item-accounting']//a//p")
    private WebElement accountingSideBarLabel;

    @FindBy(xpath = "//h3[text()='Accounting']")
    private WebElement accountingEnabled;

    @FindBy(xpath = "//span[text()='Report Off']/parent::div")
    private WebElement reportOffButton;

    @Inject
    private Common common;

    /**
     * Instantiates a new Cloudmore page.
     *
     * @param world the world
     */
    @Inject
    public CloudmorePage(final World world) {
        super(world);
    }

    /**
     * Gets cloudmore tab.
     *
     * @return cloudmore tab
     */
    public WebElement getCloudmoreTab() {
        this.common.waitForPageToLoad();
        return getWait(this.world.driver, TIMEOUT_60_SECONDS.getValue()).until(visibilityOf(cloudmoreTab));
    }

    /**
     * Gets Administration cloudmore button.
     *
     * @return administration cloudmore button
     */
    public WebElement getBtnAdministrationCloudmore() {
        this.common.waitForPageToLoad();
        try {
            return getWait(this.world.driver, TIMEOUT_30_SECONDS.getValue())
                    .until(visibilityOf(btnAdministrationCloudmore));
        } catch (TimeoutException | StaleElementReferenceException ex) {
            return getWait(this.world.driver, TIMEOUT_30_SECONDS.getValue())
                    .until(visibilityOf(administrationSideBarMenu));
        }
    }

    /**
     * Click on cloudmore tab.
     */
    public void clickOnCloudmoreTab() {
        this.common.clickForcibly(getCloudmoreTab());
    }

    /**
     * Click on btn administration cloudmore.
     */
    public void clickOnBtnAdministrationCloudmore() {
        this.common.clickForcibly(getBtnAdministrationCloudmore());
    }

    /**
     * Click on Accounting button on cloudmore page.
     */
    public void clickOnBtnAccountingCloudmore() {
        try {
            this.common.waitUntilElementIsVisible(TIMEOUT_30_SECONDS.getValue(), getBtnAccounting());
            this.common.clickForcibly(getBtnAccounting());
        } catch (TimeoutException ex) {
            getReportOffButton().click();
            this.common.waitUntilElementIsVisible(TIMEOUT_30_SECONDS.getValue(), getBtnAccounting());
            this.common.clickForcibly(getBtnAccounting());
        }
    }

    /**
     * Verify accounting card is enabled.
     *
     * @param shouldBeEnabled should be enabled
     */
    public void verifyAccountingCardIsEnabled(boolean shouldBeEnabled) {
        try {
            this.common.waitForPageToLoad();
            this.common.waitUntilElementIsVisible(TIMEOUT_30_SECONDS.getValue(), getAccountingEnabled());
            boolean isEnabled;
            isEnabled = !getAccountingEnabled().getAttribute("class").contains("disabled");
            assertEquals("Accounting applet should be enabled " + shouldBeEnabled + ", but found " + isEnabled,
                    shouldBeEnabled, isEnabled);
        } catch (TimeoutException ex) {
            getReportOffButton().click();
            this.common.waitUntilElementIsVisible(TIMEOUT_30_SECONDS.getValue(), getAccountingEnabled());
            boolean isEnabled;
            isEnabled = !getAccountingEnabled().getAttribute("class").contains("disabled");
            assertEquals("Accounting applet should be enabled " + shouldBeEnabled + ", but found " + isEnabled,
                    shouldBeEnabled, isEnabled);
        }
    }

    /**
     * Verify accounting wording.
     *
     * @param expectedText the expected text
     */
    public void verifyAccountingWording(String expectedText) {
        this.common.waitUntilElementIsVisible(TIMEOUT_30_SECONDS.getValue(), getAccountingMessage());
        assertEquals("Accounting wording should be " + expectedText + ", but found "
                        + getAccountingMessage().getText(),
                expectedText, getAccountingMessage().getText());
    }

    /**
     * Verify accounting header.
     */
    public void verifyAccountingHeader() {
        this.common.waitUntilElementIsVisible(TIMEOUT_30_SECONDS.getValue(), getBtnAccounting());
        String expectedHeader = "Accounting";
        assertEquals("Expected header is Accounting, but found "
                        + getBtnAccounting().getText(),
                expectedHeader, getBtnAccounting().getText());
    }

    /**
     * Click on accounting card in the sidebar menu.
     */
    public void clickOnAccountingCardInTheSidebarMenu() {
        this.common.waitForPageToLoad();
        getSideBarMenuBtn().click();
        this.common.waitUntilElementIsVisible(TIMEOUT_30_SECONDS.getValue(), getAccountingSideBarMenu());
        getAccountingSideBarMenu().click();
    }

    /**
     * Verify accounting card is selected in sidebar menu.
     */
    public void verifyAccountingCardIsSelectedInSideBarMenu() {
        this.common.waitForPageToLoad();
        assertTrue("Accounting applet should be selected in the sidebar menu! ",
                getAccountingSideBarMenuSelected().getAttribute("class").contains("nav-item-active"));
    }

    /**
     * Verify accounting card is disabled in sidebar menu.
     */
    public void verifyAccountingCardIsDisabledInSideBarMenu() {
        this.common.waitForPageToLoad();
        assertTrue("Accounting applet should be disabled in the sidebar menu! ",
                getAccountingSideBarMenuSelected().getAttribute("class").contains("disabled"));
    }

    /**
     * Verify accounting text is displayed in sidebar menu.
     */
    public void verifyAccountingTextIsDisplayedInSideBarMenu() {
        this.common.waitForPageToLoad();
        assertTrue("Accounting label should be disabled in Expanded menu! ",
                getAccountingSideBarLabel().isDisplayed());
    }
}
