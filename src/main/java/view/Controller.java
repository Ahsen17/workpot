package view;

import db.DBMgr;
import db.SQLite;
import tools.ElementRegistry;
import view.frm.MainFrame;
import view.frm.interfaces.BaseFrameImpl;
import view.pnl.MainPanel;
import view.pnl.interfaces.BasePanelImpl;
import view.pnl.manager.LayoutMgr;
import view.pnl.manager.MenuMgr;

import java.util.HashMap;

public class Controller {
    private static final DBMgr db = new DBMgr("workpot");

    private static final BaseFrameImpl MainFrame = new MainFrame();

    public static final ElementRegistry<BasePanelImpl> Layouts = new ElementRegistry<>();

    public static final ElementRegistry<BasePanelImpl[]> Menus = new ElementRegistry<>();

    public static final ElementRegistry<BasePanelImpl> Apps = new ElementRegistry<>();

    private Controller() {}

    static {
        initDBAndTbls();
    }

    public static void initDBAndTbls() {
        if (db.showTables() != null) {
            return;
        }
        db.createTable(
                db.newTable("app")
                        .addCharacter("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
                        .addCharacter("name", "VARCHAR(16) NOT NULL"),
                db.newTable("exe")
                        .addCharacter("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
                        .addCharacter("name", "VARCHAR(255) NOT NULL")
                        .addCharacter("path", "VARCHAR(255) NOT NULL")
        );
    }

    public static DBMgr getDB() {
        return db;
    }

    public static void initSwings() {
        try {
            MainPanel mainPanel = new MainPanel();

            MenuMgr menuMgr = new MenuMgr();
            LayoutMgr layoutMgr = new LayoutMgr();

            layoutMgr.setMenus(menuMgr.menus());
            mainPanel.setLayouts(layoutMgr.layouts());

            MainFrame.setPanel(mainPanel);
        } catch (Exception e) {
            // TODO: global exception handle
        }
    }

    public static void Home() {
        // TODO: 清除当前所有组件，重新初始化

    }

    public static void Minimize() {
        MainFrame.setVisible(false);
        MainFrame.dispose();
    }

    public static void Exit() {
        System.exit(0);
    }

    public static void OprFullscreen() {
        // TODO: 操作面板全屏
    }

    public static void UpdatePanelUI() {
        // 初始化面板
        Layouts.elements().forEach((name, layout) -> {
            layout.updateUI();
        });
        Menus.elements().forEach((name, menus) -> {
            for (BasePanelImpl menu : menus) {
                menu.updateUI();
            }
        });
        Apps.elements().forEach((name, app) -> {
            app.updateUI();
        });
    }

    public static void UpdatePanelUI(BasePanelImpl currentPanel) {
        currentPanel.updateUI();
    }
}
