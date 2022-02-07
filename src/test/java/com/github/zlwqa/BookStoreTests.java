package com.github.zlwqa;

import annotations.JiraIssue;
import annotations.JiraIssues;
import annotations.Layer;
import annotations.Microservice;
import com.github.zlwqa.config.CredentialsConfig;
import io.qameta.allure.*;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@Layer("REST")
@Owner("vshalunov")
@Tag("Web")
@JiraIssues({@JiraIssue("HOMEWORK-326")})
@DisplayName("Тестирование веб-приложения Book Store")
public class BookStoreTests extends TestBase {

    private static final CredentialsConfig CREDENTIALS_CONFIG = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    private static final String USER_NAME = CREDENTIALS_CONFIG.userName();
    private static final String PASSWORD = CREDENTIALS_CONFIG.password();


    @Test
    @DisplayName("Успешная авторизация через UserName и Password")
    @Tags({@Tag("Critical"), @Tag("Highest")})
    @Microservice("Authorization")
    @Feature("Авторизация")
    @Story("Метод /Account/v1/Authorized")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Book Store", url = "https://demoqa.com/books")
    void authorizationViaUserNameAndPasswordTest() {

        String data = "{" +
                "\"userName\": \"" + USER_NAME + "\"," +
                "  \"password\": \"" + PASSWORD + "\"}";

        given()
                .contentType(JSON)
                .baseUri("https://demoqa.com")
                .body(data)
                .when()
                .post("/Account/v1/Authorized")
                .then().log().body()
                .statusCode(200)
                .body(is("true"));
    }
}

