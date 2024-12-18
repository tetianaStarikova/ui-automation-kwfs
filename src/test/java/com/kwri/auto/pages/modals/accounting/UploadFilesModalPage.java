package com.kwri.auto.pages.modals.accounting;

import com.google.inject.Inject;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.pages.modals.BaseModal;
import com.kwri.auto.steps.GeneralWhenSteps;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.utils.DriverUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

import static com.kwri.auto.enums.WaiterValues.TIMEOUT_60_SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The type Upload files modal page.
 */
@Getter
@Slf4j
@InflateContext
public class UploadFilesModalPage extends BaseModal {

    @FindBy(xpath = "//div[@data-testid='file-upload-modal']//p")
    private WebElement lblUploadFilesDisclaimer;

    @FindBy(xpath = "//input[@data-testid='upload-file-upload-component']")
    private WebElement inputUploadFile;

    @FindBy(xpath = "//span[text()='Cancel']//parent::button")
    private WebElement btnCancelUploadFile;

    @FindBy(xpath = "//span[text()='Upload']//parent::button")
    private WebElement btnUploadFile;

    @FindBy(xpath = "//label[@for='file-upload-component' and text()='Upload']")
    private WebElement lblUpload;

    @FindBy(xpath = "//input[@data-testid='upload-file-upload-component']//following-sibling::span")
    private WebElement uploadFieldPlaceholder;

    @FindBy(xpath = "//span[text()='Browse']//parent::div")
    private WebElement btnBrowse;

    @FindBy(xpath = "//span[text()='Delete']")
    private WebElement btnDeleteFile;

    @FindBy(xpath = "//span[text()='Delete']//preceding-sibling::span")
    private WebElement trashIcon;

    @FindBy(xpath = "//span[text()='accthist1.xlsx']")
    private WebElement fileName;

    @Inject
    private Common common;
    @Inject
    private GeneralWhenSteps generalWhenSteps;
    @Inject
    private DriverUtils driverUtils;

    /**
     * Instantiates a new Upload files modal page.
     *
     * @param world the world
     */
    @Inject
    public UploadFilesModalPage(final World world) {
        super(world);
    }

    /**
     * Gets lbl title upload files.
     *
     * @return the lbl title upload files
     */
    public WebElement getLblTitleUploadFiles() {
        return this.world.driver.findElement(By.xpath("//div[@data-testid='file-upload-modal']"
                + "//span[text()='Upload Files']"));
    }

    /**
     * Verify upload files modal is opened.
     */
    public void verifyUploadFilesModalIsOpened() {
        this.common.waitForPageToLoad();
        try {
            this.common.waitUntilElementIsVisible(TIMEOUT_60_SECONDS.getValue(), getBtnCancelUploadFile());
        } catch (TimeoutException ex) {
            this.common.waitForPageToLoad();
            this.common.waitUntilElementIsVisible(TIMEOUT_60_SECONDS.getValue(), getTopRightBtnClosePopup());
        }
        assertThat(getLblTitleUploadFiles().getText())
                .as("Modal title is incorrect").isEqualTo("Upload Files");
    }

    /**
     * Verify close button is displayed in the upper right side of upload files modal.
     */
    public void verifyCloseButtonIsDisplayedInTheUpperRightSideOfUploadFilesModal() {
        this.common.waitForPageToLoad();
        assertTrue("Close button should be displayed in the upper right side of popup",
                getTopRightBtnClosePopup().isEnabled());
    }

    /**
     * Verify upload label is displayed.
     */
    public void verifyUploadLabelIsDisplayed() {
        this.common.waitForPageToLoad();
        assertTrue("Upload Label should be displayed",
                getLblUpload().isDisplayed());
    }

    /**
     * Verify upload field is enabled.
     */
    public void verifyUploadFieldIsEnabled() {
        this.common.waitForPageToLoad();
        assertTrue("Upload field should be enabled",
                getInputUploadFile().isEnabled());
    }

    /**
     * Verify browse button is enabled.
     */
    public void verifyBrowseButtonIsEnabled() {
        this.common.waitForPageToLoad();
        assertTrue("Browse button should be enabled",
                getBtnBrowse().isEnabled());
    }

    /**
     * Verify upload files disclaimer is shown.
     */
    public void verifyUploadFilesDisclaimerIsShown() {
        String expectedDisclaimer = "Please upload the accthist1.xlsx file";
        assertEquals(expectedDisclaimer, getLblUploadFilesDisclaimer().getText());
    }

    /**
     * Verify upload field placeholder.
     */
    public void verifyUploadFieldPlaceholder() {
        String expectedPlaceholder = "Drop files to upload...";
        assertEquals(expectedPlaceholder, getUploadFieldPlaceholder().getText());
    }

    /**
     * Verify cancel button is enabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    public void verifyCancelButtonIsEnabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsDisabled(getBtnCancelUploadFile(), shouldBeDisabled);
    }

    /**
     * Verify upload button is enabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    public void verifyUploadButtonIsEnabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsDisabled(getBtnUploadFile(), shouldBeDisabled);
    }

    /**
     * Click on browse button.
     */
    public void clickOnBrowseButton() {
        this.common.waitForPageToLoad();
        getBtnBrowse().click();
    }

    /**
     * Click on Upload button.
     */
    public void clickOnUploadButton() {
        this.common.waitForPageToLoad();
        getBtnUploadFile().click();
    }

    /**
     * Upload file to modal.
     *
     * @param filePath the file path
     */
    public void uploadFileToModal(String filePath) {
        getInputUploadFile().sendKeys(new File(filePath).getAbsolutePath());
        this.common.waitForPageToLoad();
    }

    /**
     * Verify delete button is present.
     */
    public void verifyDeleteBtnIsPresent() {
        this.common.waitForPageToLoad();
        assertTrue("Delete button should be present!",
                getBtnDeleteFile().isDisplayed());
    }

    /**
     * Click on delete button.
     */
    public void clickOnDeleteButton() {
        this.common.waitForPageToLoad();
        getBtnDeleteFile().click();
    }

    /**
     * Verify trash bin icon is present.
     */
    public void verifyTrashBinIconIsPresent() {
        this.common.waitForPageToLoad();
        assertTrue("Trash Bin Icon should be present!",
                getTrashIcon().isDisplayed());
    }

    /**
     * Verify file name is present.
     */
    public void verifyFileNameIsPresent() {
        this.common.waitForPageToLoad();
        assertTrue("\"accthist1.xlsx\" file should be present!",
                getFileName().isDisplayed());
    }
}
