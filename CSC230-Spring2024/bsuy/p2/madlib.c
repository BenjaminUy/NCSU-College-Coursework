/**
    @file madlib.c
    @author Benjamin Uy (bsuy)
    This program is the top-level components in the madlib project. This program utilizes all other components 
    to read from standard input and output an edited storyline based on the given replacement words.
*/

#include "input.h"
#include "replace.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

/** Exit status used if one of the five replacement strings are missing (empty-sized array) */
#define ERROR_MISSING_REPLACEMENT_STRING 101

/**
    Start of the mablib program. It first tries to the read the five replacement words from input.
    Then, until the end of input, the function tries to read each line and substitute their
    placeholders with the given replacement words. These edited lines are output to the console.
    @return successful exit status if the function reads to the end of file without issue
    @return exit status 101 if one of the five replacement strings are missing in the input
*/
int main()
{
    char noun1[ FIELD_MAX + 1 ];
    char noun2[ FIELD_MAX + 1 ];
    char verb[ FIELD_MAX + 1 ];
    char adjective[ FIELD_MAX + 1];
    char adverb[ FIELD_MAX + 1 ];

    readWord( noun1 );
    readWord( noun2 );
    readWord( verb );
    readWord( adjective );
    readWord( adverb );

    if ( ( strlen( noun1 ) == 0 ) || ( strlen( noun2 ) == 0 ) || ( strlen( verb ) == 0 ) || 
        ( strlen( adjective ) == 0 ) || ( strlen( adverb ) == 0 ) ) {
            exit( ERROR_MISSING_REPLACEMENT_STRING );
    }    

    char storyLine[ LINE_MAX + 1 ];

    readLine( storyLine );
    
    do {
        replaceWord( storyLine, noun1, "noun1\0" );
        replaceWord( storyLine, noun2, "noun2\0" );
        replaceWord( storyLine, verb, "verb\0" );
        replaceWord( storyLine, adjective, "adjective\0" );
        replaceWord( storyLine, adverb, "adverb\0" );

        printf( "%s\n", storyLine );
    } while ( readLine( storyLine ) == true );

    return EXIT_SUCCESS;
}