package ru.job4j.serialization.java;

import java.util.Arrays;

public class CarsJson {
    private final boolean sportCar;
    private final int maxSpeed;
    private final String model;
    private final String[] id;
    private final EngineCarJson engine;

    public CarsJson(boolean sportCar, int maxSpeed, String model, String[] id, EngineCarJson engine) {
        this.sportCar = sportCar;
        this.maxSpeed = maxSpeed;
        this.model = model;
        this.id = id;
        this.engine = engine;
    }

    public boolean isSportCar() {
        return sportCar;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public String[] getId() {
        return id;
    }

    public EngineCarJson getEngine() {
        return engine;
    }

    @Override
    public String toString() {
        return "CarsJson{" + "sportCar=" + sportCar + ", maxSpeed=" + maxSpeed + ", model='" + model + '\'' + ", id=" + Arrays.toString(id) + ", engine=" + engine + '}';
    }
}
