package com.github.buslocator.model;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
