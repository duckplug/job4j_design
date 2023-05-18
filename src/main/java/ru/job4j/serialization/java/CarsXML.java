package ru.job4j.serialization.java;

import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;

@XmlRootElement(name = "carXML")
@XmlAccessorType(XmlAccessType.FIELD)

public class CarsXML {

    @XmlAttribute
    private boolean sportCar;

    @XmlAttribute
    private int maxSpeed;

    @XmlAttribute
    private String model;

    @XmlElementWrapper(name = "CarId")
    @XmlElement(name = "id")
    private  String[] id;
    private  EngineCarXML engine;

    public CarsXML() {
    }

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

