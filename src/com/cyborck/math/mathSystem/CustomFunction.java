package com.cyborck.math.mathSystem;

public class CustomFunction extends Function {
    private Value function;
    private FunctionVariable x;

    public CustomFunction ( String name, Value function, FunctionVariable x ) {
        super( name );
        setFunction( function, x );
    }

    public void setFunction ( Value function, FunctionVariable x ) {
        this.function = function;
        this.x = x;

        if ( !function.contains( x ) )
            throw new IllegalArgumentException( "the function must contain the variable x!" );
    }

    @Override
    public double get ( double x ) {
        this.x.setValue( x );
        double y = function.getValue();
        this.x.setNull();
        return y;
    }
}
