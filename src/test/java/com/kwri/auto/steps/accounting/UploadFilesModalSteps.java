package com.kwri.auto.steps.accounting;

import com.google.inject.Inject;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.pages.modals.accounting.UploadFilesModalPage;
import com.kwri.auto.utils.DriverUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * The type Upload Files Modal steps.
 */
@InflateContext
public class UploadFilesModalSteps {

    @Inject
    private UploadFilesModalPage uploadFilesModalPage;

    @Inject
    private DriverUtils driverUtils;

    /**
     * User sees view security key modal is opened.
     */
    @Then("user sees 'Upload Files' modal is open")
    public void userSeesViewSecurityKeyModalIsOpened() {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.verifyUploadFilesModalIsOpened();
    }

    /**
     * User sees close btn in right upper corner of upload files modal.
     */
    @Then("user sees enabled \"Close\" button in right upper corner of Upload Files modal")
    public void userSeesCloseBtnInRightUpperCornerOfUploadFilesModal() {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.verifyCloseButtonIsDisplayedInTheUpperRightSideOfUploadFilesModal();
    }

    /**
     * User sees disclaimer for upload files modal.
     */
    @Then("user sees \"Please upload the accthist1.xlsx file\" disclaimer for Upload Files modal")
    public void userSeesDisclaimerForUploadFilesModal() {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.verifyUploadFilesDisclaimerIsShown();
    }

    /**
     * User sees upload label on upload files modal.
     */
    @Then("user sees \"Upload\" label on Upload Files modal")
    public void userSeesUploadLabelOnUploadFilesModal() {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.verifyUploadLabelIsDisplayed();
    }

    /**
     * User sees upload field on upload files modal.
     */
    @Then("user sees empty enabled \"Upload\" field with \"Drop files to upload...\" placeholder and enabled"
            + " \"Browse\" button")
    public void userSeesUploadFieldOnUploadFilesModal() {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.verifyUploadFieldIsEnabled();
        this.uploadFilesModalPage.verifyUploadFieldPlaceholder();
        this.uploadFilesModalPage.verifyBrowseButtonIsEnabled();
    }

    /**
     * User sees disabled cancel btn on upload files modal.
     *
     * @param shouldBeDisabled should be disabled
     */
    @Then("user sees disabled {} 'Cancel' button for Upload Files modal")
    public void userSeesDisabledCancelBtnOnUploadFilesModal(boolean shouldBeDisabled) {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.verifyCancelButtonIsEnabled(shouldBeDisabled);
    }

    /**
     * User sees disabled upload btn on upload files modal.
     *
     * @param shouldBeDisabled should be disabled
     */
    @Then("user sees disabled {} 'Upload' button for Upload Files modal")
    public void userSeesDisabledUploadBtnOnUploadFilesModal(boolean shouldBeDisabled) {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.verifyUploadButtonIsEnabled(shouldBeDisabled);
    }

    /**
     * User clicks on browse button on upload files modal.
     */
    @When("user clicks on \"Browse\" button in \"Upload\" field")
    public void userClicksOnBrowseButtonOnUploadFilesModal() {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.clickOnBrowseButton();
    }

    /**
     * User clicks on upload button on upload files modal.
     */
    @When("user clicks on 'Upload' button on Upload Files modal")
    public void userClicksOnUploadButtonOnUploadFilesModal() {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.clickOnUploadButton();
    }

    /**
     * User clicks on delete button on upload files modal.
     */
    @When("user clicks on \"Delete\" button on Upload Files modal")
    public void userClicksOnDeleteButtonOnUploadFilesModal() {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.clickOnDeleteButton();
    }

    /**
     * User selects file to upload files modal.
     *
     * @param fileName the file name
     */
    @When("user selects {string} file from their device")
    public void userSelectsFileToUploadFilesModal(String fileName) {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.uploadFileToModal("src/test/resources/testData/" + fileName);
    }

    /**
     * User sees delete btn on upload files modal.
     */
    @Then("user sees trash bin icon and enabled \"Delete\" button")
    public void userSeesDeleteBtnOnUploadFilesModal() {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.verifyDeleteBtnIsPresent();
        this.uploadFilesModalPage.verifyTrashBinIconIsPresent();
    }

    /**
     * User sees file name is displayed on upload files modal.
     */
    @Then("user sees \"accthist1.xlsx\" file is displayed under \"Upload\" field")
    public void userSeesFileNameIsDisplayedOnUploadFilesModal() {
        this.driverUtils.waitLoaderDisappear();
        this.uploadFilesModalPage.verifyFileNameIsPresent();
    }
}
