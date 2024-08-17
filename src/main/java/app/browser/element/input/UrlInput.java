package app.browser.element.input;

import javax.swing.*;
import java.awt.*;

public class UrlInput extends JTextField {
    public static final String HTTPS = "https://";

    public UrlInput() {
        init();
    }

    private void init() {
        setFont(new Font("url", Font.BOLD | Font.ITALIC, 20));
        setOpaque(false);
        setBorder(null);
        setText(HTTPS);
    }
}
