package view.btn;

import view.btn.interfaces.BaseButtonImpl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppButton extends BaseButtonImpl {
    public AppButton() {
        init();
    }

    private void init() {
        setSize(290, 70);
        setContentAreaFilled(false);
        setFont(new Font("appname", 3, 35));
        setFocusPainted(false); // 字体无边框
    }

    @Override
    protected void setActionListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
