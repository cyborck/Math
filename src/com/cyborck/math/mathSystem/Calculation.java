package com.cyborck.math.mathSystem;

public abstract class Calculation implements Value {
    protected final Value value1;
    protected final Value value2;

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
    public boolean contains ( FunctionVariable functionVariable ) {
        return value1.contains( functionVariable ) || value2.contains( functionVariable );
    }
}
