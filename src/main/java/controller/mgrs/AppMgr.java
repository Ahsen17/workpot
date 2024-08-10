package controller.mgrs;

import controller.Controller;
import enums.AppEnum;
import org.jetbrains.annotations.NotNull;
import view.lbl.UrlHistoryLabel;
import view.pnl.app.BaseApp;
import view.pnl.app.BrowserApp;
import view.pnl.interfaces.BasePanelImpl;
import tools.ElementRegistry;

import java.util.HashMap;

public class AppMgr {
    private final ElementRegistry<BaseApp> registry = Controller.APPS;

    public AppMgr() {
        init();
    }

    private void init() {
        registerApps();
        initGeneralCharacters();
    }

    private void registerApps() {
        BrowserApp browser;
        // TODO: 添加历史浏览记录标签，但未显示
        browser = (BrowserApp) LayoutMgr.initElement(null, BrowserApp.class, LabelMgr.initBrowserAppLabels());

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
        HashMap<String, BaseApp> eleMap = registry.elements();
        eleMap.forEach((name, panel) -> {
            if (menusMap.get(name) == null) {
                return;
            }
            panel.add(menusMap.get(name));
        });
    }

    public ElementRegistry<BaseApp> apps() {
        return registry;
    }
}
