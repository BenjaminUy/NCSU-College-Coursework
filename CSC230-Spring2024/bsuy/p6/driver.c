/**
    @file driver.c
    @author Benjamin Uy (bsuy)
    This file makes up the top-level, main component of the program. Other than containing the main function,
    this file uses the other components to read and process commands from standard input, updates the trie map, 
    and prints reponses to user commands.
*/

#include "input.h"
#include "value.h"
#include "map.h"

#include <stddef.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/** Constant for the character length of the largest valid command "remove" */
#define MAX_LENGTH_COMMAND 6

/**
    Starts the program by creating a new trie map. This function will continuously prompt the user to input commands which will change
    the structure of the map. The commands that can be input are "set", "size", "plus", "remove", "get", and "quit" -- the last one will
    close the program and free any dynamically allocated memory.
    @return integer representing the exit code; 0 if the program was quit succesfully without error.
*/
int main()
{
    char *line = NULL;
    Map *m = makeMap();
    bool quitProgram = false;
    do {
        printf( "cmd> " );

        line = readLine( stdin );

        if ( line != NULL ) {

            char first[ MAX_LENGTH_COMMAND + 1 ];  // Max valid length of the first part is "remove"
            char *second = ( char * ) malloc( strlen( line ) * sizeof( char ) ); 
            char *third = ( char * ) malloc( strlen( line ) * sizeof( char ) );   
            char extra[ 1 + 1 ];    // Need space for any non-whitespace character and the other for the terminating character
            int matches;

            printf( "%s\n", line );
            if ( ( matches = sscanf( line, "%6s", first ) ) != 1 ) {
                fprintf( stdout, "invalid\n" );
            } else if ( strcmp( first, "set" ) != 0 && strcmp( first, "get" ) != 0 && strcmp( first, "plus" ) != 0 &&
                        strcmp( first, "remove" ) != 0 && strcmp( first, "size" ) != 0 && strcmp( first, "quit" ) != 0 ) {
                fprintf( stdout, "invalid\n" );
            } else if ( strcmp( first, "quit" ) == 0 ) {                            // Quit command
                if ( sscanf( line, "%*s%1s", extra ) == 1 ) {
                    fprintf( stdout, "invalid\n" );
                } else {
                    quitProgram = true;
                }
            } else if ( strcmp( first, "set" ) == 0 ) {                             // Set command
                // Keeps track whether second contains invalid characters
                bool validSecond = true;    
                // Get the key
                if ( ( matches = sscanf( line, "%*s %s", second ) ) == 1 ) {                  
                    // First check that the characters in second are valid
                    for ( int i = 0; second[ i ]; i++ ) {
                        if ( second[ i ] < '!' || second[ i ] > '~' ) {
                            fprintf( stdout, "invalid\n" );
                            validSecond = false;
                        }
                    }
                } else {
                    fprintf( stdout, "invalid\n" );
                }
                if ( validSecond && matches == 1 ) {
                    // Get the potential value

                    if ( sscanf( line, "%*s%*s %[^\n]", third ) != 1 ) {                   
                        fprintf( stdout, "invalid\n" );
                    } else {
                        Value *v = NULL;
                        // Use our parse functions
                        v = parseInteger( third );                                          
                        if ( v == NULL ) {
                            v = parseDouble( third );
                            if ( v == NULL )
                                v = parseString( third );
                        }
                        // If the Value pointer is still null, then the value couldn't be processed
                        if ( v == NULL ) {                                                 
                            fprintf( stdout, "invalid\n" );                         
                        } else {
                            mapSet( m, second, v );
                        }
                    }                   
                }
            } else if ( strcmp( first, "get" ) == 0 ) {                             // Get command
                if ( sscanf( line, "%*s %[!-~]", second ) != 1 ) {
                    fprintf( stdout, "invalid\n" );
                } else if ( sscanf( line, "%*s%*s%1s", extra ) == 1 ) {
                    fprintf( stdout, "invalid\n" );
                } else {
                    Value *v = mapGet( m, second );
                    if ( v != NULL ) {
                        char *stringRep = ( v->toString )( v );
                        printf( "%s\n", stringRep );
                        free( stringRep );
                    } else {
                        fprintf( stdout, "invalid\n" );
                    } 
                }
            } else if ( strcmp( first, "plus" ) == 0 ) {                            // Plus command
                if ( sscanf( line, "%*s %[!-~]", second ) != 1 ) {               
                    fprintf( stdout, "invalid\n" );
                } else {
                    if ( sscanf( line, "%*s%*s %[^\n]", third ) != 1 ) {          
                        fprintf( stdout, "invalid\n" );
                    } else {
                        Value *add = NULL;
                        add = parseInteger( third );                               
                        if ( add == NULL ) {
                            add = parseDouble( third );
                            if ( add == NULL ) {
                                add = parseString( third );
                                if ( add == NULL ) {
                                    fprintf( stdout, "invalid\n" );
                                }
                            }
                        }
                        if ( add != NULL ) {
                            Value *original = NULL;
                            // Check that the given key exists in the map
                            if ( ( original = mapGet( m, second ) ) == NULL ) {
                                fprintf( stdout, "invalid\n" );
                            } else {
                                if ( !( original->plus )( original, add ) ) {
                                    // The values must've not been the same type
                                    fprintf( stdout, "invalid\n" );
                                }
                            }
                            ( add->destroy )( add );
                        }
                    }                   
                }
            } else if ( strcmp( first, "remove" ) == 0 ) {                          // Remove command
                if ( sscanf( line, "%*s %[!-~]", second ) != 1 ) {               
                    fprintf( stdout, "invalid\n" );
                } else {
                    if ( sscanf( line, "%*s%*s%1s", extra ) == 1 ) {
                        fprintf( stdout, "invalid\n" );
                    } else {
                        if ( !mapRemove( m, second ) ) {
                            fprintf( stdout, "invalid\n" );
                        }
                    }
                }
            } else if ( strcmp( first, "size" ) == 0 ) {                            // Size command
                if ( sscanf( line, "%*s%1s", extra ) == 1 ) {
                    fprintf( stdout, "invalid\n" );
                } else {
                    printf( "%d\n", mapSize( m ) );
                }
            }

            free( line );
            free( second );
            free( third );

            // Prevent from printing an extra new line after a quit command is processed
            if ( !quitProgram ) {
                printf( "\n" );
            }
        } else {
            quitProgram = true;
        }
    } while ( !quitProgram );

    // Free dynamically allocated memory from the map
    freeMap( m );
}