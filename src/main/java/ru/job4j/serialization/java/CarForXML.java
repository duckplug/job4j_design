package ru.job4j.serialization.java;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class CarForXML {

    public static void main(String[] args) throws IOException, JAXBException {
        CarsXML car = new CarsXML(false, 200, "ML", new String[] {"1", "2"}, new EngineCarXML("SC", 1.6));
        /**
         * Получаем контекст для доступа к АПИ
         */
        JAXBContext context = JAXBContext.newInstance(CarsXML.class);
        /**
         *  Создаем сериализатор
         */
        Marshaller marshaller = context.createMarshaller();
        /**
         * Указываем, что нам нужно форматирование
         */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /**
             * Сериализуем
             */
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /**
         * Для десериализации нам нужно создать десериализатор
         */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /**
             *  десериализуем
             */
            CarsXML result = (CarsXML) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}