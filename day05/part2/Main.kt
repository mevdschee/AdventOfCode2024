import java.io.File

fun main() {
    val lines = File("input").readLines()
    
    // Find the empty line that separates rules from updates
    val emptyLineIndex = lines.indexOfFirst { it.isEmpty() }
    
    // Parse the ordering rules
    val rules = lines.subList(0, emptyLineIndex).map { line ->
        val (before, after) = line.split("|").map { it.toInt() }
        before to after
    }
    
    // Parse the updates
    val updates = lines.subList(emptyLineIndex + 1, lines.size).map { line ->
        line.split(",").map { it.toInt() }
    }
    
    // Function to check if an update is in the correct order
    fun isValidOrder(update: List<Int>): Boolean {
        for ((before, after) in rules) {
            val beforeIndex = update.indexOf(before)
            val afterIndex = update.indexOf(after)
            
            // If both pages are in this update
            if (beforeIndex != -1 && afterIndex != -1) {
                // Check if 'before' comes before 'after'
                if (beforeIndex > afterIndex) {
                    return false
                }
            }
        }
        return true
    }
    
    // Function to fix the order of an update
    fun fixOrder(update: List<Int>): List<Int> {
        val result = update.toMutableList()
        
        // Keep fixing until it's valid
        var changed = true
        while (changed) {
            changed = false
            for ((before, after) in rules) {
                val beforeIndex = result.indexOf(before)
                val afterIndex = result.indexOf(after)
                
                // If both pages are in this update
                if (beforeIndex != -1 && afterIndex != -1) {
                    // If they're in the wrong order, swap them
                    if (beforeIndex > afterIndex) {
                        result[beforeIndex] = after
                        result[afterIndex] = before
                        changed = true
                    }
                }
            }
        }
        
        return result
    }
    
    // Find incorrectly ordered updates, fix them, and sum their middle page numbers
    val result = updates
        .filter { !isValidOrder(it) }
        .map { fixOrder(it) }
        .sumOf { it[it.size / 2] }
    
    println(result)
}
