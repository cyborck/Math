package com.cyborck.math.gui.navBar;

import com.cyborck.math.ColorScheme;
import com.cyborck.math.gui.ScreenPanel;
import com.cyborck.math.gui.screens.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NavigationBar extends JPanel {
    private final List<NavBarButton> buttonList;
    private final ScreenPanel screenPanel;

    private NavBarButton selectedButton;

    public NavigationBar ( ScreenPanel mcp ) {
        buttonList = new ArrayList<>();
        screenPanel = mcp;

        setBackground( ColorScheme.BACKGROUND );
        setLayout( new FlowLayout( FlowLayout.LEFT ) );
    }

    public void addScreen ( String text, Screen screen ) {
        NavBarButton b = new NavBarButton( text, this, screen );
        buttonList.add( b );
        add( b );

        if ( buttonList.size() == 1 )
            b.select();
    }

    public NavBarButton getSelectedButton () {
        return selectedButton;
    }

    void setSelectedButton ( NavBarButton b ) {
        if ( selectedButton != null )
            selectedButton.unselect();
        selectedButton = b;
        screenPanel.setScreen( b.getScreen() );
    }
}
