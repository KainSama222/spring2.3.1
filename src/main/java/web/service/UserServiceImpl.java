package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void addOrEditUser(User user) {
        System.out.println("service"+user.toString());
        if (0 == user.getId()) {
            addUser(user);
        } else {
            editUser(user);
        }
    }
    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public void deleteUser(long id) {
        if (null != getUserById(id)) {
            userDao.deleteUser(id);
        }
    }
}