package vn.td.clock.shop.model;

import java.time.Instant;
import java.util.Comparator;

public class Clock implements Comparator<Clock> {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String description;
    private Instant createDate;
    private Instant createUpdate;

    public Clock(int id, String name, double price, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Clock(String record) {
        String[] fields = record.split(",");
        id = Integer.parseInt(fields[0]);
        name = fields[1];
        price = Double.parseDouble(fields[2]);
        quantity = Integer.parseInt(fields[3]);
        description = fields[4];
        createDate = Instant.parse(fields[5]);
        String temp = fields[6];
        if (temp != null && !temp.equals("null"))
            createUpdate = Instant.parse(temp);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getCreateUpdate() {
        return createUpdate;
    }

    public void setCreateUpdate(Instant createUpdate) {
        this.createUpdate = createUpdate;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + quantity + "," + description + "," + createDate + "," + createUpdate;
    }

    @Override
    public int compare(Clock o1, Clock o2) {
        return (int) (o1.getId() - o2.getId());
    }
}
