package controller.mgrs;

import enums.AppEnum;
import enums.ModuleEnum;
import tools.ElementRegistry;
import controller.Controller;
import view.input.UrlInput;
import view.pnl.interfaces.BasePanelImpl;
import view.pnl.menu.*;
import view.pnl.menu.Taskbar;

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
        Rectangle exeMR, appMR, oprMR, ctlMR, browserMR, taskbarMR;
        exeMR = new Rectangle(0, 0, 1775, 170);
        appMR = new Rectangle(5, 60, 320, 690);
        oprMR = new Rectangle();
        ctlMR = new Rectangle(1630, 5, 140, 30);
        browserMR = new Rectangle(0, 0, 1435, 40);
        taskbarMR = new Rectangle(340, 5, 1010, 30);

        // 操作面板
        UrlInput urlI = new UrlInput();
        urlI.setBounds(0, 0, 400, 40);

        BasePanelImpl exeM, appM, oprM, ctlM, browserM, taskbarM;
        exeM = LayoutMgr.initElement(exeMR, ExeMenu.class, ButtonMgr.initExeMenuButtons(1));
        appM = LayoutMgr.initElement(appMR, AppMenu.class, ButtonMgr.initAppMenuButton(1));
        oprM = LayoutMgr.initElement(oprMR, OprMenu.class);
        ctlM = LayoutMgr.initElement(ctlMR, CtlMenu.class, ButtonMgr.initCtlMenuButtons());
        browserM = LayoutMgr.initElement(browserMR, BrwMenu.class, urlI);
        taskbarM = LayoutMgr.initElement(taskbarMR, Taskbar.class);

        registry.register(
                registry.newEntry(ModuleEnum.EXE, new BasePanelImpl[]{exeM}),
                registry.newEntry(ModuleEnum.APP, new BasePanelImpl[]{appM}),
                registry.newEntry(ModuleEnum.CTL, new BasePanelImpl[]{ctlM, taskbarM}),
                registry.newEntry(AppEnum.Browser, new BasePanelImpl[]{browserM})
        );
    }

    public HashMap<String, BasePanelImpl[]> menus() {
        return registry.elements();
    }
}
