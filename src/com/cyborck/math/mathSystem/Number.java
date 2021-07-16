package com.cyborck.math.mathSystem;

import java.util.Objects;

public class Number implements Value {
    private double value;

    public Number ( double value ) {
        this.value = value;
    }

    @Override
    public double getValue () {
        return value;
    }

    public void setValue ( double value ) {
        this.value = value;
    }

    @Override
    public void setFunctionVariable ( FunctionVariable functionVariable ) {
        //do nothing
    }

    @Override
    public boolean containsValue ( Value value ) {
        return equals( value );
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( getClass() != o.getClass() ) return false;
        return ( ( Number ) o ).getValue() == value;
    }
}
