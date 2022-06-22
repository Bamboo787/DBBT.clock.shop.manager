package vn.td.clock.shop.services;

import vn.td.clock.shop.model.Clock;

import java.util.List;

public interface IClockService {

    List<Clock> getClocks();

    void add(Clock newClock);

    void update();

    Clock getClockById(int id);

    boolean exist(int id);

    boolean checkDuplicateName(String name);

    boolean checkDuplicateId(int id);

    void remove(Clock clock);
}

