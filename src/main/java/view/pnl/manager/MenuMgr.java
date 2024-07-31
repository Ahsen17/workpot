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
        exeMR = new Rectangle(0, 0, 1775, 170);
        appMR = new Rectangle(5, 60, 320, 690);
        oprMR = new Rectangle();
        ctlMR = new Rectangle(1630, 5, 140, 30);

        BasePanelImpl exeM, appM, oprM, ctlM;
        exeM = LayoutMgr.initElement(exeMR, ExeMenu.class, ButtonMgr.initExeMenuButtons(1));
        appM = LayoutMgr.initElement(appMR, AppMenu.class, ButtonMgr.initAppMenuButton(1));
        oprM = LayoutMgr.initElement(oprMR, OprMenu.class);
        ctlM = LayoutMgr.initElement(ctlMR, CtlMenu.class, ButtonMgr.initCtlMenuButtons());

        registry.register(
                registry.newEntry(ModuleEnum.EXE, new BasePanelImpl[]{exeM}),
                registry.newEntry(ModuleEnum.APP, new BasePanelImpl[]{appM}),
                registry.newEntry(ModuleEnum.CTL, new BasePanelImpl[]{ctlM})
        );
    }

    public HashMap<String, BasePanelImpl[]> menus() {
        return registry.elements();
    }
}
