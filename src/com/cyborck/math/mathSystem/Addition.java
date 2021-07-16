package com.cyborck.math.mathSystem;

public class Addition extends Calculation {
    public Addition ( Value addend1, Value addend2 ) {
        super( addend1, addend2 );
    }

    @Override
    public double calculate () {
        return value1.getValue() + value2.getValue();
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( getClass() != o.getClass() ) return false;
        return ( ( Addition ) o ).value1.equals( value1 ) && ( ( Addition ) o ).value2.equals( value2 );
    }
}
