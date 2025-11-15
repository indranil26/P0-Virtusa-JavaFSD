package dao;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import model.Post;
import model.Comment;
import org.bson.Document;
import util.MongoDBUtil;

import java.util.*;

public class postDao {
    private MongoCollection<Document> collection;

    public postDao() {
        collection = MongoDBUtil.getDatabase().getCollection("posts");
    }

    public void insertPost(Post post) {
        Document doc = new Document("authorId", post.getAuthorId())
                .append("content", post.getContent())
                .append("timestamp", post.getTimestamp())
                .append("likedUserIds", post.getLikedUserIds())
                .append("comments", new ArrayList<Document>());
        collection.insertOne(doc);
    }

    public List<Post> getRecentFeed(int limit) {
        List<Post> posts = new ArrayList<>();
        FindIterable<Document> docs = collection.find().sort(new Document("timestamp", -1)).limit(limit);
        for (Document doc : docs) {
            Post p = new Post(
                    doc.getObjectId("_id").toString(),
                    doc.getInteger("authorId"),
                    doc.getString("content"),
                    doc.getDate("timestamp")
            );
            // Parse likes/comments if needed
            posts.add(p);
        }
        return posts;
    }

    public void addComment(String postId, Comment comment) {
        Document cmt = new Document("userId", comment.getUserId())
                .append("text", comment.getText())
                .append("timestamp", comment.getTimestamp());
        collection.updateOne(Filters.eq("_id", new org.bson.types.ObjectId(postId)),
                new Document("$push", new Document("comments", cmt)));
    }

    public void likePost(String postId, int userId) {
        collection.updateOne(Filters.eq("_id", new org.bson.types.ObjectId(postId)),
                new Document("$addToSet", new Document("likedUserIds", userId)));
    }
}
