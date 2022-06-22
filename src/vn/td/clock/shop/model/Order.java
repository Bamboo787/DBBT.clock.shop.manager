package vn.td.clock.shop.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private String name;
    private String phone;
    private String address;
    private Instant createDate;

    private List<OrderItem> orderItems = new ArrayList<>();

    private Order() {

    }
    public Order (String record){
        String[] field = record.split(",");
        id = Long.parseLong(field[0]);
        name = field[1];
        phone = field[2];
        address = field[3];
        createDate = Instant.parse(field[4]);
    }

    public Order(long id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return address;
    }

    public void setEmail(String address) {
        this.address = address;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + phone + "," + address+ "," + createDate  ;
    }
}

