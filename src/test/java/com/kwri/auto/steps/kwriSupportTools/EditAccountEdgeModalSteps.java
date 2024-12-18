package com.kwri.auto.steps.kwriSupportTools;

import com.google.inject.Inject;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.User;
import com.kwri.auto.pages.modals.kwriSupportTools.EditAccountEdgeModalPage;
import com.kwri.auto.utils.DriverUtils;
import io.cucumber.java.en.Then;

/**
 * The type Edit account edge modal steps.
 */
@InflateContext
public class EditAccountEdgeModalSteps {

    @Inject
    private EditAccountEdgeModalPage editAccountEdgeModalPage;

    @Inject
    private DriverUtils driverUtils;

    /**
     * User sees edit account edge modal is opened.
     */
    @Then("user sees 'Edit Account Edge' modal is open")
    public void userSeesEditAccountEdgeModalIsOpened() {
        this.driverUtils.waitLoaderDisappear();
        this.editAccountEdgeModalPage.verifyEditAccountEdgeModalIsOpened();
    }

    /**
     * User sees Close button in right upper corner of edit account edge modal.
     */
    @Then("user sees enabled \"Close\" button in right upper corner of Edit Account Edge modal")
    public void userSeesCloseBtnInRightUpperCornerOfEditAccountEdgeModal() {
        this.driverUtils.waitLoaderDisappear();
        this.editAccountEdgeModalPage.verifyCloseButtonIsDisplayedInTheUpperRightSideOfEditAccountEdgeModal();
    }

    /**
     * User sees info about mc on edit account edge modal.
     *
     * @param user  the user
     * @param orgId the org id
     * @throws Exception the exception
     */
    @Then("user {user} sees next MC info for organization {defaultOrg} Edit Account Edge modal")
    public void userSeesInfoAboutMCOnEditAccountEdgeModal(User user, String orgId) throws Exception {
        this.driverUtils.waitLoaderDisappear();
        this.editAccountEdgeModalPage.verifyKwfsInfoForOrganization(user, orgId);
    }

    /**
     * User sees cancel btn on edit account edge modal.
     *
     * @param shouldBeDisabled should be disabled
     */
    @Then("user sees disabled {} 'Cancel' button in the bottom of Edit Account Edge modal")
    public void userSeesCancelBtnOnEditAccountEdgeModal(boolean shouldBeDisabled) {
        this.driverUtils.waitLoaderDisappear();
        this.editAccountEdgeModalPage.verifyCancelButtonIsEnabled(shouldBeDisabled);
    }

    /**
     * User sees Save button on edit account edge modal.
     *
     * @param shouldBeDisabled should be disabled
     */
    @Then("user sees disabled {} 'Save' button in the bottom of Edit Account Edge modal")
    public void userSeesSaveBtnOnEditAccountEdgeModal(boolean shouldBeDisabled) {
        this.driverUtils.waitLoaderDisappear();
        this.editAccountEdgeModalPage.verifySaveButtonIsEnabled(shouldBeDisabled);
    }

    /**
     * User selects vendor on edit account edge modal.
     *
     * @param vendorName the vendor name
     */
    @Then("user selects {string} vendor value Edit Account Edge modal")
    public void userSelectsVendorOnEditAccountEdgeModal(String vendorName) {
        this.driverUtils.waitLoaderDisappear();
        this.editAccountEdgeModalPage.selectVendor(vendorName);
    }

    /**
     * User clicks save button on edit account edge modal.
     */
    @Then("user clicks on enabled \"Save\" button for Edit Account Edge modal")
    public void userClicksSaveButtonOnEditAccountEdgeModal() {
        this.driverUtils.waitLoaderDisappear();
        this.editAccountEdgeModalPage.clickOnSaveButton();
    }
}
