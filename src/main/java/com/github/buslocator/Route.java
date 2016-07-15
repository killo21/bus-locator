package com.github.buslocator;


import java.util.List;

public class Route {
    private final long id;

    private final String name;

    private final List<BusStop> busStops;

    public Route(long id, String name, List<BusStop> busStops) {
        this.id = id;
        this.name = name;
        this.busStops = busStops;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }
}
