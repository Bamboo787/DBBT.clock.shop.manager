package vn.td.clock.shop.services;

import vn.td.clock.shop.model.Order;
import vn.td.clock.shop.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {

    List<Order> orders = new ArrayList<>();
    public static String path = "data/order.csv";


    @Override
    public List<Order> getOrders() {
        List<Order> newOrders = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newOrders.add(new Order(record));
        }
        return orders = newOrders;
    }

    @Override
    public void add(Order newOrder) {
        newOrder.setCreateDate(Instant.now());
        List<Order> orders = getOrders();
        orders.add(newOrder);
        CSVUtils.write(path, orders);
    }

    @Override
    public void update() {
        CSVUtils.write(path, orders);
    }


    @Override
    public Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }
    @Override
    public boolean exist(int id) {
        return getOrderById(id) != null;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        for (Order order : orders) {
            if (order.getName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateId(int id) {
        for (Order order : orders) {
            if (order.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public void remove(Order order) {
        orders.remove(order);
        update();
    }

}


