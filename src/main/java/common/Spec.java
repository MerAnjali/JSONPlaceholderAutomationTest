package common;

import config.configProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;


public class Spec {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification successResponseSpec;

    public Spec() {
        System.out.println("Initializing request and response specifications");
        Spec.requestSpec = new RequestSpecBuilder()
                .setBaseUri(configProvider.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build();
        Spec.successResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(anyOf(is(200), is(201)))
                .expectStatusCode(is(both(greaterThanOrEqualTo(100)).and(lessThanOrEqualTo(399)))) // 100-399 being the range of success codes
                .build();
    }
}
