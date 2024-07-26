package view.pnl;

import view.pnl.layout.*;
import view.pnl.tools.PanelRegistry;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MainPanel extends BasePanelImpl {
    private final static PanelRegistry<JPanel> modulePanelRegister = new PanelRegistry<JPanel>();

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
        // TODO: init main panel layout
        add(new ExePanel());
        add(new AppPanel());

        WebMgrPanel opr = new WebMgrPanel();
        opr.add(new WebOprPanel());
        add(opr);
        add(new MenuPanel());
    }

    private void initModules() {
        HashMap<String, JPanel> panels = modulePanelRegister.panels();
        // TODO: init main panel modules
    }

    public static void registryModule(String pnlName, JPanel module) {
        modulePanelRegister.registry(pnlName, module);
    }
}
