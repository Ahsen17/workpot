package view.frm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BaseFrameImpl extends JFrame implements BaseFrame {
    protected Float widthPercent;
    protected Float heightPercent;
    protected Integer width;
    protected Integer height;

    private Boolean frameSizeController = true;

    public BaseFrameImpl() {}

    protected void resize() {
        setSize(getSize());
    }

    protected void setFrameFixed() {
        setResizable(false);
        setLocationRelativeTo(null); // 窗口居中
    }

    protected void setTray() {
        // OS托盘
        SystemTray tray = SystemTray.getSystemTray();

        ImageIcon iconTmp = new ImageIcon("iconPath");
        // 托盘图标
        TrayIcon trayIcon = new TrayIcon(iconTmp.getImage(), "WorkPot");
        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==1){
                    setExtendedState(JFrame.NORMAL);
                    setVisible(true);
                }
            }
        });

        try {
            tray.add(trayIcon);//将托盘图标添加道系统的托盘实例中
        } catch (AWTException e) {
            e.printStackTrace();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                setVisible(false);//使窗口不可见
                dispose();//释放当前窗体资源
            }
        });
    }

    public void switchController () {
        this.frameSizeController = !this.frameSizeController;
        resize();
    }

    public void updateFrameSizePix(int width, int height) {
        this.width = width;
        this.height = height;
        this.frameSizeController = true;
        resize();
    }

    public void updateFrameSizePct(float widthPercent, float heightPercent) {
        this.widthPercent = widthPercent;
        this.heightPercent = heightPercent;
        this.frameSizeController = false;
        resize();
    }

    public Dimension getSize() {
        int width, height;
        if (frameSizeController) {
            width = this.width;
            height = this.height;
        } else {
            Dimension temp = Toolkit.getDefaultToolkit().getScreenSize();
            width = (int) (temp.width * this.widthPercent);
            height = (int) (temp.height * this.heightPercent);
        }
        return new Dimension(width, height);
    }

    public void fullscreen() {
        updateFrameSizePct(1, 1);
        setLocation(0, 0);
    }

    public void setPanel(JPanel panel) {
        add(panel);
        panel.updateUI();
    }
}
