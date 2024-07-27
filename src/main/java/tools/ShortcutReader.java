package tools;

import net.jimmc.jshortcut.JShellLink;

public class ShortcutReader{
    private JShellLink lnkOpr;

    private ShortcutReader() {}

    public ShortcutReader(String lnkFolder, String lnkName) {
        lnkOpr = new JShellLink(lnkFolder, lnkName);
        lnkOpr.load();
    }

    public String getPath() {
        return lnkOpr.getPath();
    }

    public int getIconIndex() {
        return lnkOpr.getIconIndex();
    }

    public String getIconLocation() {
        return lnkOpr.getIconLocation();
    }
}
