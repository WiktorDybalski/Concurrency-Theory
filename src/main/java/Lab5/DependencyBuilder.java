package Lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DependencyBuilder {

    private static void printDAndILists(List<Pair<Character, Character>> D, List<Pair<Character, Character>> I) {
        System.out.println("Dependence and Independence Lists:");
        System.out.println("D = " + D);
        System.out.println("I = " + I);
        System.out.println("---------------------------");
    }

    private static boolean isDependent(Map.Entry<Character, List<Character>> entry1, Map.Entry<Character, List<Character>> entry2) {
        List<Character> tempEntry1 = entry1.getValue();
        List<Character> tempEntry2 = entry2.getValue();
        return tempEntry2.contains(entry1.getValue().get(0)) || tempEntry1.contains(entry2.getValue().get(0));
    }

    public static List<List<Pair<Character, Character>>> createDependencyLists(Map<Character, List<Character>> input) {
        List<Pair<Character, Character>> dependencyList = new ArrayList<>();
        List<Pair<Character, Character>> independencyList = new ArrayList<>();

        for (Map.Entry<Character, List<Character>> entry1 : input.entrySet()) {
            for (Map.Entry<Character, List<Character>> entry2 : input.entrySet()) {
                if (isDependent(entry1, entry2)) {
                    dependencyList.add(new Pair<>(entry1.getKey(), entry2.getKey()));
                } else {
                    independencyList.add(new Pair<>(entry1.getKey(), entry2.getKey()));
                }
            }
        }
        printDAndILists(dependencyList, independencyList);
        return new ArrayList<>(Arrays.asList(dependencyList, independencyList));
    }
}
