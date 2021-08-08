package com.cyborck.math.mathSystem;

public class Multiplication extends Calculation {
    public Multiplication ( Value factor1, Value factor2 ) {
        super( factor1, factor2 );
    }

    @Override
    public double calculate () {
        return value1.getValue() * value2.getValue();
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( getClass() != o.getClass() ) return false;
        return ( ( Multiplication ) o ).value1.equals( value1 ) && ( ( Multiplication ) o ).value2.equals( value2 );
    }
}
