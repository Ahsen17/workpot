package view.btn;

import controller.Controller;
import enums.ModuleEnum;
import view.btn.interfaces.CircleButtonImpl;
import view.pnl.app.BaseApp;
import view.pnl.layout.OprPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BarButton extends CircleButtonImpl {
    private BaseApp app;

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

    public void linkApp(BaseApp app) {
        this.app = app;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // TODO: 点击则更新操作面板的app
                OprPanel opr = (OprPanel) Controller.LAYOUTS.elements().get(ModuleEnum.OPR);
                opr.removeAll();
                opr.add(app);
                opr.updateUI();
            }
        });
    }

    public BaseApp getApp() {
        return app;
    }
}
