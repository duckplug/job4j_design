package ru.job4j.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;


    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     *
     * Проверка правильности записей в файле
     */
    private boolean checkString()  {
        boolean check = true;
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            while (rd.ready()) {
                String str = rd.readLine();
                if (str.indexOf(";") == 0 || !str.contains("@")) {
                    check = false;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return check;
    }

    public List<User> load() throws IOException {
        if (!checkString()) {
            throw new IllegalArgumentException("Неверный формат записи");
        }
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(x -> users.add(new User(x.substring(0, x.indexOf(";") - 1), x.substring(x.indexOf(";") + 1, x.length() - 1))));
        }
        if (users.size() != 2) {
            throw new IllegalArgumentException("В массиве должно быть 2 элемента");
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./data/dump.txt");
        db.save(db.load());
    }
}