package view.pnl.layout;

import view.pnl.BasePanelImpl;
import view.pnl.MainPanel;

import java.awt.*;

public class ExePanel extends BasePanelImpl {
    public ExePanel() {
        init();
    }

    private void init() {
        setLayout(null);
        setBackground(Color.CYAN);
        setBounds(10, 10, 1775, 170);
    }
}
