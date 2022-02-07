package com.github.zlwqa.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:/tmp/api.properties",
        "classpath:config/api.properties"
})
public interface ApiConfig extends Config {

    @Key("webUrl")
    String webUrl();

    @Key("apiUrl")
    String apiUrl();

    @Key("login")
    String login();

    @Key("password")
    String password();

    @Key("remoteURL")
    String remoteURL();
}
