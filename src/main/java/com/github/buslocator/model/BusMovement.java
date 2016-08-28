package com.github.buslocator.model;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.List;

@Immutable
public class BusMovement {
  private final long id;

  private final Bus bus;

  private final Route route;

  private final ImmutableList<PassedStop> passedStops;

  public BusMovement(long id, Bus bus, Route route, List<PassedStop> passedStops) {
    this.id = id;
    this.bus = bus;
    this.route = route;
    this.passedStops = ImmutableList.copyOf(passedStops);
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
