package model;

import java.util.Date;
public class Comment {
    private int userId;
    private String text;
    private Date timestamp;

    public Comment(int userId, String text, Date timestamp) {
        this.userId = userId; this.text = text; this.timestamp = timestamp;
    }

    // Getters and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
