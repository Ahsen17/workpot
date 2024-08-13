package app.browser;

import javax.swing.*;
import java.awt.*;

public class BrowserUrlInput extends JTextField {
    public static final String HTTPS = "https://";

    public BrowserUrlInput() {
        init();
    }

    private void init() {
        setFont(new Font("url", Font.BOLD | Font.ITALIC, 20));
        setOpaque(false);
        setBorder(null);
        setText(HTTPS);
    }
}
