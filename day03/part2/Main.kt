import java.io.File

fun main() {
    val input = File("input").readText()
    
    // Regex to match mul(X,Y), do(), or don't()
    val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""")
    
    var enabled = true
    var result = 0
    
    regex.findAll(input).forEach { match ->
        when (match.value) {
            "do()" -> enabled = true
            "don't()" -> enabled = false
            else -> {
                if (enabled) {
                    val x = match.groupValues[1].toInt()
                    val y = match.groupValues[2].toInt()
                    result += x * y
                }
            }
        }
    }
    
    println(result)
}
