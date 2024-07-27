package view.pnl.manager;

import enums.ModuleEnum;
import tools.ElementRegistry;
import view.btn.CtlButton;
import view.btn.ExeButton;
import view.pnl.interfaces.BasePanelImpl;
import view.pnl.menu.AppMenu;
import view.pnl.menu.CtlMenu;
import view.pnl.menu.ExeMenu;
import view.pnl.menu.OprMenu;

import java.awt.*;
import java.util.HashMap;

public class MenuMgr {
    private final ElementRegistry<BasePanelImpl[]> registry = new ElementRegistry<>();

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

        // 控制面板菜单
        BasePanelImpl ctlM = LayoutMgr.initElement(ctlMR, CtlMenu.class); {
            CtlButton[] ctlMBtns = new CtlButton[3];
            // 1. 跳转主页并初始化（释放内存）
            ctlMBtns[0] = new CtlButton("home");
            ctlMBtns[0].addActionListener(event -> {
                // TODO: 跳转主页并重新初始化程序
            });

            // 2. 最小化到托盘
            ctlMBtns[1] = new CtlButton("tray");
            ctlMBtns[1].addActionListener(event -> {
                // TODO: 最小化窗口到托盘图标
            });

            // 3. 关闭程序
            ctlMBtns[2] = new CtlButton("exit");
            ctlMBtns[2].addActionListener(event -> {
                System.exit(0);
            });

            int gap = 10;
            for (int idx = 0; idx < ctlMBtns.length; idx++) {
                ctlMBtns[idx].setLocation((ctlMBtns[idx].getWidth() + gap) * idx, 0);
            }

            ctlM.add(ctlMBtns);
        }

        registry.register(
                registry.newEntry(ModuleEnum.CTL, new BasePanelImpl[]{ctlM})
        );
    }

    public HashMap<String, BasePanelImpl[]> menus() {
        return registry.elements();
    }
}
