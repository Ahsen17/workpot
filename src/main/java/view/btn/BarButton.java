package view.btn;

import controller.Controller;
import controller.mgrs.AppMgr;
import view.btn.interfaces.CircleButtonImpl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BarButton extends CircleButtonImpl {
    public BarButton() {
        init();
    }

    public BarButton(String name) {
        setText(name);
        init();
    }

    private void init() {
        setContentAreaFilled(false);
        setSize(100,30);
        setFocusPainted(false);
//        setBorder(null);
        initCircle();

        var btnFlag = this;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // 点击则更新操作面板的app
                int index = AppMgr.TASKBAR_BUTTONS.index(btnFlag);
                if (index >= 0) AppMgr.updateView(Controller.APPS_ON_LOAD.get(index));
            }
        });
    }

    private void initCircle() {
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
    }

    // 绘制圆形边框（精度不高，有明显锯齿）
//    @Override
//    protected void paintBorder(Graphics g) {
//        g.setColor(getForeground());
//        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//    }
}
