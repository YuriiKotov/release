package springProject.service;


import springProject.domain.User;

import java.util.List;

/**
 * Created by Юрий on 10.07.2014.
 */
public interface UserService {

    void create(String login, String password);
    User read(Long id);
    void update(User user);
    void delete(long id);
    List<User> userList();
}
