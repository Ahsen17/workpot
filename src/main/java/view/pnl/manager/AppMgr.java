package view.pnl.manager;

import view.pnl.interfaces.BasePanelImpl;
import tools.ElementRegistry;

public class AppMgr {
    private final ElementRegistry<BasePanelImpl> registry = new ElementRegistry<>();

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
        registry.elements().forEach((name, panel) -> {
            panel.setLayout(null);
        });
    }

    public ElementRegistry<BasePanelImpl> panels() {
        return registry;
    }
}
