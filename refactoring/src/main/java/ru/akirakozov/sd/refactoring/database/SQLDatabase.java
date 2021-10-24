package ru.akirakozov.sd.refactoring.database;

import java.sql.*;

public class SQLDatabase {
    private static final String DB_URL = "jdbc:sqlite:test.db";

    public static void initializeTable() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String sql = "CREATE TABLE IF NOT EXISTS PRODUCT" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " PRICE          INT     NOT NULL)";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            statement.close();
        }
    }

    public static ResultSet makeQuery(String query) {
        try {
            Statement statement = DriverManager.getConnection(DB_URL).createStatement();
            return statement.executeQuery(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
