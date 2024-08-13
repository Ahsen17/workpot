package controller;

import controller.mgrs.AppMgr;
import db.SQLite;
import domain.ExeMarks;
import domain.jxbrowser.JxBrowser;
import domain.jxbrowser.JxEngine;
import enums.ModuleEnum;
import tools.ElementRegistry;
import view.btn.BarButton;
import view.frm.MainFrame;
import view.frm.interfaces.BaseFrameImpl;
import view.pnl.MainPanel;
import view.pnl.app.BaseApp;
import view.pnl.interfaces.BasePanelImpl;
import controller.mgrs.LayoutMgr;
import controller.mgrs.MenuMgr;
import view.pnl.layout.OprPanel;

import javax.swing.*;
import java.util.*;

public class Controller {
    private static final BaseFrameImpl MainFrame = new MainFrame();

    public static final SQLite DB = new SQLite("workpot");

    public static final JxEngine JX_ENGINE = new JxEngine();

    public static final ElementRegistry<BasePanelImpl> LAYOUTS = new ElementRegistry<>(Map.class);

    public static final ElementRegistry<BasePanelImpl[]> MENU_LAYOUTS = new ElementRegistry<>(Map.class);

    public static final ElementRegistry<BasePanelImpl> APP_LAYOUTS = new ElementRegistry<>(Map.class);

    // TODO: 新创建的app注册到onload，后续通过onload来控制app生命周期
    public static final ElementRegistry<BaseApp> APPS = new ElementRegistry<>(Map.class);

    public static final ElementRegistry<BaseApp> APPS_ON_LOAD = new ElementRegistry<>(List.class);

    public static final ElementRegistry<JxBrowser> JX_BROWSERS = new ElementRegistry<>(List.class);

    public static final ElementRegistry<String> URL_HISTORIES = new ElementRegistry<>(Map.class);

    public static final ElementRegistry<BarButton> TASKBAR_BUTTONS = new ElementRegistry<>(List.class);
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
            JX_BROWSERS.array().forEach(JxBrowser::close);
            JX_ENGINE.close();
            System.exit(0);
        });
    }

    public static void OprFullscreen() {
        // TODO: 操作面板全屏
    }

    public static void UpdatePanelUI() {
        // 初始化面板
        LAYOUTS.elements().forEach((name, layout) -> layout.updateUI());
        MENU_LAYOUTS.elements().forEach((name, menus) -> Arrays.stream(menus).forEach(BasePanelImpl::updateUI));
        APP_LAYOUTS.elements().forEach((name, app) -> app.updateUI());
    }

    public static JxBrowser NewJxBrowser() {
        JxBrowser jxBrowser = JxBrowser.newInstance(Controller.JX_ENGINE);
        JX_BROWSERS.register(0, jxBrowser);
        return jxBrowser;
    }

    public static void UpdatePanelUI(BasePanelImpl currentPanel) {
        currentPanel.updateUI();
    }

    public static void RunApp(BaseApp app) {
        // TODO: 添加app及任务栏按钮
        BarButton barButton = new BarButton();
        barButton.linkApp(app);
        TASKBAR_BUTTONS.register(barButton);
    }

    public static void CloseApp(int index) {
        // 删除注册器中的索引
        BarButton barBtn = TASKBAR_BUTTONS.get(index);
        OprPanel opr = (OprPanel) LAYOUTS.elements().get(ModuleEnum.OPR);
        opr.remove(barBtn.getApp());
        TASKBAR_BUTTONS.remove(index);
    }
}
