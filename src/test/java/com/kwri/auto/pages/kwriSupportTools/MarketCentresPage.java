package com.kwri.auto.pages.kwriSupportTools;

import com.google.inject.Inject;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.User;
import com.kwri.auto.model.OrgModel;
import com.kwri.auto.steps.GeneralWhenSteps;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.ui.pages.BasePage;
import com.kwri.auto.utils.DriverUtils;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.kwri.auto.enums.Endpoints.*;
import static com.kwri.auto.enums.Versions.V1;
import static io.restassured.http.Method.GET;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

/**
 * The type Market Centres tab page.
 */
@Getter
@Slf4j
@InflateContext
public class MarketCentresPage extends BasePage {
    @FindBy(xpath = "//div[contains(@class, 'styles_viewport')]//h2")
    private WebElement lblOrgName;

    @FindBy(xpath = "//div[text()='AccountEdge']")
    private WebElement btnAccountEdgeTab;

    @FindBy(xpath = "//span[text()='Change Vendor Type']//parent::button")
    private WebElement btnChangeVendorType;

    @FindBy(xpath = "//button[@data-testid='copy-api-key-button']")
    private WebElement btnCopyApiKey;

    @FindBy(xpath = "//button[@data-testid='generate-new-api-key-button']")
    private WebElement btnGenerateApiKey;

    @FindBy(xpath = "//div[@data-testid='kwfs-vendor-name']")
    private WebElement lblKwfsVendorName;

    @FindBy(xpath = "//div[@data-testid='kwfs-status']")
    private WebElement lblKwfsStatus;

    @FindBy(xpath = "//div[@data-testid='kwfs-current-account-date']")
    private WebElement lblKwfsCurrentAccountDate;

    @FindBy(xpath = "//div[@data-testid='kwfs-currency']")
    private WebElement lblKwfsCurrency;

    @FindBy(xpath = "//div[@data-testid='org-api-key']")
    private WebElement lblKwfsOrgApiKey;

    @FindBy(xpath = "//li[@data-test='kwri-support-tools-tab-chart-of-accounts']//a")
    private WebElement btnChartOfAccounts;

    @Inject
    private Common common;

    @Inject
    private DriverUtils driverUtils;

    @Inject
    private GeneralWhenSteps generalWhenSteps;
    /**
     * Gets current URL.
     *
     * @return current URL
     */
    public String getCurrentUrlForPage() {
        this.common.waitForPageToLoad();
        return this.world.driver.getCurrentUrl();
    }

    /**
     * User opens chart of accounts sub menu.
     */
    public void userOpensChartOfAccountsSubMenu() {
        this.common.waitForPageToLoad();
        getBtnChartOfAccounts().click();
    }


    /**
     * Instantiates a new Market Centres tab page.
     *
     * @param world the world
     */
    @Inject
    public MarketCentresPage(final World world) {
        super(world);
    }

    /**
     * Verify mc name and number.
     *
     * @param user  the user
     * @param orgId the org id
     * @throws Exception the exception
     */
    public void verifyOrgNameAndNumber(User user, String orgId) throws Exception {
        this.common.waitForPageToLoad();
        this.common.waitForAjaxRequestsComplete();

        OrgModel orgModelResponse = getInfAboutOrg(String.valueOf(orgId), user)
                .jsonPath().getObject("data", OrgModel.class);
        assertEquals("Error - expected orgName is invalid - expected: " + orgModelResponse.getOrgKey() + " - "
                        + orgModelResponse.getName() + ", but found: " + getLblOrgName().getText(),
                orgModelResponse.getOrgKey() + " - " + orgModelResponse.getName(),
                getLblOrgName().getText());
    }

    /**
     * Verify account edge tab is present.
     *
     * @param shouldBePresent should be present
     */
    public void verifyAccountEdgeTabIsPresent(boolean shouldBePresent) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsPresent(getBtnAccountEdgeTab(), shouldBePresent);
    }

    /**
     * Verify change vendor type btn is disabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    public void verifyChangeVendorTypeBtnIsDisabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        assertEquals("The Change Vendor Type button should be enabled" + shouldBeDisabled + ", but found - "
                        + Boolean.parseBoolean(getBtnChangeVendorType().getAttribute("aria-disabled")),
                shouldBeDisabled, Boolean.parseBoolean(getBtnChangeVendorType().getAttribute("aria-disabled")));
    }

    /**
     * Click on change vendor type button.
     */
    public void clickOnChangeVendorTypeBtn() {
        this.common.waitForPageToLoad();
        getBtnChangeVendorType().click();
    }

    /**
     * Verify generate new api key btn is disabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    public void verifyGenerateNewApiKeyBtnIsDisabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        assertEquals("The Generate New Api Key button should be enabled" + shouldBeDisabled
                        + ", but found - " + Boolean.parseBoolean(getBtnGenerateApiKey().getAttribute("aria-disabled")),
                shouldBeDisabled, Boolean.parseBoolean(getBtnGenerateApiKey().getAttribute("aria-disabled")));
    }

    /**
     * Verify api key value for organization.
     *
     * @param user  the user
     * @param orgId the org id
     * @throws Exception the exception
     */
    public void verifyApiKeyValueForOrganization(User user, String orgId) throws Exception {
        this.common.waitForPageToLoad();
        Response apiKeyResponse = this.generalWhenSteps.makeRequest(GET, KW_FS_ACCOUNTS_API_KEY, V1,
                new String[]{String.valueOf(orgId)}, user);
        apiKeyResponse.then().assertThat().statusCode(SC_OK);
        String apiKeyFromApi = apiKeyResponse.getBody().jsonPath().getString("apiKey");
        String apiKeyFromUi = getLblKwfsOrgApiKey().getText();
        assertEquals("Api key is invalid!", apiKeyFromApi, apiKeyFromUi);
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
                .isEqualTo(getLblKwfsVendorName().getText());
        assertions.assertThat(getAccountStatus(kwfsOrganizationResponse)).as("Account status is invalid!")
                .isEqualTo(getLblKwfsStatus().getText());
        assertions.assertThat(getCurrency(user, orgId)).as("Account currency is invalid!")
                .isEqualTo(getLblKwfsCurrency().getText());
        assertions.assertThat(getCurrentAccountDate(kwfsOrganizationResponse)).as("Current Account date is invalid!")
                .isEqualTo(getLblKwfsCurrentAccountDate().getText());
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

    /**
     * Verify copy icon is present.
     *
     * @param shouldBePresent the should be present
     */
    public void verifyCopyIconIsPresent(boolean shouldBePresent) {
        this.common.waitForPageToLoad();
        assertEquals("Copy icon should be present!", getBtnCopyApiKey().isDisplayed(), shouldBePresent);
    }

    /**
     * Open AccountEdge Tab.
     */
    public void openAccountEdgeTab() {
        this.common.waitForPageToLoad();
        getBtnAccountEdgeTab().click();
    }

    /**
     * Click on generate new api key btn.
     */
    public void clickOnGenerateNewApiKeyBtn() {
        this.common.waitForPageToLoad();
        getBtnGenerateApiKey().click();
    }

    /**
     * This method will return response of GET /orgs/:id end-point.
     *
     * @param orgId id of org
     * @param user  user for auth
     * @return response with info about org
     */
    private Response getInfAboutOrg(String orgId, User user) throws Exception {
        Response response = this.generalWhenSteps.makeRequest(GET, ORGS, V1, new String[]{orgId}, user);
        response.then().assertThat().statusCode(SC_OK);
        return response;
    }
}
