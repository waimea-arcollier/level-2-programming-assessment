import kotlin.system.exitProcess

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
const val PAD_LENGTH = 3
const val COUNTER = "●"
val counterP1 = COUNTER.blue()
val counterP2 = COUNTER.red()
var rowFull = false
var winner = ""
var currentPlayer = ""
var currentCounter = ""
var nameP1 = ""
var nameP2 = ""

fun main() {
    //welcome message
    val title = "Welcome to Connect 4!!"
    print("$counterP1-$counterP2-".repeat(title.length /3))
    println (counterP1)
    println("${counterP2.padEnd(12)}${title.blue().bold()}")
    print("$counterP1-$counterP2-".repeat(title.length /3))
    println (counterP1)
    //name the players
    var nameP1 = getPlayerName("Player 1, what is your name? : ")
    var nameP2 = getPlayerName("Player 2, what is your name? : ")
    //ask user if they would like to read the rules or go straight to game
    println(" ")
    println(" ")
    val confirm1 = getYesNo("Would you like to read the rules? " + "[Y/N]".bgMagenta().bold() + " : ")
    if (confirm1 == "Y") {
        clear(5)
        println(
        """ 
        The classic game of Connect 4 is now digital! 
         
        - Players will take turns. 
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

    //player turns loop
    while (true) {
        print(p1Turn)
        if (checkDraw(grid)){
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
        while (rowFull) placeCounter(grid)
        showGame(grid)
        checkWin(grid)
        if (win){
            println("$winner wins!!")
            break
        }
        if (p1Turn) p1Turn = false
        else p1Turn = true
    }
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
        print("|".padEnd(PAD_LENGTH))
        for (slot in 0..<grid[row].size){
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
        if (userInput.isNotBlank()) break
    }
    return userInput
}
/**
 * Function to ask the player for the square they want to place their counter on
 */
fun getCounterLocation(prompt: String): Int {
    var userInput: Int
    while (true) {
        print(prompt)
        userInput = readln().toInt()
        if (userInput in 1..<GRID_WIDTH) break
    }
    return userInput - 1
}
/**
 * Function to place the users counter in row and column that corresponds to how connect 4 works
 */
fun placeCounter(grid: MutableList<MutableList<String>>){
    val counterColumn = getCounterLocation("$currentPlayer, chose a column: ")
    var row = GRID_HEIGHT
    val top = 0
    while (true){
        if (grid[top][counterColumn] != EMPTY) {
            println("That row is full!")
            rowFull = true
            break
        }
        row --
        if (grid[row][counterColumn] == EMPTY) {
            grid[row][counterColumn] = currentCounter.padEnd(PAD_LENGTH + 9)
            rowFull = false
            break
        }
    }
}
/**
 * Function to check whether the game has come to a draw
 */
fun checkDraw(grid: MutableList<MutableList<String>>): Boolean{
    var slotsFull = 0

    for (row in 0..< GRID_HEIGHT){
        for (col in 0..<GRID_WIDTH){
            if (grid[row][col] != EMPTY) slotsFull++
        }
    }

    return slotsFull == GRID_HEIGHT * GRID_WIDTH

}
/**
 * Function to check whether there are four of a players counters in a line
 */
fun checkWin(grid: MutableList<MutableList<String>>){
    var countersAlignedP1 = 0
    var countersAlignedP2 = 0
    while (true){
        for (row in 0..<GRID_HEIGHT){
            for (col in 0..< GRID_WIDTH){
                if (grid[row][col] == counterP1){
                    countersAlignedP1 ++
                    countersAlignedP2 = 0
                }
                if (grid[row][col] == counterP2){
                    countersAlignedP2 ++
                    countersAlignedP1 = 0
                }
            }
        }
        if (countersAlignedP1 == 4){
            win
            winner = nameP1
        }
        if (countersAlignedP2 == 4){
            win
            winner = nameP2
        }
    }
}