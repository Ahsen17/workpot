package controller;

import controller.mgrs.AppMgr;
import db.SQLite;
import domain.ExeMarks;
import domain.jxbrowser.JxBrowser;
import domain.jxbrowser.JxEngine;
import tools.ElementRegistry;
import view.frm.MainFrame;
import view.frm.interfaces.BaseFrameImpl;
import view.pnl.MainPanel;
import view.pnl.interfaces.BasePanelImpl;
import controller.mgrs.LayoutMgr;
import controller.mgrs.MenuMgr;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    private static final BaseFrameImpl MainFrame = new MainFrame();

    public static final SQLite DB = new SQLite("workpot");

    public static final ElementRegistry<BasePanelImpl> LAYOUTS = new ElementRegistry<>();

    public static final ElementRegistry<BasePanelImpl[]> MENUS = new ElementRegistry<>();

    public static final ElementRegistry<BasePanelImpl> APPS = new ElementRegistry<>();

    public static final JxEngine JX_ENGINE = new JxEngine();

    public static final ArrayList<JxBrowser> JX_BROWSERS = new ArrayList<>();

    public static final ElementRegistry<String> URL_HISTORIES = new ElementRegistry<>();

    public static final ExeMarks EXE_MARKS = new ExeMarks();

    private Controller() {}

    public static void initSwings() {
        try {
            SwingUtilities.invokeLater(() -> {
                MainPanel mainPanel = new MainPanel();

                LayoutMgr layoutMgr = new LayoutMgr();
                AppMgr appMgr = new AppMgr();
                MenuMgr menuMgr = new MenuMgr();

                layoutMgr.setMenus(menuMgr.menus());
                appMgr.setMenus(menuMgr.menus());
                mainPanel.setLayouts(layoutMgr.layouts());

                MainFrame.setPanel(mainPanel);
            });
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
        SwingUtilities.invokeLater(() -> {
            DB.close();
            JX_BROWSERS.forEach(JxBrowser::close);
            JX_ENGINE.close();
            System.exit(0);
        });
    }

    public static void OprFullscreen() {
        // TODO: 操作面板全屏
    }

    public static void UpdatePanelUI() {
        // 初始化面板
        LAYOUTS.elements().forEach((name, layout) -> {
            layout.updateUI();
        });
        MENUS.elements().forEach((name, menus) -> {
            for (BasePanelImpl menu : menus) {
                menu.updateUI();
            }
        });
        APPS.elements().forEach((name, app) -> {
            app.updateUI();
        });
    }

    public static void UpdatePanelUI(BasePanelImpl currentPanel) {
        currentPanel.updateUI();
    }
}
