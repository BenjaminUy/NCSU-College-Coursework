# CSC116: Lab 10 Journal - Array Basics

Names: [Benjamin Uy & Aaron Ferguson]

## Processing Exam Scores Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

We are using an array to store test scores for students in CSC 116. The students' CSC116 ID is the index of the array where the scores are stored. No questions so far.

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

Algorithm findGivenScoreWithSmallestID
Input: given score
Output: smallest student ID with given score (int)
- User inputs grade as int
- Create loop where variable id (student ID) increases by 1 if it is less than (length of array - 1)
    index the first student ID in the student ID array
    if the grade int given is equal to the grade indexed from the grade array, set scoreSmallestID to grade indexed value.
    if the grade int given is not equal to the grade indexed from the grade array, continue loop until all indexes called.
    Output scoreSmallestID

Algorithm findGivenScoreWithLargestID
Input: given score
Output: largest student ID with given score (int)
- User inputs grade as int
- Create loop where variable id (student ID) decreases by 1 if it is greater than 0
    index the last student ID in the student ID array
    if the indexed student ID is equal to the grade provided as input, set scoreLargestID to grade index value.
    if the indexed student ID is not equal to the grade provided, continue the loop, subtracting one from the index called.
    Output scoreLargestID
    
Algorithm findGivenScoreList
Input: grade score (integer)
Output: str with integers of student ids
- User inputs grade as int
- Create loop where variable id (student ID) increases by 1 if it is less than (length of array - 1)
    Loop starts at 0 (first index value in array), if 0 is less than the (length of the array - 1), the below statements will be done, and the index value will         increase by 1 (0 to 1, 1 to 2, and so on). 

    (Refer to index value as generic x)
    if the grade at index x is equal to the user input grade, add the index value to the string
    Output statement = index value + ", "

Algorithm findHighestScore
Input: array
Output: Maximum grade score (int)
- using array provided, in loop index each part of array
- set the initial maximum value to the first index value
- go to the next index value in array, if the next index value is greater than the current maximum value
 update maximum value to current index value.  If value is less than, continue loop without updating maximum value.

Algorithm findLowestScore
Input: array
Output: Minimum grade score (int)
- using array provided, in loop index each part of array.
- set the initial minimum value to the first index value
- go to the next index value in array, if the next index value is less than the current minimum value
 update minimum value to current index value.  If value is greater than, continue loop without updating minimum value.
 - continue loop until all indexes have been reviewed.
 return minimum value

Algorithm countInRange
Input: grade (string)
Output: number of students with grade (int)
- program takes a letter grade like 'A' , 'B', 'F' etc.,
in loop based off a set integer range for each grade.
- count begins at 0.
- call each grade individually in a switch case, indexing the array individually for grades
if the grade indexed is within the letter grade range, increase count by one.
once each piece of array has been indexed, return the count.

## Processing Exam Scores Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

In this exercise, we applied the concepts of array basics. For example, we used for loops to examine the elements within an array of integer values. This was done by creating a generic variable that would increase or decrease by 1 from the beginning and end index, respectively. Within this loop, we used if statements to compare an array element to a previously established constant or range. We also made use of the Array class, as is seen in our listOfIndexWithValue method that used binarySearch which looked through the array to see if there was a matching element that we specified.  

Estimate how much time (minutes) you spent on this exercise.

120
