import view.frm.MainFrame;
import view.pnl.MainPanel;
import view.pnl.manager.LayoutMgr;

public class index {
    public static void main(String[] args) {
        try {
            MainFrame mainFrame = new MainFrame();
            MainPanel mainPanel = new MainPanel();
            LayoutMgr layoutMgr = new LayoutMgr();

            mainPanel.initLayout(layoutMgr.panels());
            mainFrame.setPanel(mainPanel);
        } catch (Exception e) {
            // TODO: global exception handle
        }
    }
}
