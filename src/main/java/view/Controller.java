package view;

import tools.ElementRegistry;
import view.frm.MainFrame;
import view.frm.interfaces.BaseFrameImpl;
import view.pnl.MainPanel;
import view.pnl.interfaces.BasePanelImpl;
import view.pnl.manager.LayoutMgr;
import view.pnl.manager.MenuMgr;

public class Controller {
    private static final BaseFrameImpl MainFrame = new MainFrame();

    public static final ElementRegistry<BasePanelImpl> Layouts = new ElementRegistry<>();

    public static final ElementRegistry<BasePanelImpl> Apps = new ElementRegistry<>();

    public static final ElementRegistry<BasePanelImpl[]> Menus = new ElementRegistry<>();

    public Controller() {

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
    }

    public static void Minimize() {
        MainFrame.setVisible(false);
        MainFrame.dispose();
    }

    public static void Exit() {
        System.exit(0);
    }
}
