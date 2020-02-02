package components;

import common.Spec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PhotosComponent { // each method in a component would return a REST-assured Response object

    public static Response getPhotosOnAlbum(int albumId) {
        Response allPhotosOnAlbumResponse = given()
                .spec(Spec.requestSpec)
                .param("albumId", albumId)
                .get("photos");
        allPhotosOnAlbumResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allPhotosOnAlbumResponse;
    }

}
