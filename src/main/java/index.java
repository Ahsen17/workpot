import view.frm.MainFrame;
import view.pnl.MainPanel;

public class index {
    public static void main(String[] args) {
        try {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setPanel(new MainPanel());
        } catch (Exception e) {
            // TODO: global exception handle
        }
    }
}
