package db;

import java.io.File;
import java.sql.*;

public class SQLite {

    private final static String DataPath = "data/db";

    private final String dbName;

    private Connection connection;

    public SQLite(String dbName) {
        this.dbName = dbName;
        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void init() throws SQLException {
        File dataFolder = new File(DataPath);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        connection = DriverManager.getConnection("jdbc:sqlite:" + DataPath + "/" + dbName + ".db");
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
