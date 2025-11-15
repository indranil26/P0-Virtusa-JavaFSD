package model;

import java.util.*;
public class Post {
    private String id;
    private int authorId;
    private String content;
    private Date timestamp;
    private Set<Integer> likedUserIds = new HashSet<>();
    private List<Comment> comments = new ArrayList<>();

    public Post(String id, int authorId, String content, Date timestamp) {
        this.id = id; this.authorId = authorId;
        this.content = content; this.timestamp = timestamp;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Set<Integer> getLikedUserIds() {
        return likedUserIds;
    }

    public void setLikedUserIds(Set<Integer> likedUserIds) {
        this.likedUserIds = likedUserIds;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
