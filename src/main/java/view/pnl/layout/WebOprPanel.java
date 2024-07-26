package view.pnl.layout;

import view.pnl.BasePanelImpl;

import java.awt.*;

public class WebOprPanel extends BasePanelImpl {
    public WebOprPanel() {
        init();
    }

    private void init() {
        setLayout(null);
        setBackground(Color.GREEN);
        setBounds(0, 40, 1435, 770);
    }
}
