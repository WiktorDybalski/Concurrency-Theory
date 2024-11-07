package Lab5;

import java.util.*;

public class GraphManager {
    private Map<Integer, List<Integer>> graph;

    public GraphManager(String w) {
        createGraph(w.length());
    }

    public Map<Integer, List<Integer>> getGraph() {
        return graph;
    }

    public void createGraph(int n) {
        graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int start, int end) {
        graph.get(start).add(end);
    }

    public void removeEdge(int start, int end) {
        if (graph.containsKey(start)) {
            graph.get(start).remove(Integer.valueOf(end));
        }
    }

    public boolean hasPath(int start, int end) {
        Set<Integer> visited = new HashSet<>();
        return hasPathDFS(start, end, visited);
    }

    private boolean hasPathDFS(int current, int end, Set<Integer> visited) {
        if (current == end) return true;
        visited.add(current);
        for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
            if (!visited.contains(neighbor) && hasPathDFS(neighbor, end, visited)) {
                return true;
            }
        }
        return false;
    }

    public void fillGraph(Map<Character, List<Integer>> charToInt, List<Pair<Character, Character>> D, String w) {
        for (int index = 0; index < w.length(); index++) {
            char currChar = w.charAt(index);
            for (Pair<Character, Character> pair : D) {
                if (pair.first() == currChar) {
                    char newChar = pair.second();
                    List<Integer> numbers = charToInt.get(newChar);
                    for (Integer number : numbers) {
                        if (number > index) {
                            addEdge(index, number);
                        }
                    }
                }
            }
        }
        for (List<Integer> neighbors : graph.values()) {
            Collections.sort(neighbors);
        }
        printGraph("Filled Graph: ");
    }

    public void minimizeGraph(String w) {
        for (int index = 0; index < w.length(); index++) {
            List<Integer> neighbors = new ArrayList<>(graph.get(index));
            neighbors.sort(Collections.reverseOrder());

            for (Integer neighbor : neighbors) {
                removeEdge(index, neighbor);

                if (!hasPath(index, neighbor)) {
                    addEdge(index, neighbor);
                }
            }
        }
        for (List<Integer> neighbors : graph.values()) {
            Collections.sort(neighbors);
        }
        printGraph("Minimized Graph: ");
    }

    public void printGraph(String message) {
        System.out.println(message);
        for (int vertex : graph.keySet()) {
            System.out.print(vertex + " -> ");
            for (int neighbor : graph.get(vertex)) {
                System.out.print(neighbor + " ");
            }
            if (graph.get(vertex).isEmpty()) {
                System.out.print("[]");
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }
}

