package com.cyborck.math.parser;

import com.cyborck.math.mathSystem.Number;
import com.cyborck.math.mathSystem.*;
import com.cyborck.math.workspace.Functions;
import com.cyborck.math.workspace.NamedValues;

public class Parser {
    private final Functions functions;
    private final NamedValues namedValues;

    public Parser ( Functions functions, NamedValues namedValues ) {
        this.functions = functions;
        this.namedValues = namedValues;
    }

    private Value parseValue ( String s ) throws ParseException {
        if ( s.isEmpty() ) throw new ParseException( "Parameter cannot be a value since it's empty!" );

        //try all the different possibilities
        try {
            return parseNumber( s );
        } catch ( ParseException ignored ) {
        }

        try {
            return parseCalculation( s );
        } catch ( ParseException ignored ) {
        }

        try {
            return parseFunctionReference( s );
        } catch ( ParseException ignored ) {
        }

        try {
            return parseFunctionValue( s );
        } catch ( ParseException ignored ) {
        }

        try {
            return parseNamedValueReference( s );
        } catch ( ParseException ignored ) {
        }

        try {
            return parseFunctionVariable( s );
        } catch ( ParseException ignored ) {
        }

        //else throw error, cause it must be one of the things above
        throw new ParseException( s + " is not a value!" );
    }

    public Calculation parseCalculation ( String s ) throws ParseException {
        s = s.trim();

        //check if calculation is surrounded with brackets and remove them if it is
        boolean canRemoveBraces = false;
        if ( s.toCharArray()[ 0 ] == '(' && s.toCharArray()[ s.length() - 1 ] == ')' ) {
            canRemoveBraces = true;
            int openBraces = 0;
            for ( char c: s.toCharArray() )
                if ( c == '(' ) openBraces++;
                else if ( c == ')' ) openBraces--;
                else if ( openBraces == 0 ) canRemoveBraces = false;
        }
        if ( canRemoveBraces ) s = s.substring( 1, s.length() - 1 );

        int subtractionIndex = -1;
        int multiplicationIndex = -1;
        int divisionIndex = -1;
        int powerIndex = -1;

        int openBraces = 0;
        for ( int i = 0; i < s.toCharArray().length; i++ ) {
            char c = s.toCharArray()[ i ];
            if ( c == '(' ) openBraces++;
            else if ( c == ')' ) openBraces--;
            else if ( openBraces == 0 ) {
                switch ( c ) {
                    case '+' -> {
                        String subString1 = s.substring( 0, i );
                        String subString2 = s.substring( i ).substring( 1 );
                        subString1 = subString1.length() == 0 ? "0" : subString1;
                        return new Addition( parseValue( subString1 ), parseValue( subString2 ) );
                    }
                    case '-' -> subtractionIndex = i;
                    case '*' -> multiplicationIndex = i;
                    case '/' -> divisionIndex = i;
                    case '^' -> powerIndex = i;
                }
            }
        }
        if ( subtractionIndex != -1 ) {
            String subString1 = s.substring( 0, subtractionIndex );
            String subString2 = s.substring( subtractionIndex ).substring( 1 );
            subString1 = subString1.length() == 0 ? "0" : subString1;
            return new Subtraction( parseValue( subString1 ), parseValue( subString2 ) );
        }
        if ( multiplicationIndex != -1 ) {
            String subString1 = s.substring( 0, multiplicationIndex );
            String subString2 = s.substring( multiplicationIndex ).substring( 1 );
            return new Multiplication( parseValue( subString1 ), parseValue( subString2 ) );
        }
        if ( divisionIndex != -1 ) {
            String subString1 = s.substring( 0, divisionIndex );
            String subString2 = s.substring( divisionIndex ).substring( 1 );
            return new Division( parseValue( subString1 ), parseValue( subString2 ) );
        }
        if ( powerIndex != -1 ) {
            String subString1 = s.substring( 0, powerIndex );
            String subString2 = s.substring( powerIndex ).substring( 1 );
            return new Power( parseValue( subString1 ), parseValue( subString2 ) );
        }

        throw new ParseException( s + " is not a calculation!" );
    }

    public Function parseCustomFunction ( String s ) throws ParseException {
        //first parse and than add to list in Functions class
        s = s.trim();

        //split in declaration and value
        String[] split = s.split( "=" );

        if ( split.length != 2 )
            throw new ParseException( s + " must contain exactly one '=' to be a function declaration!" );

        String totalName = split[ 0 ].trim();
        if ( !totalName.contains( "(x)" ) )
            throw new ParseException( s + " must contain \"(x)\" to be a function declaration!" );
        String name = totalName.substring( 0, totalName.indexOf( "(x)" ) );
        if ( !totalName.equals( name + "(x)" ) )
            throw new ParseException( "The name of a function must be: [name](x). " + s + " is not valid!" );
        if ( !functions.isValidFunctionName( name ) )
            throw new ParseException( name + " is not a valid name. It could be already in use or contain forbidden characters!" );

        String stringValue = split[ 1 ];
        Value value = parseValue( stringValue );

        Function function = new CustomFunction( name, value );
        functions.getAllFunctions().add( function );
        return function;
    }

    public NamedValue parseNamedValue ( String s ) throws ParseException {
        s = s.trim();

        //split in declaration and value
        String[] split = s.split( "=" );

        if ( split.length != 2 )
            throw new ParseException( s + " must contain exactly one '=' to be a value declaration!" );

        String name = split[ 0 ].trim();
        if ( !namedValues.isValidNamedValueName( name ) )
            throw new ParseException( name + " is not a valid name. It could be already in use or contain forbidden characters!" );

        String stringValue = split[ 1 ];
        Value value = parseValue( stringValue );

        NamedValue namedValue = new NamedValue( name, value );
        namedValues.getAllNamedValues().add( namedValue );
        return namedValue;
    }

    private FunctionReference parseFunctionReference ( String s ) throws ParseException {
        //f.e. f(x)
        s = s.trim();

        if ( !s.endsWith( "(x)" ) )
            throw new ParseException( s + " is not a function reference!" );

        String name = s.substring( 0, s.indexOf( "(x)" ) );
        Function function = functions.getByName( name );
        if ( function == null )
            throw new ParseException( "Function with name " + name + " does not exist!" );

        return new FunctionReference( function, new FunctionVariable() );
    }

    private Number parseFunctionValue ( String s ) throws ParseException {
        //f.e. f(5)
        s = s.trim();

        int openBracketIndex = s.indexOf( '(' );
        int closeBracketIndex = s.lastIndexOf( ')' );

        if ( openBracketIndex < 1 || closeBracketIndex != s.length() - 1 )
            throw new ParseException( s + " is not a function reference!" );

        String functionName = s.substring( 0, openBracketIndex );
        Function function = functions.getByName( functionName );
        if ( function == null )
            throw new ParseException( "Function with that name does not exist!" );

        String stringValue = s.substring( openBracketIndex + 1, closeBracketIndex );
        double x = parseValue( stringValue ).getValue();
        return new Number( function.get( x ) );
    }

    private NamedValue parseNamedValueReference ( String s ) throws ParseException {
        s = s.trim();

        NamedValue namedValue = namedValues.getByName( s );
        if ( namedValue == null )
            throw new ParseException( s + " is not a NamedValue reference!" );

        return namedValue;
    }

    private Number parseNumber ( String s ) throws ParseException {
        try {
            return new Number( Double.parseDouble( s.trim() ) );
        } catch ( NumberFormatException e ) {
            throw new ParseException( s + " is not a number!" );
        }
    }

    private FunctionVariable parseFunctionVariable ( String s ) throws ParseException {
        if ( s.trim().equals( "x" ) ) return new FunctionVariable();
        throw new ParseException( s + " is not x so it's not a function variable!" );
    }
}
