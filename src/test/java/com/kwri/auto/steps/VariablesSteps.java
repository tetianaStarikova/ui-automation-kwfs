package com.kwri.auto.steps;

import com.google.inject.Inject;
import com.kwri.auto.core.facade.VariableFacade;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.User;
import com.kwri.auto.http.facade.HttpResponseFacade;
import com.kwri.auto.utils.DataFaker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.kwri.auto.config.Config.PROPERTIES;
import static org.apache.commons.lang3.RandomStringUtils.*;
import static org.hamcrest.Matchers.notNullValue;

/**
 * The type Variables steps.
 */
@InflateContext
public class VariablesSteps {
    @Inject
    private VariableFacade variableFacade;
    @Inject
    private DataFaker dataFaker;
    @Inject
    private HttpResponseFacade responseFacade;

    /**
     * Assign to variable random alphanumeric value with prefix and length.
     *
     * @param key    the key
     * @param prefix the prefix
     * @param length the length
     */
    @When("assign to {string} variable random Alphanumeric value with {string} prefix and {int} length")
    public void assignToVariableRandomAlphanumericValueWithPrefixAndLength(String key, String prefix, int length) {
        this.variableFacade.setVariable(key, prefix + randomAlphanumeric(length));
    }

    /**
     * Assign to variable random numeric value with length.
     *
     * @param key    the key
     * @param length the length
     */
    @When("assign to {string} variable random numeric value with {int} length")
    public void assignToVariableRandomNumericValueWithLength(String key, int length) {
        this.variableFacade.setVariable(key, random(length, '1', '2', '3', '4', '5', '6', '7', '8', '9'));
    }

    /**
     * Assign to variable random numeric value with length.
     *
     * @param key    the key
     * @param length the length
     * @param prefix the prefix
     */
    @When("assign to {string} variable random numeric value with {string} prefix and {int} length")
    public void assignToVariableRandomNumericValueWithPrefixAndLength(String key, String prefix, int length) {
        this.variableFacade.setVariable(key, prefix + randomNumeric(length));
    }

    /**
     * Assign to variable value.
     *
     * @param key   the key
     * @param value the value
     */
    @When("assign to {string} variable {string} value")
    public void initializeVariable(String key, String value) {
        this.variableFacade.setVariable(key, value);
    }

    /**
     * Assign to variable value.
     *
     * @param key   the key
     * @param value the value
     */
    @Given("assign to {string} variable {defaultOrg} value")
    public void assignToVariableValue(String key, String value) {
        this.variableFacade.setVariable(key, value);
    }

    /**
     * Assign to variable value from properties.
     *
     * @param variable the variable
     * @param value    the value
     */
    @Given("assign to {string} value {string} from properties")
    public void letVariableEqualToValueFromProperties(String variable, String value) {
        this.variableFacade.setVariable(variable, PROPERTIES.getProperty(value));
    }

    /**
     * This method generate random first name and assign to variable.
     *
     * @param firstName — random generated first name
     */
    @Given("assign the random first name to {string} variable")
    public void setRandomFirstName(String firstName) {
        this.variableFacade.setVariable(firstName, this.dataFaker.getRandomFirstName());
    }

    /**
     * This method generate random last name and assign to variable.
     *
     * @param lastName — random generated last name
     */
    @Given("assign the random last name to {string} variable")
    public void setRandomLastName(String lastName) {
        this.variableFacade.setVariable(lastName, this.dataFaker.getRandomLastName());
    }

    /**
     * Save value as.
     *
     * @param key      the key
     * @param jsonPath the json path
     */
    @When("assign to {string} variable value from response property {string}")
    public void saveValueAs(String key, String jsonPath) {
        this.responseFacade.response().then().assertThat().body(jsonPath, notNullValue());
        this.variableFacade.setVariable(key, this.responseFacade.response().jsonPath().get(jsonPath).toString());
    }

    /**
     * Assign to variable first date of the current year.
     *
     * @param key     the key
     * @param pattern the pattern
     */
    @Given("assign to {string} variable first date of current year in {string} format")
    public void assignToVariableFirstDateOfCurrentYear(String key, String pattern) {
        LocalDate firstDayOfCurrentYear = LocalDate.now().withDayOfYear(1);
        this.variableFacade.setVariable(key, firstDayOfCurrentYear.format(DateTimeFormatter.ofPattern(pattern)));
    }

    /**
     * Assign to kw uid of user.
     *
     * @param key  the key
     * @param user the user
     */
    @Given("assign to {string} kwUid of {user} user")
    public void assignToKwUidOfUser(String key, User user) {
        initializeVariable(key, String.valueOf(user.getKwUid()));
    }
}
