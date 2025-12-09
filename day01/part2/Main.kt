import java.io.File

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
    
    // Count occurrences in the right list
    val rightCounts = rightList.groupingBy { it }.eachCount()
    
    // Calculate similarity score
    var similarityScore = 0
    for (num in leftList) {
        val count = rightCounts[num] ?: 0
        similarityScore += num * count
    }
    
    println(similarityScore)
}