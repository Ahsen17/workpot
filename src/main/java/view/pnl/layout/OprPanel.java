package view.pnl.layout;

import enums.FileCharacters;
import enums.ImageConstantPath;
import view.pnl.interfaces.BasePanelImpl;

import javax.swing.*;
import java.awt.*;

public class OprPanel extends BasePanelImpl {
    public OprPanel() {
        init();
    }

    public void init() {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon exePanelBg = new ImageIcon(ImageConstantPath.PanelBgPath + "/" + "MgrPanel" + FileCharacters.ImageSuffix);
        exePanelBg.paintIcon(this, g, 0, 0);
    }
}
