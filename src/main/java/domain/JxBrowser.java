// WIKI: https://teamdev.cn/jxbrowser/zh/docs/guides/engine.html
// Configure with IntelliJ IDEA: https://teamdev.com/jxbrowser/docs/tutorials/ide/intellij-idea.html

package domain;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.browser.callback.CreatePopupCallback;
import com.teamdev.jxbrowser.browser.callback.OpenPopupCallback;
import com.teamdev.jxbrowser.browser.event.BrowserClosed;
import com.teamdev.jxbrowser.engine.*;
import com.teamdev.jxbrowser.engine.event.EngineCrashed;
import com.teamdev.jxbrowser.media.Audio;
import com.teamdev.jxbrowser.navigation.Navigation;
import com.teamdev.jxbrowser.navigation.NavigationEntry;
import com.teamdev.jxbrowser.net.proxy.*;
import com.teamdev.jxbrowser.password.PasswordRecord;
import com.teamdev.jxbrowser.password.PasswordStore;
import com.teamdev.jxbrowser.profile.Profiles;
import com.teamdev.jxbrowser.ui.Size;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import tools.ElementRegistry;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class JxBrowser {
    private static final String CacheDir = "data/onlyBrowser";

    // jxbrowser 7.3+ tial key, expire on 2024-09-03
    private String LICENSE;

    // jxbrowser 7.22 test key, do not use for commercial
    private String TestLicense;

    private Engine engine;

    private Profiles profiles;

    private Browser onlyBrowser;

    private ArrayList<Browser> browserList;

    private Navigation navigation;

    private Proxy proxy;

    private PasswordStore authStore;

    private BrowserView browserView;

    private Audio audio;

    public JxBrowser() {
        init();
    }

    private void init() {
        loadLicense();
        initEngine();
        initProfiles();
        initBrowser();
        initNavigation();
        initProxy();
        initAuthStore();
        initBrowserView();
        initAudio();
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
        onlyBrowser = engine.newBrowser();
        onlyBrowser.on(BrowserClosed.class, e -> {
            System.out.println("Current onlyBrowser is closed.");
        });
//        onlyBrowser.set(CreatePopupCallback.class, params -> CreatePopupCallback.Response.suppress());  // 禁用弹出窗口
        onlyBrowser.set(OpenPopupCallback.class, (params) -> {
            Browser popup = params.popupBrowser();
            // 新增标签页
            browserList.add(popup);
            return OpenPopupCallback.Response.proceed();
        });

        browserList = new ArrayList<>();
        browserList.add(onlyBrowser);
    }

    private void initNavigation() {
        assert onlyBrowser != null;
        navigation = onlyBrowser.navigation();
    }

    private void initProxy() {
        proxy = engine.proxy();  // 使用web默认代理
        proxy.config(SystemProxyConfig.newInstance());  // 使用系统默认代理
//        proxy.config(DirectProxyConfig.newInstance());  // 使用直连代理
//        proxy.config(AutoDetectProxyConfig.newInstance());  // 自动检测代理设置
    }

    private void initAuthStore() {
        authStore = profiles.defaultProfile().passwordStore();
    }

    private void initBrowserView() {
        assert onlyBrowser != null;
        browserView = BrowserView.newInstance(onlyBrowser);
    }

    private void initAudio() {
        audio = onlyBrowser.audio();
    }

    public void closeEngine() {
        if (engine == null || engine.isClosed()) return;
        engine.close();
    }

    public void resizeBrowser(Dimension dimension) {
        assert onlyBrowser != null && !onlyBrowser.isClosed();
        onlyBrowser.resize(dimension.width, dimension.height);
    }

    public void resizeBrowser(Size size) {
        assert onlyBrowser != null && !onlyBrowser.isClosed();
        onlyBrowser.resize(size);
    }

    public void resizeBrowser(int width, int height) {
        assert onlyBrowser != null && !onlyBrowser.isClosed();
        onlyBrowser.resize(width, height);
    }

    public void browserLoadUrl(String url) {
        if (url == null || url.isEmpty()) return;
        navigation.loadUrl(url);
    }

    public void browserLoadUrlAndWait(String url, int seconds) {
        if (url == null || url.isEmpty()) return;
        navigation.loadUrlAndWait(url, Duration.ofSeconds(seconds));
    }

    public void browserLoadHtml(String htmlPath) {
        if (htmlPath == null || htmlPath.isEmpty()) return;
        navigation.loadUrl(new File(htmlPath).getAbsolutePath());
    }

    public void browserReload() {
        navigation.reload();
    }

    public void browserReloadIgnoreCache() {
        navigation.reloadIgnoringCache();
    }

    public void browserStopLoad() {
        navigation.stop();
    }

    public void browserGoBack() {
        navigation.goBack();
    }

    public void browserGoForward() {
        navigation.goForward();
    }

    public HashMap<Integer, ElementRegistry<String>.Entry> browserHistories() {
        HashMap<Integer, ElementRegistry<String>.Entry> histories = new HashMap<>();
        for (int i = 0; i < navigation.entryCount(); i++) {
            NavigationEntry entry = navigation.entryAtIndex(i);
            histories.putIfAbsent(i, new ElementRegistry<String>().newEntry(entry.title(), entry.url())); // 历史记录()
        }
        return histories;
    }

    public void browserGoIndex(int index) {
        if (index >= 0 && index < navigation.entryCount()) {
            navigation.goToIndex(index);
        }
    }

    public void proxyCustomize(String proxyRules, String exceptions) {
        if (proxyRules == null || proxyRules.isEmpty()) {
            proxyRules = "http=127.0.0.1:7890;https=127.0.0.1:7890";
            exceptions = "<local>";  // 本地网页绕过代理服务器
        }
        proxy.config(CustomProxyConfig.newInstance(proxyRules, exceptions));
    }

    public List<PasswordRecord> authAllRecords() {
        return authStore.allSaved();
    }

    public void authRemoveBasedOnUrl(String url) {
        authStore.removeByUrl(url);
    }

    public BrowserView getView() {
        return browserView;
    }

    public void closeBrowser() {
        if (browserList.isEmpty()) return;
        browserList.forEach(b -> {
            if (!b.isClosed()) b.close();
        });
    }

    public boolean audioIsPlaying() {
        return audio.isPlaying();
    }

    public boolean audioIsMuted() {
        return audio.isMuted();
    }

    public void muteAudio() {
        if (audioIsMuted()) {
            return;
        }
        audio.mute();
    }

    public void unmuteAudio() {
        if (!audioIsMuted()) {
            return;
        }
        audio.unmute();
    }

    public ArrayList<Browser> browserList() {
        return browserList;
    }
}
