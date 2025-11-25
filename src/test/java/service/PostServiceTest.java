package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Post;
import java.util.List;

public class PostServiceTest {

    @Test
    public void testCreateAndFetchPost() {
        PostService ps = new PostService();
        ps.createPost(1, "Hello World!");
        List<Post> feed = ps.getFeed(10);
        assertTrue(feed.stream().anyMatch(p -> "Hello World!".equals(p.getContent())));
    }

    @Test
    public void testLikePost() {
        PostService ps = new PostService();
        ps.createPost(2, "Like this post!");
        Post post = ps.getFeed(10).get(0);
        ps.likePost(post.getId(), 3); // User 3 likes it

        // After liking or commenting
        Post refreshed = ps.getFeed(10).stream()
                .filter(p -> p.getId().equals(post.getId()))
                .findFirst()
                .orElseThrow();
        assertTrue(refreshed.getLikedUserIds().contains(3));
    }


    @Test
    public void testCommentOnPost() {
        PostService ps = new PostService();
        ps.createPost(4, "Comment below:");
        Post post = ps.getFeed(10).get(0);
        ps.commentOnPost(post.getId(), 5, "Nice post!");

        // Fetch the post again to see updated comments
        Post refreshed = ps.getFeed(10).stream()
                .filter(p -> p.getId().equals(post.getId()))
                .findFirst()
                .orElseThrow();
        assertTrue(refreshed.getComments().stream()
                .anyMatch(c -> "Nice post!".equals(c.getText())));
    }

}
