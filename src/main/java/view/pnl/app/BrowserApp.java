package view.pnl.app;

import com.teamdev.jxbrowser.view.swing.BrowserView;
import controller.Controller;
import domain.jxbrowser.JxBrowser;
import domain.jxbrowser.JxBrowserView;

public class BrowserApp extends BaseApp{
    // WARNING: should be private, exhausted to package
    public JxBrowser jxBrowser = new JxBrowser(Controller.JX_ENGINE);

    private BrowserView browserView;

    public BrowserApp() {
        init();
    }

    private void init() {
        Controller.JX_BROWSERS.register(jxBrowser);
        browserView = JxBrowserView.getView(jxBrowser);
        browserView.setBounds(0, 40, this.getWidth(), this.getHeight() - 40);
    }

    public void updateView() {
        add(browserView);
        updateUI();
    }

    public void removeView() {
        removeAll();
    }
}
