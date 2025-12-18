/**
    @file map.c
    @author Benjamin Uy (bsuy)
    This file of the map component provides implementation of a trie holding all the key/value pairs in a map.
    The map contains a pointer to a Node representing the root of the trie. This Node points to other Nodes, which
    are considered the former Node's children. Nodes also store values -- IntegerValues, DoubleValues, or StringValues.
*/

#include "map.h"
#include "value.h"

#include <stdlib.h>

/** Lowest-numbered symbol in a key. */
#define FIRST_SYM '!'

/** Number of possible symbols in a key. */
#define SYM_COUNT ( '~' - '!' + 1 )

/** Short name for the node used to build this tree. */
typedef struct NodeStruct Node;

/** Node in the trie data structure. */
struct NodeStruct {
    /** If the substring to the root of the tree up to this node is a
        key, this is the value that goes with it. */
    Value *val;
  
    /** Array of pointers to child nodes. */
    Node *child[ SYM_COUNT ];
};

/** Representation of a trie implementation of a map. */
struct MapStruct {
    /** Root node of this tree. */
    Node *root;

    // My added fields are below (bsuy)

    /** Keeps track of the number of key/value pairs in the map */
    int size;
};

/** Make an empty map.
    @return pointer to a new map representation.
*/
Map *makeMap() {
    Map *m = ( Map * ) malloc( sizeof( Map ) );
    m->root = NULL;
    m->size = 0;
    return m;
}

/** Return the size of the given map.
    @param m Pointer to the map.
    @return Number of key/value pairs in the map. 
*/
int mapSize( Map *m ) {
    return m->size;
}
  
/** Add a new key / value pair to the map, or replace the value
    associeted with the given key.  The map will take ownership of the
    given value object, but the key is still owned by the caller.
    @param m Map to add a key/value pair to.
    @param key Key to add to map.
    @param val Value to associate with the key.
*/
void mapSet( Map *m, char const *key, Value *val ) {
    // We need to traverse the Trie map and create uninitialized Nodes as we go
    Node *n;                            // Used to store newly created Nodes
    if ( m->root == NULL ) {
        n = ( Node * ) malloc( sizeof( Node ) );
        n->val = NULL;  
        for ( int i = 0; i < SYM_COUNT; i++ ) {
            n->child[ i ] = NULL;
        }
        m->root = n;
    }
    Node *traverse = m->root;           // Keep track where we are in the Trie
    for ( int i = 0; key[ i ]; i++ ) {
        char c = key[ i ];
        int childIndex = c - FIRST_SYM;
        if ( traverse->child[ childIndex ] == NULL ) {
            n = ( Node * ) malloc( sizeof( Node ) );
            n->val = NULL;
            for ( int i = 0; i < SYM_COUNT; i++ ) {
                n->child[ i ] = NULL;
            }
            traverse->child[ childIndex ] = n;
        }
        traverse = traverse->child[ childIndex ];
    }
    if ( traverse->val == NULL ) {
        m->size++;              // Increment size of the map only if the value was NULL
    } else {
        ( traverse->val->destroy )( traverse->val );
        traverse->val = NULL;   // Remove the previous value
    }
    traverse->val = val;        // Set the value of the current node to the given val
}

/** Return the value associated with the given key. The returned Value
    is still owned by the map. The caller can use it but shouldn't free it.
    @param m Map to query.
    @param key Key to look for in the map.
    @return Value associated with the given key, or NULL if the key isn't in the map.
*/
Value *mapGet( Map *m, char const *key ) {
    // Map is empty
    if ( m->root == NULL )
        return NULL;

    // Start from the root Node pointer and work down the Trie to find the Key
    Node *traverse = m->root;

    for ( int i = 0; key[ i ]; i++ ) {
        char c = key[ i ];

        // To find the location of the pointer in the child array, subtract the ASCII code 33 (representing '!')
        int childIndex = c - FIRST_SYM;

        if ( traverse->child[ childIndex ] == NULL ) {
            return NULL;                      
        }
        traverse = traverse->child[ childIndex ];
    }
    return traverse->val;
}

/** Remove a key / value pair from the given map.
    @param m Map to remove a key from
    @param key Key to look for and remove in the map.
    @return true if the key was successfully removed (i.e., it was in
    the map)
*/
bool mapRemove( Map *m, char const *key ) {
    // Check whether the key is already in the map
    if ( mapGet( m, key ) != NULL ) {

        // Make change to map to indicate the value is removed from the map
        Node *traverse = m->root;

        int childIndex;
        for ( int i = 0; key[ i ]; i++ ) {
            char c = key[ i ];
            // To find the location of the pointer in the child array, subtract the ASCII code 33 
            childIndex = c - FIRST_SYM;
            traverse = traverse->child[ childIndex ];
        }
        // Destroy the stored value in traverse 
        ( traverse->val->destroy )( traverse->val );

        traverse->val = NULL;
        m->size--;

        return true;
    } else {
        return false;
    }
}

/**
    Helper method used to remove the key/value pairs of the given Node pointer n stored in its child array.
    This method will be called recursively, should it find non-NULL pointers in subsequent child arrays.
    @param n Node pointer to free the key/value pairs below and from
*/
static void freePairsBelow( Node *n ) {
    // Iterate through the child array of pointers
    for ( int i = 0; i < SYM_COUNT; i++ ) {
        // If we find a pointer that is not null, there is a key/value pair to free resources from
        if ( n->child[ i ] != NULL ) {
            freePairsBelow( n->child[ i ] );
        }
    }
    // Free resources from the given Node's value and from the Node itself
    if ( n->val != NULL ) {
        ( ( n->val )->destroy )( n->val );
    }
    free( n );
}

/** Free all the memory used to store a map, including all the
    memory in its key/value pairs.
    @param m The map to free.
*/
void freeMap( Map *m ) {
    if ( m->root != NULL ) {
        // Call this recursive function to free all key/value pairs in the Map
        freePairsBelow( m->root );
    }
    free( m );
}
