package com.kwri.auto.run;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

/**
 * Includes the cucumber engine as a test selector mechanism to JUnit5.
 * ConfigurationParameters can contain various Cucumber parameters.
 */
@Suite
@IncludeEngines("cucumber")
// https://github.com/cucumber/cucumber-jvm/tree/main/junit-platform-engine#supported-discovery-selectors-and-filters
@SelectClasspathResource("features") // << this must match the resource folder structure
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.kwri.auto")
public final class JUnitRunner { /* constructor for JUnit5 test runner */
    private JUnitRunner() {

    }
}
