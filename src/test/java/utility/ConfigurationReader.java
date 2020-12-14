package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
        private static Properties properties = new Properties();

        static {
            try {
                FileInputStream file = new FileInputStream("Configuration.properties");
                properties.load(file);
                file.close();
            } catch (IOException e) {
                System.out.println("Properties Not Found!!!" + e.getMessage());
            }
        }

        public static String getProperty(String keyword){
            return properties.getProperty(keyword);
        }

}
