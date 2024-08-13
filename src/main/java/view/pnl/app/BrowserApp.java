package view.pnl.app;

import com.teamdev.jxbrowser.view.swing.BrowserView;
import controller.Controller;
import domain.jxbrowser.JxBrowser;
import domain.jxbrowser.JxBrowserView;
import tools.ElementRegistry;

public class BrowserApp extends BaseApp{
    private final ElementRegistry<JxBrowser> jxBrowsers = Controller.JX_BROWSERS;
    private BrowserView browserView;

    private int index = 0;

    public BrowserApp() {
        init();
    }

    private void init() {}

    public void updateView() {
        index = 0;
        if (jxBrowsers.size() == 0) return;

        browserView = JxBrowserView.getView(jxBrowsers.list().get(index));
        browserView.setBounds(0, 40, this.getWidth(), this.getHeight() - 40);
        add(browserView);
        updateUI();
    }

    public void removeView() {
        if (browserView != null) remove(browserView);
    }
}
