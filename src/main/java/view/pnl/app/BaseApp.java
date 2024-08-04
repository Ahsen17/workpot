package view.pnl.app;

import enums.FileCharacters;
import enums.ImageConstantPath;
import view.pnl.interfaces.BasePanelImpl;

import javax.swing.*;
import java.awt.*;

public abstract class BaseApp extends BasePanelImpl {
    public BaseApp() {
        init();
    }

    private void init() {
        setSize(1435, 810);
        setLayout(null);
        setOpaque(false);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon exePanelBg = new ImageIcon(ImageConstantPath.AppBgPath + "/" + "BaseApp"  + FileCharacters.ImageSuffix);
        exePanelBg.paintIcon(this, g, 0, 40);
    }
}
