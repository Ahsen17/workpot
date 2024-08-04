package view.pnl.app;

import com.teamdev.jxbrowser.view.swing.BrowserView;
import domain.JxBrowser;

public class BrowserApp extends BaseApp{
    // WARNING: should be private, exhausted to package
    public final JxBrowser browser = new JxBrowser();

    private final BrowserView browserView = browser.getView();

    public BrowserApp() {
        init();
    }

    private void init() {
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
