package project.dao;

import project.domain.Client;

import java.util.Date;
import java.util.List;

/**
 * Created by yurii on 7/24/14.
 */
public interface ClientDao {

    void create(String login, String password, String first_name, String last_name, Integer mobile, String address, Integer spent, Date last_order, boolean super_user);
    Client read(Long id);
    void update(Client client);
    void delete(long id);
    List<Client> clientList();
}
