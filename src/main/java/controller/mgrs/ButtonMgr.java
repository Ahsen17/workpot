package controller.mgrs;

import controller.Controller;
import view.btn.AppButton;
import view.btn.CtlButton;
import view.btn.ExeButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ButtonMgr {
    public static ExeButton[] initExeMenuButtons(int page) {
        ExeButton[] exeBtns = new ExeButton[20]; {
            for (int i = 0; i < exeBtns.length; i++) {
                if (i / 10 + 1 > page) break;
                exeBtns[i] = new ExeButton(String.valueOf(i + 1));
                exeBtns[i].setLocation(15 + (i % 10) * 135, 15);
            }
        }

        return Arrays.stream(exeBtns).filter(Objects::nonNull).toArray(ExeButton[]::new);
    }

    public static AppButton[] initAppMenuButton(int page) {
        ArrayList<AppButton> appBtns = new ArrayList<>(); {
            // 1.主页
            AppButton homeApp = new AppButton("MainPane");
            appBtns.add(homeApp);
            homeApp.addActionListener(e -> {
                // TODO: 刷新操作面板即可
            });

            // 2.浏览器
            AppButton browserApp = new AppButton("Browser");
            appBtns.add(browserApp);
            browserApp.addActionListener(e -> {
                // TODO: 加载JxBrowser浏览器面板
            });

            // 3.富文本编辑器
            AppButton markdownApp = new AppButton("Markdown");
            appBtns.add(markdownApp);
            markdownApp.addActionListener(e -> {
                // TODO: 加载富文本编辑器面板
            });

            for (int i = 0; i < appBtns.size(); i++) {
                appBtns.get(i).setLocation(10, 30 + 80 * i);
            }
        }

        return appBtns.toArray(new AppButton[]{});
    }

    public static CtlButton[] initCtlMenuButtons() {
        CtlButton[] ctlMBtns = new CtlButton[3]; {
            // 1. 跳转主页并初始化（释放内存）
            ctlMBtns[0] = new CtlButton("home");
            ctlMBtns[0].addActionListener(event -> {
                Controller.Home();
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
