package com.github.buslocator.model;


import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.List;

@Immutable
public class Route {
  private long id;

  private String name;

  private ImmutableList<BusStop> busStops;

  public Route(long id, String name, List<BusStop> busStops) {
    this.id = id;
    this.name = name;
    this.busStops = ImmutableList.copyOf(busStops);
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

  @Override
  public String toString() {
    return "Route{" + "id=" + id + ", name='" + name + ", bus stops= " + busStops + "}";
  }
}
