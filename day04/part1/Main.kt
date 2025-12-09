import java.io.File

fun main() {
    val lines = File("input").readLines()
    val grid = lines.map { it.toCharArray() }
    
    val word = "XMAS"
    val directions = listOf(
        Pair(0, 1),   // right
        Pair(0, -1),  // left
        Pair(1, 0),   // down
        Pair(-1, 0),  // up
        Pair(1, 1),   // down-right
        Pair(1, -1),  // down-left
        Pair(-1, 1),  // up-right
        Pair(-1, -1)  // up-left
    )
    
    fun searchFrom(row: Int, col: Int, dr: Int, dc: Int): Boolean {
        for (i in word.indices) {
            val newRow = row + i * dr
            val newCol = col + i * dc
            
            if (newRow !in grid.indices || newCol !in grid[0].indices) {
                return false
            }
            
            if (grid[newRow][newCol] != word[i]) {
                return false
            }
        }
        return true
    }
    
    var count = 0
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            if (grid[row][col] == 'X') {
                for ((dr, dc) in directions) {
                    if (searchFrom(row, col, dr, dc)) {
                        count++
                    }
                }
            }
        }
    }
    
    println(count)
}
