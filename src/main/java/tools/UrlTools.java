package tools;

public class UrlTools {
    private UrlTools() {}

    public static boolean isUrl(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }
}
