/**
 * =====================================================================
 * Programming Project for nNCEA Level 2, Standard 91896
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
const val GRID_WIDTH = 7
const val GRID_HEIGHT = 6
const val PAD_LENGTH = 6
const val COUNTER = "●"
val counterP1 = COUNTER.blue()
val counterP2 = COUNTER.red()

fun main() {
    //welcome message
    val title = "Welcome to Connect 4!!"
    print("$counterP1-$counterP2-".repeat(title.length /3))
    println (counterP1)
    println("${counterP2.padEnd(12)}${title.blue().bold()}")
    print("$counterP1-$counterP2-".repeat(title.length /3))
    println (counterP1)
    //ask user if they would like to read the rules or go straight to game
    println(" ")
    println(" ")
    val confirm1 = getYesNo("Would you like to read the rules? " + "[Y/N]".bgMagenta().bold() + " : ")
    if (confirm1 == "Y") {
        clear(5)
        println("rules")
        clear(5)
    }
    proceed("$counterP1 Press enter to continue to game $counterP2")
    //setup grid
    val grid = setupGrid()
    clear(20)
    showGame(grid)
    //player turns loop
    //win message
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
    print("–".repeat(GRID_WIDTH + ((PAD_LENGTH - 1) * GRID_WIDTH) +2 ))
    println("+")
    for (row in 0..<grid.size){
        print("|  ")
        for (slot in 0..<grid[row].size){
            print (grid[row][slot].padEnd(PAD_LENGTH))
        }
        print("|")
        println()
    }
    print("+")
    print("–".repeat(GRID_WIDTH + ((PAD_LENGTH - 1) * GRID_WIDTH) +2 ))
    println("+")
    print("+  ")
    for (i in 0 ..< GRID_WIDTH) {
        print((i + 1).toString().padEnd(PAD_LENGTH))
    }
    print("+")
}
/**
 * Function to check whether there are four of a players counters in a line
 */

