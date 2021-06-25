package com.cyborck.math.mathSystem;

public class Division extends Calculation{
    public Division ( Value v1, Value v2 ) {
        super( v1, v2 );
    }

    @Override
    public double calculate () {
        return value1.getValue() / value2.getValue();
    }
}
