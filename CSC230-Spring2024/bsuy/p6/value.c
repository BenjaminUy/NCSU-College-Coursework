/**
    @file value.c
    @author CSC 230
    @author Benjamin Uy
    Implementation file for the value component; contains structs for the value superclass along
    with its three iterations -- IntegerValue, DoubleValue, and StringValue. 
*/

#include "value.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>

/** Length for the buffer character arrays meant to catch additional input;
    also represents the number of characters added by quotation marks when
    dynamically allocated memory for string representations */
#define BUFFER_LENGTH_OR_QUOTATIONS 2

/** Type used to represent a subclass of Value that holds an integer. */
typedef struct {
    // Superclass fields.
    char *(*toString)( Value const *v );
    bool (*plus)( Value *v, Value const *x );
    void (*destroy)( Value *v );

    // Subclass fields.
    int val;
} IntegerValue;

/** Maximum length of a 32-bit integer as a string. */
#define INTEGER_LENGTH 11

// toString method for integers
static char *integerToString( Value const *v )
{
    // Get v as a pointer to the subclass struct.
    IntegerValue *this = (IntegerValue *) v;

    // Convert to a dynamically allocated string.
    char *str = (char *) malloc( INTEGER_LENGTH + 1 );
    sprintf( str, "%d", this->val );
    return str;
}

// Plus method for integers.
static bool integerPlus( Value *v, Value const *x )
{
    // We can use a function pointer to make sure x is also an
    // integrer.
    if ( x->toString != integerToString )
        return false;
    
    // Get the parameters as IntegerValue poitners.
    IntegerValue *this = ( IntegerValue * ) v;
    IntegerValue *that = ( IntegerValue * ) x;

    // Add the value in x to v.
    this->val += that->val;
    return true;
}

// destroy method for integers
static void integerDestroy( Value *v )
{
    // All the memory for an integer is in one big block.
    free( v );
}

Value *parseInteger( char const *str )
{
    // Try to parse an integer from str.  The buffer is to make sure
    // there's no extra, non-space characters after the integer value.
    int ival;
    char buffer[ BUFFER_LENGTH_OR_QUOTATIONS ];

    if ( sscanf( str, "%d%1s", &ival, buffer ) != 1 )
        return NULL;

    // Make a new instance of an integer value.
    IntegerValue *v = ( IntegerValue * ) malloc( sizeof( IntegerValue ) );
    v->toString = integerToString;
    v->plus = integerPlus;
    v->destroy = integerDestroy;
    v->val = ival;

    // Return as a pointer to the superclass.
    return ( Value * ) v;
}

/** Type used to represent a subclass of Value that holds a double. */
typedef struct {
    // Superclass fields.
    char * ( *toString ) ( Value const *v );
    bool ( *plus ) ( Value *v, Value const *x );
    void ( *destroy ) ( Value *v );

    // Subclass fields.
    double val;
} DoubleValue;

/** This is the maximum number of characters I could get from a double value,
    printed with %f. */
#define DOUBLE_LENGTH 317

/**
    The toString method for DoubleValues
    @param v pointer to a Value to return a string representation of
    @return string representation of v as a DoubleValue
*/
static char *doubleToString( Value const *v )
{
    // Get v as a pointer to the subclass struct.
    DoubleValue *this = ( DoubleValue * ) v;

    // Convert to a dynamically allocated string.
    char *str = ( char * ) malloc( DOUBLE_LENGTH + 1 );
    sprintf( str, "%f", this->val );
    return str;
}

/**
    The plus method for DoubleValues
    @param v pointer to a Value to add to
    @param x pointer to the Value to be 'added' to v
    @return string representation of v as a DoubleValue
*/
static bool doublePlus( Value *v, Value const *x )
{
    if ( x->toString != doubleToString )
        return false;
    
    // Get the parameters as IntegerValue poitners.
    DoubleValue *this = ( DoubleValue * ) v;
    DoubleValue *that = ( DoubleValue * ) x;

    // Add the value in x to v.
    this->val += that->val;
    return true;
}

/**
    The destroy method for DoubleValues
    @param v pointer to a Value to free memory from
*/
static void doubleDestroy( Value *v )
{
    free( v );
}

/**
    This function parses the given string and dynamically allocates memory for a
    new DoubleValue pointer, which it returns as a Value pointer.
    @param str string to parse a DoubleValue from
    @return pointer to a Value cast from the created DoubleValue
*/
Value *parseDouble( char const *str ) {
    double dval;
    char buffer[ BUFFER_LENGTH_OR_QUOTATIONS ];

    if ( sscanf( str, "%lf%1s", &dval, buffer ) != 1 )
        return NULL;

    DoubleValue *v = ( DoubleValue * ) malloc( sizeof( DoubleValue ) );
    v->toString = doubleToString;
    v->plus = doublePlus;
    v->destroy = doubleDestroy;
    v->val = dval;

    return ( Value * ) v;
}

/** Type used to represent a subclass of Value that holds a string. */
typedef struct {
    // Superclass fields.
    char * ( *toString ) ( Value const *v );
    bool ( *plus ) ( Value *v, Value const *x );
    void ( *destroy ) ( Value *v );

    // Subclass fields.
    char *val;
} StringValue;

/**
    The toString method for StringValues
    @param v pointer to a Value to return a string representation of
    @return string representation of v as a StringValue
*/
static char *stringToString( Value const *v )
{
    // Get v as a pointer to the subclass struct.
    StringValue *this = ( StringValue * ) v;

    // Convert to a dynamically allocated string.
    char *str = ( char * ) malloc( strlen( this->val ) + BUFFER_LENGTH_OR_QUOTATIONS + 1 );
    sprintf( str, "\"%s\"", this->val );
    return str;
}

/**
    The plus method for StringValues
    @param v pointer to a Value to add to
    @param x pointer to the Value to be 'added' to v
    @return string representation of v as a StringValue
*/
static bool stringPlus( Value *v, Value const *x )
{
    if ( x->toString != stringToString )
        return false;   

    // Get the parameters as StringValue pointers.
    StringValue *this = ( StringValue * ) v;
    StringValue *that = ( StringValue * ) x;

    // Reallocate space for this string
    this->val = ( char * ) realloc( this->val, ( ( strlen( this->val ) + strlen( that->val ) + 1 ) * sizeof( char ) ) );
    
    // Then concatenate that's string to this's string
    strcat( this->val, that->val );
    return true;
}

/**
    The destroy method for DoubleValues
    @param v pointer to a Value to free memory from
*/
static void stringDestroy( Value *v )
{
    StringValue *this = ( StringValue * ) v;
    // Free memory from the string contents stored in val
    free( this->val );
    free( v );
}

/**
    This function parses the given string and dynamically allocates memory for a
    new StringValue pointer, which is returned as a Value pointer.
    @param str string to parse a StringValue from
    @return pointer to a Value cast from the created StringValue or NULL if str can't be parsed correctly
*/
Value *parseString( char const *str ) 
{
    char *s = ( char * ) malloc( strlen( str ) * sizeof( char ) );
    char buffer1[ BUFFER_LENGTH_OR_QUOTATIONS ];
    char buffer2[ BUFFER_LENGTH_OR_QUOTATIONS ];
    // int matches;

    if ( sscanf( str, " \"%[^\n\"]", s ) == 1 ) {
        if ( sscanf( str, " \"%*[^\n\"]%1s", buffer1 ) == 1 ) {
            // if ( strcmp( buffer1, "\"" ) != 0 ) {
            //     free( s );
            //     return NULL;
            // } else 
            if ( sscanf( str, " \"%*[^\n\"]%*1s%1s", buffer2 ) == 1 ) {
                // Detection for extra text after the second quotation mark
                free( s );
                return NULL;
            }
        } else {
            // Missing quotation mark
            free( s );
            return NULL;
        }
    } else if ( sscanf( str, "%s%1s", s, buffer1 ) == 1 ) {
        // Case where str is just double quotation marks ""
        if ( strcmp( s, "\"\"" ) == 0 ) {
            s[ 0 ] = '\0';
            s[ 1 ] = '\0';
        } else {
            free( s );
            return NULL;
        }
    } else {
        free( s );
        return NULL;
    }

    StringValue *v = ( StringValue * ) malloc( sizeof( StringValue ) );
    v->toString = stringToString;
    v->plus = stringPlus;
    v->destroy = stringDestroy;
    v->val = s;

    return ( Value * ) v;
}
