# CSC116: Lab 05 Journal - Conditionals

Names: Emma Gould and Benjamin Uy

## Race Pace Calculator Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

This program needs to be able to calculate a runner's average pace in a race, based on the length of the race given by the user. We have no questions about the problem.

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

1.  Algorithm to determine race type validity
    Input character or letter corresponding to race
    (For example, "M" = marathon)
    Output true that user input of race type is valid; otherwise return false

    Create class constants for lengths of races and time conversions:

    M = miles_Marathon = 26.2
    H = miles_HalfMarathon = 13.1
    T = miles_TenK = 6.2
    F = miles_FiveK = 3.1
    I = miles_Mile = 1

    Conditional(s) to filter out invalid inputs:

    - If any race type inputs are anything but M, H, T, F, or I, input is invalid (output "Invalid race type. Please choose from the menu options," or something similar)

    - If not, the race type is valid and program proceeds to gather race time data

2.  Algorithm to determine race pace time
    Input time in the format of H:MM:SS
    Output pace time if it satisfies formatting requirements; other return as invalid time

    Conditionals(2) to filter out invalid inputs:

    - String length HAS to be 7; if not, the race time format is invalid

    - Index 1 and 4 are colons; if not, the race time format is invalid

    - Index 0,2,3,5,6 are ints greater than or equal to zero (filters out negatives as well); if not, the race time format is invalid

    - Check the boundaries/overflow of numerical inputs:
    if seconds or minutes are greater than 59, input is invalid

    seconds <- substring of values of index 5 thru 6
    minutes <- substring values of index 2 thru 3
    hours <- substring value of index 0, length is 1

    // All inputs are now filtered and valid

    Assigning variables proper values based on input:

    Convert user input time to seconds:

    seconds_per_hour = 3600
    second_per_minute = 60

    raceTimeSeconds <- (hours * seconds_per_hour) + (minutes * seconds_per_minute) + (seconds)

    racePace <- raceTimeSeconds / mileage

    hours <- racePace / seconds_per_hour
    minutes <- racePace - (hours * seconds_per_hour)
    seconds <- racePace - ((hours * seconds_per_hour) + (minutes * seconds_per_minute))

    System will output the race pace in the format "H:MM:SS"

## Race Pace Calculator Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

In this exercise, in addition to if/else statements, we made use of switch statements to control the flow of our program. Our first switch statement was responsible for evaluating the race type input by the user, and then assigning the variable mileage the value of the number of miles corresponding to each race type. Another pre-lab concept that we used is strings (in use with scanners); these were used in both the user's inputs for race type and race time. We also divided the latter with substrings--specific sections of the input time corresponded to either hours, minutes, or seconds based on position in the string. 

Estimate how much time (minutes) you spent on this exercise.

240