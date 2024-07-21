package view.pnl;

import view.frm.BaseFrameImpl;
import view.pnl.BasePanelImpl;
import view.pnl.tools.PanelRegistry;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainPanel extends BasePanelImpl {
    private final static PanelRegistry<JPanel> layoutPnlRegistry = new PanelRegistry<JPanel>();
    private final static PanelRegistry<JPanel> modulePanelRegistry = new PanelRegistry<JPanel>();

    public MainPanel() {
        init();
    }

    public void init() {
        setBackground(Color.LIGHT_GRAY);
        setLocation(0, 0); // 居中
        setLayout(null); // 绝对布局

        initLayout();
        initModules();
    }

    private void setCharacters() {

    }

    private void initLayout() {
        HashMap<String, JPanel> panels = layoutPnlRegistry.panels();
        // TODO: init main panel layout
    }

    private void initModules() {
        HashMap<String, JPanel> panels = modulePanelRegistry.panels();
        // TODO: init main panel modules
    }
}
