package com.github.buslocator.model;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.time.LocalDateTime;

@Immutable
public class PassedStop {
  private final long id;

  private final LocalDateTime timestamp;

  private final BusStop busStop;

  public PassedStop(long id, LocalDateTime timestamp, BusStop busStop) {
    this.id = id;
    this.timestamp = timestamp;
    this.busStop = busStop;
  }

  public long getId() {
    return id;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public BusStop getBusStop() {
    return busStop;
  }

}
