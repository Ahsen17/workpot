package view.pnl;

import view.pnl.interfaces.BasePanelImpl;

import java.awt.*;
import java.util.HashMap;

public class MainPanel extends BasePanelImpl {
    public MainPanel() {
        init();
    }

    public void init() {
        setBackground(Color.LIGHT_GRAY);
        setLocation(0, 0); // 居中
        setLayout(null); // 绝对布局
    }

    private void setCharacters() {

    }

    public void setLayouts(HashMap<String, BasePanelImpl> panels) {
        panels.forEach((name, panel) -> {
            this.add(panel);
        });
    }
}
