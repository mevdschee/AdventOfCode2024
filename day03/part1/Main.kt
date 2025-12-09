import java.io.File

fun main() {
    val input = File("input").readText()
    
    // Regex to match mul(X,Y) where X and Y are 1-3 digit numbers
    val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
    
    val result = regex.findAll(input)
        .map { match ->
            val x = match.groupValues[1].toInt()
            val y = match.groupValues[2].toInt()
            x * y
        }
        .sum()
    
    println(result)
}
