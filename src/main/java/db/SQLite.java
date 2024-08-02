package db;

import java.io.File;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;

public class SQLite {

    private final static String DataPath = "/data";

    private final String dbName;

    private Connection connection;

    private Statement statement;

    public class Table {
        private final String name;

        private HashMap<String, String> characters = new HashMap<>();

        public Table(String name) {
            this.name = name;
        }

        public void setCharacters(HashMap<String, String> kvs) {
            characters.putAll(kvs);
        }
        public void addCharacter(String key, String... values) {
            StringBuffer temp = new StringBuffer();
            Arrays.stream(values).forEach(value -> temp.append(value).append(" "));
            characters.putIfAbsent(key, temp.toString());
        }
    }

    public SQLite(String dbName) throws SQLException {
        this.dbName = dbName;
        init();
    }

    private void init() throws SQLException {
        File dataFolder = new File(DataPath);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        connection = DriverManager.getConnection("jdbc:sqlite:" + DataPath + "/" + dbName + ".db");
        statement = connection.createStatement();
    }

    public Table newTable(String name) {
        return new Table(name);
    }

    public void commitTransaction(String... sqls) throws SQLException {
        if (sqls == null || sqls.length == 0) {
            return;
        }
        try {
            connection.setAutoCommit(false);
            for (String sql : sqls) {
                statement.executeUpdate(sql);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void createTables(Table... tables){
        Arrays.stream(tables).forEach(table -> {
            try {
                statement.executeUpdate("DROP TABLE IF EXISTS " + table.name);
                String characters = table.characters.toString()
                        .replace("{", "")
                        .replace("}", "")
                        .replace("=", " ");
                String sql = "CREATE TABLE " + table.name + " (" + characters.strip() + ")";
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    public void dropTables(String... names) {
        Arrays.stream(names).forEach(name -> {
            try {
                statement.executeUpdate("DROP TABLE IF EXISTS " + name);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    public String[] showTables() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
        String[] names = new String[0];
        while (rs.next()) {
            String name = rs.getString(1);
            names = Arrays.copyOf(names, names.length + 1);
            names[names.length - 1] = name;
        }
        return names;
    }

    public HashMap<String, String> showTableStruct(String tblName) {
        HashMap<String, String> struct = new HashMap<>();
        try {
            ResultSet rs = statement.executeQuery("PRAGMA table_info(" + tblName + ")");
            while (rs.next()) {
                String name = rs.getString(1);
                String type = rs.getString(2);
                struct.putIfAbsent(name, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return struct;
    }

    public void execSql(String sql) throws SQLException {
        statement.executeUpdate(sql);
    }


    public void close() throws SQLException {
        connection.close();
    }
}
