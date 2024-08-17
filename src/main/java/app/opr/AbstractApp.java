package app.opr;

import controller.Controller;
import app.opr.interfaces.BaseApp;
import view.pnl.interfaces.BasePanelImpl;

import javax.swing.*;

public abstract class AbstractApp extends BasePanelImpl implements BaseApp {
    protected String name = this.getClass().getSimpleName();

    public AbstractApp() {
        init();
    }

    private void init() {
        setSize(Controller.GetOprSize());
        setLayout(null);
        setOpaque(false);
    }

    @Override
    public JPanel getView() {
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
