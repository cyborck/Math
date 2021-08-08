package com.cyborck.math.workspace;

import com.cyborck.math.mathSystem.CustomFunction;
import com.cyborck.math.mathSystem.FunctionReference;
import com.cyborck.math.mathSystem.NamedValue;
import com.cyborck.math.mathSystem.Value;

public class Workspace {
    private final Functions functions;
    private final NamedValues namedValues;

    public Workspace () {
        functions = new Functions( this );
        namedValues = new NamedValues( this );
    }

    public void add ( NamedValue namedValue ) {
        namedValues.add( namedValue );
    }

    public void add ( CustomFunction customFunction ) {
        functions.add( customFunction );
    }

    public void remove ( NamedValue namedValue ) {
        namedValues.remove( namedValue );
        removeContaining( namedValue );
    }

    public void remove ( CustomFunction customFunction ) {
        functions.remove( customFunction );
        FunctionReference fr = new FunctionReference( customFunction );
        removeContaining( fr );
    }

    public void removeContaining ( Value value ) {
        namedValues.removeNamedValuesThatContain( value );
        functions.removeFunctionsThatContain( value );
    }

    public NamedValues getNamedValues () {
        return namedValues;
    }

    public Functions getFunctions () {
        return functions;
    }
}
