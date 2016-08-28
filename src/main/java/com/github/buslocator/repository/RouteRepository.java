package com.github.buslocator.repository;


import com.github.buslocator.model.BusMovement;
import com.github.buslocator.model.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteRepository {
  private final File file;

  private final Map<Long, Route> routeMap;

  public RouteRepository(File file) throws IOException {
    this.file = file;
    this.routeMap = loadRouteMapFromFile(file);
  }

  private Map<Long, Route> loadRouteMapFromFile(File file) throws IOException {
    if (file.exists()) {
      final String json = readStringFromFile(file);
      return convertJsonToRouteMap(json);
    } else {
      return new HashMap<>();
    }
  }

  private String readStringFromFile(File file) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      return reader.readLine();
    }
  }

  private Map<Long, Route> convertJsonToRouteMap(String json) {
    final Gson gson = new Gson();
    Type type = new TypeToken<Map<Long, Route>>() {}.getType();
    return gson.fromJson(json, type);
  }

  public void save(Route route) {
    routeMap.put(route.getId(), route);
    try {
      saveStringIntoFile(convertMapToJson());
    } catch (IOException e) {
      throw new IllegalStateException("Cannot save route: " + route, e);
    }
  }

  private String convertMapToJson() {
    final Gson gson = new Gson();
    return gson.toJson(routeMap);
  }

  private void saveStringIntoFile(String json) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(json);
      writer.write("\n");
    }
  }

  public Route load(long id) {
    return routeMap.get(id);
  }

  public List<Route> loadAll() {
    return new ArrayList<>(routeMap.values());
  }
}
