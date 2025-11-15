package service;
import dao.userDao;
import model.User;
import util.BCryptUtil;

public class UserService {
    private userDao userDao = new userDao();

    public boolean register(String username, String fullName, String email, String password) throws Exception {
        if (userDao.existsByUsernameOrEmail(username, email)) return false;
        String hash = BCryptUtil.hash(password);
        userDao.insertUser(username, fullName, email, hash);
        return true;
    }

    public User login(String username, String password) throws Exception {
        User user = userDao.getByUsername(username);
        if (user != null && BCryptUtil.check(password, user.getPasswordHash())) return user;
        return null;
    }

    public String getUsernameById(int userId) throws Exception {
        User user = userDao.getById(userId);
        return (user != null) ? user.getUsername() : "Unknown";
    }

}
