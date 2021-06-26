package com.cyborck.math.mathSystem;

public class CustomFunction extends Function {
    private Value function;
    private FunctionVariable x;

    public CustomFunction ( String name, String text, Value function ) {
        super( name, text );
        setFunction( function );
    }

    public void setFunction ( Value function ) {
        this.function = function;
        x = new FunctionVariable();

        function.setFunctionVariable( x );
    }

    @Override
    public double get ( double x ) {
        this.x.setValue( x );
        double y = function.getValue();
        this.x.setNull();
        return y;
    }
}
