package ru.job4j.serialization.java;

public class CarForXML {

    public static void main(String[] args) {
        CarsXML carsXML = new CarsXML(false, 200, "SL", new String[] {"1", "2", "C"}, new EngineCarXML("forSL", 2.2));
        System.out.println(carsXML);
    }
}
