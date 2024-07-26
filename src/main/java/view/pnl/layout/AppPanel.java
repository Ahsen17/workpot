package view.pnl.layout;

import view.pnl.BasePanelImpl;

import java.awt.*;

public class AppPanel extends BasePanelImpl {
    public AppPanel() {
        init();
    }

    private void init() {
        setLayout(null);
        setBackground(Color.YELLOW);
        setBounds(10, 190, 330, 810);
    }
}
