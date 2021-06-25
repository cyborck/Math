package com.cyborck.math.workspace;

import com.cyborck.math.mathSystem.NamedValue;
import com.cyborck.math.mathSystem.Number;

import java.util.ArrayList;
import java.util.List;

public class NamedValues {
    private final List<NamedValue> namedValues;

    public NamedValues ( List<NamedValue> namedValues ) {
        this.namedValues = namedValues;
    }

    public NamedValues () {
        namedValues = new ArrayList<>();
        namedValues.addAll( getProvidedConstants() );
    }

    public static List<NamedValue> getProvidedConstants () {
        List<NamedValue> constants = new ArrayList<>();

        constants.add( new NamedValue( "pi", new Number( Math.PI ) ) );

        return constants;
    }

    public NamedValue getByName ( String name ) {
        for ( NamedValue nv: namedValues )
            if ( nv.getName().equals( name ) )
                return nv;

        return null;
    }

    public boolean isValidNamedValueName ( String name ) {
        if ( name.isEmpty() ) return false;

        //check if name contains only letters
        for ( char c: name.toCharArray() )
            if ( !Character.isLetter( c ) )
                return false;

        //check if name is already taken
        for ( NamedValue nv: namedValues )
            if ( nv.getName().equals( name ) )
                return false;

        //else return true
        return true;
    }

    public List<NamedValue> getNamedValues () {
        return namedValues;
    }
}
