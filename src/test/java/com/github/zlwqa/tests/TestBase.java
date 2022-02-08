package com.github.zlwqa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.zlwqa.lombok.UserResponseData;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static com.github.zlwqa.config.App.API_CONFIG;
import static com.github.zlwqa.specs.Specs.requestSpec;
import static com.github.zlwqa.specs.Specs.responseSpec;
import static com.github.zlwqa.tests.BookStoreTests.USER_RESPONSE_DATA;
import static com.github.zlwqa.tests.BookStoreTests.setUserLoginData;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class TestBase {

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        RestAssured.baseURI = API_CONFIG.apiUrl();
        step("Получение токена авторизации и userId", () -> {
            UserResponseData userResponseData = given()
                    .spec(requestSpec)
                    .body(setUserLoginData())
                    .when()
                    .post("/Account/v1/Login")
                    .then()
                    .spec(responseSpec)
                    .extract().as(UserResponseData.class);

            USER_RESPONSE_DATA.setUserId(userResponseData.getUserId());
            USER_RESPONSE_DATA.setToken(userResponseData.getToken());
        });

    }

}
