package common;

import config.configProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;


public class Spec {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification successResponseSpec;

    public Spec() {
        Logger log = MyLogger.log;
        log.info("Initializing request and response specifications");
        Spec.requestSpec = new RequestSpecBuilder()
                .setBaseUri(configProvider.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build();
        Spec.successResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(anyOf(is(200), is(201)))
                .build();
    }
}
