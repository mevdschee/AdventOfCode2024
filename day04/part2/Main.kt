import java.io.File

fun main() {
    val lines = File("input").readLines()
    val grid = lines.map { it.toCharArray() }
    
    fun isValidXMAS(row: Int, col: Int): Boolean {
        // Check bounds - need space for the X pattern
        if (row - 1 < 0 || row + 1 >= grid.size) return false
        if (col - 1 < 0 || col + 1 >= grid[0].size) return false
        
        // Center must be 'A'
        if (grid[row][col] != 'A') return false
        
        // Check diagonal from top-left to bottom-right
        val topLeft = grid[row - 1][col - 1]
        val bottomRight = grid[row + 1][col + 1]
        val diagonal1Valid = (topLeft == 'M' && bottomRight == 'S') || 
                             (topLeft == 'S' && bottomRight == 'M')
        
        // Check diagonal from top-right to bottom-left
        val topRight = grid[row - 1][col + 1]
        val bottomLeft = grid[row + 1][col - 1]
        val diagonal2Valid = (topRight == 'M' && bottomLeft == 'S') || 
                             (topRight == 'S' && bottomLeft == 'M')
        
        return diagonal1Valid && diagonal2Valid
    }
    
    var count = 0
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            if (isValidXMAS(row, col)) {
                count++
            }
        }
    }
    
    println(count)
}
