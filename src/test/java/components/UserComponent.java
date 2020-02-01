package components;

import common.Spec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserComponent { // each method in a component would return a REST-assured Response object

    public static Response getAllUsers() {
        Response allUsersResponse = given()
                .spec(Spec.requestSpec)
                .get("users");
        allUsersResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allUsersResponse;
    }

}
