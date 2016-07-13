package com.github.buslocator;

import java.util.List;

public class Bus {
    private final long id;

    private final List<Event> eventList;

    private final Driver driver;

    public Bus(long id, List<Event> eventList, Driver driver) {
        this.id = id;
        this.eventList = eventList;
        this.driver = driver;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public Driver getDriver() {
        return driver;
    }

    public long getId() {
        return id;
    }
}
