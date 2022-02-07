package com.github.zlwqa.config;

import com.github.zlwqa.lombok.UserRequestData;
import com.github.zlwqa.lombok.UserResponseData;
import org.aeonbits.owner.ConfigFactory;

public class App {

    public static final CredentialsConfig CREDENTIALS_CONFIG =
            ConfigFactory.create(CredentialsConfig.class, System.getProperties());

    public static final ApiConfig API_CONFIG = ConfigFactory.create(ApiConfig.class, System.getProperties());

    public static final UserResponseData USER_RESPONSE_DATA = new UserResponseData();
    public static final UserRequestData USER_REQUEST_DATA = new UserRequestData();

    public static final String USER_NAME = CREDENTIALS_CONFIG.userName();
    public static final String PASSWORD = CREDENTIALS_CONFIG.password();

    public static UserRequestData setUserLoginData() {
        USER_REQUEST_DATA.setUserName(USER_NAME);
        USER_REQUEST_DATA.setPassword(PASSWORD);
        return USER_REQUEST_DATA;
    }

}
