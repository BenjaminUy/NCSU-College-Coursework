/**
    @file inventory.c
    @author Benjamin Uy (bsuy)
    This file contains functionality for making an Inventory struct which can store multiple Records read
    from an input file. The file also contains behaviors for sorting and listing these Records.
*/

#include "inventory.h"
#include "input.h"

#include <stdlib.h>
#include <stdio.h>
#include <stddef.h>
#include <string.h>

/** Initial capacity of the inventory */
#define INITIAL_INVENTORY_CAPACITY 5

/** Scale used when resizing the char array in readLine; we double the previous array's capacity */
#define RESIZE_SCALE 2

/** Constant for the required number of parameters provided in the first line containing record id, genre, and copies */
#define REQUIRED_FIELDS_FIRST_LINE 3

/**
    This function dynamically allocates storage for an Inventory struct and initializes its fields
    @return pointer to the newly created Inventory
*/
Inventory *makeInventory()
{
    Inventory* ip = ( Inventory * ) malloc( sizeof( Inventory ) );
    ip->list = ( Record ** ) malloc( INITIAL_INVENTORY_CAPACITY * sizeof( Record * ) );
    ip->count = 0;
    ip->capacity = INITIAL_INVENTORY_CAPACITY;

    for ( int i = 0; i < INITIAL_INVENTORY_CAPACITY; i++ ) {
        ip->list[ i ] = ( Record * ) malloc( sizeof( Record ) );
    }

    return ip;
}

/**
    This function frees the memory used to store the given Inventory, including the space for all the Records in the
    list field and the space of the Inventory struct itself
    @param inventory pointer to an Inventory struct to free memory from, including the space for all the Records in its list field
*/
void freeInventory( Inventory *inventory )
{
    // Free space for all the Records in the list of the Inventory

    for ( int i = 0; i < inventory->capacity; i++ ) {
        free( inventory->list[ i ] );
    }
    // Free the array of pointers
    free( inventory->list );

    // Free space for the Inventory struct itself
    free( inventory );
}

/**
    Helper function used to print a message to standard error whenever a record file is
    invalid. Causes the program to exit with a status of 1 and frees the given Inventory struct from memory
    @param filename name of the Record file that is invalid
    @param inventory pointer to an Inventory struct; this will be freed in memory
    @param stream pointer to filestream to close
*/
static void invalidRecordMessage( char const *filename, Inventory *inventory, FILE *stream )
{
    freeInventory( inventory );
    fprintf( stderr, "Invalid record file: %s\n", filename );
    fclose( stream );
    exit( EXIT_FAILURE );
}

/**
    This function reads all records from the given record file name. It creates an instance of the
    Record struct for a record in the file and stores a pointer to that Record in the resizable array
    in the inventory or updates the number of copies of a record with the same id that is already in
    the inventory.
    @param filename name of the record file to process
    @param inventory pointer to an Inventory struct that will contain the records processed from the file
*/
void readRecords( char const *filename, Inventory *inventory )
{
    FILE *stream = fopen( filename, "r" );
    if ( stream == NULL ) {
        fprintf( stderr, "Can't open file: %s\n", filename );
        freeInventory( inventory );
        exit( EXIT_FAILURE );
    }

    char *line = NULL;
    // Read all records from the record file with the given name
    do {
        // Process the first line
        line = readLine( stream );
        if ( line != NULL ) {
            int recordId;
            char genre[ GENRE_FIELD_CAPACITY ];
            int copies;
            int matches = sscanf( line, "%d%13s%d", &recordId, genre, &copies );
            free( line );
            if ( matches != REQUIRED_FIELDS_FIRST_LINE ) { 
                // At least one of the fields were not initialized
                invalidRecordMessage( filename, inventory, stream );
            }   
            if ( recordId < 0 || copies < 0 ) {
                invalidRecordMessage( filename, inventory, stream );
            }

            // Process the second line
            line = readLine( stream );
            char artist[ ARTIST_TITLE_FIELD_CAPACITY ];
            strncpy( artist, line, sizeof( artist ) - 1 );
            artist[ sizeof( artist ) - 1 ] = '\0';
            if ( strcmp( artist, line ) != 0 ) {                // The line for artist did not fit into the char array
                free( line );
                invalidRecordMessage( filename, inventory, stream );
            }
            free( line );


            // Process the third line
            line = readLine( stream );
            char title[ ARTIST_TITLE_FIELD_CAPACITY ];
            strncpy( title, line, sizeof( title ) - 1 );
            title[ sizeof( title ) - 1 ] = '\0';
            if ( strcmp( title, line ) != 0 ) {                // The line for title did not fit into the char array
                free( line );
                invalidRecordMessage( filename, inventory, stream );
            }
            free( line );

            // At this point, all fields -- record id, genre, copies, artist, and title -- have been initialized

            // Check current inventory and check for duplicate record ids
            bool duplicateRecord = false;
            for ( int i = 0; i < inventory->count; i++ ) {
                if ( recordId == inventory->list[ i ]->id ) {
                    duplicateRecord = true;
                    Record *r = inventory->list[ i ];
                    // Check that matching record id has the same exact genre, artist, and title
                    if ( strcmp( genre, r->genre ) != 0 || strcmp( artist, r->artist ) != 0 || strcmp( title, r->title ) != 0 ) {
                        invalidRecordMessage( filename, inventory, stream );
                    }
                    // Since we've found a matching record id, add its copies to the preexisting record
                    inventory->list[ i ]->copies += copies;
                } 
            }

            // No duplicate Record found, so add it to the Inventory list
            if ( !duplicateRecord ) {
                if ( inventory->count >= inventory->capacity ) {        // Resize the inventory list if needed
                    inventory->capacity *= RESIZE_SCALE;
                    Record **newList = ( Record ** ) malloc( inventory->capacity * sizeof( Record * ) );
                    memcpy( newList, inventory->list, inventory->count * sizeof( Record * ) );
                    free( inventory->list );
                    for ( int i = inventory->count; i < inventory->capacity; i++ ) {
                        newList[ i ] = ( Record * ) malloc( sizeof( Record ) );
                    }
                    inventory->list = newList;
                }
                Record *rp = ( Record * ) malloc( sizeof( Record ) );
                rp->id = recordId;
                strcpy( rp->artist, artist );
                strcpy( rp->title, title );
                strcpy( rp->genre, genre );
                rp->copies = copies;

                free( inventory->list[ inventory->count ] );
                inventory->list[ inventory->count ] = rp;

                inventory->count++;
            }
        }
    } while ( line != NULL );

    // Close the stream after use
    fclose( stream );
}

/**
    This function sorts the records in the given Inventory using the qsort function and the provided compare function.
    @param inventory pointer to Inventory struct to sort its Records
    @param compare pointer to a function used as a comparison function parameter in qsort; the compare function takes two const void pointers
*/
void sortRecords( Inventory *inventory, int ( *compare ) ( void const *va, void const *vb ) )
{
    qsort( inventory->list, inventory->count, sizeof( inventory->list[ 0 ] ), compare );
}

/**
    This function is used to print all or some of the Records in the given Inventory depending on the given test function
    and string representing the command
    @param inventory pointer to Inventory struct to list Records from
    @param test pointer to a function used to determine whether or not a Record from inventory should be printed
    @param str string for the type of list command
*/
void listRecords( Inventory *inventory, bool ( *test ) ( Record const *record, char const *str ), char const *str )
{
    for ( int i = 0; i < inventory->count; i++ ) {
        if ( test == NULL || test( inventory->list[ i ], str ) ) {
            Record *r = inventory->list[ i ];
            printf( "%-3d %-30s %-30s %-12s %6d\n", r->id, r->artist, r->title, r->genre, r->copies );
        }
    }
}
