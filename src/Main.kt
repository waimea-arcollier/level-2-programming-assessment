import kotlin.system.exitProcess

/**
 * =====================================================================
 * Programming Project for NCEA Level 2, Standard 91896
 * ---------------------------------------------------------------------
 * Project Name:   Connect 4
 * Project Author: Amber Collier
 * GitHub Repo:    https://github.com/waimea-arcollier/level-2-programming-assessment
 * ---------------------------------------------------------------------
 * Notes:
 * Needs lists in lists to create a two-dimensional grid
 * Input column in  user-friendly way
 * Find the row that the counter needs to be placed on based on counters that may be below it
 * Decide when there are 4 counters in a line and display a win message
 * =====================================================================
 */

const val EMPTY = "□"
const val COUNTER = "●"
const val GRID_WIDTH = 7
const val GRID_HEIGHT = 6
const val MAX_LENGTH = 25
val counterP1 = COUNTER.blue()
val counterP2 = COUNTER.red()
var currentPlayer = ""
var currentCounter = ""
var nameP1 = ""
var nameP2 = ""

fun main() {
    //welcome message
    println(border(text = "Welcome to Connect 4!!".blue().bold()))
    //name the players
    nameP1 = getPlayerName("Player 1, what is your name? : ".blue())
    nameP2 = getPlayerName("Player 2, what is your name? : ".red())
    //ask user if they would like to read the rules or go straight to game
    println(" ")
    println(" ")
    val confirm1 = getYesNo("Would you like to read the rules? " + "[Y/N]".bgMagenta().bold() + " : ")
    if (confirm1 == "Y") {
        clear(6)
        println(
        """ 
        The classic game of Connect 4 is now digital!
         
        - Players will take turns placing counters. 
        - Players must input the column they want to place their counter in. 
        - The first player to connect 4 of their counters wins! 
        """.trimIndent().bold()
        )
        clear(5)
    }
    if (confirm1 == "N") {
        clear(5)
    }
    //confirms the user has read the rules and/or is ready to play
    proceed("$counterP1 Press enter to continue to game $counterP2")

    //initial game setup
    val grid = setupGrid()
    clear(20)
    showGame(grid)

    // Start with P1
    var p1Turn = true
    // Keep track of how many turns the game has had
    var turns = 0
    //Making testing and troubleshooting easier by skipping the need to place multiple counters to initiate a draw
    if (nameP1 == "DrawTest"){
        turns = GRID_HEIGHT * GRID_WIDTH
    }
    //Player turns loop
    while (true) {
        //Check win when a win is possible
        if (turns > 6){
            var winner: String
            if (checkWin(grid)){
                //Assign a winner
                if (p1Turn){
                    winner = nameP2.red().bold()
                }
                else {
                    winner = nameP1.blue().bold()
                }
                println("")
                clear(15)
                println(border(text = winner + " wins!! Congratulations!".bold()))
                exitProcess(0)
            }
        }
        //Check for a draw
        if (turns == GRID_HEIGHT * GRID_WIDTH) {
            println("")
            println("")
            println("What's this? The game seems to have ended in a draw!\n \n...This isn't supposed to happen... \n\n Bye Bye!!")
            exitProcess(0)
        }
        //Assign the name and counters that need to be outputted
        if (p1Turn) {
            currentPlayer = nameP1
            currentCounter = counterP1
        }
        else {
            currentPlayer = nameP2
            currentCounter = counterP2
        }
        println("")
        placeCounter(grid)
        turns ++
        println("")
        showGame(grid)
        println("")
        //Change the current player
        if (p1Turn) p1Turn = false
        else p1Turn = true
    }
}
/**
 * Functions to set up the gameplay grid by creating a list within a list
 */
fun setupGrid(): MutableList<MutableList<String>> {
    val grid = mutableListOf<MutableList<String>>()

    // Work down the rows, one by one
    for (row in 0 ..< GRID_HEIGHT) {
        // Create a list for each row
        val rowList = mutableListOf<String>()
        grid.add(rowList)

        // Work across the columns for this new list
        for (column in 0 ..< GRID_WIDTH) {
            rowList.add(EMPTY)
        }
    }
    return grid
}
/**
 * Function to print a display that clears the terminal
 *
 * Reduces clutter of output by separating sections or clearing the entire terminal
 *
 * Parameters:
 * - clearRange - How many times that the tokens are printed
 */

fun clear(clearRange: Int ) {
    for (i in 0 ..< clearRange){
        println(counterP1)
        println(counterP2)
    }
}
/**
 * Function to place a counter border around text
 *
 * Parameters:
 * -text- title or announcement that will be bordered
 * Returns:
 * -textWithOutline- The provided string with a border of counters around it
 */
fun border(text: String): String {
    //top and bottom of border
    val line = "$counterP1-$counterP2-".repeat(text.length /4)
    //Place border around text
    val textWithOutline =
        "$line\n" +
        "${counterP2.padEnd(11)} $text\n" +
        "$line\n"
    return textWithOutline
}
/**
 * Function to ask the players for their names
 * Parameters:
 *  -Prompt- Text to show the user
 *
 *  Returns:
 *  - Name of the player
 */
fun getPlayerName(prompt: String): String {
    var userInput: String

    while (true) {
        print(prompt)

        userInput = readln()
        if (userInput.isNotBlank() && userInput.length <= MAX_LENGTH) {
            break
        } else if (userInput.isNotBlank()){
            println("That name is too long! Just your first name will do.")
            continue
        }
    }
    return userInput
}
/**
 * Function to get a yes/no input
 *
 * Parameters:
 * -Prompt- Text to show the user
 * Returns:
 * -The users answer as a one letter capitalized response
 */
fun getYesNo(prompt: String): String {
    var userInput: String

    while (true) {
        print(prompt)

        userInput = readlnOrNull().toString()
        if (userInput.isNotBlank()){
            userInput = userInput.uppercase().first().toString()
            if (userInput == "Y") break
            if (userInput == "N") break
        }
    }
    return userInput
}
/**
 * Function to ask for confirmation before proceeding with the game
 *
 * Parameters:
 *  -Prompt- Text to show the user
 *
 * Only allows user to continue if they do not input anything and press enter
 */
fun proceed(prompt: String) {
    var userInput: String

    while (true) {
        print(prompt)

        userInput = readln()
        if (userInput.isBlank()) break
    }
}
/**
 * Function to display the grid with numbered columns and a border so the user can tell where they are going to place their counters
 */
fun showGame(grid: MutableList<MutableList<String>>) {
    //Displays grid
    print("+")
    print("–".repeat(GRID_WIDTH + ((3 - 1) * GRID_WIDTH) +2 ))
    println("+")
    for (row in 0..<grid.size){
        print("|".padEnd(3))
        for (slot in 0..<grid[row].size){
            print (grid[row][slot].padEnd(3))
        }
        print("|")
        println()
    }
    print("+")
    print("–".repeat(GRID_WIDTH + ((3 - 1) * GRID_WIDTH) +2 ))
    println("+")
    print("+".padEnd(3))
    //Numbers the columns
    for (i in 0 ..< GRID_WIDTH) {
        print((i + 1).toString().padEnd(3))
    }
    print("+")
}
/**
 * Function to ask the player for the column they want to place their counter on
 * Parameters:
 *  -Prompt- Text to show the user
 *
 * Returns:
 * Column the user inputted adjusted for placing in a list
 */
fun getCounterLocation(prompt: String): Int {
    var userInput: Int?
    while (true) {
        print(prompt)
        userInput = readln().toIntOrNull()
        if (userInput != null) {
            if (userInput in 1..<GRID_WIDTH + 1) break
        }
    }
    return userInput !! - 1
}
/**
 * Function to place the users counter in row and column that corresponds to how connect 4 works
 */
fun placeCounter(grid: MutableList<MutableList<String>>){
    var counterColumn = getCounterLocation("$currentPlayer, chose a column: ")
    var row = GRID_HEIGHT
    val top = 0
    var userErrors = 0
    while (true) {
        //Check if the column is already full
        if (grid[top][counterColumn] != EMPTY) {
            println("That column is full!")
            userErrors ++
            //Kicks user from playing if they attempt to place counter in full columns too many times to reduce spamming of numbers
            if (userErrors > 5){
                clear(15)
                println("")
                println(
                    "Dude.\n\n" +
                            "You're telling me... \n" +
                            "You can't see that these columns are OBVIOUSLY full?\n" +
                            "You know what?\n" +
                            "I'm banning you from playing.\n" +
                            "Yeah.\n" +
                            "Maybe next time dont be a silly goose.\n\n" +
                            "Bye."
                )
                exitProcess(0)
            }
            counterColumn = getCounterLocation("$currentPlayer, chose a column: ")
            continue
        }
        row--
        if (grid[row][counterColumn] == EMPTY) {
            grid[row][counterColumn] = currentCounter.padEnd(3 + 9)
            break
        }
    }
}
/**
 * Function to check whether there are four of a players counters in a line in any direction, returns true if there is.
 */
fun checkWin(grid: MutableList<MutableList<String>>): Boolean{
    //Horizontal line check
    for (row in 0..GRID_HEIGHT - 1) {
        for (col in 0..GRID_WIDTH - 4) {
            if (grid[row][col] != EMPTY
                && grid[row][col] == grid[row][col + 1]
                && grid[row][col + 1] == grid[row][col + 2]
                && grid[row][col + 2] == grid[row][col + 3]) {
                return true
            }
        }
    }
    //Vertical line check
    for (row in 0..GRID_HEIGHT - 4) {
        for (col in 0..GRID_WIDTH - 1) {
            if (grid[row][col] != EMPTY
                && grid[row][col] == grid[row + 1][col]
                && grid[row + 1][col] == grid[row + 2][col]
                && grid[row + 2][col] == grid[row + 3][col]) {
                return true
            }
        }
    }
    //Diagonal right line check
    for (row in 0..GRID_HEIGHT - 4) {
        for (col in 0..GRID_WIDTH - 4) {
            if (grid[row][col] != EMPTY
                && grid[row][col] == grid[row + 1][col + 1]
                && grid[row + 1][col + 1] == grid[row + 2][col + 2]
                && grid[row + 2][col + 2] == grid[row + 3][col + 3]) {
                return true
            }
        }
    }
    //Diagonal left line check
    for (row in 0..GRID_HEIGHT - 4) {
        for (col in 3..GRID_WIDTH - 1) {
            if (grid[row][col] != EMPTY
                && grid[row][col] == grid[row + 1][col - 1]
                && grid[row + 1][col - 1] == grid[row + 2][col - 2]
                && grid[row + 2][col - 2] == grid[row + 3][col - 3]) {
                return true
            }
        }
    }
    return false
}
