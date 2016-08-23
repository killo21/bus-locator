package com.github.buslocator.model;


import java.util.ArrayList;
import java.util.List;

public class Route {
  private long id;

  private String name;

  private List<BusStop> busStops;

  public Route() {
    this.id = 0L;
    this.name = "";
    this.busStops = new ArrayList<>();
  }

  public Route(long id, String name, List<BusStop> busStops) {
    this.id = id;
    this.name = name;
    this.busStops = busStops;
  }

  public void setBusStops(List<BusStop> busStops) {
    this.busStops = busStops;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(long id) {
    this.id = id;
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
