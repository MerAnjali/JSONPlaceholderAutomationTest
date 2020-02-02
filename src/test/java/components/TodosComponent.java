package components;

import common.Spec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TodosComponent { // each method in a component would return a REST-assured Response object

    public static Response getTodosStatus(int userId) {
        Response allTodosResponse = given()
                .spec(Spec.requestSpec)
                .param("userId", userId)
                .get("todos");
        allTodosResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allTodosResponse;
    }

    public static Response getTodosStatus() {
        Response allTodosResponse = given()
                .spec(Spec.requestSpec)
                .get("todos");
        allTodosResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allTodosResponse;
    }
}
