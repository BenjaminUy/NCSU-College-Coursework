# CSC116: Lab 16 Journal - File Output

Names: Benjamin Uy and Yamini Ramadurai

## Collapse Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

We have to create a program that will prompt the user for an input text file. The program will then process the text file and output a file that removes extra whitespace. If valid, the program will prompt the user for the output file name. 

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

Algorithm collapseSpaces(in, out) - Input: input file, output file; Output: (a text file with extra whitespace collapsed)
 - While the scanner has a next token, make a String variable line that is the next line in the scanner
 - Create a variable to call the collapseLine method with the next line in the scanner as a parameter, using printwriter to print out that collapsed line variable

Algorithm collapseLine(line) - Input: input string to collapse spaces; Output: string with spaces collapsed

 - Create a new empty string
 - If the length of the line is 0, return the empty string
 - Make a counter for how many spaces before the first character there are; while the tokens are characters still, increment the space counter
 - Create a boolean for whether or not whitespace was included
 - Use a for loop from index 0 to the length of the string; if there is a character (not a space) at the index, add it to the string, and make sure that whitespace included is false; else if whitespace included is false and the next character is a tab/space, add one space to the new string, but also change the whitespace boolean to true
 - Create a variable that counts any extra space from the end of the string
 - Use a while loop to go backwards in the line, and for every value that there's a space before the first character that appears, increment the variable
 - Use substring of the new string from the 0th index to the extra space variable + 1 (since it's not inclusive)
 - Return the new string


Algorithm getOutput(console) - Input: console input scanner; Output: PrintWriter for output file, null if file exists
 - (Used code from in-class lab discussion)

Algorithm getInput(console) - Input: scanner console; Output: scanner for the input file
 - (Used code from in-class lab discussion)

## Collapse Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

We applied the pre-lab concepts in this exercise by using PrintWriter and FileOutputStream (and FileNotFoundException) to work with traversing through text files and referencing the text files in our main source code file(s), overwriting output files (as needed, or preventing our program from overwriting output files that already exist), using assertNotNull for our unit and integration tests (since we have Scanner and PrintWriter), and using diff to check any differences in the actual vs. expected text files.

Estimate how much time (minutes) you spent on this exercise.

150