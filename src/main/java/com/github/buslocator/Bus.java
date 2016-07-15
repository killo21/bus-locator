package com.github.buslocator;

public class Bus {
    private final long id;

    private final Driver driver;

    public Bus(long id, Driver driver) {
        this.id = id;
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public long getId() {
        return id;
    }
}
