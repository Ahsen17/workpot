// WIKI: https://teamdev.cn/jxbrowser/zh/docs/guides/engine.html
// Configure with IntelliJ IDEA: https://teamdev.com/jxbrowser/docs/tutorials/ide/intellij-idea.html

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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Scanner;

public class JxBrowser {
    private static final String CacheDir = "data/browser";

    // jxbrowser 7.3+ tial key, expire on 2024-09-03
    private String LICENSE;

    // jxbrowser 7.22 test key, do not use for commercial
    private String TestLicense;

    private Engine engine;

    private Profiles profiles;

    private Browser browser;

    private BrowserView browserView;

    public JxBrowser() {
        init();
    }

    private void init() {
        loadLicense();
        initEngine();
        initProfiles();
        initBrowser();
        initBrowserView();
    }

    private void loadLicense() {
        try {
            File license = new File("etc/LICENSE");
            File licenseTrial = new File("etc/LICENSE_TRIAL");
            if (license.exists()) {
                Scanner sc = new Scanner(new FileReader(license));
                Scanner sc2 = new Scanner(new FileReader(licenseTrial));
                TestLicense = sc.nextLine();
                LICENSE = sc2.nextLine();
                sc.close();
                sc2.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initEngine() {
        engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.OFF_SCREEN)
                .language(Language.CHINESE)  // 设置为中文
                .userDataDir(Paths.get(CacheDir))  // 设置浏览器缓存路径
                .licenseKey(LICENSE)
                .licenseKey(TestLicense)
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

    public void resizeBrowser(int width, int height) {
        assert browser != null && !browser.isClosed();
        browser.resize(width, height);
    }

    public void browserLoadUrl(String url) {
        assert browser != null && !browser.isClosed();
        browser.navigation().loadUrl(url);
    }

    public BrowserView getView() {
        return browserView;
    }

    public void closeBrowser() {
        if (browser == null || browser.isClosed()) {
            return;
        }
        browser.close();
    }
}
