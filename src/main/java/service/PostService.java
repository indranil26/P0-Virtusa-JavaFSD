package service;
import dao.postDao;
import model.Post;
import model.Comment;
import java.util.List;

public class PostService {
    private postDao postDao = new postDao();


    public void createPost(int authorId, String content) {
        Post post = new Post(null, authorId, content, new java.util.Date());
        postDao.insertPost(post);
    }

    public List<Post> getFeed(int limit) {
        return postDao.getRecentFeed(limit);
    }

    public void likePost(String postId, int userId) {
        postDao.likePost(postId, userId);
    }

    public void commentOnPost(String postId, int userId, String text) {
        Comment cmt = new Comment(userId, text, new java.util.Date());
        postDao.addComment(postId, cmt);
    }
}
