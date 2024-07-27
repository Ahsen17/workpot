package view.btn;

import view.btn.interfaces.BaseButtonImpl;

public class SwitchButton extends BaseButtonImpl {
    public SwitchButton() {
        init();
    }

    private void init() {
        setSize(50, 50);
        setContentAreaFilled(false);
        setFocusPainted(false); // 去按钮图标边框
        setBorderPainted(false); // 去掉按钮边框
    }
}
