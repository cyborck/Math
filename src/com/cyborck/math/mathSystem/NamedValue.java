package com.cyborck.math.mathSystem;

public class NamedValue implements Value {
    private String name;
    private String text;
    private Value value;

    public NamedValue ( String name, String text, Value value ) {
        this.name = name;
        this.text = text;
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

    public Value getValueObject () {
        return value;
    }

    @Override
    public void setFunctionVariable ( FunctionVariable functionVariable ) {
        value.setFunctionVariable( functionVariable );
    }

    public String getText () {
        return text;
    }

    public void setText ( String text ) {
        this.text = text;
    }

    @Override
    public boolean containsValue ( Value value ) {
        return equals( value );
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( getClass() != o.getClass() ) return false;
        return ( ( NamedValue ) o ).getName().equals( name );
    }
}
