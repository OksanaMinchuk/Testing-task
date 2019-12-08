package util;

import java.util.MissingResourceException;

import java.util.ResourceBundle;

public class PropertyReader {

    private static final String FILE_NAME = "data";

    private static ResourceBundle bundle = ResourceBundle.getBundle(FILE_NAME);

    public PropertyReader() {
    }

    public static String getValue(String key) {
        String result = null;
        if (key != null) {
            try {
                result = bundle.getString(key);
            } catch (MissingResourceException e) {
               e.printStackTrace();
            }
        }
        return result;
    }
}
