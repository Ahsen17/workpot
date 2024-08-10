package controller.mgrs;

import view.lbl.UrlHistoryLabel;
import view.lbl.interfaces.BaseLabelImpl;

public class LabelMgr {
    private LabelMgr() {}

    public static BaseLabelImpl[] initBrowserAppLabels() {
        UrlHistoryLabel[] urlHistoryLabels = new UrlHistoryLabel[5]; {
            for (int i = 0; i < urlHistoryLabels.length; i++) {
                urlHistoryLabels[i] = new UrlHistoryLabel();
                urlHistoryLabels[i].setText("Null");
                // TODO: 历史浏览记录更新
            }
        }

        return urlHistoryLabels;
    }
}
