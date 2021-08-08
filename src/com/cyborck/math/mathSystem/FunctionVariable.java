package com.cyborck.math.mathSystem;

public class FunctionVariable implements Value {
    private double value;
    private boolean valueAssigned = false;

    @Override
    public double getValue () {
        if ( !valueAssigned ) throw new ArithmeticException( "no value assigned!" );
        else return value;
    }

    public void setValue ( double value ) {
        this.value = value;
        valueAssigned = true;
    }

    @Override
    public void setFunctionVariable ( FunctionVariable functionVariable ) {
        //do nothing
    }

    public void setNull () {
        valueAssigned = false;
    }

    @Override
    public boolean containsValue ( Value value ) {
        return getClass() == value.getClass();
    }

    @Override
    public boolean equals ( Object o ) {
        return o instanceof FunctionVariable;
    }
}
