package springProject.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springProject.domain.User;

import java.util.List;

/**
 * Created by Юрий on 10.07.2014.
 */
@Transactional
@Repository
public class UserDaoImpl implements UserDao {
    private static Logger log = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    public UserDaoImpl() {
    }

    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(String login, String password) {
        User user = new User(login, password);
        Session session = factory.getCurrentSession();
        session.save(user);
    }

    @Override
    public User read(Long id) {
        Session session = factory.getCurrentSession();
        try {
            return (User) session.get(User.class, id);
        } catch (HibernateException e) {
            log.error("Fetch error", e);
        }
        return null;
    }

    @Override
    public void update(User user) {
        Session session = factory.getCurrentSession();
        try {
            session.update(user);
        } catch (HibernateException e) {
            log.error("Transaction failed", e);
        }
    }

    @Override
    public void delete(long id) {
        Session session = factory.getCurrentSession();
        try {
            session.delete(session.get(User.class, id));
        } catch (HibernateException e) {
            log.error("Fetch error", e);
        }
    }

    @Override
    public List<User> userList() {
        Session session = factory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            return criteria.list();
        } catch (HibernateException e) {
            log.error("Fetch error", e);
        }
        return null;
    }
}
