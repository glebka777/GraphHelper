package logic.util;

import logic.graph.Graph;
import logic.graph.OrientedGraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class Visualizer {

    public static void visualizeGraph(Graph graph) {
        if (graph instanceof OrientedGraph) {
            visualizeOrientedGraph((OrientedGraph) graph);
            return;
        }
        Vector<Graph.GraphNode> nodeList = graph.getNodeList();
        String fileName;
        fileName = "_files/dots/graph.dot";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("graph MyGraph {");
            bw.newLine();
            for (int i = 1; i < nodeList.size(); i++) {
                if (nodeList.get(i) != null) {
                    for (int j = 0; j < nodeList.get(i).getNeighbours().size(); j++) {
                        if ((nodeList.get(nodeList.get(i).getNeighbours().get(j)) != null) && (!nodeList
                                .get(nodeList.get(i).getNeighbours().get(j)).isVisited())) {
                            Graph.Edge edge = graph.getEdge(i, nodeList.get(i).getNeighbours().get(j));
                            if (edge.getWeight() != 0) {
                                bw.write(i + "--" + nodeList.get(i).getNeighbours().get(j) + "[ label= " + edge.getWeight() + "];");
                            }
                            else {
                                bw.write(i + "--" + nodeList.get(i).getNeighbours().get(j) + ";");
                            }
                            bw.newLine();
                        }
                    }
                    nodeList.get(i).setVisited(true);
                    bw.write(nodeList.get(i).getId() + " [label = \"" + nodeList.get(i).getData() +
                            "\\n [" + nodeList.get(i).getId() + "]\"]");
                    bw.newLine();
                }
            }
            bw.write("}");
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (Graph.GraphNode graphNode : nodeList) {
            if (graphNode != null) {
                graphNode.setVisited(false);
            }
        }
        createPNG(fileName, "graph");
    }

    public static void visualizeOrientedGraph(OrientedGraph graph) {
        Vector<Graph.GraphNode> nodeList = graph.getNodeList();
        String fileName;
        fileName = "_files/dots/graph.dot";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("digraph MyGraph {");
            bw.newLine();
            for (int i = 1; i < nodeList.size(); i++) {
                if (nodeList.get(i) != null) {
                    for (int j = 0; j < nodeList.get(i).getNeighbours().size(); j++) {
                        if ((nodeList.get(nodeList.get(i).getNeighbours().get(j)) != null) && (!nodeList.get(i).isVisited())) {
                            Graph.Edge edge = graph.getEdge(i, nodeList.get(i).getNeighbours().get(j));
                            if (edge.getWeight() != 0) {
                                bw.write(nodeList.get(i).getId() + "->" + nodeList.get(i).getNeighbours().get(j) + "[ label= " + edge.getWeight() + "];");
                            }
                            else {
                                bw.write(nodeList.get(i).getId() + "->" + nodeList.get(i).getNeighbours().get(j) + ";");
                            }
                            bw.newLine();
                        }
                    }
                    nodeList.get(i).setVisited(true);
                    bw.write(nodeList.get(i).getId() + " [label = \"" + nodeList.get(i).getData() +
                            "\\n [" + nodeList.get(i).getId() + "]\"]");
                    bw.newLine();
                }
            }
            bw.write("}");
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (Graph.GraphNode graphNode : nodeList) {
            if (graphNode != null) {
                graphNode.setVisited(false);
            }
        }
        createPNG(fileName, "graph");
    }

    public static void visualizeMatrixGraph(Graph graph) {
        if (graph instanceof OrientedGraph) {
            visualizeMatrixOrientedGraph((OrientedGraph) graph);
            return;
        }
        Vector<Graph.GraphNode> nodeList = graph.getNodeList();
        String fileName;
        fileName = "_files/dots/matrixGraph.dot";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("graph MyGraph {");
            bw.newLine();
            for (int i = 1; i < nodeList.size(); i++) {
                if (nodeList.get(i) != null) {
                    for (int j = 0; j < nodeList.get(i).getNeighbours().size(); j++) {
                        if ((nodeList.get(nodeList.get(i).getNeighbours().get(j)) != null) && (!nodeList
                                .get(nodeList.get(i).getNeighbours().get(j)).isVisited())) {
                            Graph.Edge edge = graph.getEdge(i, nodeList.get(i).getNeighbours().get(j));
                            if (edge.getWeight() != 0) {
                                bw.write(i + "--" + nodeList.get(i).getNeighbours().get(j) + "[ label= " + edge.getWeight() + "];");
                            }
                            else {
                                bw.write(i + "--" + nodeList.get(i).getNeighbours().get(j) + ";");
                            }
                            bw.newLine();
                        }
                    }
                    nodeList.get(i).setVisited(true);
                    bw.write(nodeList.get(i).getId() + " [label = \"" + nodeList.get(i).getData() +
                            "\\n [" + nodeList.get(i).getId() + "]\"]");
                    bw.newLine();
                }
            }
            bw.write("}");
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (Graph.GraphNode graphNode : nodeList) {
            if (graphNode != null) {
                graphNode.setVisited(false);
            }
        }
        createPNG(fileName, "matrixGraph");
    }

    public static void visualizeMatrixOrientedGraph(OrientedGraph graph) {
        Vector<Graph.GraphNode> nodeList = graph.getNodeList();
        String fileName;
        fileName = "_files/dots/matrixGraph.dot";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("digraph MyGraph {");
            bw.newLine();
            for (int i = 1; i < nodeList.size(); i++) {
                if (nodeList.get(i) != null) {
                    for (int j = 0; j < nodeList.get(i).getNeighbours().size(); j++) {
                        if ((nodeList.get(nodeList.get(i).getNeighbours().get(j)) != null) && (!nodeList.get(i).isVisited())) {
                            Graph.Edge edge = graph.getEdge(i, nodeList.get(i).getNeighbours().get(j));
                            if (edge.getWeight() != 0) {
                                bw.write(nodeList.get(i).getId() + "->" + nodeList.get(i).getNeighbours().get(j) + "[ label= " + edge.getWeight() + "];");
                            }
                            else {
                                bw.write(nodeList.get(i).getId() + "->" + nodeList.get(i).getNeighbours().get(j) + ";");
                            }
                            bw.newLine();
                        }
                    }
                    nodeList.get(i).setVisited(true);
                    bw.write(nodeList.get(i).getId() + " [label = \"" + nodeList.get(i).getData() +
                            "\\n [" + nodeList.get(i).getId() + "]\"]");
                    bw.newLine();
                }
            }
            bw.write("}");
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (Graph.GraphNode graphNode : nodeList) {
            if (graphNode != null) {
                graphNode.setVisited(false);
            }
        }
        createPNG(fileName, "matrixGraph");
    }

    public static void visualizeSpecialGraph(OrientedGraph graph, String fileName) {
        Vector<Graph.GraphNode> nodeList = graph.getNodeList();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("digraph MyGraph {");
            bw.newLine();
            for (int i = 1; i < nodeList.size(); i++) {
                if (nodeList.get(i) != null) {
                    for (int j = 0; j < nodeList.get(i).getNeighbours().size(); j++) {
                        if ((nodeList.get(nodeList.get(i).getNeighbours().get(j)) != null) && (!nodeList.get(i).isVisited())) {
                            bw.write(nodeList.get(i).getId() + "->" + nodeList.get(i).getNeighbours().get(j) + ";");
                            bw.newLine();
                        }
                    }
                    nodeList.get(i).setVisited(true);
                    bw.write(nodeList.get(i).getId() + " [label = \"" + nodeList.get(i).getData() +
                            "\\n [" + nodeList.get(i).getId() + "]\"]");
                    bw.newLine();
                }
            }
            bw.write("}");
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (Graph.GraphNode graphNode : nodeList) {
            if (graphNode != null) {
                graphNode.setVisited(false);
            }
        }
        String pngType = fileName.split("/")[fileName.split("/").length - 1].replace(".dot", "");
        createPNG(fileName, pngType);
    }

    public static void visualizeSortedGraph(OrientedGraph graph, List<Integer> sortedNodes) {
        Vector<Graph.GraphNode> nodeList = graph.getNodeList();
        String fileName;
        fileName = "_files/dots/sortedgraph.dot";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("digraph MyGraph {");
            bw.newLine();
            for (Integer sortedNode : sortedNodes) {
                if (nodeList.get(sortedNode) != null) {
                    for (int j = 0; j < nodeList.get(sortedNode).getNeighbours().size(); j++) {
                        if ((nodeList.get(nodeList.get(sortedNode).getNeighbours().get(j)) != null)
                                && (!nodeList.get(sortedNode).isVisited())) {
                            bw.write(nodeList.get(sortedNode).getId() + "->"
                                    + nodeList.get(sortedNode).getNeighbours().get(j) + ";");
                            bw.newLine();
                        }
                    }
                    nodeList.get(sortedNode).setVisited(true);
                    bw.write(nodeList.get(sortedNode).getId() + " [label = \"" + nodeList.get(sortedNode).getData() +
                            "\\n [" + nodeList.get(sortedNode).getId() + "]\"]");
                    bw.newLine();
                }
            }
            bw.write("{ rank = same; ");
            for (Integer sortedNode : sortedNodes) {
                if (sortedNode != null) {
                    bw.write(sortedNode + " ");
                }
            }
            bw.write("}");
            bw.newLine();
            bw.write("}");
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (Graph.GraphNode graphNode : nodeList) {
            if (graphNode != null) {
                graphNode.setVisited(false);
            }
        }
        createPNG(fileName, "sortedgraph");
    }

    public static void visualizeWithSCC(OrientedGraph graph, List<Integer> SCC) {
        Vector<Graph.GraphNode> nodeList = graph.getNodeList();
        String fileName;
        fileName = "_files/dots/CSCGraph.dot";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("digraph MyGraph {");
            bw.newLine();
            for (int i = 1; i < nodeList.size(); i++) {
                if (nodeList.get(i) != null) {
                    for (int j = 0; j < nodeList.get(i).getNeighbours().size(); j++) {
                        if ((nodeList.get(nodeList.get(i).getNeighbours().get(j)) != null) && (!nodeList.get(i).isVisited())) {
                            bw.write(nodeList.get(i).getId() + "->" + nodeList.get(i).getNeighbours().get(j) + ";");
                            bw.newLine();
                        }
                    }
                    nodeList.get(i).setVisited(true);
                    bw.write(nodeList.get(i).getId() + " [label = \"" + nodeList.get(i).getData() +
                            "\\n [" + nodeList.get(i).getId() + "]\"]");
                    bw.newLine();
                }
            }
            bw.newLine();
            writeCluster(SCC, bw);
            bw.newLine();
            bw.write("}");
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (Graph.GraphNode graphNode : nodeList) {
            if (graphNode != null) {
                graphNode.setVisited(false);
            }
        }
        createPNG(fileName, "CSCGraph");
    }

    public static void visualizeWithArtPoints(Graph graph, List<Integer> artPoints) {
        Vector<Graph.GraphNode> nodeList = graph.getNodeList();
        String fileName;
        fileName = "_files/dots/graphWithArtPoints.dot";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("graph MyGraph {");
            bw.newLine();
            for (int i = 1; i < nodeList.size(); i++) {
                if (nodeList.get(i) != null) {
                    for (int j = 0; j < nodeList.get(i).getNeighbours().size(); j++) {
                        if ((nodeList.get(nodeList.get(i).getNeighbours().get(j)) != null) && (!nodeList
                                .get(nodeList.get(i).getNeighbours().get(j)).isVisited())) {
                            bw.write(i + "--" + nodeList.get(i).getNeighbours().get(j) + ";");
                            bw.newLine();
                        }
                    }
                    nodeList.get(i).setVisited(true);
                    if (artPoints != null && artPoints.contains(nodeList.get(i).getId())) {
                        bw.write(nodeList.get(i).getId() + " [style=filled, fillcolor=green label = \"" + nodeList.get(i).getData() +
                                "\\n [" + nodeList.get(i).getId() + "]\"]");
                    }
                    else {
                        bw.write(nodeList.get(i).getId() + " [label = \"" + nodeList.get(i).getData() +
                                "\\n [" + nodeList.get(i).getId() + "]\"]");
                    }
                    bw.newLine();
                }
            }
            bw.write("}");
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (Graph.GraphNode graphNode : nodeList) {
            if (graphNode != null) {
                graphNode.setVisited(false);
            }
        }
        createPNG(fileName, "graphWithArtPoints");
    }

    public static void visualizeWithShortestPath(OrientedGraph graph) {
        Vector<Graph.GraphNode> nodeList = graph.getNodeList();
        String fileName;
        fileName = "_files/dots/graphWithShortestPath.dot";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("digraph MyGraph {");
            bw.newLine();
            for (int i = 1; i < nodeList.size(); i++) {
                if (nodeList.get(i) != null) {
                    for (int j = 0; j < nodeList.get(i).getNeighbours().size(); j++) {
                        if ((nodeList.get(nodeList.get(i).getNeighbours().get(j)) != null) && (!nodeList.get(i).isVisited())) {
                            Graph.Edge edge = graph.getEdge(i, nodeList.get(i).getNeighbours().get(j));
                            if (edge.getWeight() != 0) {
                                if (!graph.getEdge(nodeList.get(i).getId(), nodeList.get(i).getNeighbours().get(j)).isInPath()) {
                                    bw.write(nodeList.get(i).getId() + "->" + nodeList.get(i).getNeighbours().get(j) + "[ label= " + edge.getWeight() + "];");
                                }
                                else {
                                    bw.write(nodeList.get(i).getId() + "->" + nodeList.get(i).getNeighbours().get(j) + "[ label= " + edge.getWeight() + " color = green" + "];");
                                    bw.newLine();
                                    bw.write(nodeList.get(i).getId() + " [ color = green ]");
                                    bw.write(nodeList.get(i).getNeighbours().get(j) + " [ color = green ]");
                                }
                            }
                            else {
                                if (!graph.getEdge(nodeList.get(i).getId(), nodeList.get(i).getNeighbours().get(j)).isInPath()) {
                                    bw.write(nodeList.get(i).getId() + "->" + nodeList.get(i).getNeighbours().get(j) + ";");
                                }
                                else {
                                    bw.write(nodeList.get(i).getId() + "->" + nodeList.get(i).getNeighbours().get(j) + "[ color = green" + "];");
                                    bw.newLine();
                                    bw.write(nodeList.get(i).getId() + " [ color = green ]");
                                    bw.write(nodeList.get(i).getNeighbours().get(j) + " [ color = green ]");
                                }
                            }
                            bw.newLine();
                        }
                    }
                    nodeList.get(i).setVisited(true);
                    bw.write(nodeList.get(i).getId() + " [label = \"" + nodeList.get(i).getData() +
                            "\\n [" + nodeList.get(i).getId() + "]\"]");
                    bw.newLine();
                    bw.newLine();
                }
            }
            bw.write("}");
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (Graph.GraphNode graphNode : nodeList) {
            if (graphNode != null) {
                graphNode.setVisited(false);
            }
        }
        createPNG(fileName, "graphWithShortestPath");
    }

    public static void visualizeWithTree(Graph graph) {
        Vector<Graph.GraphNode> nodeList = graph.getNodeList();
        String fileName;
        fileName = "_files/dots/graphWithTree.dot";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("graph MyGraph {");
            bw.newLine();
            for (int i = 1; i < nodeList.size(); i++) {
                if (nodeList.get(i) != null) {
                    for (int j = 0; j < nodeList.get(i).getNeighbours().size(); j++) {
                        if ((nodeList.get(nodeList.get(i).getNeighbours().get(j)) != null) && (!nodeList.get(i).isVisited())) {
                            Graph.Edge edge = graph.getEdge(i, nodeList.get(i).getNeighbours().get(j));
                            if (!edge.isWritten()) {
                                if (edge.getWeight() != 0) {
                                    if (!graph.getEdge(nodeList.get(i).getId(), nodeList.get(i).getNeighbours().get(j)).isInPath()) {
                                        bw.write(nodeList.get(i).getId() + "--" + nodeList.get(i).getNeighbours().get(j) + "[ label= " + edge.getWeight() + "];");
                                    }
                                    else {
                                        bw.write(nodeList.get(i).getId() + "--" + nodeList.get(i).getNeighbours().get(j) + "[ label= " + edge.getWeight() + " color = orange" + "];");
                                        bw.newLine();
                                        bw.write(nodeList.get(i).getId() + " [ color = orange ]");
                                        bw.write(nodeList.get(i).getNeighbours().get(j) + " [ color = orange ]");
                                    }
                                }
                                else {
                                    if (!graph.getEdge(nodeList.get(i).getId(), nodeList.get(i).getNeighbours().get(j)).isInPath()) {
                                        bw.write(nodeList.get(i).getId() + "--" + nodeList.get(i).getNeighbours().get(j) + ";");
                                    }
                                    else {
                                        bw.write(nodeList.get(i).getId() + "--" + nodeList.get(i).getNeighbours().get(j) + "[ color = orange" + "];");
                                        bw.newLine();
                                        bw.write(nodeList.get(i).getId() + " [ color = orange ]");
                                        bw.write(nodeList.get(i).getNeighbours().get(j) + " [ color = orange ]");
                                    }
                                }
                                edge.setWritten(true);
                            }
                            bw.newLine();
                        }
                    }
                    nodeList.get(i).setVisited(true);
                    bw.write(nodeList.get(i).getId() + " [label = \"" + nodeList.get(i).getData() +
                            "\\n [" + nodeList.get(i).getId() + "]\"]");
                    bw.newLine();
                    bw.newLine();
                }
            }
            bw.write("}");
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (Graph.GraphNode graphNode : nodeList) {
            if (graphNode != null) {
                graphNode.setVisited(false);
            }
        }
        Vector<Graph.Edge> edgeList = graph.getEdgeList();
        for (Graph.Edge edge : edgeList) {
            if (edge != null) {
                edge.setWritten(false);
            }
        }
        createPNG(fileName, "graphWithTree");
    }

    public static void createPNG(String fileName, String pngType) {
        String[] args;
        if (pngType.equals("matrixGraph")) {
            args = new String[]{"sfdp", "-x", "-Goverlap=scale", "-Tpng", fileName, "-o", "_files/pngs/" + pngType + ".png"};
        }
        else {
            args = new String[]{"dot", "-Tpng", "-Gsize=3,5\\!", "-Gdpi=300", fileName, "-o", "_files/pngs/" + pngType + ".png"};
        }
        try {
            Runtime rt = Runtime.getRuntime();
            Process dotCall = rt.exec(args);
            dotCall.waitFor();
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void writeCluster(List<Integer> scc, BufferedWriter bw) throws IOException {
        int sccCounter = 1;
        bw.write(" subgraph cluster_scc1 {");
        bw.newLine();
        for (int i = 0; i < scc.size(); i++) {
            int node = scc.get(i);
            if (node != -1) {
                bw.write(" " + node + "; ");
            }
            else if (i != scc.size() - 1 && scc.get(i + 1) != -1) {
                bw.newLine();
                bw.write(" label = SCC_" + sccCounter + ";");
                bw.newLine();
                bw.write("graph[style=rounded, color=blue];");
                bw.newLine();
                bw.write(" }");
                bw.newLine();
                sccCounter++;
                bw.write(" subgraph cluster_SCC" + sccCounter + " {");
                bw.newLine();
            }
        }
        bw.newLine();
        bw.write(" label = SCC_" + sccCounter + ";");
        bw.newLine();
        bw.write("graph[style=rounded, color=blue];");
        bw.newLine();
        bw.write(" }");
    }

}
