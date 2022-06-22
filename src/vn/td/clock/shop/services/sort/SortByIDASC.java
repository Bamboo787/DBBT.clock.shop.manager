package vn.td.clock.shop.services.sort;

import vn.td.clock.shop.model.Clock;

import java.util.Comparator;

public class SortByIDASC implements Comparator<Clock> {
    @Override
    public int compare(Clock o1, Clock o2) {
        return o1.getId() - o2.getId();
    }
}
