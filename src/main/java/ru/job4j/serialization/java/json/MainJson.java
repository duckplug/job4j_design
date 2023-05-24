package ru.job4j.serialization.java.json;

import org.json.JSONObject;
import ru.job4j.serialization.java.CarsJson;
import ru.job4j.serialization.java.EngineCarJson;

public class MainJson {
    public static void main(String[] args) {
         /**
          *JSONObject из json-строки
          */
        JSONObject jsonCar = new JSONObject("{\"sportCar\":false, \"maxSpeed\":200, \"model\":\"S14\","
                + "\"id\":[\"1\", \"SE\"], \"engine\" : { \"model\" : \"ME\", \"capacity\":3.2}}");
        System.out.println(jsonCar);
        /**
         *Объект в json-строку
         */
        CarsJson car = new CarsJson(false, 280, "ST-12", new String[]{"1", "2", "P"}, new EngineCarJson("DO", 3.2));
        System.out.println(new JSONObject(car));
    }
}
