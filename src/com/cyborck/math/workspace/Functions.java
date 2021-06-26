package com.cyborck.math.workspace;

import com.cyborck.math.mathSystem.CustomFunction;
import com.cyborck.math.mathSystem.Function;

import java.util.ArrayList;
import java.util.List;

public class Functions {
    private final List<Function> allFunctions;
    private final List<CustomFunction> customFunctions;

    public Functions ( List<CustomFunction> customFunctions ) {
        allFunctions = new ArrayList<>();
        this.customFunctions = customFunctions;

        allFunctions.addAll( getProvidedFunctions() );
        allFunctions.addAll( customFunctions );
    }

    public Functions () {
        allFunctions = new ArrayList<>();
        customFunctions = new ArrayList<>();

        allFunctions.addAll( getProvidedFunctions() );
    }

    public static List<Function> getProvidedFunctions () {
        List<Function> providedFunctions = new ArrayList<>();

        providedFunctions.add( new Function( "sin", "sin(x)" ) {
            @Override
            public double get ( double x ) {
                return Math.sin( x );
            }
        } );

        return providedFunctions;
    }

    public Function getByName ( String functionName ) {
        for ( Function f: allFunctions )
            if ( f.getName().equals( functionName ) )
                return f;

        return null;
    }

    public boolean isValidFunctionName ( String name ) {
        if ( name.isEmpty() ) return false;

        //check if name contains only letters
        for ( char c: name.toCharArray() )
            if ( !Character.isLetter( c ) )
                return false;

        //check if name is already taken
        for ( Function f: allFunctions )
            if ( f.getName().equals( name ) )
                return false;

        //else return true
        return true;
    }

    public void add ( CustomFunction function ) {
        if ( isValidFunctionName( function.getName() ) ) {
            customFunctions.add( function );
            allFunctions.add( function );
        }
    }

    public void remove ( CustomFunction function ) {
        if ( customFunctions.contains( function ) ) {
            customFunctions.remove( function );
            allFunctions.remove( function );
        }
    }

    public void set ( int index, CustomFunction function ) {
        CustomFunction old = ( CustomFunction ) allFunctions.get( index );
        if ( isValidFunctionName( function.getName() ) || function.getName().equals( old.getName() ) ) {
            allFunctions.set( index, function );
            int customFunctionsIndex = customFunctions.indexOf( old );
            customFunctions.set( customFunctionsIndex, function );
        }
    }

    public List<Function> getAllFunctions () {
        return allFunctions;
    }

    public List<CustomFunction> getCustomFunctions () {
        return customFunctions;
    }
}
