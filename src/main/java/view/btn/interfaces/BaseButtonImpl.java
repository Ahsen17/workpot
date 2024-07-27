package view.btn.interfaces;

import view.btn.interfaces.BaseButton;

import javax.swing.*;
import java.awt.event.MouseAdapter;

public abstract class BaseButtonImpl extends JButton implements BaseButton {
    protected void setActionListener() {}

    protected void setActionListener(MouseAdapter adapter) {
        addMouseListener(adapter);
    }
}
