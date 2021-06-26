package com.cyborck.math.mathSystem;

public abstract class Function {
    private String name;
    private String text;

    public Function ( String name, String text ) {
        this.name = name;
    }

    public abstract double get ( double x );

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
