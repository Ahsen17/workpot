package domain.jxbrowser;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.*;
import com.teamdev.jxbrowser.engine.event.EngineCrashed;
import com.teamdev.jxbrowser.net.proxy.CustomProxyConfig;
import com.teamdev.jxbrowser.net.proxy.Proxy;
import com.teamdev.jxbrowser.net.proxy.SystemProxyConfig;
import com.teamdev.jxbrowser.password.PasswordStore;
import com.teamdev.jxbrowser.profile.Profiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Scanner;

public class EngineMgr {
    private static final String CacheDir = "data/browser";

    // jxbrowser 7.3+ tial key, expire on 2024-09-03
    private String LICENSE;

    // jxbrowser <7.3 test key, do not use for commercial
    private String TestLicense;

    private Engine engine;

    private Profiles profiles;

    private PasswordStore authStore;

    private Proxy proxy;

    public EngineMgr() {
        init();
    }

    private void init() {
        loadLicense();
        initEngine();
        initProfiles();
        initAuthStore();
        initProxy();
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
                .chromiumDir(Paths.get(CacheDir + "/chromium"))  // 设置浏览器缓存路径
                .licenseKey(LICENSE)
                .licenseKey(TestLicense)
                .disableTouchMenu()  // 禁用windows触控
                .build()
        );

        engine.on(EngineCrashed.class, e -> {
            int exitCode = e.exitCode();
            System.out.println("JxBrowser crashed with exit code: " + exitCode);
        });
    }

    private void initProfiles() {
        profiles = engine.profiles();
    }

    private void initAuthStore() {
        authStore = profiles.defaultProfile().passwordStore();
    }

    private void initProxy() {
        proxy = engine.proxy();  // 使用web默认代理
        proxy.config(SystemProxyConfig.newInstance());  // 使用系统默认代理
//        proxy.config(DirectProxyConfig.newInstance());  // 使用直连代理
//        proxy.config(AutoDetectProxyConfig.newInstance());  // 自动检测代理设置
        proxyCustomize("", "");  // 开启默认用户代理
    }

    public void newEngine(RenderingMode mode, Language lang) {
        engine.close();
        engine = Engine.newInstance(EngineOptions.newBuilder(mode)
                .language(lang)  // 设置语言
                .userDataDir(Paths.get(CacheDir))  // 设置用户缓存
                .chromiumDir(Paths.get(CacheDir + "/chromium"))  // 设置浏览器缓存
                .licenseKey(LICENSE)
                .licenseKey(TestLicense)
                .disableTouchMenu()  // 禁用触控
                .build());
    }

    public Browser newBrowser() {
        return engine.newBrowser();
    }

    public void proxyCustomize(String proxyRules, String exceptions) {
        if (proxyRules == null || proxyRules.isEmpty()) {
            proxyRules = "http=127.0.0.1:7890;https=127.0.0.1:7890";
            exceptions = "<local>";  // 本地网页绕过代理服务器
        }
        proxy.config(CustomProxyConfig.newInstance(proxyRules, exceptions));
    }

    public void close() {
        // TODO: stackoverflow
        if (engine != null && !engine.isClosed()) close();
    }
}
