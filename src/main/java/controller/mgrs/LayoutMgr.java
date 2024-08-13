package controller.mgrs;

import enums.ModuleEnum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import controller.Controller;
import view.lbl.DatetimeLabel;
import view.pnl.interfaces.BasePanelImpl;
import view.pnl.layout.*;
import tools.ElementRegistry;

import java.awt.*;
import java.util.HashMap;

public class LayoutMgr {
    private final ElementRegistry<BasePanelImpl> registry = Controller.LAYOUTS;

    public LayoutMgr() {
        init();
    }

    private void init() {
        registerLayouts();
        setGenerals();
    }

    private void registerLayouts() {
        Rectangle exePR, appPR, oprPR, ctlPR;
        exePR = new Rectangle(10, 10, 1775, 170);
        appPR = new Rectangle(10, 190, 330, 810);
        oprPR = new Rectangle(350, 190, 1435, 810);
        ctlPR = new Rectangle(10, 1010, 1775, 40);

        // 控制面板
        DatetimeLabel timerL = new DatetimeLabel();
        timerL.setBounds(1364,5,236,30);

        BasePanelImpl exeP, appP, oprP, ctlP;
        exeP = initElement(exePR, ExePanel.class);
        appP = initElement(appPR, AppPanel.class);
        oprP = initElement(oprPR, OprPanel.class);
        ctlP = initElement(ctlPR, CtlPanel.class, timerL);

        registry.register(
                registry.newEntry(ModuleEnum.EXE, exeP),
                registry.newEntry(ModuleEnum.APP, appP),
                registry.newEntry(ModuleEnum.OPR, oprP),
                registry.newEntry(ModuleEnum.CTL, ctlP)
        );
    }

    private void setGenerals() {
        registry.map().forEach((name, panel) -> {
            panel.setLayout(null);
        });
    }

    public static <T> @Nullable BasePanelImpl initElement(Rectangle r, @NotNull Class<T> c, Component... comps) {
        BasePanelImpl layout;
        try {
            layout = (BasePanelImpl) c.newInstance();
            layout.add(comps);
            if (r != null) {
                layout.setBounds(r);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        return layout;
    }

    public void setMenus(@NotNull HashMap<String, BasePanelImpl[]> menusMap) {
        HashMap<String, BasePanelImpl> eleMap = registry.map();
        eleMap.forEach((name, panel) -> {
            if (menusMap.get(name) == null) {
                return;
            }
            panel.add(menusMap.get(name));
        });
    }

    public HashMap<String, BasePanelImpl> layouts() {
        return registry.map();
    }
}
