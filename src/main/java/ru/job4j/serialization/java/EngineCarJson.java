package ru.job4j.serialization.java;

public class EngineCarJson {
    private final String modelEng;
    private final double capacity;

    public EngineCarJson(String model, double capacity) {
        this.modelEng = model;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "EngineCarJson{" + "model='" + modelEng + '\'' + ", capacity=" + capacity + '}';
    }
}
