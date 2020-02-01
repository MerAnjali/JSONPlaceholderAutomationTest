package tests;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import components.UserComponent;
import models.User;

import org.junit.Assert;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;

public class TestUserBlog {

    private int userId;

    @Test
    public void verifyGetAllUsers() {
        UserComponent.getAllUsers()
                .then()
                .assertThat()
                .body("size()", greaterThan(0));
    }

    @Test
    @Parameters({"userName"}) // userName fetched from testng.xml
    public void verifyGetUser(String userName) {
        List<User> users = Arrays.asList(UserComponent.getUser(userName).getBody().as(User[].class));
        if (users.size() > 0) {
            userId = users.get(0).getId();
            System.out.println("userId for the username " + userName + " = " + userId);
        } else
            Assert.fail("No user found with username = " + userName);
    }
}