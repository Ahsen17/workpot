package view.pnl.app;

import enums.FileCharacters;
import enums.ImageConstantPath;
import view.pnl.interfaces.BasePanelImpl;

import javax.swing.*;
import java.awt.*;

public class BaseApp extends BasePanelImpl {
    public BaseApp() {
        init();
    }

    private void init() {
        setBounds(0, 40, 1435, 770);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon exePanelBg = new ImageIcon(ImageConstantPath.AppBgPath + "/" + "BaseApp"  + FileCharacters.ImageSuffix);
        exePanelBg.paintIcon(this, g, 0, 0);
    }
}
