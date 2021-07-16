package com.cyborck.math.workspace;

import com.cyborck.math.mathSystem.NamedValue;
import com.cyborck.math.mathSystem.Number;
import com.cyborck.math.mathSystem.Value;

import java.util.ArrayList;
import java.util.List;

public class NamedValues {
    private final Workspace workspace;

    private final List<NamedValue> allNamedValues;
    private final List<NamedValue> customNamedValues;

    public NamedValues ( Workspace workspace, List<NamedValue> customNamedValues ) {
        this.workspace = workspace;

        allNamedValues = new ArrayList<>();
        this.customNamedValues = customNamedValues;

        allNamedValues.addAll( getProvidedConstants() );
        allNamedValues.addAll( customNamedValues );
    }

    public NamedValues ( Workspace workspace ) {
        this.workspace = workspace;

        allNamedValues = new ArrayList<>();
        customNamedValues = new ArrayList<>();

        allNamedValues.addAll( getProvidedConstants() );
    }

    public static List<NamedValue> getProvidedConstants () {
        List<NamedValue> constants = new ArrayList<>();

        constants.add( new NamedValue( "pi", "pi = " + Math.PI, new Number( Math.PI ) ) );

        return constants;
    }

    public NamedValue getByName ( String name ) {
        for ( NamedValue nv: allNamedValues )
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
        for ( NamedValue nv: allNamedValues )
            if ( nv.getName().equals( name ) )
                return false;

        //else return true
        return true;
    }

    void add ( NamedValue namedValue ) {
        if ( isValidNamedValueName( namedValue.getName() ) ) {
            customNamedValues.add( namedValue );
            allNamedValues.add( namedValue );
        }
    }

    void remove ( NamedValue namedValue ) {
        //remove namedValue
        if ( customNamedValues.contains( namedValue ) ) {
            customNamedValues.remove( namedValue );
            allNamedValues.remove( namedValue );
        }
    }

    void removeNamedValuesThatContain ( Value value ) {
        List<NamedValue> toRemove = new ArrayList<>();

        for ( NamedValue nv: customNamedValues )
            if ( nv.containsValue( value ) )
                toRemove.add( nv );

        while ( !toRemove.isEmpty() ) {
            workspace.remove( toRemove.get( 0 ) );
            toRemove.remove( 0 );
        }
    }

    public List<NamedValue> getAllNamedValues () {
        return allNamedValues;
    }

    public List<NamedValue> getCustomNamedValues () {
        return customNamedValues;
    }
}
