package com.cyborck.math.mathSystem;

public class FunctionReference implements Value {
    private final Function function;
    private FunctionVariable functionVariable;

    public FunctionReference ( Function function, FunctionVariable functionVariable ) {
        this.function = function;
        setFunctionVariable( functionVariable );
    }

    @Override
    public double getValue () {
        return function.get( functionVariable.getValue() );
    }

    @Override
    public void setFunctionVariable ( FunctionVariable functionVariable ) {
        this.functionVariable = functionVariable;
    }
}
