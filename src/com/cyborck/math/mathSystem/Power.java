package com.cyborck.math.mathSystem;

public class Power extends Calculation {
    public Power ( Value baseNumber, Value exponent ) {
        super( baseNumber, exponent );
    }

    @Override
    public double calculate () {
        return Math.pow( value1.getValue(), value2.getValue() );
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( getClass() != o.getClass() ) return false;
        return ( ( Power ) o ).value1.equals( value1 ) && ( ( Power ) o ).value2.equals( value2 );
    }
}
