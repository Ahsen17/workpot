package view.pnl.layout;

import enums.FileCharacters;
import enums.ImageConstantPath;
import view.lbl.interfaces.BaseLabelImpl;
import view.pnl.interfaces.BasePanelImpl;

import javax.swing.*;
import java.awt.*;

public class CtlPanel extends BasePanelImpl {
    public CtlPanel() {
        init();
    }

    private void init() {}

    public void setTimer(BaseLabelImpl timer) {
        add(timer);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon exePanelBg = new ImageIcon(ImageConstantPath.PanelBgPath + "/" + "CtlPanel" + FileCharacters.ImageSuffix);
        exePanelBg.paintIcon(this, g, 0, 0);
    }
}
