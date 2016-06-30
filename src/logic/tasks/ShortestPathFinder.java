package logic.tasks;

import logic.graph.Graph;
import logic.graph.OrientedGraph;
import logic.util.Visualizer;

public class ShortestPathFinder {

    private static final double max = Double.MAX_VALUE;
    private static OrientedGraph graph;

    public static boolean findShortestPath(OrientedGraph graph, int firstId, int secondId) {
        Graph.GraphNode fakeNode = graph.getNodeList().get(0);
        ShortestPathFinder.graph = graph;

        for (Graph.GraphNode graphNode : graph.getNodeList()) {
            graphNode.setParent(fakeNode);
            graphNode.setDistance(max);
        }
        Graph.GraphNode endNode = graph.getNodeList().get(secondId);
        Graph.GraphNode startNode = graph.getNodeList().get(firstId);
        startNode.setDistance(0);
        startNode.setParent(startNode);

        boolean cycleHasChanges = true;
        while (cycleHasChanges) {
            cycleHasChanges = false;
            for (Graph.Edge edge : graph.getEdgeList()) {
                if (graph.getNodeList().get(edge.getFirstNode()).getDistance() < max) {
                    if (relax(edge)) {
                        cycleHasChanges = true;
                    }
                }
            }
        }

        if (endNode.getDistance() != max) {
            markEdges(startNode, endNode);
            Visualizer.visualizeWithShortestPath(graph);
            return true;
        } else {
            return false;
        }

    }

    private static boolean relax(Graph.Edge edge) {
        Graph.GraphNode firstNode = graph.getNodeList().get(edge.getFirstNode());
        Graph.GraphNode secondNode = graph.getNodeList().get(edge.getSecondNode());
        if (secondNode.getDistance() > firstNode.getDistance() + edge.getWeight()) {
            secondNode.setDistance(firstNode.getDistance() + edge.getWeight());
            secondNode.setParent(firstNode);
            return true;
        }
        return false;
    }

    private static void markEdges(Graph.GraphNode startNode, Graph.GraphNode endNode) {
        Graph.GraphNode nextNode = endNode.getParent();
        Graph.GraphNode prevNode = endNode;
        while (prevNode != startNode) {
            graph.getEdge(nextNode.getId(), prevNode.getId()).setInPath(true);
            prevNode = nextNode;
            nextNode = nextNode.getParent();
        }
    }

}
