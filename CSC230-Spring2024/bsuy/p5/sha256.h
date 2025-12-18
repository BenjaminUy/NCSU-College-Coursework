/**
    @file sha256.h
    @author Benjamin Uy (bsuy)
    Header file for the sha256 component. Contains prototypes of functions used in the sha256 algorithm.
*/

#ifndef SHA256_H
#define SHA256_H

#include "sha256constants.h"

/** Type used to represent a byte. */
typedef unsigned char byte;

/** Type used to represent a 64-bit value. */
typedef unsigned long word64;

/** Number of bits in a byte. */
#define BBITS 8

/** Size of an input block in bytes. */
#define BLOCK_SIZE 64

/** Size of the hash, in words. */
#define HASH_WORDS 8

/** State of the SHA256 algorithm, including bytes of input data
    waiting to be hashed. */
typedef struct {
  /** Input data not yet hashed. */
  byte pending[ BLOCK_SIZE ];

  /** Number of byes currently in the pending array. */
  int pcount;

  // Add any fields you need.

  /** Total number of bytes from processed input */
  int totalBytes;

  /** Current hash value. */
  word h[ HASH_WORDS ];
} SHAState;

/**
    This function rotates the given word right by the given number of bits and returns the result.
    This rotation is done so that the bits that are shifted right do not fall off; instead, they are
    put back on the left end
    @param val word to rotate
    @param bits number of bits to shift the word right
    @return resulting word after the rotation operation
*/
word rotate( word val, int bits );

/**
    This function takes a single word parameter, a, and performs a rotation by 2 bits, followed by
    the bitwise exclusive or operation with the result of a rotation of word a by 13 bits, followed by another
    bitwise exclusive or operation with the result of a rotation of word a by 22 bits
    @param a word to perform the described operations on
    @return resulting word after the described operations
*/
word Sigma0( word a );

/**
    This function takes a single word parameter, e, and performs a rotation by 6 bits, followed by
    a bitwise exclusive or operation with the result of the rotation of e by 11 bits, followed by another
    bitwise exclusive or operation with the result of rotation of e by 25 bits
    @param e word to perform the described operations on
    @return resulting word after the described operations
*/
word Sigma1( word e );

/**
    This function evaluates the expression with bitwise operators: (e and f) xOr (~e and g) from the given word parameters.
    @param word e first word parameter
    @param word f second word parameter
    @param word g third word parameter
    @return resulting word as the described operations
*/
word ChFunction( word e, word f, word g );

/**
    This function evaluates the expression with bitwise operators: (a and b) xOr (a and c) and (b and c) from the given words.
    @param word a first word parameter
    @param word b second word parameter
    @param word c third word parameter
    @return resulting word as the described operations
*/
word MaFunction( word a, word b, word c );

/**
    This function is called during compression where a 64-byte block of input is "extended" to 64 words, which are stored in an array.
    The first 16 elements of the word array contain the same data as the original 64 bytes in the pending array, with w[0] getting
    a copy of the first four bytes and so on. Subsequent array elements after index 15 are calculated by adding w[i-16] and w[i-7] and a
    value computed from w[i-15] and a value computed from w[i-2].
    @param pending full array of bytes from a SHAState that will be extended into words
    @param w array that will store the values of the words during message extension
*/
void extendMessage( byte const pending[ BLOCK_SIZE ], word w[ BLOCK_SIZE ] );

/**
    This function is responsible for updating the hash value based on the contents of the 64-byte block in the given SHAState.
    First, the 64-byte block is extended to 64 words. Next, the 8 words in the hash array h[] are copied to variables a, b, ..., h
    These variables undergo 64 rounds of bit manipulation, with each round updating the values of said variables.
    After the 64 rounds, the final variable values are added back into the hash values of the given state.
    @param state the SHAState to compress
*/
void compression( SHAState *state );

/**
    This function makes an instance of a SHAState and initializes its fields before returning
    a pointer to it. Notably, its pcount and totalBytes are initialized to 0 and its h word array
    is initialized the values stored in the initial_h array.
    @return pointer to the newly initialized SHAState
*/
SHAState *makeState();

/**
    This function frees memory space from the given SHAState pointer
    @param state pointer to a SHAState to free memory from
*/
void freeState( SHAState *state );

/**
    This function is given data as an array of bytes along with a pointer to a SHAState struct and an integer
    indicating how many bytes are in the array. This function will collect input data into 64-byte blocks and
    process each block via the compress function. If there are leftover bytes, meaning the pending array
    of the SHAState is not completely filled, additional padding will be added via the digest function.
    @param state SHAState containing the pending array which will store the input date bytes
    @param data input array of bytes that is being processed
    @param len number of bytes in the data array
*/
void update( SHAState *state, const byte data[], int len );

/**
    This function is called once after all data has been read from input and processed by the update function.
    The function adds padding to given SHAState's pending array and processes the last block or two of the input
    and copies the final hash value to the given hash array.
    @param state pointer to SHAState to add padding to the pending array
    @param hash array for storing the final hash value
*/
void digest( SHAState *state, word hash[ HASH_WORDS ] );

#endif
