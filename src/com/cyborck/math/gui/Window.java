package com.cyborck.math.gui;

import com.cyborck.math.parser.Parser;
import com.cyborck.math.workspace.Functions;
import com.cyborck.math.workspace.NamedValues;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window ( Functions functions, NamedValues namedValues, Parser parser ) {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 1500, 1500 * 9 / 16 );
        setLocationRelativeTo( null );
        setExtendedState( MAXIMIZED_BOTH );

        JPanel content = new JPanel();
        content.setLayout( new BorderLayout() );
        add( content );

        WorkspacePanel workspacePanel = new WorkspacePanel( functions, namedValues, parser );
        content.add( workspacePanel, BorderLayout.WEST );

        setVisible( true );
    }
}
