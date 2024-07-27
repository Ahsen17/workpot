package view.pnl.manager;

import view.lbl.DatetimeLabel;
import view.pnl.BasePanelImpl;
import view.pnl.layout.*;
import view.pnl.tools.PanelRegistry;

import java.awt.*;

public class LayoutMgr {
    private final PanelRegistry<BasePanelImpl> registry = new PanelRegistry<>();

    public LayoutMgr() {
        init();
    }

    private void init() {
        registerLayouts();
        initGeneralCharacters();
    }

    private void registerLayouts() {
        registerExeLayout();
        registerAppLayout();
        registerWebMgrLayout();
        registerCtlLayout();
    }

    private void registerExeLayout() {
        ExePanel exeP = new ExePanel();

        exeP.setBackground(Color.CYAN);
        exeP.setBounds(10, 10, 1775, 170);

        registry.register("ExePanel", exeP);
    }

    private void registerAppLayout() {
        AppPanel appP = new AppPanel();

        appP.setBackground(Color.YELLOW);
        appP.setBounds(10, 190, 330, 810);

        registry.register("AppPanel", appP);
    }

    private void registerWebMgrLayout() {
        MgrPanel webMgrP = new MgrPanel();

        webMgrP.setBackground(Color.ORANGE);
        webMgrP.setBounds(350, 190, 1435, 810);

        registry.register("WebMgrPanel", webMgrP);
    }

    private void registerCtlLayout() {
        CtlPanel ctlP = new CtlPanel();
        DatetimeLabel timerL = new DatetimeLabel();
        ctlP.setTimer(timerL);

        ctlP.setBackground(Color.PINK);
        ctlP.setBounds(10,1010,1775,40);

        timerL.setBounds(1364,5,236,30);

        registry.register("CtlPanel", ctlP);
    }

    private void initGeneralCharacters() {
        registry.panels().forEach((name, panel) -> {
            panel.setLayout(null);
        });
    }

    public PanelRegistry<BasePanelImpl> panels() {
        return registry;
    }
}
