# CSC116: Lab 15 Journal - File Input (Line-Based Processing)

Names: Benjamin Uy and Alex Calisto

## Weather Reporter Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

We're creating a program that prompts the user for an input file name and reads the file. The program will then output the weather information for each day.

* What is already known about the problem?
* What questions do you have about the problem?

The text file will have the format of date, average (F), high, low, and fog/rain/etc. These categories will be separated by commas in the file. All this information for a single day is on one line. No questions so far.

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

Algorithm processLine(line)
Input a line of text that contains weather info for a given day
Output a string representation of the weather report for the day

[String contents will be divided by commas (",")]

String date = Next token as string
String year = substring for first 4 characters
String month = substring for later 2 characters
String day = substring for remaining 2 characters

double average = Next token as double
(Won't be used)

double high = Next token as double
double low = Next token as double

String weather = Next token as string
int rainNum = Character at index for rain (1)
int snowNum = Character at index for snow (2)
    String rain = "yes" if rainNum is 1, "no" if not
    String snow = "yes" if snowNum is 1, "no" if not

Return line in order: month/day/year, low, high, rain, snow (format specified in requirements)

## Weather Reporter Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

In this exercise we applied the concept of a try/catch control structure to deal with FileNotFound exceptions. 
To make our code more robust, we made our try/catch block reprompt the user of a valid file name if it caught
a FileNotFound exception. Additionally, we applied a line-based approach when scanning the text file by setting
up a scanner that looked for lines in the text file and another scanner that looked within those line. The latter
was involved in a method that process said lines into strings of weather-related info. Knowing that the format 
of a weather text file was consistent throughout the text file, we made our program read tokens one at a time--
assigning them to either strings or doubles. For example, the program always read the first token in line as the
string for the date.

Estimate how much time (minutes) you spent on this exercise.

180

