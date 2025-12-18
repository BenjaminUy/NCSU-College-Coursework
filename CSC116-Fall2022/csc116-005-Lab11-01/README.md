# CSC116: Lab 11 Journal - Modifying Arrays

Names: Benjamin Uy and Aaron Ferguson

## Character Arrays Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

We have to create a program that can accept an array of characters that can (1) swap the elements at adjacent indexes. (Note, if the array has an odd length, the last element will not be swapped). (2) The program should accept an array of characters and a string and then return another array containing the counts for each character in the character array.

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

Algorithm Swap Characters:
input: array from user containing characters
output: return a modified array with swapped characters

(For example, characters at index 0 and 1 are swapped and so on; if length is odd, the last index character will not be swapped)

User will enter an array with specific characters. Length is not specific

In a loop, a generic variable "k" will start with a value of 0. If k < (length of array - 1) and k is < length of array - 2, k += 2
    Create temporary value = character at index k + 1 (the index after)
    Character at index (k + 1) will equal character at index k
    Character at index x will equal temporary value

Algorithm countLetters
input:  array from user as well as string
output: count of whether characters in string were in array, based off index

User will enter an array with specific characters as well as entering a string.  Length is not specific

in a loop, while the index referenced in array is less than array.length-1,
   if the character at the index referenced of the array is equal to any of the characters in the string, new arrayCounts[] at substring that equals array character indexed is equal 1.
   after going through each character in array, return arrayCounts

## Character Arrays Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

In this exercise, we applied the concepts of modifying and returning arrays. For the former, we created a for loop that, rather than reverse the array contents as in the lecture, swapped adjacent indexes' characters. This was done by (1) creating temporary variables that contained the character of one index which was altered to have the character of another index and (2) having our update statement increase the generic variable by 2 (that way, it could focus on two new indexes at a time). For returning arrays, we created another array within the countCharacters method whose length depended on the length of characters. We used a for loop within a for loop that compared indexes in the string to indexes in the characters array.

Estimate how much time (minutes) you spent on this exercise.

120
