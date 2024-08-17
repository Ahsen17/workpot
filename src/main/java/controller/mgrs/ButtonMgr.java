package controller.mgrs;

import app.browser.Browser;
import controller.Controller;
import domain.ExeMarks;
import enums.AppEnum;
import enums.ModuleEnum;
import net.jimmc.jshortcut.JShellLink;
import view.btn.AppButton;
import view.btn.CtlButton;
import view.btn.ExeButton;
import view.pnl.layout.OprPanel;

import javax.swing.*;
import java.util.*;

public class ButtonMgr {
    private ButtonMgr() {}

    private static void runExec(String exePath) {
        final Runtime runtime = Runtime.getRuntime();
        final String cmd = "rundll32 url.dll FileProtocolHandler file://" + exePath;
        try {
            runtime.exec(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ExeButton[] initExeMenuButtons(int page) {
        ExeMarks exeMarks = Controller.EXE_MARKS;
        ArrayList<JShellLink> exes = exeMarks.getExes(1);
        ExeButton[] exeBtns = new ExeButton[exes.size()]; {
            for (int i = 0; i < exeBtns.length; i++) {
                if (i / 10 + 1 > page) break;
                exeBtns[i] = new ExeButton(exes.get(i).getName()); {
                    JShellLink exe = exes.get(i);
                    String path = exe.getFolder() + "\\" + exe.getName();
                    exeBtns[i].addActionListener(e -> runExec(path));
                    exeBtns[i].setIcon(new ImageIcon(exes.get(i).getIconLocation()));
                }

                exeBtns[i].setLocation(15 + (i % 10) * 135, 15);
            }
        }

        return Arrays.stream(exeBtns).filter(Objects::nonNull).toArray(ExeButton[]::new);
    }

    public static AppButton[] initAppMenuButton(int page) {
        OprPanel opr = (OprPanel) Controller.LAYOUTS.map().get(ModuleEnum.OPR);
        ArrayList<AppButton> appBtns = new ArrayList<>(); {
            // 1.主页
            AppButton homeApp = new AppButton(AppEnum.MainPane);
            appBtns.add(homeApp);
            homeApp.addActionListener(e -> {
                // 去除并刷新操作面板
                opr.removeAll();
                opr.updateUI();
            });

            // 2.浏览器
            AppButton browserApp = new AppButton(AppEnum.Browser);
            appBtns.add(browserApp);
            browserApp.addActionListener(e -> {
                // TODO: 装载app成功，但是操作界面不显示
                AppMgr.loadApp(new Browser());
            });

            for (int i = 0; i < appBtns.size(); i++) appBtns.get(i).setLocation(10, 30 + 80 * i);
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
