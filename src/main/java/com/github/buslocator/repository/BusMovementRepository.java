package com.github.buslocator.repository;

import com.github.buslocator.model.BusMovement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusMovementRepository {
  private final File file;

  private final Map<Long, BusMovement> busMovementMap;

  private final Gson gson = new GsonBuilder().create();

  public BusMovementRepository(File file) throws IOException {
    this.file = file;
    this.busMovementMap = loadBusMovementMapFromFile(file);
  }

  private Map<Long, BusMovement> loadBusMovementMapFromFile(File file) throws IOException {
    if (file.exists()) {
      final String json = readStringFromFile(file);
      return convertJsonToBusMovementMap(json);
    } else {
      return new HashMap<>();
    }
  }

  private String readStringFromFile(File file) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      return reader.readLine();
    }
  }

  private Map<Long, BusMovement> convertJsonToBusMovementMap(String json) {
    final Gson gson = new Gson();
    Type type = new TypeToken<Map<Long, BusMovement>>() {
    }.getType();
    return gson.fromJson(json, type);
  }

  public void save(BusMovement busMovement) throws IOException {
    busMovementMap.put(busMovement.getId(), busMovement);
    saveStringIntoFile(convertMapToJson());
  }

  private String convertMapToJson() {
    return gson.toJson(busMovementMap);
  }

  private void saveStringIntoFile(String json) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(json);
      writer.write("\n");
    }
  }

  public BusMovement load(long id) {
    return busMovementMap.get(id);
  }

  public List<BusMovement> loadAll() {
    return new ArrayList<>(busMovementMap.values());
  }
}
