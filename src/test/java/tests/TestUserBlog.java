package tests;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import io.restassured.response.Response;
import components.UserComponent;
import components.PostsComponent;
import components.CommentsComponent;
import models.User;
import models.Posts;
import models.Comments;

import org.junit.Assert;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;

public class TestUserBlog {

    private int userId;
    private List<Posts> allPostsByUser;
    private List<Comments> allCommentsOnUserPosts;
    private Response allCommentsOnUserPostsResponse;

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

    @Test(dependsOnMethods = "verifyGetUser")
    public void verifyGetPostsByUserId() {
        allPostsByUser = Arrays.asList(PostsComponent.getPosts(userId).getBody().as(Posts[].class));
        allPostsByUser.forEach(post -> Assert.assertEquals(post.getUserId(), userId));
    }

    @Test(dependsOnMethods = "verifyGetPostsByUserId")
    public void verifyPostComments() {
        allPostsByUser.forEach(post -> {
            System.out.println("here for the username " + allPostsByUser.toString());
            int postId = post.getId();
            allCommentsOnUserPostsResponse = CommentsComponent.getCommentsOnPost(postId);
            allCommentsOnUserPosts = Arrays.asList(allCommentsOnUserPostsResponse.getBody().as(Comments[].class));
            String emailRegex = "^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$";
            allCommentsOnUserPosts.forEach(comment -> {
                Assert.assertTrue(comment.getEmail().matches(emailRegex));
            });
        });
    }
}
