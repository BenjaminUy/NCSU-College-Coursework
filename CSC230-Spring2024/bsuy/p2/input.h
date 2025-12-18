/**
    @file input.h
    @author Benjamin Uy (bsuy)
    Header file for the input component that is responsible for reading the words and
    story lines from standard input
*/

#include "replace.h"

/**
    Function reads into the given string a replacement string from standard input.
    If the input is more than 24 characters in length or has an invalid character
    (not a letter, digit, hypen, apostrophe, or whitespace), the program will terminate.
    @param word string that will contain the word read from standard input
    @return exit status 102 if an invalid character is encountered or the word is over 24 chars long
*/
void readWord( char word[ FIELD_MAX + 1 ] );

/**
    Function reads into the given string a line of text from standard input.
    If the line is more than 100 characters in length, the program will terminate.
    @param line character array representing string being read from standard input
    @return true if the text is read without error and false if the end-of-file is reached
    @return exit status 103 if the line is more than 100 characters in length
*/
bool readLine( char line[ LINE_MAX + 1 ] );
