package components;

import common.Spec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import config.configProvider;

public class UserComponent { // each method in a component would return a REST-assured Response object

    public static Response getAllUsers() {
        Response allUsersResponse = given()
                .spec(Spec.requestSpec)
                .get(configProvider.ENDPOINT_USERS);
        allUsersResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allUsersResponse;
    }

    public static Response getUser(String username) {
        Response allUsersResponse = given()
                .spec(Spec.requestSpec)
                .param("username", username)
                .get(configProvider.ENDPOINT_USERS);
        allUsersResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allUsersResponse;
    }

}
