package com.github.zlwqa.tests;

import static com.github.zlwqa.tests.BookStoreTests.USER_RESPONSE_DATA;

public class TestData {

    public static String isbn = "9781593275846";
    public static String titleBook = "Eloquent JavaScript, Second Edition";
    public static String author = "Marijn Haverbeke";
    public static int pages = 472;
    public static String status = "Success";
    public static String result = "User authorized successfully.";

    public static String addingData = "{\"userId\": \"" + USER_RESPONSE_DATA.getUserId() + "\"," +
            "\"collectionOfIsbns\" : [{\"isbn\":\"" + isbn + "\"}]}";

    public static String removingData = "{\"isbn\":\"" + isbn + "\"," +
            "\"userId\": \"" + USER_RESPONSE_DATA.getUserId() + "\"}";

}
