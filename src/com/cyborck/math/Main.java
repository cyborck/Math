package com.cyborck.math;

import com.cyborck.math.gui.Window;
import com.cyborck.math.parser.ParseException;
import com.cyborck.math.parser.Parser;
import com.cyborck.math.workspace.Functions;
import com.cyborck.math.workspace.NamedValues;

public class Main {

    public static void main ( String[] args ) {
        test();
    }

    private static void test () {
        try {
            Functions functions = new Functions();
            NamedValues namedValues = new NamedValues();
            Parser parser = new Parser( functions, namedValues );

            namedValues.add( parser.parseNamedValue( "a=23" ) );

            Window window = new Window( functions, namedValues, parser );
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
    }
}
