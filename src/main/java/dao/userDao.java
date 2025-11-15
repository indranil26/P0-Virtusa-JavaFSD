package dao;

import model.User;
import util.JDBCUtils;
import java.sql.*;

public class userDao {
    public boolean existsByUsernameOrEmail(String username, String email) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT COUNT(*) FROM users WHERE username = ? OR email = ?");
        ps.setString(1, username); ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        rs.next(); return rs.getInt(1) > 0;
    }

    public void insertUser(String username, String fullName, String email, String passwordHash) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users(username, full_name, email, password_hash) VALUES(?,?,?,?)");
        ps.setString(1, username); ps.setString(2, fullName);
        ps.setString(3, email); ps.setString(4, passwordHash);
        ps.executeUpdate();
    }

    public User getByUsername(String username) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new User(rs.getInt("id"), rs.getString("username"),
                    rs.getString("full_name"), rs.getString("email"), rs.getString("password_hash"));
        }
        return null;
    }

    public User getById(int id) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new User(rs.getInt("id"), rs.getString("username"),
                    rs.getString("full_name"), rs.getString("email"), rs.getString("password_hash"));
        }
        return null;
    }

}
