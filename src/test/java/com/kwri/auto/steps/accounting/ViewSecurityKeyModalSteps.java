package com.kwri.auto.steps.accounting;

import com.google.inject.Inject;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.User;
import com.kwri.auto.pages.modals.accounting.ViewSecurityKeyModalPage;
import com.kwri.auto.utils.DriverUtils;
import io.cucumber.java.en.Then;

/**
 * The type View Security Key Modal steps.
 */
@InflateContext
public class ViewSecurityKeyModalSteps {

    @Inject
    private ViewSecurityKeyModalPage viewSecurityKeyModalPage;

    @Inject
    private DriverUtils driverUtils;

    /**
     * User sees view security key modal is opened.
     */
    @Then("user sees \"Your Security Key\" modal is open")
    public void userSeesViewSecurityKeyModalIsOpened() {
        this.driverUtils.waitLoaderDisappear();
        this.viewSecurityKeyModalPage.verifyViewSecurityKeyModalIsOpened();
    }

    /**
     * User sees generated security key.
     *
     * @param user  the user
     * @param orgId the org id
     * @throws Exception the exception
     */
    @Then("user {user} sees generated security key for MC {defaultOrg} with Accountedge 1 as accounting vendor")
    public void userSeesGeneratedSecurityKey(User user, String orgId) throws Exception {
        this.driverUtils.waitLoaderDisappear();
        this.viewSecurityKeyModalPage.verifyGeneratedSecurityKey(orgId, user);
    }

    /**
     * User update accounting vendor.
     *
     * @param user     the user
     * @param vendorId the vendor id
     * @param orgId    the org id
     * @throws Exception the exception
     */
    @Then("user {user} update accounting vendor to {string} for organization {defaultOrg}")
    public void userUpdateAccountingVendor(User user, String vendorId, String orgId) throws Exception {
        this.driverUtils.waitLoaderDisappear();
        this.viewSecurityKeyModalPage.changeVendorForOrganization(orgId, user, vendorId);
    }

    /**
     * User sees copy key and close buttons.
     *
     * @param isCopyKeyBtnDisabled the is copy key btn disabled
     * @param isCloseBtnDisabled   the is close btn disabled
     */
    @Then("user sees disabled {} \"Copy Key\" button and disabled {} \"Close\" in the bottom of modal")
    public void userSeesCopyKeyAndCloseButtons(boolean isCopyKeyBtnDisabled, boolean isCloseBtnDisabled) {
        this.driverUtils.waitLoaderDisappear();
        this.viewSecurityKeyModalPage.verifyCopyKeyButtonIsEnabled(isCopyKeyBtnDisabled);
        this.viewSecurityKeyModalPage.verifyCloseButtonIsEnabled(isCloseBtnDisabled);
    }
}
