package app.opr;

import controller.Controller;
import enums.FileCharacters;
import enums.ImageConstantPath;
import app.opr.interfaces.BaseApp;
import view.pnl.interfaces.BasePanelImpl;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractApp extends BasePanelImpl implements BaseApp {
    public AbstractApp() {
        init();
    }

    private void init() {
        setSize(Controller.GetOprSize());
        setLayout(null);
        setOpaque(false);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon exePanelBg = new ImageIcon(ImageConstantPath.AppBgPath + "/" + "AbstractApp"  + FileCharacters.ImageSuffix);
        exePanelBg.paintIcon(this, g, 0, 40);
    }

    @Override
    public JPanel getView() {
        return this;
    }
}
