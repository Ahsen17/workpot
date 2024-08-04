import domain.JxBrowser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestJxBrowser {
    public static void main(String[] args) {
        JxBrowser browser = new JxBrowser();
        JFrame frame = new JFrame("JxBrowser AWT/Swing");
        frame.setSize(500, 500);
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 500, 500);
        frame.add(panel);
        browser.resizeBrowser(new Dimension(500, 500));
        browser.browserLoadUrl("https://www.bilibili.com");
        panel.add(browser.getView());
//        panel.updateUI();
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                browser.closeBrowser();
                browser.closeEngine();
                System.exit(0);
            }
        });
    }
}
