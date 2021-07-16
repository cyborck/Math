package com.cyborck.math.mathSystem;

public class CustomFunction extends Function {
    private Value function;
    private FunctionVariable functionVariable;

    public CustomFunction ( String name, String text, Value function ) {
        super( name, text );
        setFunction( function );
    }

    public void setFunction ( Value function ) {
        this.function = function;
        functionVariable = new FunctionVariable();

        function.setFunctionVariable( functionVariable );
    }

    @Override
    public double get ( double x ) {
        functionVariable.setValue( x );
        double y = function.getValue();
        functionVariable.setNull();
        return y;
    }

    @Override
    public boolean containsValue ( Value value ) {
        return function.equals( value ) || function.containsValue( value );
    }
}
