package com.kwri.auto.steps;

import com.google.inject.Inject;
import com.kwri.auto.http.facade.HttpResponseFacade;
import io.cucumber.java.en.Then;

/**
 * This class contains steps of basic validation across all EPs.
 */
public class GeneralThenSteps {
    @Inject
    private HttpResponseFacade responseFacade;

    /**
     * This method will test that status code of the response equals to expected.
     *
     * @param expectedStatusCode {@link Integer} expected status code.
     */
    @Then("response status code is {int}")
    public void responseStatusCodeIs(int expectedStatusCode) {
        this.responseFacade.response().then().statusCode(expectedStatusCode);
    }
}
