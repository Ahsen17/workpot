package view.pnl.layout;

import view.pnl.BasePanelImpl;

import java.awt.*;

public class WebMgrPanel extends BasePanelImpl {
    public WebMgrPanel() {
        init();
    }

    public void init() {
        setLayout(null);
        setBackground(Color.ORANGE);
        setBounds(350, 190, 1435, 810);
    }
}
