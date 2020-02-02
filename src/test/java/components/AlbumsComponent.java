package components;

import common.Spec;
import config.configProvider;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AlbumsComponent { // each method in a component would return a REST-assured Response object

    public static Response getAlbums(int userId) {
        Response allAlbumsResponse = given()
                .spec(Spec.requestSpec)
                .param("userId", userId)
                .get(configProvider.ENDPOINT_ALBUMS);
        allAlbumsResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allAlbumsResponse;
    }

    public static Response getPosts() {
        Response allAlbumsResponse = given()
                .spec(Spec.requestSpec)
                .get(configProvider.ENDPOINT_ALBUMS);
        allAlbumsResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allAlbumsResponse;
    }
}
