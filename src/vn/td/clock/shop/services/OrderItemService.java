package vn.td.clock.shop.services;

import vn.td.clock.shop.model.OrderItem;
import vn.td.clock.shop.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOderItemService {
    List<OrderItem> orderItems = new ArrayList<>();
    public static String path = "data/orderItem.csv";


    @Override
    public List<OrderItem> getOrderItems() {
        List<OrderItem> newOrderItems = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newOrderItems.add(new OrderItem(record));
        }
        return orderItems = newOrderItems;
    }

    @Override
    public void add(OrderItem newOrderItem) {
        orderItems.add(newOrderItem);
        CSVUtils.write(path, orderItems);
    }

    @Override
    public void update() {
        CSVUtils.write(path, orderItems);
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getId() == id)
                return orderItem;
        }
        return null;
    }

}

