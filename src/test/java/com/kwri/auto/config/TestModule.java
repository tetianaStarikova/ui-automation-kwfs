package com.kwri.auto.config;

import com.kwri.auto.core.extension.TestInjectableModule;
import com.kwri.auto.core.facade.RandomGeneratorFacade;
import com.kwri.auto.core.facade.RandomGeneratorFacadeImpl;
import com.kwri.auto.core.facade.VariableFacade;
import com.kwri.auto.core.facade.VariableFacadeImpl;
import com.kwri.auto.http.extension.AbstractBindingHttpModule;
import com.kwri.auto.http.extension.TestHttpPlugin;
import com.kwri.auto.http.facade.HttpAssertionFacade;
import com.kwri.auto.http.facade.HttpAssertionFacadeImpl;
import com.kwri.auto.http.logging.HttpLoggingPlugin;
import com.kwri.auto.rest.internal.PreprocessRestRequestBody;

/**
 * Injection module.
 */
@TestInjectableModule
public class TestModule extends AbstractBindingHttpModule {
    @Override
    protected void configure() {
        bind(HttpAssertionFacade.class).to(HttpAssertionFacadeImpl.class);
        bind(VariableFacade.class).to(VariableFacadeImpl.class);
        bind(RandomGeneratorFacade.class).to(RandomGeneratorFacadeImpl.class);
        registerHttpPlugin(HttpLoggingPlugin.class);
        registerHttpPlugin(CustomLogPlugin.class);
        registerHttpPlugin(PreprocessRestRequestBody.class);
        bindPlugins(TestHttpPlugin.class);
    }
}
