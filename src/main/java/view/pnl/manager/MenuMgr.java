package view.pnl.manager;

import enums.ModuleEnum;
import tools.ElementRegistry;
import view.Controller;
import view.pnl.interfaces.BasePanelImpl;
import view.pnl.menu.AppMenu;
import view.pnl.menu.CtlMenu;
import view.pnl.menu.ExeMenu;
import view.pnl.menu.OprMenu;

import java.awt.*;
import java.util.HashMap;

public class MenuMgr {
    private final ElementRegistry<BasePanelImpl[]> registry = Controller.Menus;

    public MenuMgr() {
        init();
    }

    private void init() {
        registerMenus();
        setGenerals();
    }

    private void setGenerals() {
        registry.elements().forEach((name, menus) -> {
            for (BasePanelImpl menu : menus) {
                menu.setLayout(null);
            }
        });
    }

    private void registerMenus() {
        Rectangle exeMR, appMR, oprMR, ctlMR;
        ctlMR = new Rectangle(1630, 5, 140, 30);

        BasePanelImpl exeM, appM, oprM, ctlM;
        ctlM = LayoutMgr.initElement(ctlMR, CtlMenu.class, ButtonMgr.initCtlMenuButtons());

        registry.register(
                registry.newEntry(ModuleEnum.CTL, new BasePanelImpl[]{ctlM})
        );
    }

    public HashMap<String, BasePanelImpl[]> menus() {
        return registry.elements();
    }
}
