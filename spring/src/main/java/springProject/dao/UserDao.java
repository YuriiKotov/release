package springProject.dao;



import springProject.domain.User;

import java.util.List;

/**
 * Created by Юрий on 10.07.2014.
 */
public interface UserDao {
    void create(String login, String password);
    User read(Long id);
    void update(User user);
    void delete(long id);
    List<User> userList();
}
