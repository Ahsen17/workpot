package view.pnl.layout;

import common.FileCharacters;
import common.ImageConstantPath;
import view.pnl.BasePanelImpl;

import javax.swing.*;
import java.awt.*;

public class ExePanel extends BasePanelImpl {
    public ExePanel() {
        init();
    }

    private void init() {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon exePanelBg = new ImageIcon(ImageConstantPath.PanelBgPath + "/" + "ExePanel" + "." + FileCharacters.ImageSuffix);
        exePanelBg.paintIcon(this, g, 0, 0);
    }
}
