package com.cyborck.math.gui.prefabs;

import com.cyborck.math.ColorScheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CheckBox extends JPanel {
    private final CheckBoxButton button;

    public CheckBox ( String text ) {
        setLayout( new FlowLayout( FlowLayout.LEFT ) );
        setBackground( ColorScheme.BACKGROUND );

        button = new CheckBoxButton();
        add( button );

        JLabel label = new JLabel();
        label.setText( text );
        label.setForeground( ColorScheme.FOREGROUND_1 );
        label.setBackground( ColorScheme.BACKGROUND );
        label.setFont( ColorScheme.TEXT_FONT );
        add( label );
    }

    public void addActionListener ( ActionListener al ) {
        button.addActionListener( al );
    }

    public void removeActionListener ( ActionListener al ) {
        button.removeActionListener( al );
    }

    public boolean isSelected () {
        return button.selected;
    }

    public void setSelected ( boolean selected ) {
        button.selected = selected;
        button.updateBackground();
    }

    private class CheckBoxButton extends JLabel {
        private static final Color NORMAL_BACKGROUND = ColorScheme.BACKGROUND;
        private static final Color SELECTED_BACKGROUND = ColorScheme.FOREGROUND_1;
        private static final Color CLICK_BACKGROUND = ColorScheme.FOREGROUND_1;
        private static final Color HOVER_BACKGROUND = ColorScheme.FOREGROUND_2;

        private final List<ActionListener> actionListeners;

        private boolean selected;
        private boolean click;
        private boolean hover;

        private CheckBoxButton () {
            actionListeners = new ArrayList<>();
            actionListeners.add( e -> {
                selected = !selected;
                updateBackground();
            } );

            selected = false;
            click = false;
            hover = false;

            setPreferredSize( new Dimension( 20, 20 ) );
            setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( ColorScheme.FOREGROUND_1, 2 ),
                    BorderFactory.createLineBorder( ColorScheme.BACKGROUND, 2 ) ) );
            setOpaque( true );
            addMouseListener( new ML() );

            updateBackground();
        }

        private void addActionListener ( ActionListener al ) {
            actionListeners.add( al );
        }

        private void removeActionListener ( ActionListener al ) {
            actionListeners.remove( al );
        }

        private void performActions () {
            for ( ActionListener al: actionListeners )
                al.actionPerformed( new ActionEvent( CheckBox.this, ActionEvent.ACTION_PERFORMED, null ) );
        }

        private void updateBackground () {
            if ( click ) setBackground( CLICK_BACKGROUND );
            else if ( hover ) setBackground( HOVER_BACKGROUND );
            else if ( selected ) setBackground( SELECTED_BACKGROUND );
            else setBackground( NORMAL_BACKGROUND );
        }

        private class ML extends MouseAdapter {

            @Override
            public void mousePressed ( MouseEvent e ) {
                click = true;
                updateBackground();
                performActions();
            }

            @Override
            public void mouseReleased ( MouseEvent e ) {
                click = false;
                updateBackground();
            }

            @Override
            public void mouseEntered ( MouseEvent e ) {
                hover = true;
                updateBackground();
            }

            @Override
            public void mouseExited ( MouseEvent e ) {
                hover = false;
                updateBackground();
            }
        }
    }
}
