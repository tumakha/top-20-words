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
import static java.util.Comparator.reverseOrder;

/**
 * @author Yuriy Tumakha
 */
public class Top20WordsReader {

    private static final int TOP_LIMIT = 20;
    private static final int UPPER_A = 'A';
    private static final int UPPER_Z = 'Z';
    private static final int LOWER_A = 'a';
    private static final int LOWER_Z = 'z';

    private static final Comparator<Map.Entry<String, Long>> BY_VALUE_DESC = Map.Entry.comparingByValue(reverseOrder());
    private static final Comparator<Map.Entry<String, Long>> BY_KEY_DESC = Map.Entry.comparingByKey(reverseOrder());

    public Top20WordsReader(String filename, PrintStream printStream) throws IOException {
        // Get words with their frequencies
        Map<String, Long> wordFrequencies = new HashMap<>();

        try (FileReader fileReader = new FileReader(new File(filename), UTF_8)) {
            int code;
            StringBuilder wordBuilder = new StringBuilder();
            while ((code = fileReader.read()) != -1) {
                if ((LOWER_A <= code && code <= LOWER_Z) || (UPPER_A <= code && code <= UPPER_Z)) { // code in [A-Za-z]
                    wordBuilder.append(Character.toLowerCase((char) code));
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
                .sorted(BY_VALUE_DESC.thenComparing(BY_KEY_DESC))
                .limit(TOP_LIMIT)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
    }

}
