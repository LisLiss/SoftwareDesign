package ru.akirakozov.sd.refactoring.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
}
