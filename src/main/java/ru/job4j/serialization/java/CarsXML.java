package ru.job4j.serialization.java;

import java.util.Arrays;

public class CarsXML {
    private final boolean sportCar;
    private final int maxSpeed;
    private final String model;
    private final String[] id;
    private final EngineCarXML engine;

    public CarsXML(boolean sportCar, int maxSpeed, String model, String[] id, EngineCarXML engine) {
        this.sportCar = sportCar;
        this.maxSpeed = maxSpeed;
        this.model = model;
        this.id = id;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "CarsJson{" + "sportCar=" + sportCar + ", maxSpeed=" + maxSpeed + ", model='" + model + '\'' + ", id=" + Arrays.toString(id) + ", engine=" + engine + '}';
    }
}

