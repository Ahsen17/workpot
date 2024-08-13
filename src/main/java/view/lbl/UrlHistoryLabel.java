package view.lbl;

import controller.Controller;
import enums.AppEnum;
import view.lbl.interfaces.BaseLabelImpl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UrlHistoryLabel extends BaseLabelImpl {
    private String urlText = "";

    public UrlHistoryLabel() {
        init();
    }

    private void init() {
        setFont(new Font("datetime",3,25));
        setText(urlText);
    }

//    public void setUrlHistory(String urlText) {
//        if (urlText == null || urlText.isEmpty()) return;
//        this.urlText = urlText;
//        setText(urlText);
//        updateUI();
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                Controller.NewJxBrowser().loadUrlAndWait(urlText);
//                ((BrowserAppTmp) Controller.APP_LAYOUTS.map().get(AppEnum.Browser)).updateView();
//            }
//        });
//    }
}
