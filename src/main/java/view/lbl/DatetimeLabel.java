package view.lbl;

import view.lbl.interfaces.BaseLabelImpl;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeLabel extends BaseLabelImpl {

    private String datetimeTxt = "0000-00-00 00:00:00";

    public DatetimeLabel() {
        init();
    }

    private void init() {
        setFont(new Font("datetime",3,25));
        setText(datetimeTxt);
        setTimer();
    }

    private void setTimer() {
        final JLabel varTime = this;
        Timer timeAction = new Timer(0, event -> {
            long timeMillis = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            varTime.setText(dateFormat.format(new Date(timeMillis)));
        });
        timeAction.start();
    }
}
