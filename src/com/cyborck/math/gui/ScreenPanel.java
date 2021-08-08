package com.cyborck.math.gui;

import com.cyborck.math.ColorScheme;
import com.cyborck.math.gui.screens.Screen;

import javax.swing.*;
import java.awt.*;

public class ScreenPanel {
    private final JPanel panel;
    private Screen screen;

    public ScreenPanel () {
        panel = new JPanel();
        panel.setLayout( new FlowLayout( FlowLayout.CENTER ) );
        panel.setBackground( ColorScheme.BACKGROUND );
    }

    public JPanel getPanel () {
        return panel;
    }

    public Screen getScreen () {
        return screen;
    }

    public void setScreen ( Screen screen ) {
        this.screen = screen;
        screen.update();
        panel.removeAll();
        panel.add( screen );
    }
}
