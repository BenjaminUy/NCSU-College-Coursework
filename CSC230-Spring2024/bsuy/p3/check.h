/**
    @file check.h
    @author Benjamin Uy (bsuy)
    This header file provides prototype functions for performing four basic arithmetic
    operations on signed long values and automatically checks for cases of overflow
    or divide by zero.
*/

// The function prototypes need the bool type.
#include <stdbool.h>

/**
    This function adds the given two long values and stores them in a pointer to a long.
    @param result parameter that stores the numeric result of the addition operation
    @param a first operand
    @param b second operand
    @return true if there is no overflow and false otherwise
*/
bool add( long *result, long a, long b );

/**
    This function does subtraction with the given long values (a minus b) and stores them in a pointer to a long.
    @param result parameter that stores the numeric result of the subtraction operation
    @param a first operand
    @param b second operand 
    @return true if there is no overflow and false otherwise
*/
bool subtract( long *result, long a, long b );

/**
    This function does multiplication with the given long values and stores them in a pointer to a long.
    @param result parameter that stores the numeric result of the multiplication operation
    @param a first operand
    @param b second operand
    @return true if there is no overflow and false otherwise
*/
bool multiply( long *result, long a, long b );

/**
    This function does division with the given long values (a / b) and stores them in a pointer to a long.
    @param result parameter that stores the numeric result of the division operation
    @param a first operand
    @param b second operand
    @return true if there is no overflow and false otherwise
*/
bool divide( long *result, long a, long b );
