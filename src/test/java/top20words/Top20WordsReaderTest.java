package top20words;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Yuriy Tumakha
 */
public class Top20WordsReaderTest implements ResourceReader {

    @ParameterizedTest
    @ValueSource(strings = {"gradlew", "mobydick.txt", "gradle/wrapper/gradle-wrapper.jar"})
    public void getTop20Words(String filename) throws IOException {
        OutputStream os = new ByteArrayOutputStream();

        long startTime = System.nanoTime();
        new Top20WordsReader(filename, new PrintStream(os));
        long elapsedTimeMs = (long) ((System.nanoTime() - startTime) / 1e6);

        System.out.println(String.format("File %s processed in %d ms", filename, elapsedTimeMs));

        String expectedOutput = getResourceContent(Paths.get(filename).getFileName() + "-top20.txt");
        assertThat(os.toString()).isEqualTo(expectedOutput);
    }


}
