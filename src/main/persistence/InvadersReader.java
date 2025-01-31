package persistence;

import model.Invader;
import model.InvadersList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvadersReader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a Invaders list parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static InvadersList readInvaders(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of invader parsed from list of strings
    // where each string contains data for one invader
    private static InvadersList parseContent(List<String> fileContent) {
        InvadersList invaders = new InvadersList();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            invaders.addInvader(parseInvader(lineComponents));
        }

        return invaders;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 2 where element 0 represents the
    // xcoor, 1 represents ycoor.
    // EFFECTS: returns an account constructed from components
    private static Invader parseInvader(List<String> components) {
        int xcoor = Integer.parseInt(components.get(0));
        int ycoor = Integer.parseInt(components.get(1));
        return new Invader(xcoor,ycoor);
    }
}
