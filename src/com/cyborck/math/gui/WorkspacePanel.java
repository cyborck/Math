package com.cyborck.math.gui;

import com.cyborck.math.ColorScheme;
import com.cyborck.math.gui.prefabs.Button;
import com.cyborck.math.gui.prefabs.TextLine;
import com.cyborck.math.mathSystem.CustomFunction;
import com.cyborck.math.mathSystem.Function;
import com.cyborck.math.mathSystem.NamedValue;
import com.cyborck.math.parser.ParseException;
import com.cyborck.math.parser.Parser;
import com.cyborck.math.workspace.Functions;
import com.cyborck.math.workspace.NamedValues;
import com.cyborck.math.workspace.Workspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WorkspacePanel extends JPanel {
    private final Workspace workspace;

    private final JPanel namedValuesListPanel;
    private final JPanel functionsListPanel;

    public WorkspacePanel ( Workspace workspace, Parser parser ) {
        this.workspace = workspace;

        setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
        setBackground( ColorScheme.BACKGROUND_1 );
        setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );

        //namedValues
        JLabel namedValuesTitleLabel = new JLabel();
        namedValuesTitleLabel.setText( "Variablen" );
        namedValuesTitleLabel.setFont( ColorScheme.TITLE_FONT );
        namedValuesTitleLabel.setForeground( ColorScheme.FOREGROUND_2 );

        Box namedValuesTitleBox = Box.createHorizontalBox();
        namedValuesTitleBox.add( namedValuesTitleLabel );
        namedValuesTitleBox.add( Box.createHorizontalGlue() );
        add( namedValuesTitleBox );

        namedValuesListPanel = new JPanel();
        namedValuesListPanel.setLayout( new BoxLayout( namedValuesListPanel, BoxLayout.Y_AXIS ) );
        namedValuesListPanel.setBackground( ColorScheme.BACKGROUND_1 );
        add( namedValuesListPanel );

        //input line for new named value
        JPanel namedValueInputPanel = new JPanel();
        namedValueInputPanel.setLayout( new FlowLayout( FlowLayout.LEFT ) );
        namedValueInputPanel.setBackground( ColorScheme.BACKGROUND_1 );

        TextLine namedValueTextLine = new TextLine( 200 );
        namedValueTextLine.setDefaultTextWhenNotFocussed( "Variable hinzufügen" );
        namedValueTextLine.addKeyListener( new KeyAdapter() {
            @Override
            public void keyReleased ( KeyEvent e ) {
                if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
                    try {
                        workspace.add( parser.parseNamedValue( namedValueTextLine.getText() ) );
                        namedValueTextLine.setText( "" );
                        update();
                    } catch ( ParseException parseException ) {
                        parseException.printStackTrace();
                    }
                }
            }
        } );
        namedValueInputPanel.add( namedValueTextLine );

        Button namedValueInputButton = new Button();
        namedValueInputButton.setText( "+" );
        namedValueInputButton.addActionListener( e -> {
            try {
                workspace.add( parser.parseNamedValue( namedValueTextLine.getText() ) );
                update();
            } catch ( ParseException parseException ) {
                parseException.printStackTrace();
            }
        } );
        namedValueInputPanel.add( namedValueInputButton );

        add( namedValueInputPanel );

        //functions
        JLabel functionsTitleLabel = new JLabel();
        functionsTitleLabel.setText( "Funktionen" );
        functionsTitleLabel.setFont( ColorScheme.TITLE_FONT );
        functionsTitleLabel.setForeground( ColorScheme.FOREGROUND_2 );

        Box functionsTitleBox = Box.createHorizontalBox();
        functionsTitleBox.add( functionsTitleLabel );
        functionsTitleBox.add( Box.createHorizontalGlue() );
        add( functionsTitleBox );

        functionsListPanel = new JPanel();
        functionsListPanel.setLayout( new BoxLayout( functionsListPanel, BoxLayout.Y_AXIS ) );
        functionsListPanel.setBackground( ColorScheme.BACKGROUND_1 );
        add( functionsListPanel );

        //input line for new function
        JPanel functionInputPanel = new JPanel();
        functionInputPanel.setLayout( new FlowLayout( FlowLayout.LEFT ) );
        functionInputPanel.setBackground( ColorScheme.BACKGROUND_1 );

        TextLine functionTextLine = new TextLine( 200 );
        functionTextLine.setDefaultTextWhenNotFocussed( "Funktion hinzufügen" );
        functionTextLine.addKeyListener( new KeyAdapter() {
            @Override
            public void keyReleased ( KeyEvent e ) {
                if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
                    try {
                        workspace.add( parser.parseCustomFunction( functionTextLine.getText() ) );
                        functionTextLine.setText( "" );
                        update();
                    } catch ( ParseException parseException ) {
                        parseException.printStackTrace();
                    }
                }
            }
        } );
        functionInputPanel.add( functionTextLine );

        Button functionInputButton = new Button();
        functionInputButton.setText( "+" );
        functionInputButton.addActionListener( e -> {
            try {
                workspace.add( parser.parseCustomFunction( functionTextLine.getText() ) );
                update();
            } catch ( ParseException parseException ) {
                parseException.printStackTrace();
            }
        } );
        functionInputPanel.add( functionInputButton );

        add( functionInputPanel );

        update();
    }

    public void update () {
        //update namedValuesList
        namedValuesListPanel.removeAll();
        //add all named values
        for ( int i = 0; i < workspace.getNamedValues().getAllNamedValues().size(); i++ ) {
            NamedValue nv = workspace.getNamedValues().getAllNamedValues().get( i );

            JPanel namedValuePanel = new JPanel();
            namedValuePanel.setLayout( new FlowLayout( FlowLayout.LEFT ) );
            namedValuePanel.setBackground( ColorScheme.BACKGROUND_1 );

            //add the calculated value at the end
            String text = nv.getText();
            try {
                //if text after '=' can be parsed to a double, it must be a number, otherwise the calculated value should
                //be added to the text
                double test = Double.parseDouble( text.substring( text.indexOf( '=' ) + 1 ) );
            } catch ( NumberFormatException ignored ) {
                text += " = " + nv.getValue();
            }

            JLabel namedValueLabel = new JLabel();
            namedValueLabel.setText( text );
            namedValueLabel.setFont( ColorScheme.TEXT_FONT );
            namedValueLabel.setForeground( ColorScheme.FOREGROUND_1 );
            namedValueLabel.setBorder( null );
            namedValueLabel.setMaximumSize( new Dimension( 10000, namedValueLabel.getFontMetrics( namedValueLabel.getFont() ).
                    getHeight() ) );
            namedValuePanel.add( namedValueLabel );

            if ( workspace.getNamedValues().getCustomNamedValues().contains( nv ) ) {
                Button deleteNamedValueButton = new Button();
                deleteNamedValueButton.setText( "-" );
                deleteNamedValueButton.addActionListener( e -> {
                    workspace.remove( nv );
                    update();
                } );

                namedValuePanel.add( deleteNamedValueButton );
            }

            namedValuesListPanel.add( namedValuePanel );
        }

        //update functionsList
        functionsListPanel.removeAll();
        //add all named values
        for ( int i = 0; i < workspace.getFunctions().getAllFunctions().size(); i++ ) {
            Function f = workspace.getFunctions().getAllFunctions().get( i );

            JPanel functionPanel = new JPanel();
            functionPanel.setLayout( new FlowLayout( FlowLayout.LEFT ) );
            functionPanel.setBackground( ColorScheme.BACKGROUND_1 );

            JLabel functionLabel = new JLabel();
            functionLabel.setText( f.getText() );
            functionLabel.setFont( ColorScheme.TEXT_FONT );
            functionLabel.setForeground( ColorScheme.FOREGROUND_1 );
            functionLabel.setBorder( null );
            functionLabel.setMaximumSize( new Dimension( 10000, functionLabel.getFontMetrics( functionLabel.getFont() ).
                    getHeight() ) );
            functionPanel.add( functionLabel );

            if ( f instanceof CustomFunction ) {
                Button deleteFunctionButton = new Button();
                deleteFunctionButton.setText( "-" );
                deleteFunctionButton.addActionListener( e -> {
                    workspace.remove( ( CustomFunction ) f );
                    update();
                } );

                functionPanel.add( deleteFunctionButton );
            }

            functionsListPanel.add( functionPanel );
        }

        revalidate();
        repaint();
    }
}
