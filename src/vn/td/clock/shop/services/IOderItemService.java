package vn.td.clock.shop.services;

import vn.td.clock.shop.model.OrderItem;

import java.util.List;

public interface IOderItemService {


    List<OrderItem> getOrderItems();

    void add(OrderItem newOrderItem);

    void update();

    OrderItem getOrderItemById(int id);


}
