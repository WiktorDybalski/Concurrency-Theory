package Lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputManager {

    private Map<Integer, Character> intToChar;
    private Map<Character, List<Integer>> charToInt;
    private List<String> operations;
    private String w;

    public InputManager() {
        this.intToChar = new HashMap<>();
        this.charToInt = new HashMap<>();
        this.operations = new ArrayList<>();
    }

    public Map<Integer, Character> getIntToChar() {
        return intToChar;
    }

    public Map<Character, List<Integer>> getCharToInt() {
        return charToInt;
    }

    public String getW() {
        return w;
    }

    public void readInput() {
        String filePath = "src/main/resources/input";
        System.out.println("Input file: ");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == '(') {
                    operations.add(line.charAt(1) + "|" + line.substring(4));
                } else if (line.charAt(0) == 'w') {
                    w = line.substring(4);
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------");
    }

    public void createIntCharMaps() {
        for (int i = 0; i < w.length(); i++) {
            char currentChar = w.charAt(i);
            intToChar.put(i, currentChar);

            charToInt.putIfAbsent(currentChar, new ArrayList<>());
            charToInt.get(currentChar).add(i);
        }
    }

    public Map<Character, List<Character>> readAndFormatInput() {
        readInput();
        createIntCharMaps();
        Map<Character, List<Character>> characterListMap = new HashMap<>();

        for (String operation : operations) {
            String[] parts = operation.split("\\|");
            if (parts.length != 2) {
                System.err.println("Incorrect format of operation: " + operation);
                continue;
            }
            char mapKey = parts[0].charAt(0);
            String[] sides = parts[1].split(":=");

            if (sides.length != 2) {
                System.err.println("Incorrect format of operation: " + parts[1]);
                continue;
            }

            char leftSideVariable = sides[0].charAt(0);
            char[] rightSideCharacters = sides[1].replaceAll("[^a-zA-Z]", "").toCharArray();
            List<Character> variableList = new ArrayList<>();
            variableList.add(leftSideVariable);
            for (char c : rightSideCharacters) {
                variableList.add(c);
            }
            characterListMap.put(mapKey, variableList);
        }
        return characterListMap;
    }
}
