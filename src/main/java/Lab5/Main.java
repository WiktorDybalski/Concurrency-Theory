package Lab5;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Read and format input from Input file, create maps
        InputManager inputManager = new InputManager();
        Map<Character, List<Character>> formatedInput = inputManager.readAndFormatInput();
        System.out.println("Formatted equations: " + formatedInput);

        // Creating Dependence and Independence Lists
        List<List<Pair<Character, Character>>> dependencyArrays = DependencyBuilder.createDependencyLists(formatedInput);

        // Creating, filling and minimizing the dependency graph
        GraphManager graphManager = new GraphManager(inputManager.getW());
        graphManager.fillGraph(inputManager.getCharToInt(), dependencyArrays.getFirst(), inputManager.getW());
        graphManager.minimizeGraph(inputManager.getW());

        // Creating the Foata Normal Form
        FNFCreator fnfCreator = new FNFCreator(graphManager.getGraph());
        fnfCreator.createFNF(inputManager.getIntToChar());

        // Presenting graph using jgraphx library
        GraphPresenter presenter = new GraphPresenter(graphManager.getGraph());
        presenter.presentGraph(inputManager.getIntToChar());
        presenter.setVisible(true);
    }
}