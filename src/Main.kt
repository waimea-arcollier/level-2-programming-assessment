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
const val PAD_LENGTH = 6
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
    proceed("$counterP1 Press enter to continue to game $counterP2")
    //setup grid
    val grid = setupGrid()
    clear(20)
    showGame(grid)

    // Start with P1
    var p1Turn = true
    // Keep track of how many turns the game has had
    var turns = 0
    //Testing just for me
    var winTest = false
    if (nameP1 == "WinTest"){
        turns = 7
        winTest = true
    }
    if (nameP1 == "DrawTest"){
        turns = GRID_HEIGHT * GRID_WIDTH
    }
    //player turns loop
    while (true) {
        //Check win when possible
        if (turns > 6){
            var winner = ""
            if (checkWin(grid) || winTest){
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
        if (p1Turn) p1Turn = false
        else p1Turn = true
    }
}
/**
 * Functions to set up the grid
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
 */

fun clear(clearRange: Int ) {
    for (i in 0 ..< clearRange){
        println(counterP1)
        println(counterP2)
    }
}
/**
 * Function to place a counter border around text
 */
fun border(text: String): String {
    val line = "$counterP1-$counterP2-".repeat(text.length /4)
    val textWithOutline =
        "$line\n" +
        "${counterP2.padEnd(11)} $text\n" +
        "$line\n"
    return textWithOutline
}
/**
 * Function to get a yes/no input
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
 * Function to display the grid
 */
fun showGame(grid: MutableList<MutableList<String>>) {
    print("+")
    print("–".repeat(GRID_WIDTH + ((PAD_LENGTH - PAD_LENGTH / 3) * GRID_WIDTH) +2 ))
    println("+")
    for (row in 0..grid.size - 1){
        print("|".padEnd(PAD_LENGTH))
        for (slot in 0..grid[row].size - 1){
            print (grid[row][slot].padEnd(PAD_LENGTH))
        }
        print("|")
        println()
    }
    print("+")
    print("–".repeat(GRID_WIDTH + ((PAD_LENGTH - 1) * GRID_WIDTH) +2 ))
    println("+")
    print("+".padEnd(PAD_LENGTH))
    for (i in 0 ..< GRID_WIDTH) {
        print((i + 1).toString().padEnd(PAD_LENGTH))
    }
    print("+")
}
/**
 * Function to ask the players for their names
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
 * Function to ask the player for the square they want to place their counter on
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
        if (grid[top][counterColumn] != EMPTY) {
            println("That row is full!")
            userErrors ++
            if (userErrors > 5){
                clear(15)
                println("")
                println(
                    "Dude.\n\n" +
                            "You're telling me... \n" +
                            "You can't see that these rows are OBVIOUSLY full?\n" +
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
            grid[row][counterColumn] = currentCounter.padEnd(PAD_LENGTH + 9)
            row = GRID_HEIGHT
            break
        }
    }
}
/**
 * Function to check whether there are four of a players counters in a line
 */
fun checkWin(grid: MutableList<MutableList<String>>): Boolean{
    //Horizontal
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
    //Vertical
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
    //Diagonal right
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
    //Diagonal left
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
