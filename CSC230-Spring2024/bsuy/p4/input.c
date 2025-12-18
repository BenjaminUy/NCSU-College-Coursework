/**
    @file input.c
    @author Benjamin Uy (bsuy)
    This implementation file contains the function for reading a line from a given file stream (whether it be from a file or stdin).
    Such a function is useful for processing record files and the commands input by the user in the vinyl program.
*/

#include "input.h"

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/** Starting capacity of the array that will contain the input line read in the readLine() function*/
#define INITIAL_CAPACITY 5

/** Scale used when resizing the char array in readLine; we double the previous array's capacity */
#define RESIZE_SCALE 2

/**
    This function reads a single line of input from the given input stream whether that be
    stdin or a file. Then, it returns the line as a string inside a block of dynamically allocated
    memory. This function is used to read commands from the user and to read record descriptions
    from a record file.
    @param fp file stream to read from, if this parameter is NULL, then the function will read from stdin
    @return the line as a string inside a block of dynamically allocated memory, and NULL if there's no input to read
*/
char *readLine( FILE *fp )
{
    int capacity = INITIAL_CAPACITY;
    int len = 0;
    char *line = ( char * ) malloc( capacity * sizeof( char ) );

    int c;
    // Read the line and store in the array
    if ( fp == NULL || fp == stdin ) { 
        while ( ( c = getchar() ) != '\n' && c != EOF ) {
            if ( len >= capacity ) {        // Resizable array based off of code in Lecture Notes 12
                capacity *= RESIZE_SCALE;
                char *newLine = ( char * ) malloc( capacity * sizeof( char ) );
                memcpy( newLine, line, len * sizeof( char ) );
                free( line );
                line = newLine;
            }
            line[ len++ ] = c;            
        }
    } else {
        while ( ( c = fgetc( fp ) ) != '\n' && c != EOF ) {
            if ( len >= capacity ) {
                capacity *= RESIZE_SCALE;
                char *newLine = ( char * ) malloc( capacity * sizeof( char ) );
                memcpy( newLine, line, len * sizeof( char ) );
                free( line );
                line = newLine;
            }
            line[ len++ ] = c;
        }
    }

    // Add the terminating character at the end
    if ( len >= capacity ) {
        capacity *= RESIZE_SCALE;
        char *newLine = ( char * ) malloc( capacity * sizeof( char ) );
        memcpy( newLine, line, len * sizeof( char ) );
        free( line );
        line = newLine;
    }
    line[ len ] = '\0';
    // If no input to read, return null
    if ( len == 0 ) {
        free( line );
        return NULL;
    } else {
        return line;
    }   
}
