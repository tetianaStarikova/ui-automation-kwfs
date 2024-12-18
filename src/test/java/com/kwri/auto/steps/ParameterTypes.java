package com.kwri.auto.steps;

import com.kwri.auto.core.internal.context.InflateContext;
import com.kwri.auto.enums.ApiKeys;
import com.kwri.auto.enums.DefaultOrg;
import com.kwri.auto.enums.User;
import com.kwri.auto.enums.Versions;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;

import java.util.Arrays;

/**
 * This class contains custom parameter types for gherkin.
 */
@InflateContext
public class ParameterTypes {

    /**
     * Path parameters type list.
     *
     * @param pathParameters the path parameters
     * @return the list
     */
    @ParameterType("\\[(.*)\\]")
    public String[] pathParametersType(String pathParameters) {
        return Arrays.stream(pathParameters.split(", ?"))
                .map(String::toString)
                .toArray(String[]::new);
    }

    /**
     * User user.
     *
     * @param user the user
     * @return the user
     */
    @ParameterType(value = "\\b[A-Za-z0-9_]{2,}\\b", name = "user")
    public User user(String user) {
        return User.valueOf(user);
    }

    /**
     * Default org string.
     *
     * @param defaultOrg the default org
     * @return the string
     */
    @ParameterType(value = "[A-Z0-9_]+", name = "defaultOrg")
    public String defaultOrg(String defaultOrg) {
        return String.valueOf(DefaultOrg.valueOf(defaultOrg).getValue());
    }

    /**
     * List of string lists type string.
     *
     * @param cell the cell
     * @return the string
     */
    @DataTableType(replaceWithEmptyString = "[blank]")
    public String listOfStringListsType(String cell) {
        return cell;
    }

    /**
     * API key type string.
     *
     * @param apiKey the api key
     * @return the string
     */
    @ParameterType(value = "[A-Z_]+", name = "apiKey")
    public ApiKeys apiKey(String apiKey) {
        return ApiKeys.valueOf(apiKey);
    }

    /**
     * Version of end-point.
     *
     * @param version of end-point
     * @return end-point version
     */
    @ParameterType(value = "V\\d{1}", name = "version")
    public Versions version(String version) {
        return Versions.valueOf(version);
    }

}
