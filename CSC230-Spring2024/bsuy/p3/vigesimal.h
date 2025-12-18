/**
    @file vigesimal.h
    @author Benjamin Uy (bsuy)
    This header file contains prototype functions for reading and printing numbers in base 20.
*/

// The prototypes need the bool and FILE types.
#include <stdio.h>
#include <stdbool.h>

/**
    This function reads characters from the given stream and skips whitespace
    @param input the file stream to read from
    @return integer code of the first non-whitespace or EOF character read from the stream
*/
int skipWhitespace( FILE *input );

/**
    This function reads the next number in the given input stream in base 20.
    @param val pointer to a long variable that will store the parsed number
    @param input the file stream to read from
    @return true if the input number is successfully parsed and stored in val
    @return false if errors are detected in the input number
*/
bool parseNumber( long *val, FILE *input );

/**
    This function prints the given value to the given stream in base 20
    @param val long value to print
    @param output output stream to print val to
*/
void printNumber( long val, FILE *output );
