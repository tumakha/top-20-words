package top20words;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Yuriy Tumakha
 */
public class Top20WordsReader {

    private static final int TOP_LIMIT = 20;

    public Top20WordsReader(String filename, PrintStream printStream) throws IOException {

        // Get words with their frequencies
        Map<String, Long> wordFrequencies = new HashMap<>();

        try (FileReader fileReader = new FileReader(new File(filename), UTF_8)) {
            int c;
            StringBuilder wordBuilder = new StringBuilder();
            while ((c = fileReader.read()) != -1) {
                if (Character.isLetter(c)) {
                    wordBuilder.append(Character.toLowerCase((char) c));
                } else if (wordBuilder.length() > 0) {
                    wordFrequencies.compute(wordBuilder.toString(), (k, v) -> (v == null) ? 1L : ++v);
                    wordBuilder = new StringBuilder();
                }
            }
        }

        getTopWords(wordFrequencies).forEach((word, count) ->
                printStream.printf("%7d %s\n", count, word)
        );
    }

    // Get the 20 most frequently used words
    private Map<String, Long> getTopWords(Map<String, Long> wordFrequencies) {
        return wordFrequencies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(TOP_LIMIT)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
    }

}
