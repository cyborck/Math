package com.cyborck.math.gui;

import com.cyborck.math.ColorScheme;
import com.cyborck.math.gui.prefabs.ScrollPane;
import com.cyborck.math.parser.Parser;
import com.cyborck.math.workspace.Workspace;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window ( Workspace workspace, Parser parser ) {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 1500, 1500 * 9 / 16 );
        setLocationRelativeTo( null );
        setExtendedState( MAXIMIZED_BOTH );

        JPanel content = new JPanel();
        content.setLayout( new BorderLayout() );
        add( content );

        JPanel northPanel = new JPanel( new FlowLayout() );
        northPanel.setBackground( ColorScheme.BACKGROUND_1 );
        content.add( northPanel, BorderLayout.NORTH );

        JPanel westPanel = new JPanel( new FlowLayout() );
        westPanel.setBackground( ColorScheme.BACKGROUND_1 );

        WorkspacePanel workspacePanel = new WorkspacePanel( workspace, parser );
        westPanel.add( workspacePanel );

        ScrollPane westPanelScrollPane = new ScrollPane( westPanel );
        content.add( westPanelScrollPane, BorderLayout.WEST );

        JPanel centerPanel = new JPanel( new FlowLayout() );
        centerPanel.setBackground( ColorScheme.BACKGROUND_1 );
        content.add( centerPanel, BorderLayout.CENTER );

        JPanel eastPanel = new JPanel( new FlowLayout() );
        eastPanel.setBackground( ColorScheme.BACKGROUND_1 );
        content.add( eastPanel, BorderLayout.EAST );

        JPanel southPanel = new JPanel( new FlowLayout() );
        southPanel.setBackground( ColorScheme.BACKGROUND_1 );
        content.add( southPanel, BorderLayout.SOUTH );

        setVisible( true );
    }
}
