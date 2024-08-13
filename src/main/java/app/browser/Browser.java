package app.browser;

import app.opr.AbstractApp;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import controller.Controller;
import controller.mgrs.LayoutMgr;
import domain.jxbrowser.JxBrowser;
import domain.jxbrowser.JxBrowserView;
import tools.ElementRegistry;
import view.pnl.interfaces.BasePanelImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Browser extends AbstractApp {
    private final ElementRegistry<JxBrowser> jxBrowses = new ElementRegistry<>(List.class);

    private JxBrowser jxBrowser;

    public Browser() {
        init();
    }

    private void init() {
        jxBrowser = newJxBrowser();
    }

    public JxBrowser newJxBrowser() {
        JxBrowser tmp = JxBrowser.newInstance(Controller.JX_ENGINE);
        jxBrowses.register(0, tmp);
        return tmp;
    }

    @Override
    public JPanel getView() {
        // 菜单
        Rectangle browserMR = new Rectangle(0, 0, getWidth(), 40);
        BrowserUrlInput urlI = new BrowserUrlInput();
        urlI.setBounds(0, 0, 400, 40);
        urlI.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String url = urlI.getText();
                if (url == null || url.isEmpty()) return;
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    urlI.setText(BrowserUrlInput.HTTPS);
                    jxBrowser.loadUrl(url);
                }
            }
        });
        BasePanelImpl browserMenu = LayoutMgr.initElement(browserMR, BrowserMenu.class, urlI);

        // 浏览器视图
        BrowserView browserView = JxBrowserView.getView(jxBrowser);
        browserView.setBounds(0, 40, this.getWidth(), this.getHeight() - 40);

        add(browserView, browserMenu);
        updateUI();
        return this;
    }

    @Override
    public void dispose() {
        jxBrowses.list().forEach(JxBrowser::close);
        removeAll();
    }
}
