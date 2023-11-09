package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver_class"));
        connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
    }

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            prop.load(in);
        }
        TableEditor tableEd = new TableEditor(prop);

        tableEd.createTable("NewTable");
        System.out.println(tableEd.getTableScheme("NewTable"));

        tableEd.addColumn("NewTable", "newColumn", "text");
        System.out.println(tableEd.getTableScheme("NewTable"));

        tableEd.dropColumn("NewTable", "newColumn");
        System.out.println(tableEd.getTableScheme("NewTable"));

        tableEd.renameColumn("NewTable", "name", "newName");
        System.out.println(tableEd.getTableScheme("NewTable"));

        tableEd.dropTable("NewTable");
        System.out.println(tableEd.getTableScheme("NewTable"));
    }

    public void executeSql(String sql) throws Exception {
        try (Statement stat = connection.createStatement()) {
            stat.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (%s, %s);",
                tableName,
                "id SERIAL PRIMARY KEY",
                "name TEXT"
            );
        executeSql(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
          "DROP TABLE %s", tableName
        );
        executeSql(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s",
                tableName,
                columnName,
                type);
        executeSql(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s",
                tableName,
                columnName);
        executeSql(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName,
                columnName,
                newColumnName);
        executeSql(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}