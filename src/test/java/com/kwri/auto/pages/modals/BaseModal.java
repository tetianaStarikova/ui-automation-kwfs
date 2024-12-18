package com.kwri.auto.pages.modals;

import com.google.inject.Inject;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.ui.pages.BasePage;
import com.kwri.auto.utils.Waits;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * The Base Modal page.
 */
@Slf4j
@Getter
public class BaseModal extends BasePage implements Waits {

    @FindBy(xpath = "//div[@data-testid='Banner']//div[contains(@class, 'styles_closeIconContainer')]")
    private WebElement closeBannerBtn;

    @FindBy(xpath = "//div[contains(@id, 'tippy')]")
    private WebElement lblTooltipMessage;

    @Inject
    private Common common;

    /**
     * Instantiates a new Correction Request popup.
     *
     * @param world the world
     */
    @Inject
    public BaseModal(final World world) {
        super(world);
    }

    /**
     * Gets lbl title.
     *
     * @return the lbl title
     */
    public WebElement getLblTitle() {
        this.common.waitForPageToLoad();
        return this.world.driver.findElement(By.xpath("//div[contains(@class, 'modal__title')]"));
    }

    /**
     * Gets btn cancel modal.
     *
     * @return the btn cancel modal
     */
    public WebElement getBtnCancelModal() {
        return this.world.driver
                .findElement(By.xpath("//div[contains(@class, 'modal')]//button[contains(text(), 'Cancel')]"));
    }

    /**
     * Gets btn close modal.
     *
     * @return the btn close modal
     */
    public WebElement getBtnCloseModal() {
        return this.world.driver.findElement(By.xpath("//span[contains(@class, 'modal__close')]"));
    }

    /**
     * Gets btn close Correction Request popup.
     *
     * @return the btn close Correction Request popup
     */
    public WebElement getTopRightBtnClosePopup() {
        return this.world.driver.findElement(By.xpath("//button[@aria-label='close']/span"));
    }
}
