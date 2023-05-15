package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonSample {
    public static void main(String[] args) {
        final CarsJson car = new CarsJson(false, 280, "ST-12", new String[]{"1", "2", "P"}, new EngineCarJson("DO", 3.2));
        final Gson gson = new GsonBuilder().create();
        System.out.println("Преобразуем объект в json-строку.");
        System.out.println(gson.toJson(car));
        final String carJson =
                "{"
                        + "\"sportCar\":false,"
                        + "\"maxSpeed\":260,"
                        + "\"model\":STP,"
                        + "\"id\":"
                        + "[\"1\",\"R\"],"
                        + "\"engine\":"
                        + "{"
                        + "\"model\":\"SM\","
                        + "\"capacity\":3.2"
                        + "}"
                        + "}";
        final CarsJson carMode = gson.fromJson(carJson, CarsJson.class);
        System.out.println("Преобразуем json-строку в объект");
        System.out.println(carMode);
    }
}
