package view.pnl;

import view.pnl.layout.*;
import view.pnl.tools.PanelRegistry;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public void initLayout(PanelRegistry<BasePanelImpl> registry) {
        registry.panels().forEach((name, panel) -> {
            this.add(panel);
        });
    }
}
