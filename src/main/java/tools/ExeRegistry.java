package tools;

import controller.Controller;
import net.jimmc.jshortcut.JShellLink;

public class ExeRegistry {
    private ExeRegistry() {}

    public static void register(String name, String folder) {
        JShellLink exe = new JShellLink(folder, name);
        exe.load();
        // TODO: 注册到表

        // 注册图标后更新界面UI
        Controller.UpdatePanelUI();
    }
}
