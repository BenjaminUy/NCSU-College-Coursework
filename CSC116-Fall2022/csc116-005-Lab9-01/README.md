# CSC116: Lab 9 Journal - For Loops & Testing Update

Names: [Aaron Ferguson, Benjamin Santos Uy]

## Series of Numbers Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

We need to create a program that can output the squared values of the numbers from 1 to whatever the user inputs. This program should be able to show the squared value for ten integers (for example, if user inputs ten, program outputs "1 4 9 16 25 36 49 64 81 100"). We can't use multiplication in our code. No questions.

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

Algorithm to list squared values from 1 to integer given by user
Input "max" value to square
Output the squared values from range 1 to max

Prompt user for integer value = max (max number to square)
  If given value cannot be read as integer is a value between 1 and 10, inclusive, continue to prompt

(Store and save max value from user to be used in loop to find the square of each value in the range from 1 through max) 

(Every squared numbers list begins with 1)
Starter value = 1
  
(After 1^2, is 2^2 which is 4. The initial math value or difference between squares is three)
Math value = 3

Loop
Create a generic variable, if less than or equal to the max, it will do below actions, afterwards, variable value is increased by 1 (loop will stop when generic variable eventually reaches max)

Squared numbers list = Starter value
Squared number = Starter value + math value

(After from 1 to 2, the difference between squares increases by 2 with each number)
new math value = old math value + 2
End loop

Print squared numbers list

## Series of Numbers Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

For this exercise, we used a for loop to determine how many squared numbers would be in the printed string output by the program. In our for loop, we declared and initialized a generic variable "k" which was compared to the "max" value, an integer between 1 and 10, inclusive, input by the user. If k was found to be less than or equal to the max value, the controlled statements of the loop would run. These controlled statements were an if and else if statement that also compared k to the max. If k was equal to max, the string statement would end without with a space, whereas if k was less than max, the string statement would end with a space. Essentially, this allowed our printed string statement to have spaces in between squared values--without an unnecessary space at the end.

Estimate how much time (minutes) you spent on this exercise.

120
