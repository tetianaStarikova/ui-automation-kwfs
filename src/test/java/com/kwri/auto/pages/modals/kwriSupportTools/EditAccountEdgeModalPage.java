package com.kwri.auto.pages.modals.kwriSupportTools;

import com.google.inject.Inject;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.User;
import com.kwri.auto.pages.modals.BaseModal;
import com.kwri.auto.steps.GeneralWhenSteps;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.utils.DriverUtils;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.kwri.auto.enums.Endpoints.KW_FS_ORGANIZATION;
import static com.kwri.auto.enums.Endpoints.ORGS;
import static com.kwri.auto.enums.Versions.V1;
import static com.kwri.auto.enums.WaiterValues.TIMEOUT_60_SECONDS;
import static io.restassured.http.Method.GET;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * The type Edit account edge modal page.
 */
@Getter
@Slf4j
@InflateContext
public class EditAccountEdgeModalPage extends BaseModal {

    @FindBy(xpath = "//h1[text()='Edit Account Edge']")
    private WebElement lblTitleEditAccountEdge;

    @FindBy(xpath = "//h1[text()='Edit Account Edge']//following-sibling::div/p")
    private WebElement lblMarketCenterName;

    @FindBy(xpath = "//label[text()='Vendor']")
    private WebElement lblVendor;

    @FindBy(xpath = "//input[contains(@class, 'dummyInput')]")
    private WebElement inpVendor;

    @FindBy(xpath = "//div[@data-testid= 'status']")
    private WebElement statusValue;

    @FindBy(xpath = "//div[@data-testid= 'current-account-date']")
    private WebElement currentAccountDateValue;

    @FindBy(xpath = "//div[@data-testid= 'currency']")
    private WebElement currencyValue;

    @FindBy(xpath = "//input//preceding-sibling::div[contains(@class, 'singleValue')]")
    private WebElement lblAccountName;

    @FindBy(xpath = "//span[text()='Cancel']//parent::button")
    private WebElement btnCancelEditAccountEdge;

    @FindBy(xpath = "//span[text()='Save']//parent::button")
    private WebElement btnSaveEditAccountEdge;

    @FindBy(xpath = "//div[contains(@id, 'react-select')]")
    private List<WebElement> lstVendorOptions;

    @Inject
    private Common common;
    @Inject
    private GeneralWhenSteps generalWhenSteps;
    @Inject
    private DriverUtils driverUtils;

    /**
     * Instantiates a new Edit account edge modal page.
     *
     * @param world the world
     */
    @Inject
    public EditAccountEdgeModalPage(final World world) {
        super(world);
    }

    /**
     * Verify edit account edge modal is opened.
     */
    public void verifyEditAccountEdgeModalIsOpened() {
        this.common.waitForPageToLoad();
        try {
            this.common.waitUntilElementIsVisible(TIMEOUT_60_SECONDS.getValue(), getBtnCancelEditAccountEdge());
        } catch (TimeoutException ex) {
            this.common.waitForPageToLoad();
            this.common.waitUntilElementIsVisible(TIMEOUT_60_SECONDS.getValue(), getTopRightBtnClosePopup());
        }
        assertThat(getLblTitleEditAccountEdge().getText())
                .as("Modal title is incorrect").isEqualTo("Edit Account Edge");
    }

    /**
     * Verify close button is displayed in the upper right side of edit account edge modal.
     */
    public void verifyCloseButtonIsDisplayedInTheUpperRightSideOfEditAccountEdgeModal() {
        this.common.waitForPageToLoad();
        assertTrue("Close button should be displayed in the upper right side of popup",
                getTopRightBtnClosePopup().isEnabled());
    }

    /**
     * Verify cancel button is enabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    public void verifyCancelButtonIsEnabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsDisabled(getBtnCancelEditAccountEdge(), shouldBeDisabled);
    }

    /**
     * Verify save button is enabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    public void verifySaveButtonIsEnabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsDisabled(getBtnSaveEditAccountEdge(), shouldBeDisabled);
    }

    /**
     * Click on Save button.
     */
    public void clickOnSaveButton() {
        this.common.waitForPageToLoad();
        getBtnSaveEditAccountEdge().click();
    }

    /**
     * Select vendor.
     *
     * @param vendorName the vendor name
     */
    public void selectVendor(String vendorName) {
        this.common.waitForPageToLoad();
        getLblAccountName().click();
        this.common.waitForPageToLoad();
        getLstVendorOptions().stream()
                .filter(x -> x.getText().equals(vendorName))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Vendor '" + vendorName + "' doesn't exist"))
                .click();
        this.common.waitForPageToLoad();
    }

    /**
     * Verify KWFS info for organization.
     *
     * @param user  the user
     * @param orgId the org id
     * @throws Exception the exception
     */
    public void verifyKwfsInfoForOrganization(User user, String orgId) throws Exception {
        this.common.waitForPageToLoad();
        Response kwfsOrganizationResponse = this.generalWhenSteps.makeRequest(GET, KW_FS_ORGANIZATION, V1,
                new String[]{String.valueOf(orgId)}, user);
        kwfsOrganizationResponse.then().assertThat().statusCode(SC_OK);
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(getAccountVendor(kwfsOrganizationResponse)).as("Account vendor name is invalid!")
                .isEqualTo(getLblAccountName().getText());
        assertions.assertThat(getAccountStatus(kwfsOrganizationResponse)).as("Account status is invalid!")
                .isEqualTo(getStatusValue().getText());
        assertions.assertThat(getCurrency(user, orgId)).as("Account currency is invalid!")
                .isEqualTo(getCurrencyValue().getText());
        assertions.assertThat(getCurrentAccountDate(kwfsOrganizationResponse)).as("Current Account date is invalid!")
                .isEqualTo(getCurrentAccountDateValue().getText());
        assertions.assertAll();
    }

    /**
     * Get Account Vendor name.
     *
     * @param kwfsOrganizationResponse KWFS Organization Response
     * @return vendor name
     */
    private String getAccountVendor(Response kwfsOrganizationResponse) {
        String vendor;
        if (kwfsOrganizationResponse.getBody().jsonPath().get("vendorId").equals(1)) {
            vendor = "AccountEdge";
        } else {
            vendor = "AccountEdge International";
        }
        return vendor;
    }

    /**
     * Get Account Vendor status.
     *
     * @param kwfsOrganizationResponse KWFS Organization Response
     * @return status of vendor
     */
    private String getAccountStatus(Response kwfsOrganizationResponse) {
        String status;
        if (kwfsOrganizationResponse.getBody().jsonPath().get("status").equals(1)) {
            status = "Active";
        } else {
            status = "Inactive";
        }
        return status;
    }

    /**
     * Get Currency for Organization.
     *
     * @param user  the user
     * @param orgId the org id
     * @return currency for organization
     * @throws Exception the exception
     */
    private String getCurrency(User user, String orgId) throws Exception {
        Response response = this.generalWhenSteps.makeRequest(GET, ORGS, V1, new String[]{orgId}, user);
        response.then().assertThat().statusCode(SC_OK);
        String currency;
        if (response.getBody().jsonPath().get("data.country.code").equals("US")) {
            currency = "USD";
        } else {
            currency = "CAD";
        }
        return currency;
    }

    /**
     * Get Current Account Date.
     *
     * @param kwfsOrganizationResponse KWFS Organization Response
     * @return Current Account Date
     */
    private String getCurrentAccountDate(Response kwfsOrganizationResponse) throws ParseException {
        DateFormat sourceFormat = new SimpleDateFormat("MM/yyyy");
        String dateFromApi = kwfsOrganizationResponse.getBody().jsonPath().get("accountingMonth") + "/"
                + kwfsOrganizationResponse.getBody().jsonPath().get("accountingYear");
        Date date = sourceFormat.parse(dateFromApi);
        return sourceFormat.format(date);
    }
}
