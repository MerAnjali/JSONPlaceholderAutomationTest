package tests;

import common.MyLogger;
import components.*;
import io.restassured.response.Response;
import models.*;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class TestUserBlog {

    private int userId;
    private String testUserName;
    private List<Posts> allPostsByUser;
    private List<Comments> allCommentsOnUserPosts;
    private Response allCommentsOnUserPostsResponse;
    private List<Albums> allAlbumsByUser;
    private Response allPhotosOnUserAlbumsResponse;
    private List<Photos> allPhotosOnUserAlbums;
    private final Logger log = MyLogger.log;


    @Test
    public void verifyGetAllUsers() {
        log.info("Verifying to get all users");
        UserComponent.getAllUsers()
                .then()
                .assertThat()
                .body("size()", greaterThan(0));
    }

    @Test(dependsOnMethods = "verifyGetAllUsers")
    @Parameters({"userName"}) // userName fetched from testng.xml
    public void verifyGetUser(String userName) {
        log.info("Verifying if a user exists with the userName " + userName);
        List<User> users = Arrays.asList(UserComponent.getUser(userName).getBody().as(User[].class));
        if (users.size() > 0) {
            userId = users.get(0).getId();
            testUserName = userName;
            log.info("userId for the username " + userName + " = " + userId);
        } else
            Assert.fail("No user found with username = " + userName);
    }

    @Test(dependsOnMethods = "verifyGetUser")
    public void verifyGetPostsByUserId() {
        log.info("Verifying all posts for the userName " + testUserName);
        allPostsByUser = Arrays.asList(PostsComponent.getPosts(userId).getBody().as(Posts[].class));
        allPostsByUser.forEach(post -> Assert.assertEquals(post.getUserId(), userId));
    }

    @Test(dependsOnMethods = "verifyGetPostsByUserId")
    public void verifyPostComments() {
        log.info("Verifying all post comments for the username " + testUserName);
        allPostsByUser.forEach(post -> {
            int postId = post.getId();
            allCommentsOnUserPostsResponse = CommentsComponent.getCommentsOnPost(postId);
            allCommentsOnUserPosts = Arrays.asList(allCommentsOnUserPostsResponse.getBody().as(Comments[].class));
            String emailRegex = "^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$";
            allCommentsOnUserPosts.forEach(comment -> Assert.assertTrue(comment.getEmail().matches(emailRegex)));
        });
    }

    @Test(dependsOnMethods = {"verifyGetUser"})
    @Parameters({"postTitle", "postBody"})
    public void verifyCreatePost(String postTitle, String postBody) {
        log.info("Verifying to create a post for the userName " + testUserName);
        PostsComponent.createPost(postTitle, postBody, userId)
                .then()
                .assertThat()
                .body("title", equalTo(postTitle))
                .body("body", equalTo(postBody))
                .body("userId", equalTo(userId));
    }

    @Test(dependsOnMethods = {"verifyGetUser"})
    @Parameters({"updatedPostTitle", "fakePostId"})
    public void verifyUpdatePost(String updatedPostTitle, String postId) {
        log.info("Verifying to update a post with postId " + postId);
        PostsComponent.updatePost("title", updatedPostTitle, postId)
                .then()
                .assertThat()
                .body("title", equalTo(updatedPostTitle));
    }

    @Test(dependsOnMethods = {"verifyGetUser"})
    @Parameters({"fakePostId"})
    public void verifyDeletePost(String postId) {
        log.info("Verifying to delete a post with postId " + postId);
        PostsComponent.deletePost(postId)
                .then()
                .assertThat()
                .body(equalTo("{}"));
    }

    @Test(dependsOnMethods = "verifyGetUser")
    public void verifyGetAlbumsByUserId() {
        log.info("Verifying all albums for the userName " + testUserName);
        allAlbumsByUser = Arrays.asList(AlbumsComponent.getAlbums(userId).getBody().as(Albums[].class));
        allAlbumsByUser.forEach(album -> Assert.assertEquals(album.getUserId(), userId));
    }

    @Test(dependsOnMethods = "verifyGetAlbumsByUserId")
    public void verifyGetPhotosByAlbumId() {
        allAlbumsByUser.forEach(album -> {
            int albumId = album.getId();
            log.info("Verifying all photos for the albumId " + albumId);
            allPhotosOnUserAlbumsResponse = PhotosComponent.getPhotosOnAlbum(albumId);
            allPhotosOnUserAlbums = Arrays.asList(allPhotosOnUserAlbumsResponse.getBody().as(Photos[].class));
            allPhotosOnUserAlbums.forEach(photo -> Assert.assertEquals(photo.getAlbumId(), albumId));
        });
    }

    @Test(dependsOnMethods = "verifyGetUser")
    public void verifyGetTodosByUserId() {
        log.info("Verifying all todos for the username " + testUserName);
        List<Todos> allTodosByUser = Arrays.asList(TodosComponent.getTodosStatus(userId).getBody().as(Todos[].class));
        allTodosByUser.forEach(todos -> Assert.assertEquals(todos.getUserId(), userId));
    }
}
