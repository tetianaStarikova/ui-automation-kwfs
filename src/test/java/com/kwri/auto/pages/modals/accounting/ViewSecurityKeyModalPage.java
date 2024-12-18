package com.kwri.auto.pages.modals.accounting;

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
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.kwri.auto.enums.Endpoints.KW_FS_ACCOUNTS_API_KEY;
import static com.kwri.auto.enums.Endpoints.KW_FS_ORGANIZATION;
import static com.kwri.auto.enums.Versions.V1;
import static com.kwri.auto.enums.WaiterValues.TIMEOUT_60_SECONDS;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.PATCH;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * The type View security key modal page.
 */
@Getter
@Slf4j
@InflateContext
public class ViewSecurityKeyModalPage extends BaseModal {

    @FindBy(xpath = "//div[@data-testid='view-security-key-modal']//p")
    private WebElement lblSecurityKey;

    @FindBy(xpath = "//span[text()='Copy Key']//parent::button")
    private WebElement btnCopyKey;

    @FindBy(xpath = "//span[text()='Close']//parent::button")
    private WebElement btnCloseSecurityKeyModal;

    @Inject
    private Common common;
    @Inject
    private GeneralWhenSteps generalWhenSteps;
    @Inject
    private DriverUtils driverUtils;

    /**
     * Instantiates a new View security key modal page.
     *
     * @param world the world
     */
    @Inject
    public ViewSecurityKeyModalPage(final World world) {
        super(world);
    }

    /**
     * Gets lbl title security key.
     *
     * @return the lbl title security key
     */
    public WebElement getLblTitleSecurityKey() {
        return this.world.driver.findElement(By.xpath("//div[@data-testid='view-security-key-modal']"
                + "//span[text()='Your Security Key']"));
    }

    /**
     * Verify view security key modal is opened.
     */
    public void verifyViewSecurityKeyModalIsOpened() {
        this.common.waitForPageToLoad();
        try {
            this.common.waitUntilElementIsVisible(TIMEOUT_60_SECONDS.getValue(), getBtnCloseSecurityKeyModal());
        } catch (TimeoutException ex) {
            this.common.waitForPageToLoad();
            this.common.waitUntilElementIsVisible(TIMEOUT_60_SECONDS.getValue(), getBtnCopyKey());
        }
        assertThat(getLblTitleSecurityKey().getText())
                .as("Modal title is incorrect").isEqualTo("Your Security Key");
    }

    /**
     * Verify generated security key.
     *
     * @param orgId the org id
     * @param user  the user
     * @throws Exception the exception
     */
    public void verifyGeneratedSecurityKey(String orgId, User user) throws Exception {
        Response apiKeyResponse = this.generalWhenSteps.makeRequest(GET, KW_FS_ACCOUNTS_API_KEY, V1,
                new String[]{String.valueOf(orgId)}, user);
        apiKeyResponse.then().assertThat().statusCode(SC_OK);
        String apiKeyFromApi = apiKeyResponse.getBody().jsonPath().getString("apiKey");
        String apiKeyFromUi = getLblSecurityKey().getText();
        assertEquals("Api key is invalid!", apiKeyFromApi, apiKeyFromUi);
    }

    /**
     * Change vendor for organization.
     *
     * @param orgId    the org id
     * @param user     the user
     * @param vendorId the vendor id
     * @throws Exception the exception
     */
    public void changeVendorForOrganization(String orgId, User user, String vendorId) throws Exception {
        this.generalWhenSteps.addFieldToRequestBody("vendorId", vendorId);
        Response apiKeyResponse = this.generalWhenSteps.makeRequest(PATCH, KW_FS_ORGANIZATION, V1,
                new String[]{String.valueOf(orgId)}, user);
        apiKeyResponse.then().assertThat().statusCode(SC_NO_CONTENT);
    }

    /**
     * Verify copy key button is enabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    public void verifyCopyKeyButtonIsEnabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsDisabled(getBtnCopyKey(), shouldBeDisabled);
    }

    /**
     * Verify close button is enabled.
     *
     * @param shouldBeDisabled should be disabled
     */
    public void verifyCloseButtonIsEnabled(boolean shouldBeDisabled) {
        this.common.waitForPageToLoad();
        this.driverUtils.verifyElementIsDisabled(getBtnCloseSecurityKeyModal(), shouldBeDisabled);
    }
}
