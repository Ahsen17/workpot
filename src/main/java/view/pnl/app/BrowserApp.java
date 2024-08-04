package view.pnl.app;

import domain.JxBrowser;

public class BrowserApp extends BaseApp{
    private static final JxBrowser browser = new JxBrowser();

    public BrowserApp() {
        init();
    }

    private void init() {

    }

    public void initBrowser(String url) {
        if (url == null && url.length() == 0) {
            return;
        }

        browser.browserLoadUrl(url);
        var browserView = browser.getView();
        browserView.setBounds(0, 40, this.getWidth(), this.getHeight() - 40);
        add(browserView);
        updateUI();
    }

    public void loadUrl(String url) {
        browser.browserLoadUrl(url);
    }

    public void back() {

    }

    public void forward() {

    }
}
