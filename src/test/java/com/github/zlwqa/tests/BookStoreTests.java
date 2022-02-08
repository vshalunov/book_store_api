package com.github.zlwqa.tests;

import annotations.JiraIssue;
import annotations.JiraIssues;
import annotations.Layer;
import annotations.Microservice;
import com.github.zlwqa.lombok.UserRequestDataLogin;
import com.github.zlwqa.lombok.UserResponseData;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.github.zlwqa.config.App.CREDENTIALS_CONFIG;
import static com.github.zlwqa.specs.Specs.requestSpec;
import static com.github.zlwqa.specs.Specs.responseSpec;
import static com.github.zlwqa.tests.TestData.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Layer("REST")
@Owner("vshalunov")
@Tag("API")
@JiraIssues({@JiraIssue("HOMEWORK-326")})
@DisplayName("Тестирование веб-приложения Book Store")
public class BookStoreTests extends TestBase {

    public static final UserResponseData USER_RESPONSE_DATA = new UserResponseData();
    public static final UserRequestDataLogin USER_REQUEST_DATA_LOGIN = new UserRequestDataLogin();

    public static final String USER_NAME = CREDENTIALS_CONFIG.userName();
    public static final String PASSWORD = CREDENTIALS_CONFIG.password();

    public static UserRequestDataLogin setUserLoginData() {
        USER_REQUEST_DATA_LOGIN.setUserName(USER_NAME);
        USER_REQUEST_DATA_LOGIN.setPassword(PASSWORD);
        return USER_REQUEST_DATA_LOGIN;
    }


    @Test
    @DisplayName("Успешная генерация токена (с использованием Models)")
    @Tags({@Tag("Critical"), @Tag("Highest")})
    @Microservice("Generation Token")
    @Feature("Генерация токена")
    @Story("Метод /Account/v1/GenerateToken")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Book Store", url = "https://demoqa.com/books")
    void tokenGenerationTest() {
        given()
                .spec(requestSpec)
                .body(setUserLoginData())
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .spec(responseSpec)
                .body("token", notNullValue(),
                        "status", is("Success"),
                        "result", is("User authorized successfully."));
    }

    @Test
    @DisplayName("Отображение списка всех книг (с использованием Lombok)")
    @Tags({@Tag("Major"), @Tag("Medium")})
    @Microservice("BookStore")
    @Feature("Список книг")
    @Story("Метод GET /BookStore/v1/Books")
    @Severity(SeverityLevel.NORMAL)
    void displayAListOfAllBooksTest() {
        UserResponseData userResponseData = given()
                .spec(requestSpec)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .body("books", notNullValue(),
                        "books[0].isbn", is(isbn),
                        "books[0].title", is(titleBook))
                .extract().as(UserResponseData.class);
    }

    @Test
    @DisplayName("Отображение определенной книги по ISBN в списке всех книг (с использованием Groovy")
    @Tags({@Tag("Major"), @Tag("Medium")})
    @Microservice("BookStore")
    @Feature("Список книг")
    @Story("Метод GET /BookStore/v1/Book?ISBN=")
    @Severity(SeverityLevel.NORMAL)
    void displayABookByISBNInTheListOfAllBooksTest() {
        given()
                .spec(requestSpec)
                .formParam("ISBN", isbn)
                .when()
                .get("/BookStore/v1/Book")
                .then()
                .spec(responseSpec)
                .body(notNullValue())
                .body("isbn", is(isbn),
                        "title", is(titleBook));
    }

    @Test
    @DisplayName("Добавление и удаление книги в профиль пользователя")
    @Tags({@Tag("Blocker"), @Tag("High")})
    @Microservice("BookStore")
    @Feature("Список добавленных книг в профиле пользователя")
    @Story("Метод POST /BookStore/v1/Books")
    @Severity(SeverityLevel.BLOCKER)
    void addingABookToAUserProfileTest() {
        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + USER_RESPONSE_DATA.getToken())
                .body(addingData)
                .when()
                .post("/BookStore/v1/Books")
                .then().log().all()
                .statusCode(201)
                .body("books[0].isbn", is(isbn));

        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + USER_RESPONSE_DATA.getToken())
                .body(removingData)
                .when()
                .delete("/BookStore/v1/Book")
                .then().log().all()
                .statusCode(204)
                .body(is(""));

    }
}

