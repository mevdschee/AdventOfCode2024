import java.io.File
import kotlin.math.abs

fun main() {
    val reports = File("input").readLines()
    
    var safeCount = 0
    
    for (report in reports) {
        val levels = report.trim().split(Regex("\\s+")).map { it.toInt() }
        
        if (isSafe(levels)) {
            safeCount++
        }
    }
    
    println(safeCount)
}

fun isSafe(levels: List<Int>): Boolean {
    if (levels.size < 2) return true
    
    // Determine if the sequence should be increasing or decreasing
    val isIncreasing = levels[1] > levels[0]
    
    for (i in 0 until levels.size - 1) {
        val diff = levels[i + 1] - levels[i]
        val absDiff = abs(diff)
        
        // Check if difference is between 1 and 3
        if (absDiff < 1 || absDiff > 3) {
            return false
        }
        
        // Check if direction is consistent
        if (isIncreasing && diff <= 0) {
            return false
        }
        if (!isIncreasing && diff >= 0) {
            return false
        }
    }
    
    return true
}
