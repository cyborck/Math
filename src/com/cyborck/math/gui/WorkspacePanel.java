package com.cyborck.math.gui;

import com.cyborck.math.ColorScheme;
import com.cyborck.math.mathSystem.NamedValue;
import com.cyborck.math.parser.ParseException;
import com.cyborck.math.parser.Parser;
import com.cyborck.math.workspace.Functions;
import com.cyborck.math.workspace.NamedValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class WorkspacePanel extends JPanel {

    public WorkspacePanel ( Functions functions, NamedValues namedValues, Parser parser ) {
        setLayout( new GridLayout( 0, 1 ) );
        setBackground( ColorScheme.BACKGROUND_1 );
        setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );

        JLabel namedValuesTitleLabel = new JLabel();
        namedValuesTitleLabel.setText( "Variablen" );
        namedValuesTitleLabel.setFont( ColorScheme.TITLE_FONT );
        namedValuesTitleLabel.setForeground( ColorScheme.FOREGROUND_2 );
        //Box namedValuesTitleLabelBox = Box.createHorizontalBox();
        //namedValuesTitleLabelBox.add( namedValuesTitleLabel );
        //namedValuesTitleLabelBox.add( Box.createHorizontalGlue() );
        add( namedValuesTitleLabel );

        for ( int i = 0; i < namedValues.getAllNamedValues().size(); i++ ) {
            NamedValue nv = namedValues.getAllNamedValues().get( i );
            JTextField namedValueLabel = new JTextField();
            namedValueLabel.setText( nv.getText() );
            namedValueLabel.setFont( ColorScheme.TEXT_FONT );
            namedValueLabel.setForeground( ColorScheme.FOREGROUND_1 );
            namedValueLabel.setBackground( ColorScheme.BACKGROUND_1 );
            namedValueLabel.setBorder( null );
            namedValueLabel.setMaximumSize( new Dimension( 10000, namedValueLabel.getFontMetrics( namedValueLabel.getFont() ).getHeight() ) );
            if ( !namedValues.getCustomNamedValues().contains( nv ) ) {
                namedValueLabel.setEditable( false );
                namedValueLabel.setFocusable( false );
            } else {
                int index = i;
                namedValueLabel.addFocusListener( new FocusListener() {
                    @Override
                    public void focusGained ( FocusEvent e ) {
                        namedValueLabel.setBorder( BorderFactory.createLineBorder( ColorScheme.FOREGROUND_1 ) );
                        namedValueLabel.setBackground( ColorScheme.BACKGROUND_2 );
                    }

                    @Override
                    public void focusLost ( FocusEvent fe ) {
                        namedValueLabel.setBorder( null );
                        namedValueLabel.setBackground( ColorScheme.BACKGROUND_1 );

                        try {
                            NamedValue newNV = parser.parseNamedValue( namedValueLabel.getText() );
                            namedValues.set( index, newNV );
                            namedValueLabel.setText( newNV.getText() );
                        } catch ( ParseException e ) {
                            namedValueLabel.setText( nv.getText() );
                            e.printStackTrace();
                        }
                    }
                } );
                namedValueLabel.addKeyListener( new KeyAdapter() {
                    @Override
                    public void keyPressed ( KeyEvent ke ) {
                        if ( ke.getKeyCode() == KeyEvent.VK_ENTER ) {
                            try {
                                NamedValue newNV = parser.parseNamedValue( namedValueLabel.getText() );
                                namedValues.set( index, newNV );
                                namedValueLabel.setText( newNV.getText() );
                            } catch ( ParseException e ) {
                                namedValueLabel.setText( nv.getText() );
                                e.printStackTrace();

                                //
                                // DAS SYSTEM IST SCHEISSE
                                //
                            }
                        }
                    }
                } );
            }
            add( namedValueLabel );
        }
    }
}
