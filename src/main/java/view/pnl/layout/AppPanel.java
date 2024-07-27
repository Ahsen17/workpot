package view.pnl.layout;

import enums.FileCharacters;
import enums.ImageConstantPath;
import view.pnl.interfaces.BasePanelImpl;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends BasePanelImpl {
    public AppPanel() {
        init();
    }

    private void init() {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon exePanelBg = new ImageIcon(ImageConstantPath.PanelBgPath + "/" + "AppPanel" + FileCharacters.ImageSuffix);
        exePanelBg.paintIcon(this, g, 0, 0);
    }
}
