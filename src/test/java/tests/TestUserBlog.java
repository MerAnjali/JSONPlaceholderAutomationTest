package tests;

import org.testng.annotations.Test;
import components.UserComponent;

import static org.hamcrest.Matchers.greaterThan;

public class TestUserBlog {

    @Test
    public void verifyGetAllUsers() {
        UserComponent.getAllUsers()
                .then()
                .assertThat()
                .body("size()", greaterThan(0));
    }
}
