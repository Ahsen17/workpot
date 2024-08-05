package domain.jxbrowser;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.view.swing.BrowserView;

public class BrowserViewMgr {
    private BrowserViewMgr() {}

    public static BrowserView getView(Browser browser) {
        return BrowserView.newInstance(browser);
    }
}
