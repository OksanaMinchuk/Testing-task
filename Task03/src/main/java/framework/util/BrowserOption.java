package framework.util;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;

/**
 * Setting for browsers
 */

public class BrowserOption {

    public static ChromeOptions getChromeOptions() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("intl.accept_languages", DataPropertyReader.getValue("language"));
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", Constant.DOWNLOAD_DIR_PATH);
        chromePrefs.put("safebrowsing.enabled", "true");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        return chromeOptions;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setPreference("intl.accept_languages", DataPropertyReader.getValue("language"));
        profile.setPreference("browser.download.dir", Constant.DOWNLOAD_DIR_PATH);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-debian-package");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWhenStarting",false);
        firefoxOptions.setProfile(profile);
        return firefoxOptions;
    }

}
