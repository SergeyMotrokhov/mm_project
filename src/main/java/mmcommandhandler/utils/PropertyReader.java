package mmcommandhandler.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyReader {

    public static String getValue(String propertyKey) {
        String result = null;

        try (InputStream input = new FileInputStream("src/main/resources/mattermost.properties")) {
            Properties props = new Properties();
            props.load(input);
            result = props.getProperty(propertyKey);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
