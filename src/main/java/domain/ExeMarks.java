package domain;

import controller.Controller;
import db.SQLite;
import db.TableMgr;
import db.interfaces.NeedStorage;
import net.jimmc.jshortcut.JShellLink;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExeMarks implements NeedStorage {
    private final SQLite db = Controller.DB;

    public ExeMarks() {
        init();
    }

    private void init() {
        initTbl();
    }

    @Override
    public void initTbl() {
        if (TableMgr.exists(db, "exe")) return;
        String createTbl = "CREATE TABLE exe (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "folder VARCHAR(255) NOT NULL," +
                "name VARCHAR(255) NOT NULL, " +
                "path VARCHAR(255) NOT NULL, " +
                "icon VARCHAR(255), " +
                "iconIdx INTEGER, " +
                "createTime DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ")";
        TableMgr.execSql(db, createTbl);
    }

    public void addExe(JShellLink exe) {
        if (getExe(exe.getName()) != null) {
            return;
        }

        String insertRow = "INSERT INTO exe(folder, name, path, icon, iconIdx) VALUES(" +
                "'" + exe.getFolder() + "', " +
                "'" + exe.getName() + "', " +
                "'" + exe.getPath() + "', " +
                "'" + exe.getIconLocation() + "', " +
                "'" + exe.getIconIndex() + "'" +
                ")";
        TableMgr.execSql(db, insertRow);
    }

    public void deleteExe(JShellLink exe) {
        String deleteRow = "DELETE FROM exe WHERE name = '" + exe.getName() + "'";
        TableMgr.execSql(db, deleteRow);
    }

    public JShellLink getExe(String name) {
        String selectRow = "SELECT * FROM exe WHERE name = '" + name + "'";
        JShellLink exe = null;
        try (Connection conn = db.getConnection();
             Statement state = conn.createStatement();
             ResultSet res = state.executeQuery(selectRow)) {
            while (res.next()) {
                exe = new JShellLink(
                        res.getString("folder"),
                        res.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exe;
    }

    public ArrayList<JShellLink> getExes(int page) {
        String selectRow = "SELECT * FROM exe LIMIT 10 OFFSET " + (page - 1) * 10;
        ArrayList<JShellLink> exes = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement state = conn.createStatement();
             ResultSet res = state.executeQuery(selectRow)) {
            while (res.next()) {
                JShellLink exe = new JShellLink(
                        res.getString("folder"),
                        res.getString("name")
                );
                exes.add(exe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exes;
    }
}
