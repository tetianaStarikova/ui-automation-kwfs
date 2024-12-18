package com.kwri.auto.config;

import com.kwri.auto.http.extension.TestHttpPlugin;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of TestHttpPlugin with custom filters.
 */
@Slf4j
public class CustomLogPlugin implements TestHttpPlugin {
    @Override
    public void beforeRequest(RequestSpecification requestSpecification) {
        requestSpecification
                .filter(new CustomLogFilter()
                        .setRequestTemplate("http-request.ftl")
                        .setResponseAttachmentName("Response"));
    }

    @Override
    public void afterRequest(Response response) {

    }

    @Override
    public void beforeAllTests() {

    }

    @Override
    public void afterAllTests() {

    }

    @Override
    public void beforeScenario() {

    }

    @Override
    public void afterScenario() {

    }
}
