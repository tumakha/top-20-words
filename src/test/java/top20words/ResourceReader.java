package top20words;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Yuriy Tumakha
 */
public interface ResourceReader {

    default Path getResourcePath(String filename) throws IOException {
        URL resource = getClass().getClassLoader().getResource(filename);
        if (resource != null) {
            try {
                return Paths.get(resource.toURI());
            } catch (URISyntaxException e) {
                throw new IOException(e);
            }
        } else {
            throw new IOException("File not found: " + filename);
        }
    }

    default String getResourceContent(String filename) throws IOException {
        return Files.readString(getResourcePath(filename), UTF_8);
    }

}
