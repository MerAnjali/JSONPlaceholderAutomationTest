package tests;

import common.MyLogger;
import components.CommentsComponent;
import components.PostsComponent;
import components.UserComponent;
import models.Comments;
import models.Posts;
import models.User;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TestUserBlogNegativeScenarios {

    private final Logger log = MyLogger.log;

    @Test
    @Parameters({"invalidUserName"}) // get userName from testng.xml
    public void verifyGetUserWithInvalidName(String invalidUserName) {
        log.info("Verifying no user exists with username " + invalidUserName);
        List<User> users = Arrays.asList(UserComponent.getUser(invalidUserName).getBody().as(User[].class));
        Assert.assertEquals("No users found with name " + invalidUserName, users.size(), 0);
    }

    @Test
    @Parameters({"invalidUserId"}) // get userId from testng.xml
    public void verifyGetPostsByInvalidUserId(String invalidUserId) {
        log.info("Verifying no posts exist for userId " + invalidUserId);
        int invalidUserIdInt = Integer.parseInt(invalidUserId);
        List<Posts> posts = Arrays.asList(PostsComponent.getPosts(invalidUserIdInt).getBody().as(Posts[].class));
        Assert.assertEquals("No user found with userId " + invalidUserIdInt, posts.size(), 0);
    }

    @Test
    @Parameters({"invalidPostId"}) // get postId from testng.xml
    public void verifyGetCommentsByInvalidPostId(String invalidPostId) {
        log.info("Verifying no comments exist for postId " + invalidPostId);
        int invalidPostIdInt = Integer.parseInt(invalidPostId);
        List<Comments> comments = Arrays.asList(CommentsComponent.getCommentsOnPost(invalidPostIdInt).getBody().as(Comments[].class));
        Assert.assertEquals("No comments found for postId " + invalidPostId, comments.size(), 0);
    }
}
