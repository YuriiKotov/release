package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.ClientDao;
import project.domain.Client;

import java.util.Date;
import java.util.List;

/**
 * Created by yurii on 7/24/14.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientDao clientDao;

    public ClientServiceImpl() {
    }

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public void create(String login, String password, String first_name, String last_name, Integer mobile, String address, int spent, Date last_order, boolean super_user) {
        clientDao.create(login, password, first_name, last_name, mobile, address, spent, last_order, super_user);
    }

    @Override
    public Client read(Long id) {
        return clientDao.read(id);
    }

    @Override
    public void update(Client client) {
        clientDao.update(client);
    }

    @Override
    public void delete(long id) {
        clientDao.delete(id);
    }

    @Override
    public List<Client> clientList() {
        return clientDao.clientList();
    }
}
