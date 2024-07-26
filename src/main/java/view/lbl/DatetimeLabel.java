package view.lbl;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeLabel extends BaseLabelImpl{
    public DatetimeLabel() {
        init();
    }

    private void init() {
        setFont(new Font("datetime",3,25));
        setTimer();
    }

    private void setTimer() {
        final JLabel varTime = this;
        Timer timeAction = new Timer(1000, event -> {
            long timeMillis = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            varTime.setText(dateFormat.format(new Date(timeMillis)));
        });
        timeAction.start();
    }
}
