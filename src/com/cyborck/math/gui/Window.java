package com.cyborck.math.gui;

import com.cyborck.math.ColorScheme;
import com.cyborck.math.gui.navBar.NavigationBar;
import com.cyborck.math.gui.prefabs.ScrollPane;
import com.cyborck.math.gui.screens.CoordinateSystemScreen;
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

        //main content
        ScreenPanel sp = new ScreenPanel();
        content.add( sp.getPanel(), BorderLayout.CENTER );

        //screens
        CoordinateSystemScreen css = new CoordinateSystemScreen( workspace.getFunctions() );

        //navigation bar
        NavigationBar navBar = new NavigationBar( sp );
        navBar.addScreen( "Koordinatensystem", css );
        content.add( navBar, BorderLayout.NORTH );

        //workspace
        JPanel westPanel = new JPanel( new FlowLayout() );
        westPanel.setBackground( ColorScheme.BACKGROUND );
        WorkspacePanel workspacePanel = new WorkspacePanel( workspace, sp, parser );
        westPanel.add( workspacePanel );
        ScrollPane westPanelScrollPane = new ScrollPane( westPanel );
        content.add( westPanelScrollPane, BorderLayout.WEST );

        setVisible( true );
    }
}
