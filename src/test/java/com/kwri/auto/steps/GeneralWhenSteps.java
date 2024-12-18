package com.kwri.auto.steps;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kwri.auto.config.Config;
import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.Endpoints;
import com.kwri.auto.enums.User;
import com.kwri.auto.enums.Versions;
import com.kwri.auto.http.facade.HttpRequestFacade;
import com.kwri.auto.http.facade.HttpResponseFacade;
import com.kwri.auto.rest.facade.RestRequestFacade;
import com.kwri.auto.run.JiraReportHooks;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import static com.kwri.auto.enums.User.AVENGERSTEAMRAINMAKER;
import static com.kwri.auto.run.CucumberHooks.getTokenMap;
import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.json.JSONObject.NULL;

/**
 * List of general When steps across repository.
 */
@Slf4j
@Data
@Singleton
@InflateContext
public class GeneralWhenSteps {
    @Inject
    private HttpResponseFacade responseFacade;
    @Inject
    private HttpRequestFacade requestFacade;
    @Inject
    private RestRequestFacade restRequestFacade;
    @Inject
    private JiraReportHooks jiraReportHooks;
    private String ongoingJiraTag;

    /**
     * Get JIRA tag of ongoing test to put it into User Agent header.
     *
     * @param scenario ongoing scenario
     */
    @Before
    public void getTestTag(Scenario scenario) {
        this.ongoingJiraTag = String.valueOf(this.jiraReportHooks.getJiraTicketNumber(scenario));
    }

    /**
     * This method will make request with version and base path.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param version    API version
     * @return response of the request
     */
    @When("the user performs {} request on {} endpoint of version {version}")
    public Response makeRequest(Method httpMethod, Endpoints url, Versions version) throws Exception {
        return makeRequest(httpMethod, url, version, AVENGERSTEAMRAINMAKER);
    }

    /**
     * This method will make request with version as user.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param version    API version
     * @param user       the user
     * @return the response
     */
    @When("the user performs {} request on {} endpoint of version {version} as {user} user")
    public Response makeRequest(Method httpMethod, Endpoints url, Versions version, User user) throws Exception {
        this.requestFacade.baseUri(Config.ENV.getBaseUriApi());
        this.requestFacade.overwriteHeader("Authorization", "Bearer " + getTokenMap().get(user));
        this.requestFacade.overwriteHeader("Content-Type", ContentType.JSON.withCharset(UTF_8));
        this.requestFacade.setTestHeader(user.getLogin());
        this.requestFacade.overwriteHeader("User-Agent",
                format("Avengers UI AMS Automation %s", getOngoingJiraTag()));
        this.responseFacade.doRequest(httpMethod.toString(),
                Config.ENV.getBaseUriApi() + versionManager(url.getUrl(), version));
        log.info("Response time: " + this.responseFacade.response().timeIn(TimeUnit.MILLISECONDS) + " ms");
        return responseFacade.response();
    }

    /**
     * This method will make request with version and parameters as user.
     *
     * @param httpMethod the http method
     * @param url        the url
     * @param version    API version
     * @param parameters the parameters
     * @param user       the user
     * @return the response
     */
    @When("the user performs {} request on {} endpoint of version {version} with parameters {pathParametersType} "
            + "as {user} user")
    public Response makeRequest(Method httpMethod, Endpoints url, Versions version, String[] parameters, User user)
            throws Exception {
        this.requestFacade.baseUri(Config.ENV.getBaseUriApi());
        this.requestFacade.overwriteHeader("Authorization", "Bearer " + getTokenMap().get(user));
        this.requestFacade.overwriteHeader("Content-Type", ContentType.JSON.withCharset(UTF_8));
        this.requestFacade.setTestHeader(user.getLogin());
        this.requestFacade.overwriteHeader("User-Agent",
                format("Avengers UI AMS Automation %s", getOngoingJiraTag()));
        this.responseFacade.doRequest(httpMethod.toString(),
                Config.ENV.getBaseUriApi() + versionManager(url.getUrl(parameters), version));
        log.info("Response time: " + this.responseFacade.response().timeIn(TimeUnit.MILLISECONDS) + " ms");
        return responseFacade.response();
    }

    /**
     * Query param.
     *
     * @param key   the key
     * @param value the value
     */
    @When("set query parameter {string} is {string}")
    public void queryParam(String key, String value) {
        if (!"-//-".equals(key)) {
            this.requestFacade.queryParam(key, value);
        }
    }

    /**
     * Add field to request body.
     *
     * @param key   the key
     * @param value the value
     */
    @When("add to request body {string}: {}")
    public void addFieldToRequestBody(String key, Object value) {
        JSONObject jsonBody = this.restRequestFacade.getRequestBody() == null
                ? new JSONObject()
                : new JSONObject(this.restRequestFacade.getRequestBody());
        if ("null".equals(value)) {
            jsonBody.put(key, NULL);
        } else if (value.toString().matches("\"[0-9]+\"")) {
            jsonBody.put(key, value.toString().replaceAll("\"", ""));
        } else if (value.toString().matches("[0-9]+")) {
            jsonBody.put(key, new BigInteger(value.toString()));
        } else if ("[]".equals(value)) {
            jsonBody.put(key, new JSONArray());
        } else if ("[{}]".equals(value)) {
            jsonBody.put(key, new JSONArray().appendElement(new JSONObject()));
        } else if ("[null]".equals(value)) {
            jsonBody.put(key, new JSONArray().appendElement(NULL));
        } else if (!"-//-".equals(value)) {
            jsonBody.put(key, value);
        }
        this.restRequestFacade.setRequestBody(jsonBody.toString());
    }

    /**
     * This method will replace "{version}" by version.
     *
     * @param url     string to process
     * @param version version to be used
     * @return url with processed "{version}" tag
     */
    private String versionManager(String url, Versions version) {
        if (version == null) {
            return url;
        }
        return url.replace("{version}", version.getVersion());
    }
}
