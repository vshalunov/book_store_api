package com.github.zlwqa.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:/tmp/api.properties",
        "classpath:config/api.properties"
})
public interface ApiConfig extends Config {

    @Key("apiUrl")
    String apiUrl();

}
