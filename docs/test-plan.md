# Plan for Testing the Program

The test plan lays out the actions and data I will use to test the functionality of my program.

Terminology:

- **VALID** data values are those that the program expects
- **BOUNDARY** data values are at the limits of the valid range
- **INVALID** data values are those that the program should reject

---

## Getting the players names

When a player inputs their name, it should not be blank and must fulfill the maximum character limit

### Test Data To Use

Start the game and input various names and non-names to test all possible types of input from the user (Short name, too-long name,blank space)

### Expected Test Result

When a name is under the maximum character limit or equal to the limit the name should be accepted. When a name is over the limit the player should be asked again. When they input nothing, they should be asked again. 
When asked after an invalid input, the player should be asked multiple times until they provide a valid input
---

## Yes/No response

A player is asked whether they would like to read the rules and is required to input a variation of yes or no

### Test Data To Use

Input blank spaces, non yes/no answers, and different variations of yes/no (yep, Y, yipperoni)

### Expected Test Result

Every variation of yes or no will be accepted given that it begins with a y or an n. Any other inputs will result in being asked again until a valid input is entered.

---
## Press enter to continue

The user should be asked for confirmation before continuing to the game by pressing enter

### Test Data To Use

Pressing enter, inputting letters, numbers, symbols, and extra spaces

### Expected Test Result

The only inputs that will be accepted are blank spaces or pressing enter. anything else will result in being asked again.

---

## Ask user where they would like to place their counter

The user is prompted to input the numbered column they would like to place their counter in and their counter is placed.
The user should be told if the column they select is full, and should be booted if they continue to attempt to place counters in rows that are full

### Test Data To Use

Valid column numbers, invalid column numbers, non-numbers, blank spaces

### Expected Test Result

The user should be asked again if their input is invalid, and their counter should be placed correctly if their input is valid. The user should be asked again if their input is invalid, including a full row. The user is booted after multiple attempts of putting a counter in a full row.

---
## Game win

If there are four counters in a row, a winner should be announced

### Test Data To Use

Lining up counters in all directions with both players.

### Expected Test Result

The winner should be the correct name and colour and all wins should be detected as soon as the winning counter is placed.

---
