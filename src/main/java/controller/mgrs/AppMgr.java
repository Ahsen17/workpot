package controller.mgrs;

import controller.Controller;
import enums.AppEnum;
import org.jetbrains.annotations.NotNull;
import view.pnl.app.BrowserApp;
import view.pnl.interfaces.BasePanelImpl;
import tools.ElementRegistry;

import java.util.HashMap;

public class AppMgr {
    private final ElementRegistry<BasePanelImpl> registry = Controller.APPS;

    public AppMgr() {
        init();
    }

    private void init() {
        registerApps();
        initGeneralCharacters();
    }

    private void registerApps() {
        BasePanelImpl browser;
        browser = LayoutMgr.initElement(null, BrowserApp.class);

        registry.register(
                registry.newEntry(AppEnum.Browser, browser)
        );
    }

    private void initGeneralCharacters() {
        registry.elements().forEach((name, panel) -> {
            panel.setLayout(null);
        });
    }

    public void setMenus(@NotNull HashMap<String, BasePanelImpl[]> menusMap) {
        HashMap<String, BasePanelImpl> eleMap = registry.elements();
        eleMap.forEach((name, panel) -> {
            if (menusMap.get(name) == null) {
                return;
            }
            panel.add(menusMap.get(name));
        });
    }

    public ElementRegistry<BasePanelImpl> panels() {
        return registry;
    }
}
