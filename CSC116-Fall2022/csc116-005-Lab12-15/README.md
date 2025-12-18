# CSC116: Lab 12 Journal - Multi-dimensional Arrays & Arrays of Objects

Names: Alex Calisto & Benjamin Uy

## String Arrays Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

Our program will have two methods that will each take in an array of strings. One method will check if an array is a palindrome, and the other will check if a 2D array is equal to another 2D array. No questions.

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

Algorithm isPalindrome(A)
Input an array of strings, A
Output true the string elements are the same forwards and backwards within the array, false otherwise

If array has a length of one
    Automatically, array is palindrome (return true)

If array has a length of two
    Compare arr[0] to arr[1]
        If equal, return true
        If not, false

If array has a length more than two
    Loop with generic variable k starting at 0, when k is < (array length / 2), k++
        Compare arr[k] to arr[arr.length - 1 - k]
            If equal, return true
            If not, false

Algorithm equals2D(A, B)
Input an array, A
      an array, B
Output true if A and B should be considered equal to each other

If length/height (row and column count) of array A doesn't equal array B
    return false

Loop comparing contents in row; generic variable k starting at 0, when k < array length, k++
    Loop comparing contents in column; generic variable p starting at 0, when p < array length, p++
        Compare A[k][p] to B[k][p]
            If equal, return true
            If not, false

## String Arrays Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

In this exercise, we applied the concepts of multi-dimensional arrays into our code, particularly for building our method that determined if two two-dimension arrays of strings were equal (having the same elements in the same locations). By using two for loops that looked into the rows and columns of an array, we could compare elements of the arrays without using an array equals method.

Estimate how much time (minutes) you spent on this exercise.

150
