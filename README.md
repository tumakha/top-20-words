# top-20-words (Technical test)

[![Java-Gradle-CI](https://github.com/tumakha/top-20-words/workflows/Java-Gradle-CI/badge.svg)](https://github.com/tumakha/top-20-words/actions)

Given the attached text file as an argument, your program will read the file,
and output the 20 most frequently used words in the file in order, along with their frequency. 
The output should be the same to that of the following bash script `./top20words.sh`, 
where the first argument is the text file to count:

#### Requirements
Java 14+, Gradle 6 or gradle-wrapper

#### Build

    gradle build

#### Run

    java -jar build/libs/top20words.jar <filename>
    
For example

    java -jar build/libs/top20words.jar data/mobydick.txt
    
### Compare with bash script output

    ./top20words.sh data/mobydick.txt
