/**
    @file vigesimal.c
    @author Benjamin Uy (bsuy)
    This implementation file contains functions for reading and printing numbers in base 20.
*/

#include "vigesimal.h"
#include "check.h"

#include <ctype.h>

/** Base of the number system we're implementing. */
#define BASE 20

/** Maximum number of characters needed to print the largest number. */
#define MAX_NUMBER_LEN 16

/** ASCII code for 'A' -- used to convert a digit to a char in printNumber() */
#define ASCII_CODE_A 65

/**
    This function reads characters from the given stream and skips whitespace
    @param input the file stream to read from
    @return integer code of the first non-whitespace or EOF character read from the stream
*/
int skipWhitespace( FILE *input )
{
    int ch = -1;
    do {
        ch = fgetc( input );
    } while ( ch != EOF && isspace( ch ) );

    // After a non-whitespace or EOF character is found, return its ASCII code
    return ch;
} 

/**
    This function reads the next number in the given input stream in base 20.
    @param val pointer to a long variable that will store the parsed number
    @param input the file stream to read from
    @return true if the input number is successfully parsed and stored in val
    @return false if errors are detected in the input number
*/
bool parseNumber( long *val, FILE *input ) 
{
    long value = 0;
    long *vptr = &value;
    bool negative = false;

    int ch = skipWhitespace( input );

    if ( ch == EOF ) {
        return false;
    }
    
    // Dash in front indicates if the number is negative
    if ( ch == '-' ) {
        negative = true;
        ch = fgetc( input );
    }

    if ( ch < 'A' || ch > 'T' ) {
        return false;
    }

    while ( ch >= 'A' && ch <= 'T' ) {
        // convert char to digit in base 20
        int digit = ch - ASCII_CODE_A;

        // First and only case where we multiply the calculation by a negative number -- used when dealing with negative numbers
        // Afterwards, we can just subtract
        if ( negative && ( value > 0 ) && ( multiply( vptr, value, BASE * -1 ) || subtract( vptr, value, digit ) == false) ) {
            return false;
        } 
        if ( negative && ( ( multiply( vptr, value, BASE ) == false ) || ( subtract( vptr, value, digit ) == false ) ) ) {
            return false;
        } else if ( !negative && ( ( multiply( vptr, value, BASE ) == false ) || ( add( vptr, value, digit ) == false ) ) ) {
            return false;
        }
        ch = fgetc( input );
    }
    if (ch != EOF ) {
        ungetc( ch, input );
    }
    *val = *vptr;
    return true;
}

/**
    This function prints the given value to the given output stream in base 20
    @param val long value to print
    @param output output stream to print val to
*/
void printNumber( long val, FILE *output )
{
    int index = 0;
    char str[ MAX_NUMBER_LEN ];

    if ( val == 0 ) {
        if ( output != NULL) {
            fprintf( output, "A" );
        } else {
            printf( "A" );
        }
    }

    // Case where val is negative
    if ( val < 0 ) {
        if ( output != NULL ) {
            fprintf( output, "-" );
        } else {
            printf( "-" );
        }
        int digit = val % (BASE);
        char ch = -digit + ASCII_CODE_A; 

        str[ index++ ] = ch;
        val = -val / BASE;
    }

    // Case where val is positive
    while ( val != 0 ) {
        int digit = val % BASE;
        char ch = digit + ASCII_CODE_A;

        str[ index++ ] = ch;

        val = val / BASE;
    }

    str[ index ] = '\0';

    for ( int i = index - 1; i >= 0; i-- ) {
        if ( output != NULL ) {
            fprintf( output, "%c", str[ i ] );
        } else {
            printf( "%c", str[ i ] );
        }
    }
    
    if ( output != NULL ) {
        fprintf( output, "\n" );
    } else {
        printf( "\n" );
    }
}
