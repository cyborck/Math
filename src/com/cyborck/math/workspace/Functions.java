package com.cyborck.math.workspace;

import com.cyborck.math.mathSystem.CustomFunction;
import com.cyborck.math.mathSystem.Function;
import com.cyborck.math.mathSystem.Value;

import java.util.ArrayList;
import java.util.List;

public class Functions {
    private final Workspace workspace;

    private final List<Function> allFunctions;
    private final List<CustomFunction> customFunctions;

    public Functions ( Workspace workspace, List<CustomFunction> customFunctions ) {
        this.workspace = workspace;

        allFunctions = new ArrayList<>();
        this.customFunctions = customFunctions;

        allFunctions.addAll( getProvidedFunctions() );
        allFunctions.addAll( customFunctions );
    }

    public Functions ( Workspace workspace ) {
        this.workspace = workspace;

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

            @Override
            public boolean containsValue ( Value value ) {
                return false;
            }
        } );
        providedFunctions.add( new Function( "cos", "cos(x)" ) {
            @Override
            public double get ( double x ) {
                return Math.cos( x );
            }

            @Override
            public boolean containsValue ( Value value ) {
                return false;
            }
        } );
        providedFunctions.add( new Function( "tan", "tan(x)" ) {
            @Override
            public double get ( double x ) {
                return Math.tan( x );
            }

            @Override
            public boolean containsValue ( Value value ) {
                return false;
            }
        } );
        providedFunctions.add( new Function( "asin", "asin(x)" ) {
            @Override
            public double get ( double x ) {
                return Math.asin( x );
            }

            @Override
            public boolean containsValue ( Value value ) {
                return false;
            }
        } );
        providedFunctions.add( new Function( "acos", "acos(x)" ) {
            @Override
            public double get ( double x ) {
                return Math.acos( x );
            }

            @Override
            public boolean containsValue ( Value value ) {
                return false;
            }
        } );
        providedFunctions.add( new Function( "atan", "atan(x)" ) {
            @Override
            public double get ( double x ) {
                return Math.atan( x );
            }

            @Override
            public boolean containsValue ( Value value ) {
                return false;
            }
        } );
        providedFunctions.add( new Function( "exp", "exp(x)" ) {
            @Override
            public double get ( double x ) {
                return Math.exp( x );
            }

            @Override
            public boolean containsValue ( Value value ) {
                return false;
            }
        } );
        providedFunctions.add( new Function( "log", "log(x)" ) {
            @Override
            public double get ( double x ) {
                return Math.log( x );
            }

            @Override
            public boolean containsValue ( Value value ) {
                return false;
            }
        } );
        providedFunctions.add( new Function( "log10", "log10(x)" ) {
            @Override
            public double get ( double x ) {
                return Math.log10( x );
            }

            @Override
            public boolean containsValue ( Value value ) {
                return false;
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

    void add ( CustomFunction function ) {
        if ( isValidFunctionName( function.getName() ) ) {
            customFunctions.add( function );
            allFunctions.add( function );
        }
    }

    void remove ( CustomFunction customFunction ) {
        //remove function
        if ( this.customFunctions.contains( customFunction ) ) {
            this.customFunctions.remove( customFunction );
            allFunctions.remove( customFunction );
        }
    }

    void removeFunctionsThatContain ( Value value ) {
        List<CustomFunction> toRemove = new ArrayList<>();

        for ( CustomFunction cf: customFunctions )
            if ( cf.containsValue( value ) )
                toRemove.add( cf );

        while ( !toRemove.isEmpty() ) {
            workspace.remove( toRemove.get( 0 ) );
            toRemove.remove( 0 );
        }
    }

    public List<Function> getAllFunctions () {
        return allFunctions;
    }

    public List<CustomFunction> getCustomFunctions () {
        return customFunctions;
    }
}
