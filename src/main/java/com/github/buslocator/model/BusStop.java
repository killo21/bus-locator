package com.github.buslocator.model;


public class BusStop {
  private final long id;

  private final String name;

  public BusStop(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "BusStop{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
