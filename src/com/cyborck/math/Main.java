package com.cyborck.math;

import com.cyborck.math.gui.FunctionPanel;
import com.cyborck.math.gui.Window;
import com.cyborck.math.mathSystem.Number;
import com.cyborck.math.mathSystem.*;
import com.cyborck.math.workspace.Functions;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main ( String[] args ) {
        test();
    }

    private static void test () {
        //function 1: x^2
        FunctionVariable x1 = new FunctionVariable();
        Calculation power = new Power( x1, new Number( 2 ) );
        CustomFunction f1 = new CustomFunction( "f", power, x1 );

        //function 2: 2*x+5
        FunctionVariable x2 = new FunctionVariable();
        Calculation multiplication = new Multiplication( new Number( 2 ), x2 );
        Calculation addition = new Addition( multiplication, new Number( 5 ) );
        CustomFunction f2 = new CustomFunction( "g", addition, x2 );

        //function 3: sin(x)
        Function f3 = Functions.getProvidedFunctions().get( 0 );

        FunctionPanel fp = new FunctionPanel( 1000, 1000 );
        fp.addFunction( f1 );
        fp.addFunction( f2 );
        fp.addFunction( f3 );

        JPanel panel = new JPanel( new FlowLayout() );
        panel.add( fp );

        Window window = new Window();
        window.add( panel );
    }
}
