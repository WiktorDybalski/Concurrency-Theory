package Lab5;

import java.util.*;

public class FNFCreator {
    Map<Integer, List<Integer>> graph;
    List<Set<Integer>> fnf;
    Map<Integer, Integer> inDegree;

    public FNFCreator(Map<Integer, List<Integer>> originalGraph) {
        this.graph = originalGraph;
        this.fnf = new ArrayList<>();
        this.inDegree = new HashMap<>();
    }

    private void printFNF(Map<Integer, Character> intToChar) {
        System.out.print("Foata Normal Form (FNF): ");
        for (Set<Integer> layer : fnf) {
            System.out.print("[");
            for (Integer node : layer) {
                System.out.print(intToChar.get(node));
            }
            System.out.print("]");
        }
        System.out.println();
    }

    public void createFNF(Map<Integer, Character> intToChar) {
        for (Integer node : graph.keySet()) {
            inDegree.put(node, 0);
        }
        for (Integer node : inDegree.keySet()) {
            for (Integer neighbour : graph.get(node)) {
                inDegree.put(neighbour, inDegree.get(neighbour) + 1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Integer node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            Set<Integer> currentLayer = new HashSet<>(queue);
            fnf.add(currentLayer);

            Queue<Integer> nextQueue = new LinkedList<>();
            for (Integer node : queue) {
                for (Integer neighbour : graph.getOrDefault(node, Collections.emptyList())) {
                    inDegree.put(neighbour, inDegree.get(neighbour) - 1);
                    if (inDegree.get(neighbour) == 0) {
                        nextQueue.add(neighbour);
                    }
                }
            }
            queue = nextQueue;
        }
        printFNF(intToChar);
    }
}
