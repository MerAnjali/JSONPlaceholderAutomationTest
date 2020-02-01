package components;

import common.Spec;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostsComponent { // each method in a component would return a REST-assured Response object

    public static Response getPosts(int userId) {
        Response allPostsResponse = given()
                .spec(Spec.requestSpec)
                .param("userId", userId)
                .get("posts");
        allPostsResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allPostsResponse;
    }

    public static Response getPosts() {
        Response allPostsResponse = given()
                .spec(Spec.requestSpec)
                .get("posts");
        allPostsResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allPostsResponse;
    }

}
