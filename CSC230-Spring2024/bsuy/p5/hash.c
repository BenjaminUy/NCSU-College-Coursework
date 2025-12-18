/**
    @file hash.c
    @author Benjamin Uy (bsuy)
    Implementation file for the main component of the program. This file contains the main function and is responsible
    for parsing the command line argument, reading the input and printing out the resulting hash value.
*/

#include "sha256.h"

#include <stdio.h>

/** Constant representing the max number of command line arguments that can be provided when running the hash program */
#define MAX_NUM_ARGS 2

/**
    This function starts the hash program. It expects an optional command-line argument for the name of the file it should
    compute the hash. Alternatively, the function will also read from standard input the contents of a file. Afterwards, 
    the function will compute and output the final hash value for the file.
    @param argc number of command line arguments
    @param argv array of char pointers that contain the command line arguments
    @return integer for the exit code of the program; returns 1 if the program is run with more than two command-line arguments
        or the given input file cannot be opened; returns 0 to indicate program success.
*/
int main( int argc, char *argv[] )
{
    FILE *stream;
    if ( argc > MAX_NUM_ARGS ) {
        fprintf( stderr, "usage: hash [input_file]\n" );
        return 1;
    } else if ( argc == MAX_NUM_ARGS ) {                    // Reading from file command-line
        stream = fopen( argv[ 1 ], "rb" );
        if ( stream == NULL ) {
            perror( argv[ 1 ] );
            return 1;
        }
    } else if ( argc < MAX_NUM_ARGS ) {                     // Reading from standard input
        stream = freopen( NULL, "rb", stdin );
    }

    byte buffer[ BLOCK_SIZE ];
    int len;

    SHAState *state = makeState();

    while ( ( len = fread( buffer, 1, sizeof( buffer ), stream ) ) != 0 ) {
        update( state, buffer, len );
    }

    // Once we've reached the end of input, call the digest function
    word finalH[ HASH_WORDS ];
    digest( state, finalH );

    // Output the final hash on a single line
    for ( int i = 0; i < HASH_WORDS; i++ ) {
        printf( "%08x", finalH[ i ] );            // Print each final hash value in an 8 character field with 0s for padding
    }
    printf( "\n" );

    fclose( stream );
    freeState( state );

    return 0;
}
