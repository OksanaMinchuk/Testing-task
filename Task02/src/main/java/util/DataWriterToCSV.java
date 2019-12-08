package util;

import java.io.*;
import java.util.List;

public class DataWriterToCSV {

    public static void writeFile(List<String> wantedElements) {

        String[] strings = wantedElements.toArray(new String[wantedElements.size()]);
        String outputFilePath = PropertyReader.getValue("resultFile");

        try (PrintWriter writer = new PrintWriter(new File(outputFilePath))) {

            for (int i = 0; i < strings.length; i++) {
                writer.write(strings[i]);
                writer.write(";");
            }

        } catch (IOException e) {
            System.err.println("Some error occured while Writing from File");
            e.printStackTrace();
        }
    }
}
