package domain.jxbrowser;

import com.teamdev.jxbrowser.view.swing.BrowserView;

public class JxBrowserView {
    private JxBrowserView() {}

    public static BrowserView getView(JxBrowser jxBrowser) {
        return BrowserView.newInstance(jxBrowser.getBrowser());
    }
}
