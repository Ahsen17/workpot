package controller.mgrs;

import app.opr.AbstractApp;
import controller.Controller;
import enums.ModuleEnum;
import tools.ElementRegistry;
import view.btn.BarButton;
import view.pnl.layout.OprPanel;
import view.pnl.menu.Taskbar;

import java.util.List;

public class AppMgr {
    private static final OprPanel opr = (OprPanel) Controller.LAYOUTS.map().get(ModuleEnum.OPR);

    private static final ElementRegistry<AbstractApp> onload = Controller.APPS_ON_LOAD;

    private static final Taskbar taskbar = (Taskbar) LayoutMgr.initElement(null, Taskbar.class);

    public static final ElementRegistry<BarButton> TASKBAR_BUTTONS = new ElementRegistry<>(List.class);


    private static void updateTaskbar() {
        taskbar.removeAll();
        List<BarButton> btns = TASKBAR_BUTTONS.list();
        btns.forEach(btn -> {
            int index = TASKBAR_BUTTONS.index(btn);
            btn.setLocation(index * btn.getWidth(), 0);
        });
        taskbar.add(btns.toArray(BarButton[]::new));
        taskbar.updateUI();
    }

    public static void updateView(AbstractApp app) {
        opr.removeAll();
        if (app != null) opr.add(app.getView());
        opr.updateUI();
    }

    public static void loadApp(AbstractApp app) {
        onload.register(app);
        TASKBAR_BUTTONS.register(new BarButton(app.getName()));
        updateView(app);
        updateTaskbar();
    }

    public static void unloadApp(int index) {
        updateView(null);
        onload.remove(index);
        updateTaskbar();
    }

    public static Taskbar getTaskbar() {
        return taskbar;
    }
}
