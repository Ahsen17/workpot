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
        setLocation(350, 190);
        setBounds(10, 190, 330, 810);
    }
}
