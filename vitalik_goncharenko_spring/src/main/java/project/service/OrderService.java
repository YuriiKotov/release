package project.service;

import project.domain.Client;
import project.domain.Order;

import java.util.Date;
import java.util.List;

/**
 * Created by yurii on 7/24/14.
 */
public interface OrderService {

    void create(Date order_date, Client client, Integer cost, String start, String finish);
    Order read(Long id);
    void update(Order order);
    void delete(long id);
    List<Order> orderList();
}
