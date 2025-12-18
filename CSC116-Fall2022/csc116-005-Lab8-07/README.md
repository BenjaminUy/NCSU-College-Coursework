# CSC116: Lab 8 Journal - While & Do-While Loops

Names: Benjamin Uy, Bryson Brading, and Eric Chin

## All Digits Odd? Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

We have to create a program that will prompt the user to enter an integer (negative or positive). After recieving input from the user, the program should determine if all of the digits are odd. If so, the program should output true, and, if not, the program should output false. One of the requirements of this problem is that the methods should be completed with integers only, not strings. No questions so far.

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

Algorithm to determine if all digits of an integer are odd
Input is an integer entered by the user when prompted to enter an integer
Output true if all digits of the integer are odd

  If user does not input something that isn't an integer, reprompt them again until they do so
  
  Convert user input integer into a positive number (take absolute value = |integer|). (This makes code simpler)
  
  If false at any point, program outputs that the integer is NOT made of only odd digits
  
  1.  If |integer| is less than 10 (one digit)
        If |integer| % 2 = 0, integer is not made of only odd numbers, return false
        If |integer| % 2 != 0, integer is made of only odd numbers, return true
    
  2.  If |integer| is greater than 10 (two or more digits)
      Check if the last digit is odd/even with (|integer| % 10) % 2
        If (|integer| % 10) % 2 = 0, integer is not made of only odd numbers, return false
        If (|integer| % 10) % 2 != 0, the last digit is odd, return true
          If true, remove the last digit with (|integer| / 10) <-- This will be the new value of |integer|
  
  Repeat step 2 (if conditions are met) until, eventually, it has to do step 1. If step 1 returns true, then 
  program outputs that the integer is made of only odd digits.
  
## All Digits Odd? Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

For this exercise, we made use of while loops and the hasNextInt() method. With while loops, we were able to create a program that could evaluate whether the last digit of an integer was odd/even, return the integer without the last digit (one digit smaller), and repeat itself to ultimately determine if all the digits in the integer were odd. In addition, we used a while loop working in tandem with a hasNextInt() method to reprompt the user to enter an integer, should they input something that is not considered by the program as an integer. 

Estimate how much time (minutes) you spent on this exercise.

180
