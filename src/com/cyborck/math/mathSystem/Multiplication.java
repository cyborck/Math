package com.cyborck.math.mathSystem;

public class Multiplication extends Calculation{
    public Multiplication ( Value factor1, Value factor2 ) {
        super( factor1, factor2 );
    }

    @Override
    public double calculate () {
        return value1.getValue() * value2.getValue();
    }
}
