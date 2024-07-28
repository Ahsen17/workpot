package view.pnl.interfaces;

import javax.swing.*;
import java.awt.*;

public abstract class BasePanelImpl extends JPanel implements BasePanel {
    public void add(Component... components) {
        if (components == null) {
            return;
        }
        for (Component comp : components) {
            super.add(comp);
        }
    }
}
