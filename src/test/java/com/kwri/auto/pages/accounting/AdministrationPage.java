package com.kwri.auto.pages.accounting;

import com.google.inject.Inject;
import com.kwri.auto.ui.di.World;
import com.kwri.auto.ui.methods.Common;
import com.kwri.auto.ui.pages.BasePage;
import com.kwri.auto.utils.Waits;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Administration page.
 */
@Getter
@Slf4j
public class AdministrationPage extends BasePage implements Waits {

    @Inject
    private Common common;

    /**
     * Instantiates a new Administration page.
     *
     * @param world the world
     */
    @Inject
    public AdministrationPage(final World world) {
        super(world);
    }

    /**
     * Gets current URL.
     *
     * @return current URL
     */
    public String getCurrentUrlForPage() {
        this.common.waitForPageToLoad();
        return this.world.driver.getCurrentUrl();
    }
}
