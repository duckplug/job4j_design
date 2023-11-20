package ru.job4j.jdbc;

import ru.job4j.io.Config;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

          Class.forName("org.postgresql.Driver");
          /**
           * Пробный комит.
           *
           */

        Config conf = new Config("D:\\JP\\job4j_design\\data\\app.properties");
        conf.load();
        try (Connection connection = DriverManager.getConnection(conf.value("url"), conf.value("login"), conf.value("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
