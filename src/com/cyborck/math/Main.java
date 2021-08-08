package com.cyborck.math;

import com.cyborck.math.gui.Window;
import com.cyborck.math.parser.ParseException;
import com.cyborck.math.parser.Parser;
import com.cyborck.math.workspace.Workspace;

public class Main {

    public static void main ( String[] args ) {
        test();
    }

    private static void test () {
        try {
            Workspace workspace = new Workspace();
            Parser parser = new Parser( workspace );

            workspace.add( parser.parseNamedValue( "a=23" ) );

            Window window = new Window( workspace, parser );
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
    }
}
