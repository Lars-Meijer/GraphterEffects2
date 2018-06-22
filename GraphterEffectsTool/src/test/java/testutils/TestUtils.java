package testutils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class TestUtils {

    public static String readFromResources(String path) throws IOException {
        try {
            path = Objects.requireNonNull(TestUtils.class.getClassLoader().getResource(path)).toURI().getPath();
            path = System.getProperty("os.name").contains("indow") ? path.substring(1) : path;
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, Charset.defaultCharset());
        } catch (URISyntaxException e) {
            //This should never happen according to specification of getResource.
            System.exit(1);
            return null;
        }
    }
}
