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

    public void setValue ( Value value ) {
        this.value = value;
    }

    @Override
    public boolean contains ( FunctionVariable functionVariable ) {
        return false;
    }
}
