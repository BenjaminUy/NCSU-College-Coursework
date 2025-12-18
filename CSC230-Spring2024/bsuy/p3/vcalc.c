/**
    @file vcalc.c
    @author Benjamin Uy (bsuy)
    This implementation file contains the main function along with other functions used to
    help parse and evaluate statements and expressions written in the base 20 number system.
*/

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

#include "vigesimal.h"
#include "check.h"

/** Requried input-file argument. */
#define INPUT_ARG 1

/** Optional, output-file argument. */
#define OUTPUT_ARG 2

/** Size of the English alphabet, used as the size of the variables array */
#define ALPHABET_SIZE 26

/** Minimum characters of a command line argument for initializing a variable */
#define MIN_INITIALIZATION_ARG_LEN 3

/** ASCII code for 'A' -- used to convert a digit to a char in parseCommandLineArgument */
#define ASCII_CODE_A 65

/** Base of the number system we're implementing. */
#define BASE 20

/** Global array used to store the current value of each variable */
long variables[ ALPHABET_SIZE ] = { 0 };

/**
    Initially-provided function for outputting an error message to standard error
    whenever there are too few arguments provided in the command line
*/
static void usage()
{
    fprintf( stderr, "usage: vcalc INPUT-FILE [OUTPUT-FILE]\n" );
    exit( EXIT_FAILURE );
}

/**
    This function reads the next statement from the input stream. If it is an expression,
    the function reports its value to the given output stream using the given stmtNum.
    @param stmtNum current statement number -- used when reporting the value of an expression
    @param input file input stream that is being read from
    @param output file output stream that is being output to
    @return true if the input contains a valid statement and false otherwise
*/
bool parseStatement( int stmtNum, FILE *input, FILE *output );

/**
    Helper function used to determine if the given string or array of characters
    is a valid variable assignment. If the string is valid, then the function will initialize
    the variable using the global array.
    @param arg command line argument string to parse through
    @return true if the string is valid and false if the given assignment has invalid characters or would cause overflow
*/
bool parseCommandLineAssignment( char arg[] ) {

    // Minimum assignment involves 3 characters and requires the second character to be an equal sign
    if ( strlen( arg ) < MIN_INITIALIZATION_ARG_LEN ) {
        return false;
    } else if ( arg[ 1 ] != '=' ) {
        return false;
    } else if ( arg[ 0 ] < 'a' || arg[ 0 ] > 'z' ) {    // Assignment requires the first character to be a variable (lower-case letter)
        return false;
    }

    char variable = arg[ 0 ];
    long value = 0;
    long *vptr = &value;
    bool negative = false;

    // Start from index 2 and iterate to the end of arg to evaluate the expression
    for ( int i = MIN_INITIALIZATION_ARG_LEN - 1; i < strlen( arg ); i++ ) {

        // Case for negative numbers -- make sure that we are not already at the end of the string
        if ( i == MIN_INITIALIZATION_ARG_LEN - 1 && i < strlen( arg ) - 1 ) {
            if ( arg[ i ] == '-' ) {
                negative = true;
            }
        }

        // Skip the current loop iteration since we've found that the current character is a '-'
        if ( negative && i == MIN_INITIALIZATION_ARG_LEN - 1 ) {
            continue;  
        }

        // Checking for invalid characters
        if ( isspace( arg[ i ] ) || arg[ i ] < 'A' || arg[ i ] > 'T' ) {
            return false;
        }

        int digit = arg[ i ] - ASCII_CODE_A;

        if ( negative && ( value > 0 ) && ( multiply( vptr, value, BASE * -1 ) || subtract( vptr, value, digit ) == false ) ) {
            return false;
        } else if ( negative && ( ( multiply( vptr, value, BASE ) == false ) || ( subtract( vptr, value, digit ) == false ) ) ) {
            return false;
        } else if ( !negative && ( ( multiply( vptr, value, BASE ) == false ) || ( add( vptr, value, digit ) == false ) ) ) {
            return false;
        }
    }

    // Afterwards, vptr contains the value of the expression after the equal sign
    variables[ variable - 'a' ] = *vptr;

    return true;
}

/**
    Start of the vcalc program. Begins with checking the number of arguments given in the command line
    and attempts to create file input/output streams. If successfully done, the function will repeatedly
    call parseStatement -- outputting the results of the statements -- until the end of the file.
    @param argc number of arguments in the command line
    @param argv array of char pointers; each command line argument has a char pointer
    @return successful exit code if it is able to read through the input file and output the results of the statements/expressions
    @return exit code of 1 if a command line argument for initializing a variable is found to be invalid
    @return exit code of 1 if either the input or output file stream cannot be created
*/
int main( int argc, char *argv[] )
{
    if ( argc == 1 ) {
        usage();
    }
    
    bool inputGiven = false;
    bool outputGiven = false;

    FILE *input = NULL;
    FILE *output = NULL;

    int i = 1;
    while ( i < argc ) {
        if ( strlen( argv[ i ] ) >= MIN_INITIALIZATION_ARG_LEN - 1 && ( argv[ i ][ 1 ] == '=' ) ) {
            if ( !parseCommandLineAssignment( argv[ i ] ) ) {
                fprintf( stderr, "Invalid variable initialization\n" );
                exit( EXIT_FAILURE );
            }
        } else if ( !inputGiven ) {
            input = fopen( argv[ i ] , "r" );
            if ( input == NULL ) {
                fprintf( stderr, "Can't open file: %s\n", argv[ i ] );
                exit( EXIT_FAILURE );
            }
            inputGiven = true;
        } else if ( inputGiven && !outputGiven ) {
            output = fopen( argv[ i ], "w" );
            if ( output == NULL ) {
                fprintf( stderr, "Can't open file: %s\n", argv[ i ] );
                exit( EXIT_FAILURE );
            }
            outputGiven = true;
        }
        i++;
    }

    int statementNum = 1;
    int ch = -1;
    do {
        
        if ( parseStatement( statementNum, input, output ) == false ) {
            // Error encountered while parsing statement
            // Print error message to standard output
            if ( output != NULL ) {
                fprintf( output, "S%d: invalid\n", statementNum );
            } else {
                printf( "S%d: invalid\n", statementNum );
            }

            // Remove the next semi-colon from input stream
            do {
                ch = fgetc( input );
            } while ( ch != EOF && ch != ';' );
        }
        
        ch = skipWhitespace( input );
        if ( ch != EOF ) {
            ungetc( ch, input );
        }
        statementNum++;
    } while ( ch != EOF );

    fclose( input );
    if ( output != NULL ) {
        fclose( output );
    }

    exit( EXIT_SUCCESS );
}

/**
    This function attempts to read the next operand from the given input stream.
    It also stores the value of the operand in the long variable pointed to by val.
    If the operand is a variable, its name is stored in vname -- otherwise, the name
    is set to '\0'
    @param val value of the operand
    @param vname name of the variable
    @param input input stream to read from
    @return true if the input contains a valid operand and false otherwise
*/
bool parseOperand( long *val, char *vname, FILE *input )
{
    // First check if the operand is a variable
    char ch = skipWhitespace( input );

    if ( ch >= 'a' && ch <= 'z' ) {
        *vname = ch;

        *val = variables[ ch - 'a' ];   // Get the current value of the variable from our array
        return true;
    } else {
        ungetc( ch, input );
    }

    // Next check if the operand is in base 20
    if ( parseNumber( val, input ) == true ) {
        *vname = '\0';
        return true;
    }
    return false;
}

/**
    This function reads an expression as a sequence operands separated by '+', '-', '*', and '/' operators.
    It computes the result of the expresion and stores it in the long variable pointed to by result.
    @param result stores the finished calculation of the expression
    @param left first operand that is already parsed from the expression
    @param input input stream to read from
    @return true if the expression is valid and the result is successfully stored -- false otherwise
*/
bool parseExpression( long *result, long left, FILE *input )
{
    long val = 0;
    long *value = &val;

    char operator = '\0';
    char *vname = &operator;
    
    *result = left;

    // Case where left is immediately followed by ';'
    operator = skipWhitespace( input );

    if ( operator == ';' ) {
        *result = left;
        return true;
    } else if ( operator == EOF ) {
        return false;
    } else {
        // Potentially an operator encountered, unget the character
        ungetc( operator, input );
    }

    do {
        // Read for operator (+, -, *, or /)
        operator = skipWhitespace( input );

        if ( operator == ';' ) {
            return true;
        } else if ( ( operator == '+' ) || ( operator == '-' ) || ( operator == '*' ) || ( operator == '/' ) ) {

            char math = operator;

            // Immediately check for operand after the operand
            if ( parseOperand( value, vname, input ) == false ) {
                return false;
            }

            // Operand was successfully found, now do the operation
            if ( math == '+' ) {
                if ( !add( result, *result, *value ) ) {
                    return false;
                }
            } else if ( math == '-' ) {
                if ( !subtract( result, *result, *value ) ) {
                    return false;
                }
            } else if ( math == '*' ) {
                if ( !multiply( result, *result, *value ) ) {
                    return false;
                }
            } else if ( math == '/' ) {
                if ( !divide( result, *result, *value ) ) {
                    return false;
                }
            }
        } else {
            return false;
        }
    } while ( operator != ';' && operator != EOF );
    return true;
}

/**
    This function reads the next statement from the input stream. If it is an expression,
    the function reports its value to the given output stream using the given stmtNum.
    @param stmtNum current statement number -- used when reporting the value of an expression
    @param input file input stream that is being read from
    @param output file output stream that is being output to
    @return true if the input contains a valid statement and false otherwise
*/
bool parseStatement( int stmtNum, FILE *input, FILE *output )
{
    long val = 0;
    long *valptr = &val;

    char operand = '\0';
    char *vname = &operand;
    
    if ( parseOperand( valptr, vname, input ) == false ) {
        return false;
    }

    if ( *vname == '\0' ) {
        // The first operand was a number given in base 20, we must handle the remaining line as an expression
        if ( parseExpression( valptr, *valptr, input ) ) {
            if ( output != NULL ) {
                fprintf( output, "S%d: ", stmtNum );
            } else {
                printf( "S%d: ", stmtNum);
            }
            printNumber( *valptr, output );
            return true;
        } else {       
            return false;
        }
    } else {
        // The first operand was a variable
        // Check for '=' which indicates an assignment statement
        int ch = skipWhitespace( input );

        if ( ch == '=' ) {
            char assign = *vname;

            if ( !parseOperand( valptr, vname, input ) ) {
                return false;
            } else {
                // Operand after the equal sign was found successfully
                // Now evaluate the remaining line with parseExpression
                if ( !parseExpression( valptr, *valptr, input ) ) {
                    return false;
                } else {
                    variables[ assign - 'a' ] = *valptr;
                    return true;
                }
                
            }
        } else if ( ch == ';' ) {
            if ( output != NULL ) {
                fprintf( output, "S%d: ", stmtNum );
            } else {
                printf( "S%d: ", stmtNum );
            }
            printNumber( *valptr, output );
        } else {                                                    // No '=' found, so we have an expression
            ungetc( ch, input );

            if ( parseExpression( valptr, *valptr, input ) ) {
                if ( output != NULL ) {
                    fprintf( output, "S%d: ", stmtNum );
                } else {
                    printf( "S%d: ", stmtNum );
                }
                printNumber( *valptr, output );
            } else {
                return false;
            }
        }
    }
    return true;
}
