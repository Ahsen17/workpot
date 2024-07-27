package view.pnl.manager;

import view.pnl.BasePanelImpl;
import view.pnl.tools.PanelRegistry;

public class AppMgr {
    private final PanelRegistry<BasePanelImpl> registry = new PanelRegistry<>();

    public AppMgr() {
        init();
    }

    private void init() {
        registerApps();
        initGeneralCharacters();
    }

    private void registerApps() {

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
