package com.github.buslocator.repository;

import com.github.buslocator.model.BusMovement;

import com.google.gson.Gson;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BusMovementRepository {
    private final File file;

    private final Map<Long, BusMovement> busMovementMap;

    public BusMovementRepository(File file) throws IOException{
        this.file = file;
        this.busMovementMap = loadBusMovementMapFromFile(file);
    }

    private Map<Long, BusMovement> loadBusMovementMapFromFile(File file) throws IOException{
        if(file.exists()){
            final String json = readStringFromFile(file);
            return convertJsonToBusMovementMap(json);
        }else{
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
        return gson.fromJson(json, Map.class);
    }

    public void save(BusMovement busMovement) throws IOException {
        busMovementMap.put(busMovement.getId(), busMovement);
        saveStringIntoFile(convertMapToJson());
    }

    private String convertMapToJson() {
        final Gson gson = new Gson();
        return gson.toJson(busMovementMap);
    }

    private void saveStringIntoFile(String json) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(json);
            writer.write("\n");
        }
    }

    public BusMovement load(long id) {return busMovementMap.get(id);}
}
