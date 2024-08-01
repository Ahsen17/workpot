import db.SQLite;

import java.sql.SQLException;
import java.util.Arrays;

public class TestSqlite {
    public static void main(String[] args) throws SQLException {
        // WIKI: https://blog.csdn.net/FlyLikeButterfly/article/details/89405749

        SQLite sqLite = new SQLite("workpot");
//        SQLite.Table t = sqLite.newTable("test");
//        t.addCharacter("id", "INTEGER PRIMARY KEY AUTOINCREMENT");
//        t.addCharacter("name", "VARCHAR(255)");
//        sqLite.createTables(t);

        sqLite.dropTables("test");
        System.out.println(Arrays.toString(sqLite.showTables()));
    }
}
