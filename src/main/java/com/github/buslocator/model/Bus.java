package com.github.buslocator.model;

import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public class Bus {
  private final long id;

  private final String name;

  public Bus(long id, String name) {
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
    return "Bus{" + "id=" + id + ", name='" + name + "'" + '}';
  }
}
