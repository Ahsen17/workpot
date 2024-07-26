package view.pnl.layout;

import view.pnl.BasePanelImpl;

import java.awt.*;

public class CtlPanel extends BasePanelImpl {
    public CtlPanel() {
        init();
    }

    private void init() {
        setLayout(null);
        setBackground(Color.PINK);
        setBounds(10,1010,1775,40);
    }
}
