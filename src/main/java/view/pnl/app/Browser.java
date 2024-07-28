package view.pnl.app;

import view.pnl.menu.BrowserMenu;

public class Browser extends BaseApp{
    public Browser() {

    }

    private void init() {
        BrowserMenu bM = new BrowserMenu();
        add(bM);
    }
}
