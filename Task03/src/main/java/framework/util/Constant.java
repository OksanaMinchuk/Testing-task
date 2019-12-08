package framework.util;

public class Constant {

    public static final String DOWNLOAD_DIR_PATH = System.getProperty("user.dir") + "/downloads";
    public static final String DOWNLOAD_FILE_NAME = "steam_latest.deb";
    public static final long DOWNLOAD_FILE_LENGHT = 2878572;

    public static final String LOCAL = DataPropertyReader.getValue("language");

    public static final int WAIT_DOWNLOAD_TIME = 2000;

    public static final int TIME_OUT_SEC = 5;

    public static final int WITH_TIME_OUT_SEC = 30;
    public static final int POLLING_EVERY_MLS = 100;

}
