package com.cyborck.math.mathSystem;

public interface Value {

    double getValue ();

    void setFunctionVariable ( FunctionVariable functionVariable );

    boolean containsValue ( Value value );
}
