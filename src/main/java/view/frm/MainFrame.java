package view.frm;

public class MainFrame extends BaseFrameImpl{
    public MainFrame() {
        init();
    }

    private void init() {
        // size characters
        this.width = 1800;
        this.height = 1060;
        this.widthPercent = 0.8F;
        this.heightPercent = 0.8F;
        resize();

        // other character
        setUndecorated(true);
        setVisible(true);
        setFrameFixed();

        // tray character
//        setTray();

        // fullscreen
//        fullscreen();
    }
}
