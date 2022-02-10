package com.github.zlwqa.tests;

import annotations.JiraIssue;
import annotations.JiraIssues;
import annotations.Layer;
import annotations.Microservice;
import com.github.zlwqa.lombok.BookList;
import com.github.zlwqa.lombok.UserRequestDataLogin;
import com.github.zlwqa.lombok.UserResponseData;
import com.github.zlwqa.models.UserToken;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @Tags({@Tag("GenerateToken"), @Tag("Highest")})
    @Microservice("Generate Token")
    @Feature("Генерация токена")
    @Story("Метод /Account/v1/GenerateToken")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Book Store", url = "https://demoqa.com/books")
    void tokenGenerationWithModelsTest() {
        UserToken userToken = given()
                .spec(requestSpec)
                .body(setUserLoginData())
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .spec(responseSpec)
                .extract().as(UserToken.class);

        assertThat(userToken.getToken()).isNotNull();
        assertThat(userToken.getStatus()).isEqualTo(status);
        assertThat(userToken.getResult()).isEqualTo(result);
    }

    @Test
    @DisplayName("Отображение списка всех книг (с использованием Groovy)")
    @Tags({@Tag("BookStore"), @Tag("Medium")})
    @Microservice("BookStore")
    @Feature("Список книг")
    @Story("Метод GET /BookStore/v1/Books")
    @Severity(SeverityLevel.NORMAL)
    void displayAListOfAllBooksWithGroovyTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .body("books", notNullValue(),
                        "books.findAll{it.website =~/http.*?/}.website.flatten()",
                        hasItem("http://eloquentjavascript.net/"));
    }


    @Test
    @DisplayName("Отображение списка всех книг (с использованием Lombok)")
    @Tags({@Tag("BookStore"), @Tag("Medium")})
    @Microservice("BookStore")
    @Feature("Список книг")
    @Story("Метод GET /BookStore/v1/Books")
    @Severity(SeverityLevel.NORMAL)
    void displayAListOfAllBooksWithLombokTest() {
        BookList bookList = given()
                .spec(requestSpec)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .extract().as(BookList.class);

        assertEquals(isbn, bookList.getBookList()[6].getIsbn());
        assertEquals(titleBook, bookList.getBookList()[6].getTitle());
        assertEquals(author, bookList.getBookList()[6].getAuthor());
        assertEquals(pages, bookList.getBookList()[6].getPages());
    }

    @Test
    @DisplayName("Отображение списка всех книг (с использованием jsonSchema)")
    @Tags({@Tag("BookStore"), @Tag("Medium")})
    @Microservice("BookStore")
    @Feature("Список книг")
    @Story("Метод GET /BookStore/v1/Books")
    @Severity(SeverityLevel.NORMAL)
    void displayAListOfAllBooksWithJsonSchemaTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .body(matchesJsonSchemaInClasspath("schema/DisplayAListOfAllBooksWithLombokTestSchema.json"));
    }

    @Test
    @DisplayName("Отображение определенной книги по ISBN в списке всех книг")
    @Tags({@Tag("BookStore"), @Tag("Medium")})
    @Microservice("BookStore")
    @Feature("Список книг")
    @Owner("AnotherQA")
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
    @Tags({@Tag("BookStore"), @Tag("High")})
    @Microservice("BookStore")
    @Feature("Список добавленных книг в профиле пользователя")
    @Story("Методы POST /BookStore/v1/Books ❘ DELETE /BookStore/v1/Book")
    @Severity(SeverityLevel.BLOCKER)
    void addingAndRemovingABookToAUserProfileTest() {
        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + USER_RESPONSE_DATA.getToken())
                .body(addingData)
                .when()
                .post("/BookStore/v1/Books")
                .then().log().headers().and().log().body()
                .statusCode(201)
                .body("books[0].isbn", is(isbn));

        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + USER_RESPONSE_DATA.getToken())
                .body(removingData)
                .when()
                .delete("/BookStore/v1/Book")
                .then().log().headers().and().log().body()
                .statusCode(204)
                .body(is(""));
    }
}

