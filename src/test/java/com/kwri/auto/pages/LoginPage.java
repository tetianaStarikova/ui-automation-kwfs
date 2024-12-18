package com.kwri.auto.pages;

import com.google.inject.Inject;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.pages.BasePage;
import com.kwri.auto.utils.Waits;
import lombok.Getter;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Date;

import static com.kwri.auto.enums.WaiterValues.TIMEOUT_30_SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Page object for Login page.
 */
@Getter
public class LoginPage extends BasePage implements Waits {

    @FindBy(css = "input[id='username']")
    private WebElement usernameInput;

    @FindBy(css = "input[id='password']")
    private WebElement passwordInput;
    @FindBy(css = "button[id='signInButton']")
    private WebElement submitBtn;
    @FindBy(xpath = "//h1[text()='The Smartest Ads in Real Estate']")
    private WebElement smartTestAddsPopUp;
    @FindBy(xpath = "//h1[text()='The Smartest Ads in Real Estate']//parent::div"
            + "//following-sibling::span//div[@data-testid='close-control']//button")
    private WebElement btnCloseSmartTestAddsPopUp;

    /**
     * Instantiates a new Login page.
     *
     * @param world the world
     */
    @Inject
    public LoginPage(final World world) {
        super(world);
    }

    /**
     * Accept onboarding.
     */
    public void acceptOnboarding() {
        getWait(this.world.driver, TIMEOUT_30_SECONDS.getValue());
        try {
            getWait(this.world.driver, TIMEOUT_30_SECONDS.getValue()).until(urlContains("onboarding"));
            String changedUrl = this.world.driver.getCurrentUrl().replace("onboarding?redirectTo=/command", "command");
            this.world.driver.get(changedUrl);
            getWait(this.world.driver, TIMEOUT_30_SECONDS.getValue()).until(urlContains("command"));
        } catch (TimeoutException exception) {
            getWait(this.world.driver, TIMEOUT_30_SECONDS.getValue()).until(urlContains("command"));
        }
    }

    /**
     * Accept smartTest Adds.
     */
    public void closeSmartTestAdds() {
        getWait(this.world.driver, TIMEOUT_30_SECONDS.getValue());
        if (isSmartTestAddsModalPresent()) {
            getBtnCloseSmartTestAddsPopUp().click();
        }
    }

    /**
     * Sets cookies with language.
     *
     * @param language the language
     */
    public void setCookiesWithLanguage(String language) {
        Date date = new Date();
        Cookie newCookie = new Cookie("user_language", "{%22locale%22:%22" + language
                + "%22%2C%22personal%22:%22\" + language + \"%22}", ".kw.com", "/", date);
        this.world.driver.manage().addCookie(newCookie);
    }

    /**
     * Gets Administration cloudmore button.
     *
     * @return administration cloudmore button
     */
    public WebElement getSubmitBtn() {
        return getWait(this.world.driver, TIMEOUT_30_SECONDS.getValue()).until(visibilityOf(submitBtn));
    }

    /**
     * Verify is Smart Test Adds Modal Present.
     *
     * @return isPresent is Adds Modal Present
     */
    private boolean isSmartTestAddsModalPresent() {
        boolean isPresent;
        try {
            getSmartTestAddsPopUp().isDisplayed();
            isPresent = true;
        } catch (NoSuchElementException e) {
            isPresent = false;
        }
        return isPresent;
    }
}
