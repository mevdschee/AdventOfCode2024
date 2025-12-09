### Installing

Execute the following:

    sudo apt update
    sudo apt install default-jdk
    curl -s https://get.sdkman.io/ | bash
    sdk install kotlin

Now you can use the kotlinc command.

### Running

Go into a folder, like `day01/part1` and execute:

    kotlinc Main.kt -include-runtime -d Main.jar && java -jar Main.jar

