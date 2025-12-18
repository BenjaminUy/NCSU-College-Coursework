# CSC116: Lab 18 Journal - Constructors and Other Methods

Names: Benjamin Uy and Yamini Ramadurai 

## Rational Number Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

We are creating a object class program that can take two rational numbers and do mathematical operations with them such as add, subtract, multiply, and divide. Our program should also be able to reduce fractions and, if needed, move the negative sign from the denominator to the numerator. No questions so far.

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

Algorithm add(RN1, RN2): Input two rational numbers, RN1 and RN2; Output a rational number that represents the sum (RN1 + RN2)
 * If the denominators of RN1 and RN2 are not equal to each other, create a temporary denominator that is the original value of RN1's denominator
 * Multiply RN1's denominator by RN2's denominator and RN2's denominator by the temp value of RN1's denominator
 * Multiply RN2's numerator by the temp value of RN1's denominator and RN1's numerator by RN2's denominator as well
 * If the denominators are equal to each other, just add the numerators together when returning the value
 * Set a new RationalNumber variable where the numerator is the sum of the new numerators, and the denominator is either new denominator calculated

Algorithm subtract(RN1, RN2): Input two rational numbers, RN1 and RN2; Output a rational number that represents the difference (RN1 - RN2)
 * If the denominators of RN1 and RN2 are not equal to each other, create a temporary denominator that is the original value of RN1's denominator
 * Multiply RN1's denominator by RN2's denominator and RN2's denominator by the temp value of RN1's denominator
 * Multiply RN2's numerator by the temp value of RN1's denominator and RN1's numerator by RN2's denominator as well
 * If the denominators are equal to each other, just subtract the numerators from each other when returning the value
 * Set a new RationalNumber variable where the numerator is the difference of the new numerators, and the denominator is either new denominator calculated

Algorithm multiply(RN1, RN2): Input two rational numbers, RN1 and RN2; Output a rational number that represents the product (RN1 * RN2)
 * Multiply the numerator of RN1 by the numerator of RN2, and multiply the denominator of RN1 by RN2 as well -- return this value

Algorithm divide(RN1, RN2): Input two rational numbers, RN1 and RN2; Output a rational number that represents the ratio (RN1 / RN2)
 * If the denominators of RN1 and RN2 are not equal to each other, create a temporary denominator that is the original value of RN1's denominator
 * Multiply RN1's denominator by RN2's denominator and RN2's denominator by the temp value of RN1's denominator
 * Multiply RN2's numerator by the temp value of RN1's denominator and RN1's numerator by RN2's denominator as well
 * If RN2's numerator goes into RN1's numerator evently, divide RN1 by RN2 as the numerator, and the denominator is 1 -- return this rational number
 * If RN2's numerator doesn't go evenly into RN1's numerator,  the new national numbers' numerator is RN1 and the denominator is the common demonimator between RN1 and RN2

Algorithm reduce(N,D): Input a numerator, N a denominator, D; Output a rational number that represents the reduced (simplified) form of N/D
 * If D is less than 0, multiply both D and N by -1
 * Create two ints (a and b) and set them equal to N and D, respectively
 * While b is not equal to 0, create a temporary variable a and set b equal to the temp a value; find the remainder of b with the temporary value of a, and continue this until b equals 0
 * If a is less than 0, multiply by -1
 * The final D value is D / a, and the final N value is N /a

Algorithm equals(RN1, RN2): Input two rational numbers, RN1 and RN2; Output whether RN1 and RN2 are equivalent
 * If the numerator of RN1 and the numerator of RN2 are the same and the denominator of RN1 and the denominator of RN2 are the same, return true
 * If any of the conditional statements previously listed are not correct, return false

## Rational Number Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

In this exercise, we applied the pre-lab concepts of object constructors and methods to create a Rational Number object class that could add, subtract, multiply, and multiply two rational numbers or fractions. For example, we had two constructors--one creating the rational number with the default fraction 0/1 and the other using "this." to assign our fields (numerator and denominator) to the parameters of the constructor method (int numerator and int denominator). In addition, we also used toString and equals methods which outputted the rational number object as a fraction and determined if two rational number objects were equal, respectively. For the equals method, we incorporated the concepts of object casting and instanceof which the lectures covered.

Estimate how much time (minutes) you spent on this exercise.

150
