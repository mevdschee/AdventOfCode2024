import java.io.File

fun main() {
    val grid = File("input").readLines().map { it.toCharArray() }
    val height = grid.size
    val width = grid[0].size
    
    // Find the starting position and direction
    var startRow = 0
    var startCol = 0
    var startDir = 0 // 0=up, 1=right, 2=down, 3=left
    
    for (r in grid.indices) {
        for (c in grid[r].indices) {
            when (grid[r][c]) {
                '^' -> { startRow = r; startCol = c; startDir = 0 }
                '>' -> { startRow = r; startCol = c; startDir = 1 }
                'v' -> { startRow = r; startCol = c; startDir = 2 }
                '<' -> { startRow = r; startCol = c; startDir = 3 }
            }
        }
    }
    
    // Direction vectors: up, right, down, left
    val dRow = listOf(-1, 0, 1, 0)
    val dCol = listOf(0, 1, 0, -1)
    
    // Function to check if guard gets stuck in a loop with current grid configuration
    fun causesLoop(): Boolean {
        var row = startRow
        var col = startCol
        var dir = startDir
        
        val visited = mutableSetOf<Triple<Int, Int, Int>>()
        visited.add(Triple(row, col, dir))
        
        while (true) {
            val nextRow = row + dRow[dir]
            val nextCol = col + dCol[dir]
            
            // Check if out of bounds - guard escapes (no loop)
            if (nextRow < 0 || nextRow >= height || nextCol < 0 || nextCol >= width) {
                return false
            }
            
            // Check if there's an obstacle
            if (grid[nextRow][nextCol] == '#') {
                // Turn right 90 degrees
                dir = (dir + 1) % 4
            } else {
                // Move forward
                row = nextRow
                col = nextCol
            }
            
            // Check if we've been in this state before (loop detected)
            val state = Triple(row, col, dir)
            if (state in visited) {
                return true
            }
            visited.add(state)
        }
    }
    
    var count = 0
    
    // Try placing an obstruction at each empty position
    for (r in grid.indices) {
        for (c in grid[r].indices) {
            // Skip if not empty or if it's the starting position
            if (grid[r][c] != '.' || (r == startRow && c == startCol)) {
                continue
            }
            
            // Place obstruction
            grid[r][c] = '#'
            
            // Check if this causes a loop
            if (causesLoop()) {
                count++
            }
            
            // Remove obstruction
            grid[r][c] = '.'
        }
    }
    
    println(count)
}
