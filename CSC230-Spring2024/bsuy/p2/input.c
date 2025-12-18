/**
    @file input.c
    @author Benjamin Uy (bsuy)
    Implementation file for the component that reads in the replacement strings and lines of text.
    Contains the functions for reading the replacement words and story line from standard input.
*/

#include "input.h"

#include <ctype.h>
#include <stdlib.h>
#include <stdio.h>

/** Exit status whenever readWord encounters an invalid character or replacement word over 24 chars long */
#define ERROR_INVALID_CHARACTER_OR_CAPACITY 102

/** Exit status whenever readLine reads in a line that is over 100 characters */
#define ERROR_INVALID_LINE_LENGTH 103

/**
    Function reads into the given string a replacement string from standard input.
    If the input is more than 24 characters in length or has an invalid character
    (not a letter, digit, hypen, apostrophe, or whitespace), the program will terminate.
    @param word string that will contain the word read from standard input
    @return exit status 102 if an invalid character is encountered or the word is over 24 chars long
*/
void readWord( char word[ FIELD_MAX + 1 ] )
{
    char currentChar = getchar();
    int capacity = 0;

    while ( currentChar != EOF && currentChar != '\n' ) {

        // Check that currentChar is valid -- a letter, digit, hypen, apostrophe, or space
        if ( !isalpha( currentChar ) && !isdigit( currentChar ) && currentChar != '-' && currentChar != '\'' && currentChar != ' ' ) {
            exit( ERROR_INVALID_CHARACTER_OR_CAPACITY );
        } else if ( capacity >= FIELD_MAX ) {
            exit( ERROR_INVALID_CHARACTER_OR_CAPACITY );
        }
        word[ capacity ] = currentChar;
        capacity++;
        currentChar = getchar();
    }

    // Add terminating character to the string after reading from input
    word[ capacity ] = '\0';
}

/**
    Function reads into the given string a line of text from standard input.
    If the line is more than 100 characters in length, the program will terminate.
    @param line character array representing string being read from standard input
    @return true if the text is read without error and false if the end-of-file is reached
    @return exit status 103 if the line is more than 100 characters in length
*/
bool readLine( char line[ LINE_MAX + 1 ] ) 
{
    char currentChar = getchar();
    int capacity = 0;

    while ( currentChar != EOF && currentChar != '\n' ) {
        if ( capacity >= LINE_MAX ) {
            exit( ERROR_INVALID_LINE_LENGTH );
        }
        line[ capacity ] = currentChar;
        capacity++;
        currentChar = getchar();
    }

    // Add terminating character to the string after reading from input
    line[ capacity ] = '\0';

    if ( currentChar == EOF ) {
        return false;
    }
    // At this point, currentChar should be newline
    return true;
}