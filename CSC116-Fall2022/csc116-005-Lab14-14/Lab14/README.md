# CSC116: Lab 14 Journal - File Input

Names: Alex Calisto and Benjamin Uy

## Process File for Integers Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

We are creating a program that can process an input file and identify the minimum & maximum integer value, the count, the sum, and the average of the integers. No questions.

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

Algorithm process(n)
Input a Scanner for a given input
Output array of stats: number of integers in scanner, minimum integer value in scanner, maximum integer value in scanner, and sum of all integer values in scanner.

Declare and initialize integer array of 4 elements; 
- element 1 is the count
- 2 is the min
- 3 is the max
- 4 is the sum

int count;
int min;
int max;
int sum;

Create while loop looking for the next string
    If hasNextInt is true, 
        count++

        if count == 0;
        min = 0;
        max = 0;
        sum = 0;

        if count == 1;
        min = next int;
        max = next int;
        sum = next int;

        if count > 1;
        min = min of (previous min and next int);
        max = max of (previous max and next int);
        sum = previous sum + next int;


## Process File for Integers Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

For this exercise, although unable to finish, we attempted to apply the concepts of reading a file when it comes to file input. Similar to the lecture video on this topic, we made use of a scanner and a FileInputStream object to read our file, along with a while loop that searched for tokens within a text file using hasNext() and hasNextInt(). We also made sure to add a scan.close() to stop the end the scanner. The rest of the exercise was pretty self-explanatory, as determining minimum and maximum values are exercises we've done before.

Estimate how much time (minutes) you spent on this exercise.

180
