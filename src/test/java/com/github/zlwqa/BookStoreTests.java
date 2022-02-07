package com.github.zlwqa;

import annotations.JiraIssue;
import annotations.JiraIssues;
import annotations.Layer;
import annotations.Microservice;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.zlwqa.config.ApiConfig;
import com.github.zlwqa.config.CredentialsConfig;
import com.github.zlwqa.lombok.UserRequestData;
import com.github.zlwqa.lombok.UserResponseData;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Layer("REST")
@Owner("vshalunov")
@Tag("API")
@JiraIssues({@JiraIssue("HOMEWORK-326")})
@DisplayName("Тестирование веб-приложения Book Store")
public class BookStoreTests {

    private static final CredentialsConfig CREDENTIALS_CONFIG =
            ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    private static final String USER_NAME = CREDENTIALS_CONFIG.userName();
    private static final String PASSWORD = CREDENTIALS_CONFIG.password();

    public static final ApiConfig API_CONFIG = ConfigFactory.create(ApiConfig.class, System.getProperties());

    private static final UserResponseData USER_RESPONSE_DATA = new UserResponseData();
    private static final UserRequestData USER_REQUEST_DATA = new UserRequestData();

    public static UserRequestData setUserLoginData() {
        USER_REQUEST_DATA.setUserName(USER_NAME);
        USER_REQUEST_DATA.setPassword(PASSWORD);
        return USER_REQUEST_DATA;
    }


    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        RestAssured.baseURI = API_CONFIG.apiUrl();

        UserResponseData userResponseData = given()
                .contentType(JSON)
                .body(setUserLoginData())
                .when()
                .post("/Account/v1/Login")
                .then()
                .extract().as(UserResponseData.class);

        USER_RESPONSE_DATA.setUserId(userResponseData.getUserId());
        USER_RESPONSE_DATA.setToken(userResponseData.getToken());
    }

    @Test
    @DisplayName("Успешная генерация токена")
    @Tags({@Tag("Critical"), @Tag("Highest")})
    @Microservice("Generation Token")
    @Feature("Генерация токена")
    @Story("Метод /Account/v1/GenerateToken")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Book Store", url = "https://demoqa.com/books")
    void tokenGenerationTest() {

        given()
                .contentType(JSON)
                .body(setUserLoginData())
                .when()
                .post("/Account/v1/GenerateToken")
                .then().log().all()
                .statusCode(200)
                .body("token", notNullValue(),
                        "status", is("Success"),
                        "result", is("UserRequestData authorized successfully."));
    }

    @Test
    @DisplayName("Добавление книги")
    @Tags({@Tag("Critical"), @Tag("Highest")})
    @Microservice("Generation Token")
    @Feature("Генерация токена")
    @Story("Метод /BookStore/v1/Books")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Book Store", url = "https://demoqa.com/books")
    void addBookTest() {
        String data = "{\"userId\": \"" + USER_RESPONSE_DATA.getUserId() + "\"," +
                "\"collectionOfIsbns\" : [{\"isbn\":\"9781449325862\"}]}";

        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + USER_RESPONSE_DATA.getToken())
                .body(data)
                .when()
                .post("/BookStore/v1/Books")
                .then().log().all()
                .statusCode(201);
    }
}

