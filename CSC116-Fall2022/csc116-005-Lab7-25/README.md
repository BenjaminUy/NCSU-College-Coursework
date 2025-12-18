# CSC116: Lab 07 Journal - Unit and Integration Testing

Names: Benjamin Uy, Bryson Brading, and Eric Chin

## Secret Message Bugs

[Document each of the bugs you find and fix. For each bug, indicate whether you discovered the bug through unit and integration testing or system testing.]

Discovered from integration/unit testing:
- Line 93: Changed message.charAt(length) to message.charAt(length - 1)
- Line 89: Changed length < 2 to length <= 2
- Line 101: Added else statement
- Line 108: Removed indexOfSecond
- Line 127: Changed message.substring(length - 2) to message.substring(0, length - 2)
- Line 127: Changed message.substring(length - 1) to message.charAt(length - 1)
- Line 153: Changed message.substring(mid) to message.substring(mid + 1, length)
- Line 57: Moved message = moveCharacter(message) from line 58 to line 57
- Line 73: Changed swapSubstrings(message) to message = swapSubstrings(message)
- Line 74: Changed moveCharacter(message) to message = moveCharacter(message)
- Line 75: Changed swapCharacter(message) to message = swapCharacter(message)

Discovered from system testing:
- Line 37: Added a break

## Secret Message Reflection (***After*** writing code)

How did you apply the pre-lab concepts in this exercise? 

Essentially, this lab allowed us to use the concepts of integration/unit testing in tandem with system testing to ensure that our program was working appropriately. With unit testing, we could check individual parts of our original program by creating test methods that expected specific results depending on various conditions and the user's input. Through unit testing, we could better understand our code and more easily determine which segments required correction.

Estimate how much time (minutes) you spent on this exercise.

150
