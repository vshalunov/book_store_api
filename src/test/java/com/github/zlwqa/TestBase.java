package com.github.zlwqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.zlwqa.config.ApiConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import static java.lang.String.format;

public class TestBase {

    public static final ApiConfig API_CONFIG = ConfigFactory.create(ApiConfig.class, System.getProperties());
    private static final String LOGIN = API_CONFIG.login();
    private static final String PASSWORD = API_CONFIG.password();
    private static final String SELENOID_URL = API_CONFIG.remoteURL();
    private static final String REMOTE_URL = format("https://%s:%s@%s", LOGIN, PASSWORD, SELENOID_URL);

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        RestAssured.baseURI = API_CONFIG.apiUrl();
        //Configuration.remote = REMOTE_URL;
    }
}
