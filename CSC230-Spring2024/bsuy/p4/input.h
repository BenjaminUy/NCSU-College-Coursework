/**
    @file input.h
    @author Benjamin Uy (bsuy)
    This header file provides the prototype function for processing input from a given filestream.
*/

#include <stdio.h>

/**
    This function reads a single line of input from the given input stream whether that be
    stdin or a file. Then, it returns the line as a string inside a block of dynamically allocated
    memory. This function is used to read commands from the user and to read record descriptions
    from a record file.
    @param fp file stream to read from, if this parameter is NULL, then the function will read from stdin
    @return the line as a string inside a block of dynamically allocated memory, and NULL if there's no input to read
*/
char *readLine( FILE *fp );
