package com.cyborck.math.mathSystem;

public class Division extends Calculation {
    public Division ( Value v1, Value v2 ) {
        super( v1, v2 );
    }

    @Override
    public double calculate () {
        return value1.getValue() / value2.getValue();
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( getClass() != o.getClass() ) return false;
        return ( ( Division ) o ).value1.equals( value1 ) && ( ( Division ) o ).value2.equals( value2 );
    }
}
