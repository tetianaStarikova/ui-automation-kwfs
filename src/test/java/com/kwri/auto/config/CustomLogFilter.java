package com.kwri.auto.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.qameta.allure.attachment.DefaultAttachmentProcessor;
import io.qameta.allure.attachment.FreemarkerAttachmentRenderer;
import io.qameta.allure.attachment.http.HttpRequestAttachment;
import io.qameta.allure.attachment.http.HttpResponseAttachment;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.FilterContext;
import io.restassured.internal.NameAndValue;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class contains logic for filtering out sensetive data from console logs and reports.
 */
@Slf4j
public final class CustomLogFilter extends AllureRestAssured {
    private String requestTemplatePath = "http-request.ftl";
    private String responseTemplatePath = "http-response.ftl";
    private String responseAttachmentName;

    /**
     * This method will set name for Allure attachments.
     *
     * @param responseAttachment the response attachment
     * @return response Attachment Name
     */
    public AllureRestAssured setResponseAttachmentName(String responseAttachment) {
        this.responseAttachmentName = responseAttachment;
        return this;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext filterContext) {
        Prettifier prettifier = new Prettifier();
        HttpRequestAttachment.Builder requestAttachmentBuilder =
                HttpRequestAttachment.Builder.create("Request",
                                replaceSensitiveData(requestSpec.getURI()))
                        .setMethod(requestSpec.getMethod())
                        .setHeaders(toMapConverter(requestSpec.getHeaders()))
                        .setCookies(toMapConverter(requestSpec.getCookies()));
        if (Objects.nonNull(requestSpec.getBody())) {
            requestAttachmentBuilder.setBody(replaceSensitiveData(
                    prettifier.getPrettifiedBodyIfPossible(requestSpec)));
        }

        HttpRequestAttachment requestAttachment = requestAttachmentBuilder.build();
        (new DefaultAttachmentProcessor()).addAttachment(requestAttachment,
                new FreemarkerAttachmentRenderer(this.requestTemplatePath));
        Response response = filterContext.next(requestSpec, responseSpec);
        HttpResponseAttachment responseAttachment = HttpResponseAttachment.Builder
                .create(this.responseAttachmentName)
                .setResponseCode(response.getStatusCode())
                .setHeaders(toMapConverter(response.getHeaders()))
                .setBody(replaceSensitiveData(prettifier.getPrettifiedBodyIfPossible(response, response.getBody())))
                .build();
        (new DefaultAttachmentProcessor()).addAttachment(responseAttachment,
                new FreemarkerAttachmentRenderer(this.responseTemplatePath));

        String requestLog = """
                Request:
                Request method:  %s
                Request URI:     %s
                Request params:  %s
                Query params:    %s
                Form params:     %s
                Path params:     %s
                Headers:         %s
                Cookies:         %s
                Multiparts:      %s
                Body:            %s
                *************
                """.formatted(
                requestSpec.getMethod(),
                replaceSensitiveData(requestSpec.getURI()),
                printMap(requestSpec.getRequestParams()),
                printMap(requestSpec.getQueryParams()),
                printMap(requestSpec.getFormParams()),
                printMap(requestSpec.getPathParams()),
                replaceSensitiveData(requestSpec.getHeaders().toString()),
                requestSpec.getCookies().asList().isEmpty() ? "" : requestSpec.getCookies().asList().toString(),
                requestSpec.getMultiPartParams().isEmpty() ? "" : requestSpec.getMultiPartParams().toString(),
                requestSpec.getBody() != null
                        ? replaceSensitiveData(prettifier.getPrettifiedBodyIfPossible(requestSpec))
                        : "");

        log.info(requestLog);

        String responseLog = """
                Response:
                %s
                """.formatted(response.getStatusLine());

        String body;
        try {
            body = replaceSensitiveData(new GsonBuilder().setPrettyPrinting().serializeNulls()
                    .create().toJson(JsonParser.parseString(response.asString())));
        } catch (JsonSyntaxException exception) {
            body = response.asString();
        }
        responseLog = responseLog + body;

        log.info(responseLog);
        return response;
    }

    /**
     * Map printer with sensitive data filter.
     *
     * @param map to print
     * @return filtered map
     */
    private String printMap(Map<String, String> map) {
        return map.isEmpty() ? "<none>" : replaceSensitiveData(map.toString());
    }

    /**
     * Map converter.
     *
     * @param items data to convert into map
     * @return map with items
     */
    private static Map<String, String> toMapConverter(Iterable<? extends NameAndValue> items) {
        Map<String, String> result = new HashMap<>();
        items.forEach(h -> {
            if ("Authorization".equals(h.getName())) {
                result.put(h.getName(), "Bearer {token}");
            } else if ("apiKey".equals(h.getName())) {
                result.put(h.getName(), "{apiKey}");
            } else {
                result.put(h.getName(), h.getValue());
            }
        });
        return result;
    }

    /**
     * Sensitive data replacement module.
     *
     * @param input data to filter
     * @return filtered data
     */
    private static String replaceSensitiveData(String input) {
        return input.replaceAll("password=.+", "password={password}")
                .replaceAll("token(\":) .+\\w", "token\": {token}")
                .replaceAll("tokenId(\":) .+\\w", "tokenId\": {token}")
                .replaceAll("Authorization=.+\\w", "Authorization=Bearer {token}")
                .replaceAll("iPlanetDirectoryPro=.+\\w", "iPlanetDirectoryPro= {token}")
                .replaceAll("apiKey=.+\\w", "apiKey={apiKey}")
                .replaceAll("(\"password\": \").+\",", "\"password\": {password}")
                .replaceAll("(\"confirm_value\": \").+\",", "\"confirm_value\": {password}")
                .replaceAll("(\"new_value\": \").+\",", "\"new_value\": {password}")
                .replaceAll("(\"current_value\": \").+\"", "\"current_value\": {password}")
                .replaceAll("X-OpenAM-Password=.+\\w", "X-OpenAM-Password= {token}");
    }
}
