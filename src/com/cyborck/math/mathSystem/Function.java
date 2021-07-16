package com.cyborck.math.mathSystem;

public abstract class Function {
    private String name;
    private String text;

    public Function ( String name, String text ) {
        this.name = name;
        this.text = text;
    }

    public abstract double get ( double x );

    public abstract boolean containsValue ( Value value );

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getText () {
        return text;
    }

    public void setText ( String text ) {
        this.text = text;
    }
}
