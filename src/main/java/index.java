import view.frm.MainFrame;
import view.pnl.MainPanel;
import view.pnl.PanelMgr;

public class index {
    public static void main(String[] args) {
        try {
            MainFrame mainFrame = new MainFrame();
            MainPanel mainPanel = new MainPanel();
            PanelMgr panelMgr = new PanelMgr();

            mainPanel.initLayout(panelMgr.getLayoutsPanels());

            mainFrame.setPanel(mainPanel);
        } catch (Exception e) {
            // TODO: global exception handle
        }
    }
}
