package dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Post;
import model.Comment;
import dao.postDao;

import java.util.List;
import java.util.Date;

public class postDaoTest {

    @Test
    public void testInsertAndGetPostsByAuthorId() {
        postDao pd = new postDao();
        Post post = new Post(null, 5, "Testing Mongo insertion", new Date());
        pd.insertPost(post);
        List<Post> posts = pd.getPostsByAuthorId(5);
        assertTrue(posts.stream().anyMatch(p -> "Testing Mongo insertion".equals(p.getContent())));
    }

    @Test
    public void testLikeAndComment() {
        postDao pd = new postDao();
        Post post = new Post(null, 6, "Initial post", new Date());
        pd.insertPost(post);

        Post inserted = pd.getPostsByAuthorId(6).get(0);
        pd.likePost(inserted.getId(), 7);
        pd.addComment(inserted.getId(), new Comment(8, "Looks good!", new Date()));

        List<Post> posts = pd.getPostsByAuthorId(6);
        Post checkPost = posts.get(0);
        assertTrue(checkPost.getLikedUserIds().contains(7));
        assertTrue(checkPost.getComments().stream().anyMatch(c -> "Looks good!".equals(c.getText())));
    }
}
