/**
    @file vinyl.c
    @author Benjamin Uy
    This program contains functions that allow the user to get information about vinyl records stored in a text file
    and to place one or more orders to purchase these records.
*/

#include "inventory.h"
#include "input.h"

#include <stdlib.h>
#include <string.h>

/** Initial capacity of the order */
#define INITIAL_ORDER_CAPACITY 5

/** Scale used when resizing current order's list of OrderItems; we double the previous array's capacity */
#define RESIZE_SCALE 2

/** Constant representing the max number of 'words' that can be a valid command (e.g. list genre command and add/remove command) */
#define MAX_ARGUMENT_LIMIT 3

/** Constant representing the max number of 'words' for the list order command and, for the add/remove commands, the required number of integers in the command */
#define LIST_ORDER_AND_REQUIRED_INTEGERS 2

/** Constant for the max character length of the first part of a valid command (such as "list" or "checkout"), including the null terminator */
#define MAX_FIRST_COMMAND_LENGTH 9

/** Constant for the max character length of the second part of a valid command ("artist"), including the null terminator */
#define MAX_SECOND_COMMAND_LENGTH 7

/** Constant for the max character length of the third part of a valid command -- based on the max length of the search parameter -- including the null terminator */
#define MAX_THIRD_COMMAND_LENGTH 16

/** Constant for the max character length for the genre part of a valid command, including the null terminator */
#define MAX_GENRE_LENGTH 13

/**
    This function compares two Records based on the list command (by id). It returns an integer representing
    how the first parameter pointer should be ordered relative to the second parameter pointer.
    @param va first pointer (to a Record) to compare
    @param vb second pointer (to a Record) to compare
    @return -1 if va should be ordered before vb, 1 if va should be ordered after, and 0 if both are considered equal
*/
static int compareID( void const *va, void const *vb )
{
    Record * const *a = va;
    Record * const *b = vb;

    // printf( "comparing: %d and %d\n", (*a)->id, (*b)->id );

    if ( ( *a )->id < ( *b )->id ) {
        return -1;
    } else if ( ( *a )->id > ( *b )->id ) {
        return 1;
    }
    return 0;
}

/** 
    This function compares two Records based on the list genre command (by artist, then title, then id). 
    It returns an integer representing how the first parameter pointer should be ordered relative to the second parameter pointer.
    @param va first pointer (to a Record) to compare
    @param vb second pointer (to a Record) to compare
    @return -1 if va should be ordered before vb, 1 if va should be ordered after, and 0 if both are considered equal
*/
static int compareGenre( void const *va, void const *vb )
{
    Record * const *a = va;
    Record * const *b = vb;

    // Compare by artist
    if ( strcmp( ( *a )->artist, ( *b ) ->artist ) < 0 ) {
        return -1;
    } else if ( strcmp( ( *a )->artist, ( *b ) ->artist ) > 0 ) {
        return 1;
    }

    // Compare by title
    if ( strcmp( ( *a )->title, ( *b )->title ) < 0 ) {
        return -1;
    } else if ( strcmp( ( *a )->title, ( *b )->title ) > 0 ) {
        return 1;
    }

    // Lastly compare by ID if no differences are found
    return compareID( va, vb );
}

/**
    Function used to determine if the given pointer to a Record should be printed based on the given string for genre
    @param record pointer to a Record to check
    @param str string for the genre to search for
    @return true if the given pointer's Record contains the same genre string--false otherwise
*/
static bool testGenre( Record const *record, char const *str )
{    
    if ( strcmp( record->genre, str ) == 0 ) {
        return true;
    } 
    return false;
}

/**
    Function determines if the given string is contained in the given pointer to a Record's artist field.
    Used to determine which Records are listed in a search artist command.
    @param record pointer to a Record to check
    @param str string for the artist to search for
    @return true if the given string is contained in the given pointer's Record's artist string--false otherwise
*/
static bool testSearchArtist( Record const *record, char const *str )
{
    if ( strstr( record->artist, str ) != NULL ) {
        return true;
    }
    return false;
}

/**
    Function determines if the given string is contained in the given pointer to a Record's title field.
    Used to determine which Records are listed in a search title command.
    @param record pointer to a Record to check
    @param str string for the title to search for
    @return true if the given string is contained in the given pointer's Record's title string--false otherwise
*/
static bool testSearchTitle( Record const *record, char const *str )
{
    if ( strstr( record->title, str ) != NULL ) {
        return true;
    }
    return false;
}

/**
    Helper function used to print the header line used in listing out the appropriate Records for a command
*/
void printHeaderLine()
{
    printf( "ID  Artist                         Title                          Genre        Copies\n" );
}

typedef struct OrderItemStruct {
    Record *rp;
    int copies;
} OrderItem;

typedef struct OrderStruct {
    OrderItem **list;
    int count, capacity;
} Order;

/**
    Helper function used to add a number of copies to an OrderItem in the Order list
    @param ord pointer to Order that the Record will be added to, if not already
    @param copies numbers of copies to add to the OrderItem
    @param rec pointer to Record to add
*/
void addToOrder( Order *ord, int copies, Record *rec )
{
    // Check whether the given Record pointer already exists in the Order list
    bool duplicateOrderItem = false;
    for ( int i = 0; i < ord->count; i++ ) {
        if ( ( ord->list[ i ] )->rp->id == rec->id ) {  // Get the given OrderItem's pointer to a Record
            duplicateOrderItem = true;                  // and compare the Records' ids (assuming uniqueness)
            // Just add copies to the order
            ord->list[ i ]->copies += copies;
        }
    }
    if ( !duplicateOrderItem ) {
        if ( ord->count >= ord->capacity ) {            // Resize the Order list if needed
            ord->capacity *= RESIZE_SCALE;
            OrderItem **newList = ( OrderItem ** ) malloc( ord->capacity * sizeof( OrderItem * ) );

            memcpy( newList, ord->list, ord->count * sizeof( OrderItem * ) );

            free( ord->list );
            ord->list = newList;

            for ( int i = ord->count; i < ord->capacity; i++ ) {
                ord->list[ i ] = ( OrderItem * ) malloc( sizeof( OrderItem ) );
            }
        }
        OrderItem *oip = ( OrderItem * ) malloc( sizeof( OrderItem ) );
        oip->rp = rec;
        oip->copies = copies;
        free( ord->list[ ord->count ] );
        ord->list[ ord->count ] = oip;
        ord->count++;
    }
}

/**
    Helper function used to remove a number of copies from an OrderItem in the Order list
    @param ord pointer to Order whose OrderItems copies will be decreased
    @param copies number of copies to remove from the OrderItem
    @param index integer for the index of the OrderItem in the Order list to remove copies from
*/
void removeFromOrder( Order *ord, int copies, int index ) {
    ord->list[ index ]->copies -= copies;

    // If all copies of the Record in the Order is 0, remove the Record from the Order
    if ( ord->list[ index ]->copies == 0 ) {

        // Shift the OrderItems down the Order list (toward index 0)
        for ( int i = index; i < ord->count; i++ ) {
            if ( i < ord->capacity - 1 ) {
                OrderItem *shiftedOrder = ( OrderItem * ) malloc( sizeof( OrderItem ) );
                memcpy( shiftedOrder, ord->list[ i + 1 ], sizeof( OrderItem ) );
                free( ord->list[ i ] );
                ord->list[ i ] = shiftedOrder; 
            } else {
                // If we're at the end bounds of the list, simply free the OrderItem
                free( ord->list[ i ] );
                ord->list[ i ] = ( OrderItem * ) malloc( sizeof( OrderItem ) );
            }
        }
        ord->count--;
    }
}

/**
    This function frees the memory used to store the given Order, including the space for all the OrderItems in the
    list field and the space of the Order struct itself
    @param Order pointer to an Order struct to free memory from, including the space for all the OrderItems in its list field
*/
void freeOrder( Order *ord ) {
    // Free space for all the OrderItems in the Order list
    for ( int i = 0; i < ord->capacity; i++ ) {
        free( ord->list[ i ] );
    }

    // Free space for the array of pointers (the list)
    free( ord->list );

    // Free space for the Order struct itself
    free( ord );
}

/**
    Start of the vinyl program. First processes the record files in the command line arguments before
    asking the user for a command. These commands will alter the type and amount of Records in inventory
    and the user's purchasing order.
    @param argc number of arguments in the command line
    @param argv array of pointers to chars storing the command line arguments 
    @return integer for the exit code.
*/
int main( int argc, char *argv[] )
{
    if ( argc == 1 ) {
        fprintf( stderr, "usage: vinyl record-file+\n" );
        exit( EXIT_FAILURE );
    }

    Inventory *ivt = makeInventory();
    for ( int i = 1; i < argc; i++ ) {
        readRecords( argv[ i ], ivt );
    }
    
    Order* ord = ( Order * ) malloc( sizeof( Order ) );
    ord->list = ( OrderItem** ) malloc( INITIAL_ORDER_CAPACITY * sizeof( OrderItem * ) );
    ord->count = 0;
    ord->capacity = INITIAL_ORDER_CAPACITY;

    for ( int i = 0; i < INITIAL_ORDER_CAPACITY; i++ ) {
        ord->list[ i ] = ( OrderItem * ) malloc( sizeof( OrderItem ) );
    }

    char *buffer = NULL;
    int matches;

    bool quitProgram = false;

    do {
        printf( "cmd> " );

        char first[ MAX_FIRST_COMMAND_LENGTH ];     // Max length is 8 ("checkout")
        char second[ MAX_SECOND_COMMAND_LENGTH ];   // Max length is 6 ("artist" for the search command ) 
        char third[ MAX_THIRD_COMMAND_LENGTH ];     // Max length for the search command is up to 15 non-whitespace chars
        char genre[ MAX_GENRE_LENGTH ];             // Used for the list genre command, max length is up to 12 non-whitespace chars
        int rid;                                    // Used for the add/remove commands, represents record id
        int rcount;                                 // Used for the add/remove commands, represents number of copies to add/remove from order
        char fourth;                                // Used to detect if there are too many arguments in the user command

        buffer = readLine( stdin );

        if ( buffer != NULL ) {
            matches = sscanf( buffer, "%8s%6s%15s%c", first, second, third, &fourth );

            // There should be between 1 and 3 'arguments' in the input line
            // The first arg can only be "list", "add", "remove", "checkout", or "quit"
            if ( matches == 0 || matches > MAX_ARGUMENT_LIMIT ) {
                printf( "%s\n", buffer );
                fprintf( stdout, "Invalid command\n" );
            } else if ( strcmp( first, "list" ) != 0 && strcmp( first, "add" ) != 0 && strcmp( first, "remove" ) != 0 && strcmp( first, "checkout" ) != 0 && 
                    strcmp( first, "quit" ) != 0 && strcmp( first, "search" ) != 0 ) {
                printf( "%s\n", buffer );
                fprintf( stdout, "Invalid command\n" );
            } else if ( matches == 1 && strcmp( first, "list" ) == 0 ) {                                                                                                        // List command
                printf( "%s\n", buffer );
                printHeaderLine();
                sortRecords( ivt, compareID );
                listRecords( ivt, NULL, NULL );
            } else if ( matches == MAX_ARGUMENT_LIMIT && strcmp( first, "search" ) == 0 ) {
                if ( strcmp( second, "artist" ) == 0 ) {
                    printf( "%s\n", buffer );
                    printHeaderLine();
                    sortRecords( ivt, compareID );
                    listRecords( ivt, testSearchArtist, ( const char * ) third );
                } else if ( strcmp( second, "title" ) == 0 ) {
                    printf( "%s\n", buffer );
                    printHeaderLine();
                    sortRecords( ivt, compareID );
                    listRecords( ivt, testSearchTitle, ( const char * ) third );
                } else {
                    printf( "%s\n", buffer );
                    fprintf( stdout, "Invalid command\n" );
                }
            } else if ( matches == MAX_ARGUMENT_LIMIT && strcmp( first, "list" ) == 0 && strcmp( second, "genre" ) == 0 && sscanf( buffer, "%*s%*s%12s", genre ) == 1 ) {       // List genre
                printf( "%s\n", buffer );
                printHeaderLine();
                sortRecords( ivt, compareGenre );
                listRecords( ivt, testGenre, ( const char * ) &genre );
            } else if ( matches == MAX_ARGUMENT_LIMIT && strcmp( first, "add" ) == 0 && sscanf( buffer, "%*s%d%d", &rid, &rcount ) == LIST_ORDER_AND_REQUIRED_INTEGERS ) {      // Add ### #
                if ( rid < 0 || rcount < 0 ) {
                    printf( "%s\n", buffer );
                    fprintf( stdout, "Invalid command\n" );
                } else {
                    // Check if the record is contained in the Inventory and see if there are enough copies for purchase
                    bool recordFound = false;
                    for ( int i = 0; i < ivt->count; i++ ) {
                        if ( rid == ( ivt->list[ i ] )->id ) {
                            recordFound = true;

                            int orderCopies = 0;
                            for ( int i = 0; i < ord->count; i++ ) {                           // Check number of copies in the current Order
                                if ( rid == ord->list[ i ]->rp->id ) {
                                    orderCopies = ord->list[ i ]->copies;
                                }
                            }

                            if ( ( ( ivt->list[ i ] )->copies - orderCopies ) < rcount ) {     // Total number of copies should not exceed the original number
                                printf( "%s\n", buffer );
                                fprintf( stdout, "Invalid command\n" );
                            } else {
                                printf( "%s\n", buffer );
                                addToOrder( ord, rcount, ivt->list[ i ] );                     // Add the record to the current order
                            }
                        }
                    }
                    if ( !recordFound ) {                                                      // If the record id is not found, the command is invalid
                        printf( "%s\n", buffer );
                        fprintf( stdout, "Invalid command\n" );
                    }
                }
            } else if ( matches == MAX_ARGUMENT_LIMIT && strcmp( first, "remove" ) == 0 && sscanf( buffer, "%*s%d%d", &rid, &rcount ) == LIST_ORDER_AND_REQUIRED_INTEGERS ) {   // Remove ### #
                if ( rid < 0 || rcount < 0 ) {
                    printf( "%s\n", buffer );
                    fprintf( stdout, "Invalid command\n" );
                } else {
                    // Check if the record id and copies are contained in an OrderItem of the Order list
                    bool orderItemFound = false;
                    for ( int i = 0; i < ord->count; i++ ) {
                        if ( rid == ( ord->list[ i ]->rp->id ) ) {
                            orderItemFound = true;
                            if ( ( ord->list[ i ]->copies < rcount ) ) {
                                fprintf( stdout, "Invalid command\n" );
                            } else {
                                printf( "%s\n", buffer );
                                removeFromOrder( ord, rcount, i );
                            }
                        }
                    }
                    if ( !orderItemFound ) {
                        printf( "%s\n", buffer );
                        fprintf( stdout, "Invalid command\n" );
                    }
                }
            } else if ( matches == LIST_ORDER_AND_REQUIRED_INTEGERS && strcmp( first, "list" ) == 0 && strcmp( second, "order" ) == 0 ) {                                       // List order
                printf( "%s\n", buffer );
                printHeaderLine();
                for ( int i = 0; i < ord->count; i++ ) {
                    Record *r = ord->list[ i ]->rp;
                    printf( "%-3d %-30s %-30s %-12s %6d\n", r->id, r->artist, r->title, r->genre, ord->list[ i ]->copies );
                }
            } else if ( matches == 1 && strcmp( first, "checkout" ) == 0 ) {                                                    // Checkout
                printf( "%s\n", buffer );

                // Clear the current order by subtracting the number of copies from the Record pointers
                // Then free memory from all current the OrderItems in the Order
                for ( int i = 0; i < ord->count; i++ ) {
                    ord->list[ i ]->rp->copies -= ord->list[ i ]->copies;
                    free( ord->list[ i ] );
                    ord->list[ i ] = ( OrderItem * ) malloc( sizeof( OrderItem ) );
                }
                ord->count = 0;
            } else if ( matches == 1 && strcmp( first, "quit" ) == 0 ) {                                                        // Quit
                printf( "%s", buffer );
                quitProgram = true;
            } else {
                printf( "%s\n", buffer );
                fprintf( stdout, "Invalid command\n" );
            }
            printf( "\n" );
            free( buffer );
        } else {
            quitProgram = true;
        }
    } while ( !quitProgram );
    freeOrder( ord );
    freeInventory( ivt );
}
