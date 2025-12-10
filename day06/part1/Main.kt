import java.io.File

fun main() {
    val grid = File("input").readLines()
    val height = grid.size
    val width = grid[0].length
    
    // Find the starting position and direction
    var row = 0
    var col = 0
    var dir = 0 // 0=up, 1=right, 2=down, 3=left
    
    for (r in grid.indices) {
        for (c in grid[r].indices) {
            when (grid[r][c]) {
                '^' -> { row = r; col = c; dir = 0 }
                '>' -> { row = r; col = c; dir = 1 }
                'v' -> { row = r; col = c; dir = 2 }
                '<' -> { row = r; col = c; dir = 3 }
            }
        }
    }
    
    // Direction vectors: up, right, down, left
    val dRow = listOf(-1, 0, 1, 0)
    val dCol = listOf(0, 1, 0, -1)
    
    // Track visited positions
    val visited = mutableSetOf<Pair<Int, Int>>()
    visited.add(Pair(row, col))
    
    // Simulate the guard's movement
    while (true) {
        // Calculate next position
        val nextRow = row + dRow[dir]
        val nextCol = col + dCol[dir]
        
        // Check if next position is out of bounds
        if (nextRow < 0 || nextRow >= height || nextCol < 0 || nextCol >= width) {
            break
        }
        
        // Check if there's an obstacle
        if (grid[nextRow][nextCol] == '#') {
            // Turn right 90 degrees
            dir = (dir + 1) % 4
        } else {
            // Move forward
            row = nextRow
            col = nextCol
            visited.add(Pair(row, col))
        }
    }
    
    val result = visited.size
    println(result)
}
