/**
    @file inventory.h
    @author Benjamin Uy (bsuy)
    This header file contains the prototype functions for making an Inventory struct which can store multiple Records read
    from an input file, along with sorting and listing Records in said Inventory
*/

#include <stdbool.h>

/** Capacity of the artist and title char arrays; these arrays can hold up to 30 characters */
#define ARTIST_TITLE_FIELD_CAPACITY 31

/** Capacity of the genre char arrays; these arrays can hold up to 12 non-whitespace characters */
#define GENRE_FIELD_CAPACITY 13

/**
    Struct representing a Record stored as a pointer in the list of an Inventory. A Record has five fields:
    a record id (an integer), artist, title, genre (these three are strings), and copies (integer)
*/
typedef struct RecordStruct {
        int id;
        char artist[ ARTIST_TITLE_FIELD_CAPACITY ];
        char title[ ARTIST_TITLE_FIELD_CAPACITY ];
        char genre[ GENRE_FIELD_CAPACITY ];
        int copies;
} Record;

/**
    Struct representing an Inventory which stores pointers to Records in a list. These Records are created when
    the vinyl program starts and processes the command-line arguments for record text files. Aside from the list
    storing pointers to Records, an Inventory has fields for count and capacity, which correspond to its list
*/
typedef struct InventoryStruct {
    Record **list;
    int count, capacity;
} Inventory;

/**
    This function dynamically allocates storage for an Inventory struct and initializes its fields
    @return pointer to the newly created Inventory
*/
Inventory *makeInventory();

/**
    This function frees the memory used to store the given Inventory, including the space for all the Records in the
    list field and the space of the Inventory struct itself
    @param inventory pointer to an Inventory struct to free memory from, including the space for all the Records in its list field
*/
void freeInventory( Inventory *inventory );

/**
    This function reads all records from the given record file name. It creates an instance of the
    Record struct for a record in the file and stores a pointer to that Record in the resizable array
    in the inventory or updates the number of copies of a record with the same id that is already in
    the inventory.
    @param filename name of the record file to process
    @param inventory pointer to an Inventory struct that will contain the records processed from the file
*/
void readRecords( char const *filename, Inventory *inventory );

/**
    This function sorts the records in the given Inventory using the qsort function and the provided compare function.
    @param inventory pointer to Inventory struct to sort its Records
    @param compare pointer to a function used as a comparison function parameter in qsort; the compare function takes two const void pointers
*/
void sortRecords( Inventory *inventory, int ( *compare ) ( void const *va, void const *vb ) );

/**
    This function is used to print all or some of the Records in the given Inventory depending on the given test function
    and string representing the command
    @param inventory pointer to Inventory struct to list Records from
    @param test pointer to a function used to determine whether or not a Record from inventory should be printed
    @param str string for the type of list command
*/
void listRecords( Inventory *inventory, bool ( *test ) ( Record const *record, char const *str ), char const *str );
