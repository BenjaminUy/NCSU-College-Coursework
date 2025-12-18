# CSC116: Lab 04 Journal - Conditionals

Names: Benjamin Uy and Emma Gould

## Triangle Type Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

We need to create a program where we can input three numbers that represent potential triangle side lengths. If one of these side lengths is longer than the sum of the other two, or if at least one of the sides is negative, then the triangle is invalid. We also know the definitions of equilateral, isosceles, and scalene triangles. (Question) Is there a point where a certain set of side lengths yield an invalid triangle?

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

Algorithm to determine triangle validity
Input three side lengths a, b, c
Output true if the sides represent a valid triangle; otherwise return false

    If a, b, or c are negative, then the triangle is considered invalid

    If not continue

Longside <-- side length (a, b, or c) with the highest value

    If Longside is less than or equal to the sum of the other remaining two sides, then the triangle is valid

    If not, the triangle is invalid

Algorithm to determine triangle type
Input based on previous input side lengths
Output whether the sides make an equilateral, isosceles, or scalene triangle

    If a = b = c, then the triangle is equilateral
        System will output the triangle is equilateral

    If two of the side lengths are equal, then the triangle is isosceles
        System will output the triangle is isosceles

    If none of the side lengths are equal, then the triangle is scalene
        System will output the triangle is scalene

## Triangle Type Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

For this exercise, we used logical & equality/relational operators in tandem with conditionals to allow our program to use three side lengths (input by a user) to evaluate for a triangle's validity and type. Because of conditionals, we could create a program more complex than the eggs cost calculator. Several times, we used "else if" to have the program check the input side lengths against our conditions, in order to "filter" the measurements by triangle validity and type. For example, the first of our if statements checked if the longest side of the triangle (the maxiumum value) was greater than or equal to the two shorter sides. By using this conditional, we were able to separate inputs that form valid triangles from inputs that don't.

Estimate how much time (minutes) you spent on this exercise.

120 minutes