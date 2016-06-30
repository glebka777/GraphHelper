package logic.tasks;

import logic.graph.Graph;
import logic.util.Visualizer;

public class TreeFinder {

    private static final double max = Double.MAX_VALUE;

    public static boolean findTree(Graph graph) {
        Graph.GraphNode fakeNode = graph.getNodeList().get(0);

        for (Graph.GraphNode graphNode : graph.getNodeList()) {
            graphNode.setDistance(max);
            graphNode.setParent(fakeNode);
            graphNode.setVisited(false);
        }
        graph.getNodeList().get(1).setDistance(0);

        for (int i = 1; i < graph.getNodeList().size(); i++) {
            int currId = -1;
            for (int j = 1; j < graph.getNodeList().size(); j++) {
                Graph.GraphNode graphNode = graph.getNodeList().get(j);
                if (!graphNode.isVisited()
                        && (currId == -1 || graphNode.getDistance() < graph.getNodeList().get(currId).getDistance())) {
                    currId = j;
                }
            }
            Graph.GraphNode currNode = graph.getNodeList().get(currId);
            if (currNode.getDistance() == max) {
                return false;
            }
            currNode.setVisited(true);
            if (graph.getNodeList().get(currId).getParent() != fakeNode) {
                graph.getEdge(currNode.getParent().getId(), currId).setInPath(true);
            }
            for (int re = 1; re < graph.getNodeList().size(); re++) {
                if (graph.getEdge(currId, re) != null) {
                    Graph.GraphNode tempNode = graph.getNodeList().get(re);
                    if (graph.getEdge(currId, re).getWeight() < tempNode.getDistance()) {
                        tempNode.setDistance(graph.getEdge(currId, re).getWeight());
                        tempNode.setParent(currNode);
                    }
                }
            }
        }
        graph.reset();
        Visualizer.visualizeWithTree(graph);
        return true;
    }

}
