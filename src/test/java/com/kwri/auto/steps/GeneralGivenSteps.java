package com.kwri.auto.steps;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.User;
import com.kwri.auto.http.facade.HttpResponseFacade;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

import static com.kwri.auto.enums.Endpoints.LOGIN;
import static com.kwri.auto.run.CucumberHooks.getTokenMap;
import static com.kwri.auto.run.CucumberHooks.setTokens;
import static io.restassured.http.Method.POST;
import static org.apache.http.HttpStatus.SC_BAD_GATEWAY;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.internal.Checks.notNull;

/**
 * List of general Given steps across repository.
 */
@Slf4j
@Singleton
@InflateContext
public class GeneralGivenSteps {
    @Inject
    private GeneralWhenSteps generalWhenSteps;
    @Inject
    private HttpResponseFacade responseFacade;
    private int badGatewayRetry;

    /**
     * Gets auth for user.
     *
     * @param user the user
     */
    @Given("user authorized as {user} user")
    public void getAuthForUser(User user) throws Exception {
        if (!getTokenMap().containsKey(user)) {
            this.generalWhenSteps.addFieldToRequestBody("username", user.getLogin());
            this.generalWhenSteps.addFieldToRequestBody("password", notNull(user.getPassword()));
            this.generalWhenSteps.makeRequest(POST, LOGIN, null);
            final int maxRetryCount = 4;
            if (this.responseFacade.response().statusCode() == SC_BAD_GATEWAY && badGatewayRetry < maxRetryCount) {
                badGatewayRetry++;
                getAuthForUser(user);
            } else {
                this.responseFacade.response().then().assertThat().statusCode(SC_OK);
                setTokens(user, this.responseFacade.response().then().extract()
                        .jsonPath().getString("access_token"));
            }
        }
    }
}
