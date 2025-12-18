/**
    @file value.h
    @author CSC 230
    @author Benjamin Uy (bsuy)
    Header file containing prototypes functions for the Value superclass, along with
    the IntegerValue, DoubleValue, and StringValue prototypes.
*/

#ifndef VALUE_H
#define VALUE_H

#include <stdbool.h>

/** Give a short name to the Value struct defined below. */
typedef struct ValueStruct Value;

/** Abstract type used to represent an arbitrary value. All Values
    support four basic operations. */
struct ValueStruct {
  /** Convert the given value to a dynamically allocated string.
      @param v Pointer to the value object to string-ify.
      @return dynamically allocated strign representation for v. */
  char *(*toString)( Value const *v );
  
  /** Subclass-specific behavior performing a += operation, adding
      two different values of the same type.
      @param v Pointer to the value we're modifying (adding to).
      @param x Pointer to the value we're adding to v.
      @return true if the types of v and x permit addition. */
  bool (*plus)( Value *v, Value const *x );
  
  /** Free any memory used to store this value.
      @param v Pointer to the value object to free. */
  void (*destroy)( Value *v );
};

/** Parse the given strign as an integer and create a dynamically allocated
    instance of Value for it.
    @param str string to parse as an integer.
    @return new value or NULL if str can't be parsed as an integer. */
Value *parseInteger( char const *str );

/**
    This function parses the given string and dynamically allocates memory for a
    new DoubleValue pointer, which it returns as a Value pointer.
    @param str string to parse a DoubleValue from
    @return pointer to a Value cast from the created DoubleValue or NULL, if str can't be parsed correctly
*/
Value *parseDouble( char const *str );

/**
    This function parses the given string and dynamically allocates memory for a
    new StringValue pointer, which is returned as a Value pointer.
    @param str string to parse a StringValue from
    @return pointer to a Value cast from the created StringValue or NULL if str can't be parsed correctly
*/
Value *parseString( char const *str );

#endif
