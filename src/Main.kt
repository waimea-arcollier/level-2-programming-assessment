
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
const val GRID_WIDTH = 7
const val GRID_HEIGHT = 6
const val COUNTER = "●"
val counterP1 = COUNTER.blue()
val counterP2 = COUNTER.red()

fun main() {
    //welcome message
    val title = "Welcome to Connect 4!!"
    print("$counterP1-$counterP2-".repeat(title.length /3))
    println (counterP1)
    println("$counterP2   ${title.blue().bold()}")
    print("$counterP1-$counterP2-".repeat(title.length /3))
    println (counterP1)

    //rules
    //setup grid
    val grid = setupGrid()
    println(grid)
    //player turns loop
    //win message
}
/**
 * Functions to set up the grid (incomplete)
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
 * Function to display the grid
 */

/**
 * Function to check whether there are four of a players counters in a line
 */

