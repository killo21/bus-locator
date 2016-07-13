package com.github.buslocator;

import java.util.Date;

public class Event {
    private final long BusStopId;

    private final Date timestamp;

    private final String name;

    public Event(long busStopId, Date timestamp, String name) {
        BusStopId = busStopId;
        this.timestamp = timestamp;
        this.name = name;
    }

    public long getBusStopId() {
        return BusStopId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }
}
