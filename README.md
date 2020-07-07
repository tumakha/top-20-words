# top-20-words (Technical test)

Given the attached text file as an argument, your program will read the file,
and output the 20 most frequently used words in the file in order, along with their frequency. 
The output should be the same to that of the following bash script `./top20words.sh`, 
where the first argument is the text file to count:

#### Requirements
Java 14+, Gradle 6 or gradle-wrapper

#### Build

    gradle build

#### Run

    java -jar ./build/libs/top20words.jar <filename>
    
For example

    java -jar ./build/libs/top20words.jar mobydick.txt
    
### Verify with bash script output

    ./top20words.sh mobydick.txt
