package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableMgr {
    private TableMgr() {}

    public static boolean exists(SQLite db, String tblName) {
        Connection conn = db.getConnection();
        // return if table exists
        Statement state = null;
        try {
            state = conn.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
            ArrayList<String> tbls = new ArrayList<>();
            while (res.next()) {
                tbls.add(res.getString(1));
            }
            if (tbls.size() != 0) {
                tbls.stream().filter(s -> s.equals("exe")).forEach(System.out::println);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void execSql(SQLite db, String sql) {
        Connection conn = db.getConnection();
        Statement state = null;
        try {
            state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException e) {
            db.rollback();
            e.printStackTrace();
        }
    }
}
