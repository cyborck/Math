package com.cyborck.math.gui.screens;

import com.cyborck.math.ColorScheme;
import com.cyborck.math.gui.prefabs.CheckBox;
import com.cyborck.math.gui.prefabs.CoordinateSystem;
import com.cyborck.math.mathSystem.CustomFunction;
import com.cyborck.math.mathSystem.Function;
import com.cyborck.math.workspace.Functions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CoordinateSystemScreen extends Screen {
    private final Functions functions;
    private final CoordinateSystem coordinateSystem;
    private final JPanel checkBoxPanel;

    private final List<Function> currentFunctions;
    private final List<Color> currentFunctionColors;

    public CoordinateSystemScreen ( Functions functions ) {
        this.functions = functions;

        setLayout( new FlowLayout( FlowLayout.CENTER ) );
        setBackground( ColorScheme.BACKGROUND );

        coordinateSystem = new CoordinateSystem( 800,800 );
        add( coordinateSystem );

        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout( new BoxLayout( checkBoxPanel, BoxLayout.Y_AXIS ) );
        add( checkBoxPanel );

        currentFunctions = new ArrayList<>();
        currentFunctionColors = new ArrayList<>();
    }

    @Override
    public void update () {
        List<Function> previousFunctions = new ArrayList<>( currentFunctions );
        List<Function> previousSelectedFunctions = coordinateSystem.getFunctions();
        List<Color> previousFunctionColors = new ArrayList<>( currentFunctionColors );

        currentFunctions.clear();
        currentFunctionColors.clear();
        checkBoxPanel.removeAll();

        List<CustomFunction> customFunctions = functions.getCustomFunctions();
        for ( int i = 0; i < customFunctions.size(); i++ ) {
            CustomFunction f = customFunctions.get( i );

            Color color = previousFunctions.contains( f ) ? previousFunctionColors.get( previousFunctions.indexOf( f ) ) :
                    coordinateSystem.generateRandomColor();

            CheckBox cb = new CheckBox( f.getName() );
            cb.addActionListener( new FunctionCheckBoxAction( coordinateSystem, f, color ) );
            if ( previousSelectedFunctions.contains( f ) )
                cb.setSelected( true );

            currentFunctions.add( f );
            currentFunctionColors.add( color );
            checkBoxPanel.add( cb );
        }

        checkBoxPanel.add( Box.createVerticalGlue() );

        revalidate();
        repaint();
    }

    private static class FunctionCheckBoxAction implements ActionListener {
        private final CoordinateSystem coordinateSystem;
        private final Function function;
        private final Color color;

        public FunctionCheckBoxAction ( CoordinateSystem coordinateSystem, Function function, Color color ) {
            this.coordinateSystem = coordinateSystem;
            this.function = function;
            this.color = color;
        }

        @Override
        public void actionPerformed ( ActionEvent e ) {
            if ( ( ( CheckBox ) e.getSource() ).isSelected() )
                coordinateSystem.addFunction( function, color );
            else
                coordinateSystem.removeFunction( function );
        }
    }
}
