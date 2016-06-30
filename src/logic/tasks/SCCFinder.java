package logic.tasks;

import logic.graph.Graph;
import logic.graph.OrientedGraph;
import logic.util.Visualizer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SCCFinder {

    private static OrientedGraph graph;
    private static List<Integer> scc;

    public static void findSCC(OrientedGraph graph) {
        scc = new ArrayList<>();
        SCCFinder.graph = graph;
        int id = -1;
        for (Graph.GraphNode graphNode : SCCFinder.graph.getNodeList()) {
            if (graphNode != null && graphNode.getId() != 0) {
                id = graphNode.getId();
                break;
            }
        }
        graph.dfs(id);
        List<Integer> sortedNodes = graph.sortNodesByEndTime();
        transpose();
        Visualizer.visualizeSpecialGraph(graph, "_files/dots/transposedGraph.dot");
        sortedDFS(sortedNodes);
        transpose();
        Visualizer.visualizeWithSCC(graph, SCCFinder.scc);
        graph.reset();
    }

    private static void transpose() {
        int[][] changedEdges = new int[graph.getNodeList().size()][graph.getNodeList().size()];
        for (int[] changedEdge : changedEdges) {
            for (int i : changedEdge) {
                i = 0;
            }
        }
        for (Graph.GraphNode graphNode : graph.getNodeList()) {
            if (graphNode != null) {
                for (int i = 0; i < graphNode.getNeighbours().size(); i++) {
                    Graph.GraphNode neighbour = graph.getNodeList().get(graphNode.getNeighbours().get(i));
                    if (graphNode.getNeighbours().contains(neighbour.getId())
                            && neighbour.getNeighbours().contains(graphNode.getId())) {
                        changedEdges[neighbour.getId()][graphNode.getId()] = 1;
                        changedEdges[graphNode.getId()][neighbour.getId()] = 1;
                    }
                    if (changedEdges[neighbour.getId()][graphNode.getId()] == 0) {
                        neighbour.addNeighbour(graphNode.getId());
                        graphNode.removeNeighbour(neighbour.getId());
                        changedEdges[graphNode.getId()][neighbour.getId()] = 1;
                        i--;
                    }
                }
            }
        }
    }

    private static void sortedDFS(List<Integer> sortedNodes) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("_files/dots/SCC.dot"));
            bw.write("digraph SCC {");
            bw.newLine();
            for (Integer sortedNode : sortedNodes) {
                Graph.GraphNode graphNode = graph.getNodeList().get(sortedNode);
                if (graphNode != null && !graphNode.isVisited()) {
                    sortedDFS(graphNode, bw);
                    scc.add(-1);
                }
            }
            bw.write("}");
            bw.close();
            for (Graph.GraphNode graphNode : graph.getNodeList()) {
                if (graphNode != null) {
                    graphNode.setVisited(false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Visualizer.createPNG("_files/dots/SCC.dot", "SCC");
    }

    private static void sortedDFS(Graph.GraphNode tempNode, BufferedWriter bw) throws IOException {
        tempNode.setVisited(true);
        scc.add(tempNode.getId());
        for (int i = 0; i < tempNode.getNeighbours().size(); i++) {
            if (graph.getNodeList().get(tempNode.getNeighbours().get(i)) != null &&
                    !graph.getNodeList().get(tempNode.getNeighbours().get(i)).isVisited()) {
                sortedDFS(graph.getNodeList().get(tempNode.getNeighbours().get(i)), bw);
                bw.write(tempNode.getId() + "->" + graph.getNodeList().get(tempNode.getNeighbours().get(i)).getId());
                bw.newLine();
            }
        }
        bw.write(tempNode.getId() + " [label = \"" + tempNode.getData() +
                " \\n [" + tempNode.getId() + "]\"]");
        bw.newLine();
    }

}
