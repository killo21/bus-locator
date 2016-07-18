package com.github.buslocator.model;

import java.util.Date;

public class PassedStop {
    private final long id;

    private final Date timestamp;

    private final BusStop busStop;

    public PassedStop(long id, Date timestamp, BusStop busStop) {
        this.id = id;
        this.timestamp = timestamp;
        this.busStop = busStop;
    }

    public long getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public BusStop getBusStop() {
        return busStop;
    }
}
