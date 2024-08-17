package app.browser;

import app.browser.element.menu.NavigationBar;
import app.browser.element.input.UrlInput;
import app.opr.AbstractApp;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import controller.Controller;
import controller.mgrs.LayoutMgr;
import domain.jxbrowser.JxBrowser;
import domain.jxbrowser.JxBrowserView;
import enums.FileCharacters;
import enums.ImageConstantPath;
import view.pnl.interfaces.BasePanelImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Browser extends AbstractApp {
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
        return JxBrowser.newInstance(Controller.JX_ENGINE);
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
        Rectangle navigationMR, sideMR;
        navigationMR = new Rectangle(0, 0, getWidth(), 40);

        UrlInput urlI = new UrlInput();
        urlI.setBounds(0, 0, 400, 40);
        urlI.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String url = urlI.getText();
                if (url == null || url.isEmpty()) return;
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    urlI.setText(UrlInput.HTTPS);
                    jxBrowser.loadUrlAndWait(url);
                    updateView();
                }
            }
        });
        BasePanelImpl navigationBar = LayoutMgr.initElement(navigationMR, NavigationBar.class, urlI);

        add(navigationBar);
        updateUI();
        return this;
    }

    @Override
    public void dispose() {
        jxBrowser.close();
        removeAll();
    }
}
