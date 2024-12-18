package com.kwri.auto.factory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.kwri.auto.core.extension.TestInjectableModule;
import com.kwri.auto.core.internal.di.BindingGuiceModule;
import io.cucumber.core.backend.ObjectFactory;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.ScenarioScope;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Threaded object factory.
 */
public class ThreadedObjectFactory implements ObjectFactory {
    private final ThreadLocal<Injector> localInjector = new ThreadLocal<>();

    /**
     * Get Injector.
     *
     * @return injector
     */
    private Injector getInjector() {
        lazyInitInjector();
        return localInjector.get();
    }

    /**
     * Start.
     */
    public void start() {
        getInjector().getInstance(ScenarioScope.class).enterScope();
    }

    /**
     * Stop.
     */
    public void stop() {
        getInjector().getInstance(ScenarioScope.class).exitScope();
    }

    /**
     * Add Class.
     *
     * @param aClass aClass
     * @return is a class
     */
    public boolean addClass(Class<?> aClass) {
        return true;
    }

    /**
     * Get Instance.
     *
     * @param aClass aClass
     * @param <T>    type
     * @return instance
     */
    public <T> T getInstance(Class<T> aClass) {
        return getInjector().getInstance(aClass);
    }

    /**
     * Lazy Init Injector.
     */
    private void lazyInitInjector() {
        if (localInjector.get() == null) {
            Set<Module> modules = new HashSet<>();

            modules.add(CucumberModules.createScenarioModule());
            modules.add(new BindingGuiceModule());

            modules.addAll(createInstancesOf(TestInjectableModule.class, "com.kwri.auto"));

            localInjector.set(Guice.createInjector(Stage.PRODUCTION, modules));
        }
    }

    /**
     * Create Instances Of.
     *
     * @param annotation  the annotation
     * @param scanPackage the scanPackage
     * @param <T>         type
     * @return instance of
     */
    private <T> Set<T> createInstancesOf(Class<? extends Annotation> annotation, String scanPackage) {
        return new Reflections(scanPackage)
                .getTypesAnnotatedWith(annotation)
                .stream()
                .map(aClass -> {
                    try {
                        return (T) aClass.getConstructor().newInstance();
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
