package com.github.buslocator;


import java.util.List;

public class BusMovement {
    private final long id;

    private final Bus bus;

    private final Route route;

    private final List<PassedStop> passedStops;

    public BusMovement(long id, Bus bus, Route route, List<PassedStop> passedStops) {
        this.id = id;
        this.bus = bus;
        this.route = route;
        this.passedStops = passedStops;
    }

    public long getId() {
        return id;
    }

    public Bus getBus() {
        return bus;
    }

    public Route getRoute() {
        return route;
    }

    public List<PassedStop> getPassedStops() {
        return passedStops;
    }
}
