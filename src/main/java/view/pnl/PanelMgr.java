package view.pnl;

import view.lbl.DatetimeLabel;
import view.pnl.layout.*;
import view.pnl.tools.PanelRegistry;

import java.awt.*;

public class PanelMgr {
    private final PanelRegistry<BasePanelImpl> layoutRegistry = new PanelRegistry<>();
    private final PanelRegistry<BasePanelImpl> appRegistry = new PanelRegistry<>();

    public PanelMgr() {
        registerLayouts();
        registerApps();

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

        layoutRegistry.registry("ExePanel", exeP);
    }

    private void registerAppLayout() {
        AppPanel appP = new AppPanel();

        appP.setBackground(Color.YELLOW);
        appP.setBounds(10, 190, 330, 810);

        layoutRegistry.registry("AppPanel", appP);
    }

    private void registerWebMgrLayout() {
        MgrPanel webMgrP = new MgrPanel();

        webMgrP.setBackground(Color.ORANGE);
        webMgrP.setBounds(350, 190, 1435, 810);

        layoutRegistry.registry("WebMgrPanel", webMgrP);
    }

    private void registerCtlLayout() {
        CtlPanel ctlP = new CtlPanel();
        DatetimeLabel timerL = new DatetimeLabel();
        ctlP.setTimer(timerL);

        ctlP.setBackground(Color.PINK);
        ctlP.setBounds(10,1010,1775,40);

        timerL.setBounds(1364,5,236,30);

        layoutRegistry.registry("CtlPanel", ctlP);
    }

    private void registerApps() {

    }

    private void initGeneralCharacters() {
        layoutRegistry.panels().forEach((name, panel) -> {
            panel.setLayout(null);
        });
        appRegistry.panels().forEach((name, panel) -> {
            panel.setLayout(null);
        });

    }

    public PanelRegistry<BasePanelImpl> getLayoutsPanels() {
        return layoutRegistry;
    }

    public PanelRegistry<BasePanelImpl> getAppsPanels() {
        return appRegistry;
    }
}
