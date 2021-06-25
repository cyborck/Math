package com.cyborck.math.gui;

import javax.swing.*;

public class Window extends JFrame {

    public Window () {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setResizable( false );
        setSize( 1500, 1500 * 9 / 16 );
        setLocationRelativeTo( null );
        setExtendedState( MAXIMIZED_BOTH );
        setVisible( true );
    }
}
