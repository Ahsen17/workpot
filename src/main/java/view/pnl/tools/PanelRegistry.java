package view.pnl.tools;

import view.pnl.interfaces.BasePanel;

import java.util.HashMap;


public class PanelRegistry<T> {
    private HashMap<String, T> panelMap = new HashMap<String, T>();

    public PanelRegistry() {}

    public boolean register(String pName, T panel) {
        if (!(panel instanceof BasePanel)) {
            return false;
        }
        panelMap.putIfAbsent(pName, panel);
        return true;
    }

    public HashMap<String, T> panels() {
        return panelMap;
    }
}
