package view.pnl.manager;

import view.Controller;
import view.btn.CtlButton;

public class ButtonMgr {
    public static CtlButton[] initCtlMenuButtons() {
        CtlButton[] ctlMBtns = new CtlButton[3]; {
            // 1. 跳转主页并初始化（释放内存）
            ctlMBtns[0] = new CtlButton("home");
            ctlMBtns[0].addActionListener(event -> {
                // TODO: 跳转主页并重新初始化程序
            });

            // 2. 最小化到托盘
            ctlMBtns[1] = new CtlButton("tray");
            ctlMBtns[1].addActionListener(event -> {
                Controller.Minimize();
            });

            // 3. 关闭程序
            ctlMBtns[2] = new CtlButton("exit");
            ctlMBtns[2].addActionListener(event -> {
                Controller.Exit();
            });

            int gap = 10;
            for (int idx = 0; idx < ctlMBtns.length; idx++) {
                ctlMBtns[idx].setLocation((ctlMBtns[idx].getWidth() + gap) * idx, 0);
            }
        }

        return ctlMBtns;
    }
}
