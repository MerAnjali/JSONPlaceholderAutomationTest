package components;

import common.Spec;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class AlbumsComponent { // each method in a component would return a REST-assured Response object

    public static Response getAlbums(int userId) {
        Response allAlbumsResponse = given()
                .spec(Spec.requestSpec)
                .param("userId", userId)
                .get("albums");
        allAlbumsResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allAlbumsResponse;
    }

    public static Response getPosts() {
        Response allAlbumsResponse = given()
                .spec(Spec.requestSpec)
                .get("albums");
        allAlbumsResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allAlbumsResponse;
    }
}
