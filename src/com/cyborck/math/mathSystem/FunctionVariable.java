package com.cyborck.math.mathSystem;

public class FunctionVariable implements Value {
    private double value;
    private boolean valueAssigned = false;

    @Override
    public double getValue () {
        if ( !valueAssigned ) throw new ArithmeticException( "no value assigned!" );
        else return value;
    }

    @Override
    public void setFunctionVariable ( FunctionVariable functionVariable ) {
        //do nothing
    }

    public void setValue ( double value ) {
        this.value = value;
        valueAssigned = true;
    }

    public void setNull () {
        valueAssigned = false;
    }
}
