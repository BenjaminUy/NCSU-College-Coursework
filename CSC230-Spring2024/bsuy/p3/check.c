/**
    @file check.c
    @author Benjamin Uy (bsuy)
    This implementation file provides functions for performing four basic arithmetic
    operations on signed long values and automatically checks for cases of overflow
    or divide by zero.
*/

#include "check.h"
#include <limits.h>

#include <stdio.h>

/**
    This function adds the given two long values and stores them in a pointer to a long.
    @param result parameter that stores the numeric result of the addition operation
    @param a first operand
    @param b second operand
    @return true if there is no overflow and false otherwise
*/
bool add( long *result, long a, long b ) 
{
    long placeholder = a + b;
    if ( a > 0 && b > 0 && placeholder < 0 ) {          // Sum of two (+) numbers should be (+)
        return false;
    } else if ( a < 0 && b < 0 && placeholder > 0 ) {   // Sum of two (-) numbers should be (-)
        return false;
    }
    // If no overflow, store the sum in result and return true
    *result = placeholder;
    return true;
}

/**
    This function does subtraction with the given long values (a minus b) and stores them in a pointer to a long.
    @param result parameter that stores the numeric result of the subtraction operation
    @param a first operand
    @param b second operand 
    @return true if there is no overflow and false otherwise
*/
bool subtract( long *result, long a, long b )
{
    long placeholder = a - b;

    if ( a < 0 && b > 0 && placeholder > 0 ) {             // Negative minus a positive should be negative
        return false;
    } else if ( a > 0 && b < 0 && placeholder < 0 ) {      // Pos minus neg should be pos if |a| > |b|
        return false;
    }

    // If no overflow, store the difference and return true
    *result = placeholder;
    return true;
}

/**
    This function does multiplication with the given long values and stores them in a pointer to a long.
    @param result parameter that stores the numeric result of the multiplication operation
    @param a first operand
    @param b second operand
    @return true if there is no overflow and false otherwise
*/
bool multiply( long *result, long a, long b )
{
    if ( a > 0 && b < 0 ) {
        // Largest negative value such that x * b is greater than or equal to LONG_MIN
        // Used to determine if overflow would occur
        long largestNegValue = LONG_MIN / a;
        if ( b < largestNegValue ) {
            return false;
        }
    } else if ( a < 0 && b > 0 ) {
        long largestNegValue = LONG_MIN / b;
        if ( a < largestNegValue ) {
            return false;
        }
    } else if ( ( a > 0 && b > 0 ) ) {
        // Largest positive value such that x * b is less than or equal to LONG_MAX
        // Used to determine if overflow would occur
        long largestPosValue = LONG_MAX / b;

        if ( a > largestPosValue ) {          // Overflow: a * b would be too large
            return false;
        }
    } else if ( a < 0 && b < 0 ) {
        long largestNegValue = LONG_MAX / b;
        if ( a < largestNegValue ) {
            return false;
        }
    }

    // If no overflow, store the product and return true
    *result = a * b;
    return true;
}

/**
    This function does division with the given long values (a / b) and stores them in a pointer to a long.
    @param result parameter that stores the numeric result of the division operation
    @param a first operand
    @param b second operand
    @return true if there is no overflow and false otherwise
*/
bool divide( long *result, long a, long b )
{
    if ( b == 0 ) {
        return false;
    } else if ( a == LONG_MIN && b == -1 ) {
        return false;
    }

    *result = a / b;
    return true;
}
