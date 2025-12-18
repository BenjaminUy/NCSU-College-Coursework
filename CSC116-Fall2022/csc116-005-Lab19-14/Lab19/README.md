# CSC116: Lab 19 Journal - Interacting Classes

Names: Yamini Ramadurai and Benjamin Uy

## Pack Bakery Planning (***Before*** writing code)

### Initial Planning - Problem Analysis

What are the requirements of this problem?

* What is already known about the problem?
* What questions do you have about the problem?

We are working with multiple object classes: Bread, PackBakery, Sandwich, and SandwichFilling, and these object classes interact with each other. In this scenario, each sandwich has a name, type of bread, and type of filling. For breads, they each have a type and number of calories per slice. Lastly, for sandwich fillings, they each have a type and number of calories per serving. We need to incorporate this information into our object classes. No questions so far.

What knowledge concepts from pre-lab would help you solve this problem?

Some knowledge concepts from the pre lab that would help us solve this problem are (interpreting and understanding) UML diagrams and how interacting object classes are visually shown.

### Initial Planning - Solution Plan

What algorithms will be needed to address the problem? Describe the algorithms using pseudocode or English statements. You should not write out Java code yet.

Algorithm getCalories(S): Input a sandwich, S; Output the number of calories in the sandwich
 * Call upon the individual parts of the sandwich -- the filling, and its bread
 * Once you get the amount of calories for the bread, multiply that by 2 (two slices of bread in a sandwich)
 * Add that product to the amount of calories in the filling of the sandwich
 * Return the sum calculated in that last step for the total calories in 1 serving of the specific sandwich

Algorithm getSandwich(M, name): Input an array M of Sandwiches, the name of a Sandwich; Output the Sandwich with the given name, or null if the array does not have a Sandwich with the given name
 * Using a for loop, continue while the value of i is less than the menu length (to stay wtihin the bounds), and increment i from 0 up until the upper length
 * For each value of i, go through the menu with menu[i]
 * Check at each index to see if the String present at that index is the same as the String name given of the Sandwich
 * If it is, return the name of the Sandwich, if not, keep parsing through
 * If no value in the menu is equal to the name of the sandwich, return null

## Pack Bakery Reflection (***After*** writing code)

How well did your code address the requirements of this exercise? 

Our code fully addressed all the requirements of the exercise! We received full coverage on all branches for our unit and integration tests.

How did you apply the pre-lab concepts in this exercise? 

During this exercise, we frequently looked back at the requirements through the UML diagrams showcasing the object classes and their interactions with one another. It was helpful to understand from the prelab lectures what the different symbols/features indicated on each diagram such as the "+" representing a public method or the phrase after a ":" being the return value. Additionally, what was really helpful was the example of interacting classes with the student objects. Being somewhat new with writing object classes, these examples acted like a template for the more complex methods in our classes (e.g. toString and equals methods). 

What are some new insights that you learned from this exercise? 

It helps to go back and reference notes from class to make sure you fully understand how the code you're dealing with is supposed to work. When debugging, it helps to go slowly and part by part for each method, instead of trying to tackle the class as a whole. It is also helpful to look at other examples of interacting object classes and their code, especially when you're not familiar with writing those kinds of things. Just noticing similarities in the example object code helps me better understand how I should write my own object class code.

Estimate how much time (minutes) you spent on this exercise.

180