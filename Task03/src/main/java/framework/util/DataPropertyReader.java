package framework.util;

import org.apache.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DataPropertyReader {

    private static final Logger LOGGER = Logger.getRootLogger();
    private static final String CONFIG_FILE_NAME = "data";

    private static ResourceBundle bundle = ResourceBundle.getBundle(CONFIG_FILE_NAME);

    public DataPropertyReader() {
    }

    public static String getValue(String key) {
        String result = null;
        if (key != null) {
            try {
                result = bundle.getString(key);
            } catch (MissingResourceException e) {
               LOGGER.error("Parameter: " + key + " not found.", e);
            }
        }
        return result;
    }
}
