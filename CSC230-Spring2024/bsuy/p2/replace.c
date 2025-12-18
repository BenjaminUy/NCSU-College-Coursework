/**
    @file replace.c
    @author Benjamin Uy (bsuy)
    Implementation file that for functions involved in inserting the replacement words
    to substitute the placeholders throughout the given storyline.
*/

#include "replace.h"

#include <string.h>
#include <stdlib.h>
#include <stdio.h>

/** Constant representing the number of angle bracket characters '<' and '>' that surround a placeholder */
#define BRACKET_CHARACTERS 2

/** Exit status used if the line expands to become longer than 100 characters */
#define ERROR_LINE_TOO_LONG 103

/** Exit status used when an invalid placeholder is encountered */
#define ERROR_INVALID_PLACEHOLDER 104

/**
    Function replaces a given placeholder in the line with the given replacement string.
    @param line string representing string for the story line
    @param word string representing string for the word that will replace instances of the placeholder
    @param placeholder string containing the placeholder which will be replaced in the text
    @return exit status 104 if a placeholder is found to be invalid -- not "noun1", "noun2", etc.
*/
void replaceWord( char line[ LINE_MAX + 1 ], char word[ FIELD_MAX + 1 ], char placeholder[] )
{
    // Read through the story line until a '<' char is found -- this is a potential placeholder
    for ( int i = 0; i < strlen( line ); i++ ) {
        if ( line[ i ] == '<' ) {
            char potentialPlaceholder[ LINE_MAX + 1 ];
            int q = 0;
            int k = i + 1;
            do {
                potentialPlaceholder[ q ] = line[ k ];
                q++;
                k++;
            } while ( line[ k ] != '>' );

            potentialPlaceholder[ q ] = '\0';

            // At this point, potentialPlaceholder should have the placeholder, not including the '<' or '>'
            
            // Now compare against all valid placeholders
            if ( ( strcmp( potentialPlaceholder, "noun1\0" ) != 0 ) && ( strcmp( potentialPlaceholder, "noun2\0" ) != 0 ) && 
                ( strcmp( potentialPlaceholder, "verb\0" ) != 0 ) && ( strcmp( potentialPlaceholder, "adjective\0" ) != 0 ) &&
                ( strcmp( potentialPlaceholder, "adverb\0" ) != 0 ) ) {
                    exit( ERROR_INVALID_PLACEHOLDER );
            }

            // Check that potentialPlaceholder matches with placeholder then,
            // compute length to prevent buffer overflow before replacement
            if ( strcmp( potentialPlaceholder, placeholder ) == 0 && computeLen( line, word, placeholder ) == true ) {
                
                if ( ( strlen( placeholder ) + BRACKET_CHARACTERS ) > strlen( word ) ) {
                    // case 1: placeholder is longer than replacement word

                    // Shift story line left to fill the unused placeholder
                    int shiftLeft = ( strlen( placeholder ) + BRACKET_CHARACTERS - strlen( word ) );

                    while ( shiftLeft > 0 ) {
                        for ( int s = i; s < strlen( line ); s++ ) {
                            line[ s ] = line[ s + 1 ]; 
                        }
                        shiftLeft--;
                    }
                } else if ( ( strlen( placeholder ) + BRACKET_CHARACTERS ) < strlen( word ) ) {
                    // case 2: placeholder is shorter than replacment word

                    // Shift story line right to make space for replacement word
                    int shiftRight = ( strlen( word ) - strlen( placeholder ) - BRACKET_CHARACTERS );
                    while ( shiftRight > 0 ) {
                        for ( int s = strlen( line ); s > i; s-- ) {
                            line[ s + 1 ] = line[ s ];
                        }
                        shiftRight--;
                    }
                } else {
                    // case 3: placeholder and replacement are equal lengths, no changes happen
                }

                // Sub in the replacement word after necessary shifting
                int k = i;
                for ( int g = 0; g < strlen( word ); g++ ) {
                    line[ k ] = word[ g ];
                    k++;
                }
            }
        } 
    }
}

/**
    Function computes the resulting length of the story line if the specifed placeholder
    is replaced by the replacement string.
    @param line string for the line of text
    @param word string for the word that will replace instances of the placeholder
    @param placeholder string containing the placeholder which will be replaced in the text
    @return true if the replacement strign can be inserted into the line without a buffer overflow occuring -- false otherwise.
    @return exit status 103 in the scenario that, had the replacement word substituted the placeholder in the storyline,
        the line would be over 100 characters long
*/
bool computeLen( char line[ LINE_MAX + 1 ], char word[ FIELD_MAX + 1 ], char placeholder[] )
{
    if ( ( strlen( line ) + ( strlen( word ) - strlen( placeholder ) - BRACKET_CHARACTERS ) ) > LINE_MAX ) {
        exit( ERROR_LINE_TOO_LONG );
    }
    return true;
}