package com.cyborck.math.gui.prefabs;

import com.cyborck.math.ColorScheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {
    private ChangeTextHoverListener changeTextOnHoverMouseListener;
    private ChangeBackgroundOnClick changeBackgroundOnClickMouseListener;

    public Button () {
        super();

        setFocusPainted( false );
        setFont( ColorScheme.TEXT_FONT );
        setForeground( ColorScheme.FOREGROUND_1 );
        setBackground( ColorScheme.BACKGROUND );
        setBorder( BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder( ColorScheme.FOREGROUND_1, 2 ),
                BorderFactory.createEmptyBorder( 5, 5, 5, 5 )
        ) );
        setModel( new NoClickEffectModel() );
        changeBackgroundOnClick( ColorScheme.BACKGROUND, ColorScheme.BACKGROUND );
    }

    public void changeTextOnHover ( String normalText, String hoverText ) {
        if ( changeTextOnHoverMouseListener != null )
            removeMouseListener( changeTextOnHoverMouseListener );
        changeTextOnHoverMouseListener = new ChangeTextHoverListener( normalText, hoverText );
        addMouseListener( changeTextOnHoverMouseListener );
    }

    public void changeBackgroundOnClick ( Color normalColor, Color clickColor ) {
        if ( changeBackgroundOnClickMouseListener != null )
            removeMouseListener( changeBackgroundOnClickMouseListener );
        changeBackgroundOnClickMouseListener = new ChangeBackgroundOnClick( normalColor, clickColor );
        addMouseListener( changeBackgroundOnClickMouseListener );
    }

    private static class ChangeTextHoverListener extends MouseAdapter {
        private final String normalText;
        private final String hoverText;

        public ChangeTextHoverListener ( String normalText, String hoverText ) {
            this.normalText = normalText;
            this.hoverText = hoverText;
        }

        @Override
        public void mouseEntered ( MouseEvent e ) {
            ( ( JButton ) e.getSource() ).setText( hoverText );
        }

        @Override
        public void mouseExited ( MouseEvent e ) {
            ( ( JButton ) e.getSource() ).setText( normalText );
        }
    }

    private static class ChangeBackgroundOnClick extends MouseAdapter {
        private final Color normalColor;
        private final Color clickColor;

        private ChangeBackgroundOnClick ( Color normalColor, Color clickColor ) {
            this.normalColor = normalColor;
            this.clickColor = clickColor;
        }

        @Override
        public void mousePressed ( MouseEvent e ) {
            ( ( JButton ) e.getSource() ).setBackground( clickColor );
        }

        @Override
        public void mouseReleased ( MouseEvent e ) {
            ( ( JButton ) e.getSource() ).setBackground( normalColor );
        }
    }

    private static class NoClickEffectModel extends DefaultButtonModel {
        @Override
        public boolean isPressed () {
            return false;
        }
    }
}
