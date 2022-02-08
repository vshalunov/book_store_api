package com.github.zlwqa.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.github.zlwqa.config.App.API_CONFIG;
import static com.github.zlwqa.filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.HEADERS;

public class Specs {

    public static RequestSpecification requestSpec = with()
            .baseUri(API_CONFIG.apiUrl())
            //.basePath("path")
            .filter(customLogFilter().withCustomTemplates())
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(HEADERS)
            .log(BODY)
            .expectStatusCode(200)
            .build();
}
