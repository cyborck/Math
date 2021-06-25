package com.cyborck.math.mathSystem;

public class Addition extends Calculation {
    public Addition ( Value addend1, Value addend2 ) {
        super( addend1, addend2 );
    }

    @Override
    public double calculate () {
        return value1.getValue() + value2.getValue();
    }
}
