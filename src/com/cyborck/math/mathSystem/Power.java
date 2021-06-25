package com.cyborck.math.mathSystem;

public class Power extends Calculation {
    public Power ( Value baseNumber, Value exponent ) {
        super( baseNumber, exponent );
    }

    @Override
    public double calculate () {
        return Math.pow( value1.getValue(), value2.getValue() );
    }
}
