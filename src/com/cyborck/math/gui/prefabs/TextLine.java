package com.cyborck.math.gui.prefabs;

import com.cyborck.math.ColorScheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextLine extends JTextField {

    public TextLine ( int width ) {
        super();
        setFont( ColorScheme.TEXT_FONT );
        setForeground( ColorScheme.FOREGROUND_1 );
        setBackground( ColorScheme.BACKGROUND );
        setCaretColor( ColorScheme.FOREGROUND_1 );
        setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( ColorScheme.FOREGROUND_1, 2 ),
                BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) ) );
        setPreferredSize( new Dimension( width, getPreferredSize().height ) );
    }

    public void setDefaultTextWhenNotFocussed ( String text ) {
        DefaultTextWhenNotFocussed d = new DefaultTextWhenNotFocussed( text );
        addFocusListener( d );
        if ( !isFocusOwner() ) {
            setForeground( d.defaultForeground );
            setText( text );
        }
    }

    private static class DefaultTextWhenNotFocussed implements FocusListener {
        private final String defaultText;
        private final Color defaultForeground;
        private final Color focusForeground;

        public DefaultTextWhenNotFocussed ( String defaultText ) {
            this.defaultText = defaultText;
            defaultForeground = ColorScheme.FOREGROUND_1;
            focusForeground = ColorScheme.FOREGROUND_1;
        }

        @Override
        public void focusGained ( FocusEvent e ) {
            //clear and set to normal color
            ( ( TextLine ) e.getSource() ).setText( "" );
            ( ( TextLine ) e.getSource() ).setForeground( focusForeground );
        }

        @Override
        public void focusLost ( FocusEvent e ) {
            //set to default text and default color
            ( ( TextLine ) e.getSource() ).setText( defaultText );
            ( ( TextLine ) e.getSource() ).setForeground( defaultForeground );
        }
    }
}
