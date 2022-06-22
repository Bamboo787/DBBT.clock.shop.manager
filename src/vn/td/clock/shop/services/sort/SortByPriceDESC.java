package vn.td.clock.shop.services.sort;

import vn.td.clock.shop.model.Clock;

import java.util.Comparator;

public class SortByPriceDESC implements Comparator<Clock> {
    @Override
    public int compare(Clock o1, Clock o2) {
        if (o2.getPrice() > o1.getPrice()) {
            return 1;
        } else if (o1.getPrice() == o2.getPrice()) {
            return 0;
        } else
            return -1;
    }
}
