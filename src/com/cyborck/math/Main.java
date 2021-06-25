package com.cyborck.math;

import com.cyborck.math.gui.FunctionPanel;
import com.cyborck.math.gui.Window;
import com.cyborck.math.mathSystem.Function;
import com.cyborck.math.mathSystem.NamedValue;
import com.cyborck.math.parser.ParseException;
import com.cyborck.math.parser.Parser;
import com.cyborck.math.workspace.Functions;
import com.cyborck.math.workspace.NamedValues;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main ( String[] args ) {
        test();
    }

    private static void test () {
        try {
            Functions functions = new Functions();
            NamedValues namedValues = new NamedValues();
            Parser parser = new Parser( functions, namedValues );

            Function f1 = parser.parseCustomFunction( "f(x) = x^2" );
            Function f2 = parser.parseCustomFunction( "g(x) = pi * x" );
            NamedValue nv = parser.parseNamedValue( "a = g(3) + f(3)" );
            Function f3 = parser.parseCustomFunction( "h(x) = x + a" );
            Function f4 = parser.parseCustomFunction( "k(x) = f(x) - h(x)" );

            FunctionPanel fp = new FunctionPanel( 1000, 1000 );
            fp.addFunction( f1 );
            fp.addFunction( f2 );
            fp.addFunction( f3 );
            fp.addFunction( f4 );

            JPanel panel = new JPanel( new FlowLayout() );
            panel.add( fp );

            Window window = new Window();
            window.add( panel );
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
    }
}
