package vn.td.clock.shop.services;

import vn.td.clock.shop.model.Order;

import java.util.List;

public  interface IOrderService {

    List<Order> getOrders();

    void add(Order newOrder);

    void update();

    Order getOrderById(int id);

    boolean exist(int id);

    boolean checkDuplicateName(String name);

    boolean checkDuplicateId(int id);

    void remove(Order order);
}

