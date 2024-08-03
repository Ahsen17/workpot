import controller.Controller;
import db.SQLite;
import db.TableMgr;
import domain.ExeMarks;
import net.jimmc.jshortcut.JShellLink;

import java.sql.SQLException;
import java.util.Arrays;

public class TestSqlite {
    public static void main(String[] args) throws SQLException {
        // WIKI: https://blog.csdn.net/FlyLikeButterfly/article/details/89405749
        String folder = "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs";
        String name = "firefox";
        JShellLink lnk = new JShellLink(folder, name);
        lnk.load();

        new ExeMarks().addExe(lnk);
    }
}
