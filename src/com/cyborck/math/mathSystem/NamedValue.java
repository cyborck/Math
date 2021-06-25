package com.cyborck.math.mathSystem;

public class NamedValue implements Value {
    private String name;
    private Value value;

    public NamedValue ( String name, Value value ) {
        this.name = name;
        this.value = value;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    @Override
    public double getValue () {
        return value.getValue();
    }

    @Override
    public void setFunctionVariable ( FunctionVariable functionVariable ) {
        value.setFunctionVariable( functionVariable );
    }

    public void setValue ( Value value ) {
        this.value = value;
    }
}
