/**
    @file replace.h
    @author Benjamin Uy (bsuy)
    Header file for the replace component which is responsible for substituting placeholders
    in the story line with the words read from standard input
*/

#include <stdbool.h>

/** Maximum length for replacement string. */
#define FIELD_MAX 24

/** Maximum length for line of text. */
#define LINE_MAX 100

/**
    Function replaces a given placeholder in the line with the given replacement string.
    @param line string representing string for the story line
    @param word string representing string for the word that will replace instances of the placeholder
    @param placeholder string containing the placeholder which will be replaced in the text
*/
void replaceWord( char line[ LINE_MAX + 1 ], char word[ FIELD_MAX + 1 ], char placeholder[] );

/**
    Function computes the resulting length of the story line if the specifed placeholder
    is replaced by the replacement string.
    @param line string for the line of text
    @param word string for the word that will replace instances of the placeholder
    @param placeholder string containing the placeholder which will be replaced in the text
    @return true if the replacement strign can be inserted into the line without a buffer overflow occuring -- false otherwise.
*/
bool computeLen( char line[ LINE_MAX + 1 ], char word[ FIELD_MAX + 1 ], char placeholder[] );