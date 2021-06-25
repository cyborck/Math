package com.cyborck.math.mathSystem;

public abstract class Function {
    private String name;

    public Function ( String name ) {
        this.name = name;
    }

    public abstract double get ( double x );

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }
}
