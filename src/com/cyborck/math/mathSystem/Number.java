package com.cyborck.math.mathSystem;

public class Number implements Value {
    private double value;

    public Number ( double value ) {
        this.value = value;
    }

    @Override
    public double getValue () {
        return value;
    }

    public void setValue ( double value ) {
        this.value = value;
    }

    @Override
    public boolean contains ( FunctionVariable functionVariable ) {
        return false;
    }
}
