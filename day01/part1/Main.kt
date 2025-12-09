import java.io.File
import kotlin.math.abs

fun main() {
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()
    
    // Read the input file
    File("input").forEachLine { line ->
        val parts = line.trim().split(Regex("\\s+"))
        if (parts.size == 2) {
            leftList.add(parts[0].toInt())
            rightList.add(parts[1].toInt())
        }
    }
    
    // Sort both lists
    leftList.sort()
    rightList.sort()
    
    // Calculate total distance
    var totalDistance = 0
    for (i in leftList.indices) {
        totalDistance += abs(leftList[i] - rightList[i])
    }
    
    println(totalDistance)
}