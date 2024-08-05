package controller;

import controller.mgrs.AppMgr;
import db.SQLite;
import domain.ExeMarks;
import enums.AppEnum;
import tools.ElementRegistry;
import view.frm.MainFrame;
import view.frm.interfaces.BaseFrameImpl;
import view.pnl.MainPanel;
import view.pnl.app.BrowserApp;
import view.pnl.interfaces.BasePanelImpl;
import controller.mgrs.LayoutMgr;
import controller.mgrs.MenuMgr;

import javax.swing.*;

public class Controller {
    private static final BaseFrameImpl MainFrame = new MainFrame();

    public static final SQLite db = new SQLite("workpot");

    public static final ElementRegistry<BasePanelImpl> Layouts = new ElementRegistry<>();

    public static final ElementRegistry<BasePanelImpl[]> Menus = new ElementRegistry<>();

    public static final ElementRegistry<BasePanelImpl> Apps = new ElementRegistry<>();

    public static final ElementRegistry<String> UrlHistories = new ElementRegistry<>();

    public static final ExeMarks ExeMarks = new ExeMarks();

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
        BrowserApp browserApp = (BrowserApp) Apps.elements().get(AppEnum.Browser);
        SwingUtilities.invokeLater(() -> {
            browserApp.removeView();
            browserApp.browser.closeBrowser();
            browserApp.browser.closeEngine();
            System.exit(0);
        });
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
