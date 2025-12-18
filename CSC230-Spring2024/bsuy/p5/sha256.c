/**
    @file sha256.c
    @author Benjamin Uy (bsuy)
    This implementation file of the sha256 component contains various functions used in the sha256 algorithm.
    These functions include rotate, Sigma0, Sigma1, ChFunction, MaFunction, extendMessage, compression, and digest.
*/

#include "sha256.h"

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

/** Constant for number of bits for a word -- a unsigned int */
#define TOTAL_BITS_WORD 32

/** Value for the index that we fill the word array to in the extendMessage function from the original 64 bytes */
#define INITIAL_EXTENSION_INDEX 16

/** Value used as a mask in the rotate function; during rotation, the bits are shifted right, so we need to mask off the leftmost bit */
#define ROTATE_MASK 0x7FFFFFFF

/** Hexadecimal value for the byte that is added after the end of the original input in the pending array */
#define DIGEST_END_OF_INPUT 0x80

/** Value for the number of bytes that are reserved for storing the original length of input in bits; used in the digest function */
#define DIGEST_ORIGINAL_LENGTH 8

/** Hexadecimal value used to mask off the bits that store the original length of input; the remaining bits are stored as a byte */
#define DIGEST_ORG_LEN_MASK 0x00000000000000FF

/**
    This function rotates the given word right by the given number of bits and returns the result.
    This rotation is done so that the bits that are shifted right do not fall off; instead, they are
    put back on the left end.
    @param val word to rotate
    @param bits number of bits to shift the word right
    @return resulting word after the rotation operation
*/
word rotate( word val, int bits )
{
    word placeholder = val;

    for ( int i = 0; i < bits; i++ ) {
        // Grab the rightmost bit from val and store in placeholder
        placeholder &= 0x00000001;

        placeholder = placeholder << ( TOTAL_BITS_WORD - 1 ); // Shift so that this bit will be at left end of val

        val = ( val >> 1 ) & ROTATE_MASK;        // Bitwise shift right once, then retain the original bits from val but the leftmost bit
        val |= placeholder;                     // Then insert the new bit to the end
        placeholder = val;
    }
    return val;     // val should contain the rotated word
}

/**
    This function takes a single word parameter, a, and performs a rotation by 2 bits, followed by
    the bitwise exclusive or operation with the result of a rotation of word a by 13 bits, followed by another
    bitwise exclusive or operation with the result of a rotation of word a by 22 bits
    @param a word to perform the described operations on
    @return resulting word after the described operations
*/
word Sigma0( word a )
{
    return ( rotate( a, 2 ) ^ rotate( a, 13 ) ^ rotate( a, 22 ) );
}

/**
    This function takes a single word parameter, e, and performs a rotation by 6 bits, followed by
    a bitwise exclusive or operation with the result of the rotation of e by 11 bits, followed by another
    bitwise exclusive or operation with the result of rotation of e by 25 bits
    @param e word to perform the described operations on
    @return resulting word after the described operations
*/
word Sigma1( word e )
{
    return ( rotate( e, 6 ) ^ rotate( e, 11 ) ^ rotate( e, 25 ) );
}

/**
    This function evaluates the expression with bitwise operators: (e and f) xOr (~e and g) from the given word parameters.
    @param word e first word parameter
    @param word f second word parameter
    @param word g third word parameter
    @return resulting word as the described operations
*/
word ChFunction( word e, word f, word g )
{
    return ( ( e & f ) ^ ( ~e & g ) );
}

/**
    This function evaluates the expression with bitwise operators: (a and b) xOr (a and c) and (b and c) from the given words.
    @param word a first word parameter
    @param word b second word parameter
    @param word c third word parameter
    @return resulting word as the described operations
*/
word MaFunction( word a, word b, word c )
{
    return ( a & b ) ^ ( a & c ) ^ ( b & c );
}

/**
    This function is called during compression where a 64-byte block of input is "extended" to 64 words, which are stored in an array.
    The first 16 elements of the word array contain the same data as the original 64 bytes in the pending array, with w[0] getting
    a copy of the first four bytes and so on. Subsequent array elements after index 15 are calculated by adding w[i-16] and w[i-7] and a
    value computed from w[i-15] and a value computed from w[i-2].
    @param pending full array of bytes from a SHAState that will be extended into words
    @param w array that will store the values of the words during message extension
*/
void extendMessage( byte const pending[ BLOCK_SIZE ], word w[ BLOCK_SIZE ] )
{
    int bytesContained = 0;
    for ( int i = 0; i < INITIAL_EXTENSION_INDEX; i++ ) {
        w[ i ] = pending[ bytesContained++ ];
        w[ i ] = w[ i ] << ( BBITS );               // Shifting the bits so that they end up in MSB order (left-most = most significant)

        w[ i ] |= pending[ bytesContained++ ];
        w[ i ] = w[ i ] << ( BBITS );

        w[ i ] |= pending[ bytesContained++ ];
        w[ i ] = w[ i ] << ( BBITS );

        w[ i ] |= pending[ bytesContained++ ];
    }

    for ( int i = INITIAL_EXTENSION_INDEX; i < BLOCK_SIZE; i++ ) {
        word a = rotate( w[ i - 15 ], 7 ) ^ rotate( w[ i - 15 ], 18 ) ^ ( w[ i - 15 ] >> 3 );   // Manipulate w[i-15]
        word b = rotate( w[ i - 2 ], 17 ) ^ rotate( w[ i - 2 ], 19 ) ^ ( w[ i - 2 ] >> 10 );   // Manipulate w[i-2] 

        w[ i ] = w[ i - 16 ] + w[ i - 7 ] + ( a + b );  // Add the results together, ignoring any overflow
    }
}

/**
    This function is responsible for updating the hash value based on the contents of the 64-byte block in the given SHAState.
    First, the 64-byte block is extended to 64 words. Next, the 8 words in the hash array h[] are copied to variables a, b, ..., h
    These variables undergo 64 rounds of bit manipulation, with each round updating the values of said variables.
    After the 64 rounds, the final variable values are added back into the hash values of the given state.
    @param state the SHAState to compress
*/
void compression( SHAState *state )
{
    word words[ BLOCK_SIZE ];
    extendMessage( state->pending, words );

    // Set our word variables to copies of the values stored in the SHAState's h[] array
    int index = 0;
    word a = state->h[ index++ ];
    word b = state->h[ index++ ];
    word c = state->h[ index++ ];
    word d = state->h[ index++ ];
    word e = state->h[ index++ ];
    word f = state->h[ index++ ];
    word g = state->h[ index++ ];
    word h = state->h[ index ];

    // 64 rounds of bit manipulation
    for ( int i = 0; i < BLOCK_SIZE; i++ ) {

        // Holds the initial calculations for word A
        word placeholder1 = ( words[ i ] + constant_k[ i ] ) + h + ChFunction( e, f, g ) + Sigma1( e ) ;
        word placeholder2 = d + placeholder1;
        placeholder1 = placeholder1 + MaFunction( a, b, c ) + Sigma0( a );

        h = g;
        g = f;
        f = e;
        e = placeholder2;
        d = c;
        c = b;
        b = a;
        a = placeholder1;
    }

    // After all 64 rounds, the final values of A, B, ... H are added back into
    // the hash values in the state by 32-bit unsigned addition
    index = 0;
    state->h[ index++ ] += a;
    state->h[ index++ ] += b;
    state->h[ index++ ] += c;
    state->h[ index++ ] += d;
    state->h[ index++ ] += e;
    state->h[ index++ ] += f;
    state->h[ index++ ] += g;
    state->h[ index ] += h;

    // Reset the number of bytes contained in the pending array
    state->pcount = 0;
}

/**
    This function makes an instance of a SHAState and initializes its fields before returning
    a pointer to it. Notably, its pcount and totalBytes are initialized to 0 and its h word array
    is initialized the values stored in the initial_h array.
    @return pointer to the newly initialized SHAState
*/
SHAState *makeState()
{
    SHAState *state = malloc( sizeof( SHAState ) );
    state->pcount = 0;
    state->totalBytes = 0;

    // Initialize the word array to match the initial_h array
    for ( int i = 0; i < HASH_WORDS; i++ ) {
        ( state->h )[ i ] = initial_h[ i ];
    }
    return state;
}

/**
    This function frees memory space from the given SHAState pointer
    @param state pointer to a SHAState to free memory from
*/
void freeState( SHAState *state )
{
    free( state );
}

/**
    This function is given data as an array of bytes along with a pointer to a SHAState struct and an integer
    indicating how many bytes are in the array. This function will collect input data into 64-byte blocks and
    process each block via the compress function. If there are leftover bytes, meaning the pending array
    of the SHAState is not completely filled, additional padding will be added via the digest function.
    @param state SHAState containing the pending array which will store the input date bytes
    @param data input array of bytes that is being processed
    @param len number of bytes in the data array
*/
void update( SHAState *state, const byte data[], int len )
{
    // Keep track of how many bytes are left in the data array
    int bytesRemaining = len;

    // Keep track of which bytes we've collected from data
    int counter = 0;

    // Keep storing the data into 64-byte blocks until there are leftover bytes
    // Process the full 64-byte blocks with the compression method
    while ( bytesRemaining >= BLOCK_SIZE ) {
        for ( int i = 0; i < BLOCK_SIZE; i++ ) {
            state->pending[ i ] = data[ counter++ ];
            state->pcount++;
            state->totalBytes++;
            bytesRemaining--;
        }
        compression( state );
        assert( state->pcount == 0 );
    }

    // Store the leftover bytes in pending to be processed later
    for ( int i = 0; i < bytesRemaining; i++ ) {
        state->pending[ i ] = data[ state->pcount ];
        state->pcount++;
        state->totalBytes++;
    }
}

/**
    This function is called once after all data has been read from input and processed by the update function.
    The function adds padding to given SHAState's pending array and processes the last block or two of the input
    and copies the final hash value to the given hash array.
    @param state pointer to SHAState to add padding to the pending array
    @param hash array for storing the final hash value
*/
void digest( SHAState *state, word hash[ HASH_WORDS ] )
{
    word64 totalBits = state->totalBytes * BBITS;              // Get total number of bits 

    // First add the 0x80 byte to the end of input
    state->pending[ state->pcount ] = DIGEST_END_OF_INPUT;
    state->pcount++;

    if ( state->pcount > BLOCK_SIZE - DIGEST_ORIGINAL_LENGTH ) {        // Case where adding 0x80 causes the pending array to not have space for the required last 8 bytes
        while ( state->pcount != BLOCK_SIZE ) {                         // Fill the pending array with 0x00 bytes, then compress it
            state->pending[ state->pcount ] = 0x00;
            state->pcount++;
        }
        compression( state );
    }
    while ( state->pcount != BLOCK_SIZE - DIGEST_ORIGINAL_LENGTH ) {    // Default case of adding 0x00 bytes until the pending array is 8 bytes short of 64
        state->pending[ state->pcount ] = 0x00;
        state->pcount++;
    }

    // At this point, we add the remaining 8 bytes that indicate the original input length in MSB order
    word64 placeholder;
    for ( int i = 0; i < DIGEST_ORIGINAL_LENGTH; i++ ) {
        placeholder = totalBits & DIGEST_ORG_LEN_MASK;
        state->pending[ BLOCK_SIZE - 1 - i ] = placeholder;
        state->pcount++;
        totalBits = totalBits >> BBITS;     
    }

    compression( state );

    // Copy the final hash value to the given hash array
    for ( int i = 0; i < HASH_WORDS; i++ ) {
        hash[ i ] = state->h[ i ];
    }
}
