package domain.jxbrowser;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.browser.callback.CreatePopupCallback;
import com.teamdev.jxbrowser.browser.internal.rpc.BrowserClosed;
import com.teamdev.jxbrowser.media.Audio;
import com.teamdev.jxbrowser.navigation.Navigation;
import com.teamdev.jxbrowser.navigation.NavigationEntry;
import com.teamdev.jxbrowser.ui.Size;
import tools.ElementRegistry;
import tools.UrlTools;

import java.awt.*;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class JxBrowser {
    private final JxEngine jxEngine;

    private Browser browser;

    private ArrayList<Browser> browserList;

    private Navigation navigation;

    private Audio audio;

    public JxBrowser(JxEngine mgr) {
        jxEngine = mgr;
        init();
    }

    private void init() {
        initBrowser();
        initNavigation();
        initAudio();
    }

    public static JxBrowser newInstance(JxEngine jxEngine){
        JxBrowser jxBrowser = new JxBrowser(jxEngine);
        jxBrowser.init();
        return jxBrowser;
    }

    public static JxBrowser newInstance(JxEngine jxEngine, Browser popup){
        JxBrowser mgr = new JxBrowser(jxEngine);
        mgr.browser = popup;
        mgr.init();
        return mgr;
    }

    private void initBrowser() {
        if (browser == null) browser = jxEngine.newBrowser();
        browserList = new ArrayList<>();
        browserList.add(browser);

        browser.on(BrowserClosed.class, e-> System.out.printf("Browser[%s] is closed.\n", browser.hashCode()));
        //        browser.set(CreatePopupCallback.class, params -> CreatePopupCallback.Response.suppress());  // 禁用弹出窗口
//        browser.set(CreatePopupCallback.class, (params) -> CreatePopupCallback.Response.create());  // 默认弹出窗口
        browser.set(CreatePopupCallback.class, (params) -> {
            // WIKI: https://cloud.tencent.com/developer/ask/sof/737483
            // 抑制弹出窗并在当前标签页跳转
            System.out.printf("Load URL: %s\n", params.targetUrl());
            loadUrl(params.targetUrl());
            return CreatePopupCallback.Response.suppress();
        });
//        browser.set(OpenPopupCallback.class, (params) -> {
//            Browser popup = params.popupBrowser();
//            return OpenPopupCallback.Response.proceed();
//        });
    }

    private void initNavigation() {
        navigation = browser.navigation();
    }

    private void initAudio() {
        audio = browser.audio();
    }

    protected Browser getBrowser() {
        return browser;
    }

    public void resize(Dimension dimension) {
        browser.resize(dimension.width, dimension.height);
    }

    public void resize(Size size) {
        browser.resize(size);
    }

    public void resize(Point point) {
        browser.resize(point.x, point.y);
    }

    public void resize(int width, int height) {
        browser.resize(width, height);
    }

    public void loadUrl(String url) {
        if (url != null && !url.isEmpty() && UrlTools.isUrl(url)) navigation.loadUrl(url);
    }

    public void loadUrlAndWait(String url) {
        if (url != null && !url.isEmpty() && UrlTools.isUrl(url)) navigation.loadUrlAndWait(url);
    }

    public void loadUrlAndWait(String url, int seconds) {
        if (url != null && !url.isEmpty() && UrlTools.isUrl(url)) navigation.loadUrlAndWait(url, Duration.ofSeconds(seconds));
    }

    public void loadHtml(String html) {
        if (html != null && !html.isEmpty()) navigation.loadUrl(new File(html).getAbsolutePath());
    }

    public void reload() {
        navigation.reload();
    }

    public void reloadIgnoringCache() {
        navigation.reloadIgnoringCache();
    }

    public void stop() {
        navigation.stop();
    }

    public void goBack() {
        navigation.goBack();
    }

    public void goForward() {
        navigation.goForward();
    }

    public HashMap<Integer, ElementRegistry<String>.Entry> histories() {
        HashMap<Integer, ElementRegistry<String>.Entry> histories = new HashMap<>();
        for (int i = 0; i < navigation.entryCount(); i++) {
            NavigationEntry entry = navigation.entryAtIndex(i);
            histories.putIfAbsent(i, new ElementRegistry<String>().newEntry(entry.title(), entry.url())); // 历史记录()
        }
        return histories;
    }

    public void goIndex(int index) {
        if (index >= 0 && index < navigation.entryCount()) navigation.goToIndex(index);
    }

    public void close() {
        if (browser != null && !browser.isClosed()) browser.close();
    }

    public boolean isAudioPlaying() {
        return audio.isPlaying();
    }

    public void muteOrUnmuteAudio() {
        if (isAudioPlaying()) audio.mute();
        else audio.unmute();
    }

    public ArrayList<Browser> browserList() {
        return browserList;
    }
}
