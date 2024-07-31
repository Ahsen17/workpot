package view.btn;

import view.btn.interfaces.BaseButtonImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExeButton extends BaseButtonImpl {
    private final String name;

    public ExeButton(String name) {
        this.name = name;
        init();
    }

    private void init() {
        setSize(120, 140);
        setText(name);
        setContentAreaFilled(false); // 按钮透明
//        setBorder(null); // 无边框

        setActionListener();
    }

    @Override
    protected void setActionListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 鼠标点击执行操作
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // 鼠标移入的btn样式效果
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 鼠标移出的btn样式效果
            }
        });
    }
}
