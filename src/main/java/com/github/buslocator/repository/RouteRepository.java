package com.github.buslocator.repository;


import com.github.buslocator.model.Route;
import com.google.gson.Gson;

import java.io.*;

public class RouteRepository {
    private final File file;

    public RouteRepository(File file) {
        this.file = file;
    }

    public void save(Route route) throws IOException {
        final String json = convertRouteToJson(route);
        saveStringIntoFile(json);
    }

    private String convertRouteToJson(Route route) {
        final Gson gson = new Gson();
        return gson.toJson(route);
    }

    private void saveStringIntoFile(String json) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(json);
            writer.write("\n");
        }
    }

    public Route load(long id) throws IOException {
        final String json = readStringFromFile();
        return convertJsonToRoute(json);
    }

    private String readStringFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine();
        }
    }

    private Route convertJsonToRoute(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, Route.class);
    }
}
