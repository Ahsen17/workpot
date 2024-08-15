package app.browser;

import app.opr.AbstractApp;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import controller.Controller;
import controller.mgrs.LayoutMgr;
import domain.jxbrowser.JxBrowser;
import domain.jxbrowser.JxBrowserView;
import enums.FileCharacters;
import enums.ImageConstantPath;
import tools.ElementRegistry;
import view.pnl.interfaces.BasePanelImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Browser extends AbstractApp {
    private static final ElementRegistry<JxBrowser> JX_BROWSERS = new ElementRegistry<>(List.class);

    private JxBrowser jxBrowser;

    private BrowserView view;

    public Browser() {
        init();
    }

    private void init() {
        jxBrowser = newJxBrowser();
    }

    private void updateView() {
        // 更新浏览器视图
        if (view != null) remove(view);
        view = JxBrowserView.getView(jxBrowser);
        view.setBounds(0, 40, getWidth(), getHeight() - 40);
        add(view);
        updateUI();
    }

    public JxBrowser newJxBrowser() {
        JxBrowser tmp = JxBrowser.newInstance(Controller.JX_ENGINE);
        JX_BROWSERS.register(0, tmp);
        return tmp;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon exePanelBg = new ImageIcon(ImageConstantPath.AppBgPath + "/" + "Browser"  + FileCharacters.ImageSuffix);
        exePanelBg.paintIcon(this, g, 0, 40);
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
                    updateView();
                }
            }
        });
        BasePanelImpl browserMenu = LayoutMgr.initElement(browserMR, BrowserMenu.class, urlI);

        add(browserMenu);
        updateUI();
        return this;
    }

    @Override
    public void dispose() {
        JX_BROWSERS.list().forEach(JxBrowser::close);
        removeAll();
    }
}
