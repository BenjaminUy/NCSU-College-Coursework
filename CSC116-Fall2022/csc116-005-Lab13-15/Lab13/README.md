# CSC116: Lab 13 Journal - Testing Arrays

Names: Alex Calisto & Benjamin Uy

## Sorted Arrays Bug

Line 39, under main method, fixed while loop for getting double input. Initially was "while (!scan.hasNextInt())", replaced with "while (!scan.hasNextDouble())"

Line 37, under main method, "i = 0" --> "i = 1" & "i <=" --> "i <"

Line 72, under getNewArray method, removed "=" from "(numElements <= 0)"

    Line 89, under isSorted method, added  if statement that if arr.length was 0, method would return true. Also changed the for loop; variable i is now initially set to 0, replaced "i < arr.length" with "i < arr.length - 1", and control statement has "arr[i] <= arr[i + 1]" instead of "[...] arr[i - 1]". (OUTDATED)

Starting from line 93, under isSorted method, added int outOfOrder and set to 0. (Whenever in the for loop that arr[i] > arr[i + 1], isSorted will increase)

In for loop under isSorted method, changed the control statement so that if arr[i] > arr[i + 1], outOfOrder++. (Removes previous changes on Line 89)

After the for loop under isSorted method, instead of returning false, added if statemenet that if outOfOrder = 0, method returned true, and otherwise, returned false.

Line 124, under sequentialSearch method, changed if statement to "if (!isSortedArr)) [...]" to properly throw an IllegalArgumentException

Line 128, under sequentialSearch method, removed "()" from "arr.length()"

    Line 132, under sequentialSearch method, altered return statement "(-1 * index - 1)" to "(-1 * index)" (OUTDATED)

Line 154, under sequentialSearch method, altered parts of and around the for loop. Replaced "int index = 1" with "int insertionPoint = 0". Now, if arr[i] = element, insertion point was set equal to i. Now, if not previous, and if arr[i] > element, insertionPoint was set equal to i and the statement returned "(-insertionPoint - 1)". Added segment after the for loop that insertionPoint = (-arr.length - 1). (Removes previous changes on Line 132)

    Line 164, under addElement method and "if (indexToAdd < 0)", changed equation to "indexToAdd = indexToAdd * -1 + 1" (the * -1 and + 1 operations were switched) (OUTDATED)

Line 198, under addElement method and "if (indexToAdd < 0)", changed equation to "indexToAdd = (indexToAdd + 1) * -1" (gave priority to the addition operation) (Updates previous changes on Line 164)

Line 204, under addElement method, changed if statement to "if (i < indexToAdd) [...]" (Initially had <= instead of <)

Line 207, under addElement method, swapped equation to "newArray[i + 1] = arr[i]" (Initially was assigning arr[] not newArray[])

Line 216, under indexOfMinValue method, altered for loop so that loop would go backward from indexB to indexA. Essentially took parts of the original for loop going from indexA to indexB and reversed them. For example, replaced "for (int i = indexA + 1; i <= indexB; i++)" with "for (int i = indexB - 1; i >= indexA; i--)".

Line 221, under swap method, moved "arr[indexA] = arr[IndexB];" line under "double temp = arr[indexA];"

    Line 237, under selectionSort method, indexOfMinValue now is equal to indexOfMinValue(arr, i, arr.length - 1); the -1 operation was missing). Also changed if statement to "if (i < indexOfMinValue) [...]". (OUTDATED)

Line 264, under selectionSort method, under for loop, declared and initialized indexOfMinValue to equal to indexOfMinValue(arr, i, arr.length - 1) (same as before). Also, added that if indexOfMinValue was not equal to i, swap(arr, indexOfMinValue, i) was called. (Initially was "swap(arr, i, indexOfMinValue)")

## Sorted Arrays Reflection (***After*** writing code)

How well did your code address the requirements of this exercise? 

We believe that our code addressed the requirements of the exercise as all of our system and unit/integration tests passed. 

How did you apply the pre-lab concepts in this exercise? 

Much of this exercise involved us creating our own unit tests to test the various methods in our code. As such, we applied the pre-lab concepts of testing arrays under various conditions such as invalid parameters (null arrays). In addition to our unit tests, we also created tests where valid inputs were used and checked to make sure that our method was working as expected. Another thing we kept in mind when making our unit tests was making sure that our method didn't modify the original array. This was done through creating a "copy" and then comparing the copy to the original check for any modifications. Through this process/cycle of creating unit tests, running them, and debugging the code, we were able to make our code fulfill the requirements of the exercise, for the most part. 

What are some new insights that you learned from this exercise? 

It is a lot easier when you work on one method at a time. I found that when I focused on fixing the simplest methods first, particularly the ones that other methods called upon, it made the debugging process faster. Also by writing our own tests, I feel that I understand better what a unit test looks like and what it is looking for.

Estimate how much time (minutes) you spent on this exercise.

200
