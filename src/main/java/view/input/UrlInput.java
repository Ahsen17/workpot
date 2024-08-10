package view.input;

import controller.Controller;
import enums.AppEnum;
import view.pnl.app.BrowserApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UrlInput extends JTextField {
    private static final String HTTPS = "https://";

    public UrlInput() {
        init();
        setActionListener();
    }

    private void init() {
        setFont(new Font("url", 3, 20));
        setOpaque(false);
        setBorder(null);
        setText(HTTPS);
    }

    private void setActionListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String url = getText();
                if (url == null || url.isEmpty()) return;
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    setText(HTTPS);
                    Controller.NewJxBrowser().loadUrl(url);
                    BrowserApp browser = (BrowserApp) Controller.APPS.elements().get(AppEnum.Browser);
                    browser.removeView();
                    browser.updateView();
                }
            }
        });
    }
}
