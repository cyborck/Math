package com.cyborck.math.mathSystem;

public class Subtraction extends Calculation{
    public Subtraction ( Value minuend, Value subtrahend ) {
        super( minuend, subtrahend );
    }

    @Override
    public double calculate () {
        return value1.getValue() - value2.getValue();
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( getClass() != o.getClass() ) return false;
        return ( ( Subtraction ) o ).value1.equals( value1 ) && ( ( Subtraction ) o ).value2.equals( value2 );
    }
}
