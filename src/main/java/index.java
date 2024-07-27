import view.frm.MainFrame;
import view.pnl.manager.MenuMgr;
import view.pnl.MainPanel;
import view.pnl.manager.LayoutMgr;

public class index {
    public static void main(String[] args) {
        try {
            MainFrame mainFrame = new MainFrame();
            MainPanel mainPanel = new MainPanel();

            MenuMgr menuMgr = new MenuMgr();
            LayoutMgr layoutMgr = new LayoutMgr();

            layoutMgr.setMenus(menuMgr.menus());
            mainPanel.setLayouts(layoutMgr.panels());
            mainFrame.setPanel(mainPanel);
        } catch (Exception e) {
            // TODO: global exception handle
        }
    }
}
