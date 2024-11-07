package Lab5;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GraphPresenter extends JFrame {
    private Map<Integer, List<Integer>> graph;
    private static final Color BACKGROUND_COLOR = new Color(30, 30, 30);
    private static final Color VERTEX_COLOR = new Color(70, 130, 180);
    private static final Color EDGE_COLOR = new Color(200, 200, 200);
    private static final Color TEXT_COLOR = new Color(240, 240, 240);

    public GraphPresenter(Map<Integer, List<Integer>> graph) {
        this.graph = graph;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.8);
        int height = (int) (screenSize.height * 0.8);

        setSize(width, height);

        setLocationRelativeTo(null);

        getContentPane().setBackground(BACKGROUND_COLOR);

        setLayout(new BorderLayout());
    }

    private mxStylesheet createCustomStylesheet() {
        mxStylesheet stylesheet = new mxStylesheet();

        Hashtable<String, Object> vertexStyle = new Hashtable<>();
        vertexStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        vertexStyle.put(mxConstants.STYLE_FILLCOLOR, String.format("#%02x%02x%02x",
                VERTEX_COLOR.getRed(), VERTEX_COLOR.getGreen(), VERTEX_COLOR.getBlue()));
        vertexStyle.put(mxConstants.STYLE_STROKECOLOR, "#FFFFFF");
        vertexStyle.put(mxConstants.STYLE_FONTCOLOR, String.format("#%02x%02x%02x",
                TEXT_COLOR.getRed(), TEXT_COLOR.getGreen(), TEXT_COLOR.getBlue()));
        vertexStyle.put(mxConstants.STYLE_STROKEWIDTH, 2);
        vertexStyle.put(mxConstants.STYLE_ROUNDED, true);
        vertexStyle.put(mxConstants.STYLE_SHADOW, true);
        vertexStyle.put(mxConstants.STYLE_FONTSIZE, 14);
        vertexStyle.put(mxConstants.STYLE_FONTSTYLE, Font.BOLD);

        Hashtable<String, Object> edgeStyle = new Hashtable<>();
        edgeStyle.put(mxConstants.STYLE_STROKECOLOR, String.format("#%02x%02x%02x",
                EDGE_COLOR.getRed(), EDGE_COLOR.getGreen(), EDGE_COLOR.getBlue()));
        edgeStyle.put(mxConstants.STYLE_STROKEWIDTH, 2.0);
        edgeStyle.put(mxConstants.STYLE_ROUNDED, true);
        edgeStyle.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ORTHOGONAL);

        stylesheet.putCellStyle("VERTEX", vertexStyle);
        stylesheet.putCellStyle("EDGE", edgeStyle);

        return stylesheet;
    }

    public void presentGraph(Map<Integer, Character> intToChar) {
        mxGraph mxGraph = new mxGraph();
        mxGraph.setStylesheet(createCustomStylesheet());
        Object parent = mxGraph.getDefaultParent();

        Map<Integer, Object> vertices = new HashMap<>();

        mxGraph.getModel().beginUpdate();
        try {
            for (Integer node : graph.keySet()) {
                Object vertex = mxGraph.insertVertex(parent, null, intToChar.get(node), 0, 0, 60, 60, "VERTEX");
                vertices.put(node, vertex);
            }

            for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
                Integer fromNode = entry.getKey();
                for (Integer toNode : entry.getValue()) {
                    mxGraph.insertEdge(parent, null, "", vertices.get(fromNode),
                            vertices.get(toNode), "EDGE");
                }
            }

        } finally {
            mxGraph.getModel().endUpdate();
        }

        mxCircleLayout layout = new mxCircleLayout(mxGraph);
        int radius = Math.min(getWidth(), getHeight()) / 3;
        layout.setRadius(radius);
        layout.execute(parent);

        mxGraphComponent graphComponent = new mxGraphComponent(mxGraph);
        graphComponent.setBackground(BACKGROUND_COLOR);
        graphComponent.getViewport().setBackground(BACKGROUND_COLOR);
        graphComponent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        graphComponent.setEnabled(false);

        add(graphComponent, BorderLayout.CENTER);
    }
}