package com.github.zlwqa.tests;

import com.github.zlwqa.lombok.*;

import static com.github.zlwqa.config.App.CREDENTIALS_CONFIG;

public class TestData {

    public static final UserResponseData USER_RESPONSE_DATA = new UserResponseData();
    public static final UserRequestDataLogin USER_REQUEST_DATA_LOGIN = new UserRequestDataLogin();
    public static final BookDataForAdd BOOK_DATA_FOR_ADD = new BookDataForAdd();
    public static final CollectionOfIsbns COLLECTION_OF_ISBNS = new CollectionOfIsbns();
    public static final BookDataForRemove BOOK_DATA_FOR_REMOVE = new BookDataForRemove();

    public static final String USER_NAME = CREDENTIALS_CONFIG.userName();
    public static final String PASSWORD = CREDENTIALS_CONFIG.password();

    public static String isbn = "9781593275846";
    public static String titleBook = "Eloquent JavaScript, Second Edition";
    public static String author = "Marijn Haverbeke";
    public static int pages = 472;
    public static String status = "Success";
    public static String result = "User authorized successfully.";

    public static UserRequestDataLogin setUserLoginData() {
        USER_REQUEST_DATA_LOGIN.setUserName(USER_NAME);
        USER_REQUEST_DATA_LOGIN.setPassword(PASSWORD);
        return USER_REQUEST_DATA_LOGIN;
    }

    public static CollectionOfIsbns setIsbn() {
        COLLECTION_OF_ISBNS.setIsbn(isbn);
        return COLLECTION_OF_ISBNS;
    }

    public static BookDataForRemove setBookDataForRemove() {
        BOOK_DATA_FOR_REMOVE.setIsbn(isbn);
        BOOK_DATA_FOR_REMOVE.setUserId(USER_RESPONSE_DATA.getUserId());
        return BOOK_DATA_FOR_REMOVE;
    }

    public static BookDataForAdd setBookDataForAdd() {
        BOOK_DATA_FOR_ADD.setCollectionOfIsbns(new CollectionOfIsbns[]{setIsbn()});
        BOOK_DATA_FOR_ADD.setUserId(USER_RESPONSE_DATA.getUserId());
        return BOOK_DATA_FOR_ADD;
    }
}
