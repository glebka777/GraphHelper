package logic.tasks;

import logic.graph.Graph;
import logic.graph.OrientedGraph;

import java.util.ArrayList;

public class CycleFinder {

    private static OrientedGraph graph;
    private static ArrayList<String> backEdges = null;
    private static boolean acyclic;

    public static ArrayList<String> checkAcyclic(OrientedGraph graph) {
        CycleFinder.graph = graph;
        int id = -1;
        for (Graph.GraphNode graphNode : CycleFinder.graph.getNodeList()) {
            if (graphNode != null && graphNode.getId() != 0) {
                id = graphNode.getId();
                break;
            }
        }
        CycleFinder.graph.dfs(id);
        backEdges = findBackEdges();
        acyclic = backEdges.isEmpty();
        return backEdges;
    }

    private static void printResult() {
        if (acyclic) {
            System.out.println("Graph is acyclic.");
        }
        else {
            System.out.println("Graph is cyclic.");
            System.out.println("Back edges: ");
            for (String backEdge : backEdges) {
                System.out.println(backEdge);
            }
        }
    }

    private static ArrayList<String> findBackEdges() {
        ArrayList<String> backEdges = new ArrayList<String>();
        for (Graph.GraphNode graphNode : graph.getNodeList()) {
            if (graphNode != null) {
                for (int i = 0; i < graphNode.getNeighbours().size(); i++) {
                    if (graphNode.getStartTime() > graph.getNodeList().get(graphNode.getNeighbours().get(i)).getStartTime()
                            && graphNode.getEndTime() < graph.getNodeList().get(graphNode.getNeighbours().get(i)).getEndTime()) {
                        backEdges.add(graphNode.getId() + "->" + graphNode.getNeighbours().get(i));
                    }
                }
            }
        }
        return backEdges;
    }

}
