package project.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.domain.Client;

import java.util.Date;
import java.util.List;

/**
 * Created by yurii on 7/24/14.
 */
@Transactional
@Repository
public class ClientDaoImpl implements ClientDao {
    private static Logger log = Logger.getLogger(ClientDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    public ClientDaoImpl() {
    }

    public ClientDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public void create(String login,String password, String first_name, String last_name, Integer mobile, String address, Integer spent, Date last_order, boolean super_user) {
        Client client = new Client(login, password, first_name, last_name, mobile, address, spent, last_order, super_user);
        Session session = factory.getCurrentSession();
        session.save(client);
    }

    @Override
    public Client read(Long id) {
        Session session = factory.getCurrentSession();
        try {
            return (Client) session.get(Client.class, id);
        } catch (HibernateException e) {
            log.error("Fetch error", e);
        }
        return null;
    }

    @Override
    public void update(Client client) {
        Session session = factory.getCurrentSession();
        try {
            session.update(client);
        } catch (HibernateException e) {
            log.error("Fetch error", e);
        }
    }

    @Override
    public void delete(long id) {
        Session session = factory.getCurrentSession();
        try {
            session.delete(session.get(Client.class, id));
        } catch (HibernateException e) {
            log.error("Fetch error", e);
        }
    }

    @Override
    public List<Client> clientList() {
        Session session = factory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Client.class);
            return criteria.list();
        } catch (HibernateException e) {
            log.error("Fetch error", e);
        }
        return null;
    }
}
