package controller;

import app.opr.AbstractApp;
import app.opr.interfaces.BaseApp;
import db.SQLite;
import domain.ExeMarks;
import domain.jxbrowser.JxEngine;
import enums.ModuleEnum;
import tools.ElementRegistry;
import view.btn.BarButton;
import view.frm.MainFrame;
import view.frm.interfaces.BaseFrameImpl;
import view.pnl.MainPanel;
import view.pnl.interfaces.BasePanelImpl;
import controller.mgrs.LayoutMgr;
import controller.mgrs.MenuMgr;
import view.pnl.layout.OprPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Controller {
    private static final BaseFrameImpl MainFrame = new MainFrame();

    public static final SQLite DB = new SQLite("workpot");

    public static final JxEngine JX_ENGINE = new JxEngine();

    public static final ElementRegistry<BasePanelImpl> LAYOUTS = new ElementRegistry<>(Map.class);

    public static final ElementRegistry<BasePanelImpl[]> MENU_LAYOUTS = new ElementRegistry<>(Map.class);

    public static final ElementRegistry<AbstractApp> APPS_ON_LOAD = new ElementRegistry<>(List.class);

    public static final ElementRegistry<String> URL_HISTORIES = new ElementRegistry<>(Map.class);

    public static final ExeMarks EXE_MARKS = new ExeMarks();

    private Controller() {}

    public static void initSwings() {
        try {
            SwingUtilities.invokeLater(() -> {
                MainPanel mainPanel = new MainPanel();

                LayoutMgr layoutMgr = new LayoutMgr();
                MenuMgr menuMgr = new MenuMgr();

                layoutMgr.setMenus(menuMgr.menus());
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
            APPS_ON_LOAD.list().forEach(BaseApp::dispose);
            DB.close();
            JX_ENGINE.close();
            System.exit(0);
        });
    }

    public static void OprFullscreen() {
        // TODO: 操作面板全屏
    }

    public static void UpdatePanelUI() {
        // 初始化面板
        LAYOUTS.map().forEach((name, layout) -> layout.updateUI());
        MENU_LAYOUTS.map().forEach((name, menus) -> Arrays.stream(menus).forEach(BasePanelImpl::updateUI));
        LAYOUTS.map().forEach((name, app) -> app.updateUI());
    }

    public static Dimension GetOprSize() {
        OprPanel opr = (OprPanel) LAYOUTS.map().get(ModuleEnum.OPR);
        return opr.getSize();
    }
}
