package com.cyborck.math.mathSystem;

public class Subtraction extends Calculation{
    public Subtraction ( Value minuend, Value subtrahend ) {
        super( minuend, subtrahend );
    }

    @Override
    public double calculate () {
        return value1.getValue() - value2.getValue();
    }
}
