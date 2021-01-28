package persistence;


import model.Missile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissilesReader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of Missile parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static ArrayList<Missile> readMissiles(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of Missiles parsed from list of strings
    // where each string contains data for one Missile
    private static ArrayList<Missile> parseContent(List<String> fileContent) {
        ArrayList<Missile> missiles = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            missiles.add(parseInvader(lineComponents));
        }

        return missiles;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 3 where element 0 represents the
    // xcoor, 1 represent ycoor, 2 represents the missileType
    // EFFECTS: returns an account constructed from components
    private static Missile parseInvader(List<String> components) {
        int xcoor = Integer.parseInt(components.get(0));
        int ycoor = Integer.parseInt(components.get(1));
        Boolean missileType = Boolean.parseBoolean(components.get(2));
        return new Missile(xcoor,ycoor,missileType);
    }
}
