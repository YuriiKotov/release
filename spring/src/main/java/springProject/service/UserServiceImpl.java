package springProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springProject.dao.UserDao;
import springProject.domain.User;

import java.util.List;

/**
 * Created by Юрий on 10.07.2014.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(String login, String password) {
        userDao.create(login, password);
    }

    @Override
    public User read(Long id) {
        return userDao.read(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public List<User> userList() {
        return userDao.userList();
    }
}
