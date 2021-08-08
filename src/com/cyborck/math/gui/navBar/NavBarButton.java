package com.cyborck.math.gui.navBar;

import com.cyborck.math.ColorScheme;
import com.cyborck.math.gui.screens.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavBarButton extends JLabel {
    private static final Font FONT = ColorScheme.TITLE_FONT;
    private static final Color FOREGROUND = ColorScheme.FOREGROUND_1;
    private static final Color BACKGROUND = ColorScheme.BACKGROUND;

    private static final Color NORMAL_BORDER_COLOR = ColorScheme.BACKGROUND;
    private static final Color HOVER_BORDER_COLOR = ColorScheme.FOREGROUND_2;
    private static final Color CLICK_BORDER_COLOR = ColorScheme.FOREGROUND_1;
    private static final Color SELECTED_BORDER_COLOR = ColorScheme.FOREGROUND_1;

    private final NavigationBar navBar;
    private final Screen screen;

    private boolean hover;
    private boolean click;
    private boolean selected;

    public NavBarButton ( String text, NavigationBar navBar, Screen screen ) {
        hover = false;
        click = false;
        selected = false;

        setText( text );
        setFont( FONT );
        setForeground( FOREGROUND );
        setBackground( BACKGROUND );
        setOpaque( true );
        updateBorder();

        addMouseListener( new SetScreenAction() );
        addMouseListener( new ChangeBorderOnHover() );
        addMouseListener( new ChangeBorderOnClick() );

        this.navBar = navBar;
        this.screen = screen;
    }

    void select () {
        if ( !selected ) {
            navBar.setSelectedButton( this );
            selected = true;
            updateBorder();
        }
    }

    void unselect () {
        if ( selected ) {
            selected = false;
            updateBorder();
        }
    }

    private void updateBorder () {
        if ( click )
            setBorderColor( CLICK_BORDER_COLOR );
        else if ( hover )
            setBorderColor( HOVER_BORDER_COLOR );
        else if ( selected )
            setBorderColor( SELECTED_BORDER_COLOR );
        else
            setBorderColor( NORMAL_BORDER_COLOR );
    }

    private void setBorderColor ( Color color ) {
        setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( color, 2 ),
                BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) ) );
    }

    public Screen getScreen () {
        return screen;
    }

    private class SetScreenAction extends MouseAdapter {
        @Override
        public void mousePressed ( MouseEvent e ) {
            select();
        }
    }

    private class ChangeBorderOnHover extends MouseAdapter {
        @Override
        public void mouseEntered ( MouseEvent e ) {
            hover = true;
            updateBorder();
        }

        @Override
        public void mouseExited ( MouseEvent e ) {
            hover = false;
            updateBorder();
        }
    }

    private class ChangeBorderOnClick extends MouseAdapter {
        @Override
        public void mousePressed ( MouseEvent e ) {
            click = true;
            updateBorder();
        }

        @Override
        public void mouseReleased ( MouseEvent e ) {
            click = false;
            updateBorder();
        }
    }
}
