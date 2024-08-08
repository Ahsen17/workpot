package view.btn;

import view.btn.interfaces.CircleButtonImpl;

import java.awt.*;

public class BarButton extends CircleButtonImpl {
    public BarButton() {
        init();
    }

    private void init() {
        setContentAreaFilled(false);
        setSize(30,30);
        setFocusPainted(false);
        setBorder(null);
        initCircle();
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
