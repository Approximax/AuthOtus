import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropReader {
    public String getProp (String key) throws IOException {
        Properties properties = new Properties();
        File file = new File("src/main/resources/conf.properties");
        properties.load(new FileReader(file));
        return properties.getProperty(key);
    }
}
