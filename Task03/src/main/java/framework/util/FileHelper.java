package framework.util;

import org.apache.log4j.Logger;

import java.io.File;

public class FileHelper {


    private static final Logger LOGGER = Logger.getRootLogger();

    public static void cleanDirectory() {
        File dir = new File(Constant.DOWNLOAD_DIR_PATH);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        } else {
            LOGGER.error("method: cleanDirectory : Directory is not exists or is not a directory.");
        }
    }

    public static File getFileFromDir() {
        File dir = new File(Constant.DOWNLOAD_DIR_PATH);
        File downloadFile = null;
        if (dir.exists() && dir.isDirectory()) {
            try {
                Thread.sleep(Constant.WAIT_DOWNLOAD_TIME);
            } catch (InterruptedException e) {
                LOGGER.error("The thread is interrupted, either before or during the activity." + e);
            }
            File[] files = dir.listFiles();

            if (files.length != 0) {
                downloadFile = files[0];
            } else {
                LOGGER.error("method: getFileFromDir : Directory is empty.");
            }
        } else {
            LOGGER.error("method: getFileFromDir : Directory is not exists or is not a directory.");
        }
        return downloadFile;
    }

}