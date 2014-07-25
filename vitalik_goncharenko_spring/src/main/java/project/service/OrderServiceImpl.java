package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.OrderDao;
import project.domain.Client;
import project.domain.Order;

import java.util.Date;
import java.util.List;

/**
 * Created by yurii on 7/24/14.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    public OrderServiceImpl() {
    }

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public void create(Date order_date, Client client, Integer cost, String start, String finish) {
        orderDao.create(order_date, client, cost, start, finish);
    }

    @Override
    public Order read(Long id) {
        return orderDao.read(id);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public void delete(long id) {
        orderDao.delete(id);
    }

    @Override
    public List<Order> orderList() {
        return orderDao.orderList();
    }
}
