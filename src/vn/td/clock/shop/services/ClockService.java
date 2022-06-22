package vn.td.clock.shop.services;

import vn.td.clock.shop.model.Clock;
import vn.td.clock.shop.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ClockService implements IClockService {
    List<Clock> clocks = new ArrayList<>();
    public static String path = "data/products.csv";

    public ClockService() {
        getClocks();
    }

    @Override
    public List<Clock> getClocks() {
        List<Clock> newClocks = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newClocks.add(new Clock(record));
        }
        return clocks = newClocks;
    }

    @Override
    public void add(Clock newClock) {
        clocks.add(newClock);
        CSVUtils.write(path, clocks);
    }

    @Override
    public void update() {
        List<Clock> clocks1 = getClocks();
        for (Clock clock : clocks1) {
            clock.setCreateUpdate(Instant.now());
            CSVUtils.write(path, clocks1);
            break;
        }
    }


    @Override
    public Clock getClockById(int id) {
        for (Clock clock : clocks) {
            if (clock.getId() == id)
                return clock;
        }
        return null;
    }

    @Override
    public boolean exist(int id) {
        return getClockById(id) != null;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        for (Clock clock : clocks) {
            if (clock.getName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateId(int id) {
        for (Clock clock : clocks) {
            if (clock.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public void remove(Clock clock) {
        clocks.remove(clock);
        update();
    }
}
