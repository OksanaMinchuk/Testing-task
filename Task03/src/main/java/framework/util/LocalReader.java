package framework.util;

import org.apache.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocalReader {

    private static final Logger LOGGER = Logger.getRootLogger();

    private static ResourceBundle bundle = ResourceBundle.getBundle(getLocalFileName());

    public LocalReader() {
    }

    private static String getLocalFileName() {
        String localFileName = null;
        if (Constant.LOCAL.equals("en")) {
            localFileName = "local_en";
        } else if (Constant.LOCAL.equals("ru")) {
            localFileName = "local_ru";
        } else {
            LOGGER.error("Localization file for LOCAL=" + Constant.LOCAL+ " not found.");
        }
        return localFileName;
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
