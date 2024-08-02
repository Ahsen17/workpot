package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DBMgr {
    private final SQLite sqlite;

    public class ModTblType {
        public static final String ADD = "ADD";
        public static final String DROP = "DROP";
        public static final String MODIFY = "MODIFY";
        public static final String RENAME = "RENAME";
    }

    public DBMgr(String name) {
        try {
            sqlite = new SQLite(name);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public SQLite.Table newTable(String name) {
        return sqlite.newTable(name);
    }

    public void createTable(SQLite.Table... tables) {
        sqlite.createTables(tables);
    }

    public void dropTable(String name) {
        sqlite.dropTables(name);
    }

    public void modTable(String tblName, String column, String oprType, String characters) {
        String sql = null;
        switch (oprType) {
            case ModTblType.ADD:
                sql = "ALTER TABLE " + tblName + " ADD COLUMN " + column + " " + characters;
                break;
            case ModTblType.DROP:
                sql = "ALTER TABLE " + tblName + " DROP COLUMN " + column;
                break;
            case ModTblType.MODIFY:
                sql = "ALTER TABLE " + tblName + " MODIFY COLUMN " + column + " " + characters;
                break;
            case ModTblType.RENAME:
                sql = "ALTER TABLE " + tblName + " RENAME COLUMN " + column + " TO " + characters;
                break;
            default:
                return;
        }

        try {
            sqlite.commitTransaction(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> showTables() {
        ArrayList<String> tableNames = null;
        try {
            tableNames = sqlite.showTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableNames;
    }

    public HashMap<String, String> showTableStruct(String tblName) {
        return sqlite.showTableStruct(tblName);
    }

    public void execSql(String... sqls) {
        try {
            sqlite.commitTransaction(sqls);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
