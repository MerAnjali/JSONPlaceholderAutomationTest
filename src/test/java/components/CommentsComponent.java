package components;

import common.Spec;
import config.configProvider;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CommentsComponent { // each method in a component would return a REST-assured Response object

    public static Response getCommentsOnPost(int postId) {
        Response allCommentsOnPostResponse = given()
                .spec(Spec.requestSpec)
                .param("postId", postId)
                .get(configProvider.ENDPOINT_COMMENTS);
        allCommentsOnPostResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allCommentsOnPostResponse;
    }

}
