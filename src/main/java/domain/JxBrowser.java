// WIKI: https://teamdev.cn/jxbrowser/zh/docs/guides/engine.html

package domain;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.browser.event.BrowserClosed;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.Language;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.engine.event.EngineCrashed;
import com.teamdev.jxbrowser.profile.Profiles;
import com.teamdev.jxbrowser.ui.Size;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import java.awt.*;
import java.nio.file.Paths;

public class JxBrowser {
    private static final String CacheDir = "data/browser";

    private Engine engine;

    private Profiles profiles;

    private Browser browser;

    private BrowserView browserView;

    public JxBrowser() {
        init();
    }

    private void init() {
        initEngine();
        initProfiles();
        initBrowser();
        initBrowserView();
    }

    private void initEngine() {
        engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.OFF_SCREEN)
                .language(Language.CHINESE)  // 设置为中文
                .userDataDir(Paths.get(CacheDir))  // 设置浏览器缓存路径
                .chromiumDir(Paths.get(CacheDir + "/chromium"))  // 设置浏览器缓存路径
                .disableTouchMenu()  // 禁用windows触控
                .build()
        );

        engine.on(EngineCrashed.class, e -> {
            int exitCode = e.exitCode();
            System.out.println("JxBrowser crashed with exit code: " + exitCode);
        });
    }

    private void initProfiles() {
        // 暂时不需要，用默认的即可
        profiles = engine.profiles();
    }

    private void initBrowser() {
        assert engine != null;
        browser = engine.newBrowser();
        browser.on(BrowserClosed.class, e -> {
            System.out.println("Current browser is closed.");
        });
    }

    private void initBrowserView() {
        assert browser != null;
        browserView = BrowserView.newInstance(browser);
    }

    public void closeEngine() {
        if (engine == null || engine.isClosed()) {
            return;
        }
        engine.close();
    }

    public void resizeBrowser(Dimension dimension) {
        assert browser != null && !browser.isClosed();
        browser.resize(dimension.width, dimension.height);
    }

    public void resizeBrowser(Size size) {
        assert browser != null && !browser.isClosed();
        browser.resize(size);
    }

    public void closeBrowser() {
        if (browser == null || browser.isClosed()) {
            return;
        }
        browser.close();
    }
}
