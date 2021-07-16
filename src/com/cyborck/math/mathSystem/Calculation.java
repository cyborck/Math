package com.cyborck.math.mathSystem;

public abstract class Calculation implements Value {
    protected Value value1;
    protected Value value2;

    protected Calculation ( Value v1, Value v2 ) {
        value1 = v1;
        value2 = v2;
    }

    public abstract double calculate ();

    @Override
    public double getValue () {
        return calculate();
    }

    @Override
    public void setFunctionVariable ( FunctionVariable functionVariable ) {
        if ( value1 instanceof FunctionVariable ) value1 = functionVariable;
        else value1.setFunctionVariable( functionVariable );
        if ( value2 instanceof FunctionVariable ) value2 = functionVariable;
        else value2.setFunctionVariable( functionVariable );
    }

    @Override
    public boolean containsValue ( Value value ) {
        return equals( value ) || value1.containsValue( value ) || value2.containsValue( value );
    }
}
