package com.kwri.auto.utils;

import io.qameta.allure.Step;
import net.datafaker.Faker;

/**
 * The type Data faker.
 */
public class DataFaker {
    private static final Faker FAKER = new Faker();

    /**
     * This method will generate random first name.
     *
     * @return - random first name
     */
    @Step("get random first name")
    public String getRandomFirstName() {
        return FAKER.name().firstName();
    }

    /**
     * This method will generate random last name.
     *
     * @return - random last name
     */
    @Step("get random last name")
    public String getRandomLastName() {
        return FAKER.name().lastName();
    }
}
