package vn.td.clock.shop.model;

public class OrderItem {
    private long id;
    private double price;
    private int quantity;
    private long orderId;
    private int clockId;
    private String clockName;
    private double total;

    public OrderItem(long id, double price, int quantity, long orderId, int clockId, String clockName, double total) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.clockId = clockId;
        this.clockName = clockName;
        this.total = total;
    }

    public OrderItem() {

    }

    public OrderItem(String record) {
        String[] fields = record.split(",");
        id = Long.parseLong(fields[0]);
        price = Double.parseDouble(fields[1]);
        quantity = Integer.parseInt(fields[2]);
        orderId = Long.parseLong(fields[3]);
        clockId = Integer.parseInt(fields[4]);
        clockName = fields[5];
        total = Double.parseDouble(fields[6]);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getClockId() {
        return clockId;
    }

    public void setClockId(int clockId) {
        this.clockId = clockId;
    }

    public String getClockName() {
        return clockName;
    }

    public void setClockName(String clockName) {
        this.clockName = clockName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return id + "," + price + "," + quantity + "," + orderId + "," + clockId + "," + clockName + "," + total;
    }


}

