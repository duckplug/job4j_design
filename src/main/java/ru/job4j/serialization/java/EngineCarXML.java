package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EngineXML")
public class EngineCarXML {

    @XmlAttribute
    private String modelEng;
    @XmlAttribute
    private double capacity;

    public EngineCarXML() {
    }

    public EngineCarXML(String model, double capacity) {
        this.modelEng = model;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "EngineCarJson{" + "model='" + modelEng + '\'' + ", capacity=" + capacity + '}';
    }
}
