package com.cyborck.math.mathSystem;

public class FunctionReference implements Value {
    private final Function function;
    private FunctionVariable functionVariable;

    public FunctionReference ( Function function ) {
        this.function = function;
    }

    @Override
    public double getValue () {
        return function.get( functionVariable.getValue() );
    }

    @Override
    public void setFunctionVariable ( FunctionVariable functionVariable ) {
        this.functionVariable = functionVariable;
    }

    @Override
    public boolean containsValue ( Value value ) {
        return equals( value );
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        FunctionReference that = ( FunctionReference ) o;
        return this.function == that.function;
    }
}
