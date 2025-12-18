# CSC116: Lab 06 Journal - Methods

Names: Benjamin Uy and Emma Gould

## Math Calculations Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

There needs to be a program that can recieve data sets varying in size from 2 to 4 integers. For each data set, the program needs to calculate the minimum value, the maximum value, the sum of the values, the mean value, and the median value. (Question) Should the program be able to process negative integers? What about data sets with repeating numbers (e.g., 3, 5, 5, 10)? (Answer) Negative numbers are invalid inputs, and repeated numbers will not matter in calculations.

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

1.  Algorithm to determine to size of data set
    Input integer 2, 3, or 4 corresponding to data set size
    Output true that user input is valid; otherwise, return false. If valid, the number of values will be set equal to user input
        If user input = 2, numvalues = 2
        If user input = 3, numvalues = 3
        If user input = 4, numvalues = 4
        If neither, program outputs invalid message

2.  NumValues = 2; input is always two integer values a and b
    If input does not match format: "# #", output invalid message
        Algorithm minimum (a, b)
        Output the minimum value of a and b

        Algorithm maximum (a, b)
        Output the maximum value of a and b

        Algorithm sum (a, b)
        Output the total value of a and b
            sum = a + b

        Algorithm mean (a, b)
        Output the mean value of a and b
            mean = sum / NumValues

        Algorithm median (a, b)
        Output the median values of a and b
            median = mean 
            (Because there is an even number of values in the data set, the median
            will be the average of the two middle numbers. In this case, because 
            there are only 2 values, the mean will = median)

3.  NumValues = 3; input is always three integer values a, b, and c
    If input does not match format: "# # #", output invalid message
        Algorithm minimum (a, b, c)
        Output the minimum value of a, b, and c
            minimum value = min of (a, b) then compare the min to c

        Algorithm maximum (a, b, c)
        Output the maximum value of a, b, and c
            maximum value = max of (a, b) then compare the max to c

        Algorithm sum (a, b, c)
        Output the total value of a, b, and c
            sum = a + b + c

        Algorithm mean (a, b, c)
        Output the mean value of a, b, and c
            mean = sum / NumValues

        Algorithm median (a, b, c)
        Output the median values of a, b, and c
            median = value a, b, or c that is not the max or min
            (Because there are three values in the data set, the median will be second
            data point--surrounded by the max and min value)

4.  NumValues = 4; input is always four integer values a, b, c, and d
    If input does not match format: "# # # #", output invalid message
        Algorithm minimum (a, b, c, d)
        Output the minimum value of a, b, c, and d
            minimum value = find min of (a, b), find min of (c, d), then find smaller value of the two mins

        Algorithm maximum (a, b, c, d)
        Output the maximum value of a, b, c, and d
            maximum value = max of (a, b), find max of (c, d), then find larger value of the two maximums

        Algorithm sum (a, b, c, d)
        Output the total value of a, b, c, and d
            sum = a + b + c + d

        Algorithm mean (a, b, c, d)
        Output the mean value of a, b, c, and d
            mean = sum / NumValues

        Algorithm median (a, b, c, d)
        Output the median values of a, b, c, and d
            median = remove the max and min values of the data set, then take the average of the remaining
            two numbers; (number1 that isn't a max/min + number2 that isn't a max/min) / 2
            (Because there are four values in the data set, the median will be the average of the two
            middle numbers)

## Math Calculations Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

Throughout our program, we used multiple static methods related to calculating many key features of a data set (mean, median, maximum, minimum, and sum). As mentioned in the pre-lab lecture on the subject, we defined our parameters and return values. Because we had to account for varying sizes of data sets (from two to four integers), we made use of method overloading in which we defined multiple parameters (pertaining to each individual integer value) in a single method. We also implemented a throw IllegalArgumentException in the event that the number of values did not equal 2, 3, or 4. 

Estimate how much time (minutes) you spent on this exercise.

150 minutes.
