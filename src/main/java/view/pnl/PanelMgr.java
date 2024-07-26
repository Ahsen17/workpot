package view.pnl;

import view.lbl.DatetimeLabel;
import view.pnl.interfaces.BasePanel;
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
        BasePanelImpl exeP = new ExePanel();

        exeP.setBackground(Color.CYAN);
        exeP.setBounds(10, 10, 1775, 170);

        layoutRegistry.registry("ExePanel", exeP);
    }

    private void registerAppLayout() {
        BasePanelImpl appP = new AppPanel();

        appP.setBackground(Color.YELLOW);
        appP.setBounds(10, 190, 330, 810);

        layoutRegistry.registry("AppPanel", appP);
    }

    private void registerWebMgrLayout() {
        BasePanelImpl webMgrP = new WebMgrPanel();
        BasePanelImpl webOprP = new WebOprPanel();
        webMgrP.add(webOprP);

        webMgrP.setBackground(Color.ORANGE);
        webOprP.setBackground(Color.GREEN);
        webMgrP.setBounds(350, 190, 1435, 810);
        webOprP.setBounds(0, 40, 1435, 770);

        layoutRegistry.registry("WebMgrPanel", webMgrP);
    }

    private void registerCtlLayout() {
        BasePanelImpl ctlP = new CtlPanel();
        DatetimeLabel timerL = new DatetimeLabel();
        ctlP.add(timerL);

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
