package controller.mgrs;

import app.opr.AbstractApp;
import controller.Controller;
import enums.ModuleEnum;
import tools.ElementRegistry;
import view.pnl.layout.OprPanel;

public class AppMgr {
    private static final ElementRegistry<AbstractApp> onload = Controller.APPS_ON_LOAD;

    private static final OprPanel opr = (OprPanel) Controller.LAYOUTS.map().get(ModuleEnum.OPR);

    public static void loadApp(AbstractApp app) {
        opr.removeAll();
        opr.add(app.getView());
        Controller.UpdatePanelUI(opr);

        onload.register(0, app);
//        Controller.UpdatePanelUI();
    }

    public static void unloadApp(int index) {
        AbstractApp app = onload.list().get(index);
        opr.removeAll();
        Controller.UpdatePanelUI(opr);

        onload.remove(index);
//        Controller.UpdatePanelUI();
    }
}
