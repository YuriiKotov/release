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
import project.domain.Order;

import java.util.Date;
import java.util.List;

/**
 * Created by yurii on 7/24/14.
 */
@Transactional
@Repository
public class OrderDaoImpl implements OrderDao {
    private static Logger log = Logger.getLogger(OrderDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    public OrderDaoImpl() {
    }

    public OrderDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public void create(Date order_date, Client client, Integer cost, String start, String finish) {
        Order order = new Order(order_date, client, cost, start, finish);
        Session session = factory.getCurrentSession();
        session.save(order);
    }

    @Override
    public Order read(Long id) {
        Session session = factory.getCurrentSession();
        try {
            return (Order) session.get(Order.class, id);
        } catch (HibernateException e) {
            log.error("Fetch error", e);
        }
        return null;
    }

    @Override
    public void update(Order order) {
        Session session = factory.getCurrentSession();
        try {
            session.update(order);
        } catch (HibernateException e) {
            log.error("Fetch error", e);
        }
    }

    @Override
    public void delete(long id) {
        Session session = factory.getCurrentSession();
        try {
            session.delete(session.get(Order.class, id));
        } catch (HibernateException e) {
            log.error("Fetch error", e);
        }
    }

    @Override
    public List<Order> orderList() {
        Session session = factory.getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Order.class);
            return criteria.list();
        } catch (HibernateException e) {
            log.error("Fetch error", e);
        }
        return null;
    }
}
