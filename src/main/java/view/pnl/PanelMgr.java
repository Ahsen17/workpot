package view.pnl;

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
        BasePanelImpl exeP = new ExePanel();
        BasePanelImpl appP = new AppPanel();
        BasePanelImpl webMgrP = new WebMgrPanel();
        BasePanelImpl webOprP = new WebOprPanel();
        BasePanelImpl ctlP = new CtlPanel();

        webMgrP.add(webOprP);

        exeP.setBackground(Color.CYAN);
        appP.setBackground(Color.YELLOW);
        webMgrP.setBackground(Color.ORANGE);
        webOprP.setBackground(Color.GREEN);
        ctlP.setBackground(Color.PINK);

        exeP.setBounds(10, 10, 1775, 170);
        appP.setBounds(10, 190, 330, 810);
        webMgrP.setBounds(350, 190, 1435, 810);
        webOprP.setBounds(0, 40, 1435, 770);
        ctlP.setBounds(10,1010,1775,40);

        layoutRegistry.registry("ExePanel", exeP);
        layoutRegistry.registry("AppPanel", appP);
        layoutRegistry.registry("WebMgrPanel", webMgrP);
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
