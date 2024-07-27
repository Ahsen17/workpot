package view.btn;

import enums.FileCharacters;
import enums.ImageConstantPath;
import view.btn.interfaces.BaseButtonImpl;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CtlButton extends BaseButtonImpl {
    private final String name;

    public CtlButton(String name) {
        this.name = name;

        init();
    }

    private void init() {
        setSize(40, 30);
        setContentAreaFilled(false); // 设置按键透明
        setBorder(null);
        setIcon(new ImageIcon(ImageConstantPath.IconPath +
                "/" + name + "Light" + FileCharacters.ImageSuffix));

        setActionListener();
    }

    @Override
    protected void setActionListener() {
        String BtnName = name;
        String IconFolder = ImageConstantPath.IconPath;
        String IconSuffix = FileCharacters.ImageSuffix;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContentAreaFilled(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setContentAreaFilled(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon(IconFolder + "/" + BtnName + "Shadow" + IconSuffix));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon(IconFolder + "/" + BtnName + "Light" + IconSuffix));
            }
        });
    }
}
