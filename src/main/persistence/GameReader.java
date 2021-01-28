package persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read game data from a file
public class GameReader {
    public static final String DELIMITER = ",";


    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Integer> readGame(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }


    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }


    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    private static List<Integer> parseContent(List<String> fileContent) {
        List<Integer> data = new ArrayList<>();

        String line = fileContent.get(0);
        ArrayList<String> lineComponents = splitString(line);
        for (String number : lineComponents) {
            int stringToNumber = Integer.parseInt(number);
            data.add(stringToNumber);
        }
        return data;
    }
}
