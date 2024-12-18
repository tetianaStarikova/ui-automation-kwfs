package com.kwri.auto.config;

import com.kwri.auto.enums.Environment;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Config.
 */
@Slf4j
public final class Config {
    public static final Environment ENV = (System.getProperty("ENVIRONMENT") != null)
            ? Environment.valueOf(System.getProperty("ENVIRONMENT").toUpperCase())
            : Environment.QA;
    public static final Properties PROPERTIES = System.getProperties();

    static {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(ENV.toString().toLowerCase() + ".test.properties");
        InputStream passwordsStream = loader.getResourceAsStream("passwords.properties");
        try {
            PROPERTIES.load(stream);
            PROPERTIES.load(passwordsStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private Config() {
    }
}
