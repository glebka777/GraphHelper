package logic.tasks;

import logic.graph.Graph;
import logic.util.Visualizer;

import java.util.ArrayList;
import java.util.List;

public class ArticulationPointFinder {

    private static int counter;
    private static Graph graph;
    private static List<Integer> artPointList;

    public static List<Integer> findArtPoints(Graph graph) {
        artPointList = new ArrayList<>();
        ArticulationPointFinder.graph = graph;
        graph.reset();
        for (Graph.GraphNode graphNode : ArticulationPointFinder.graph.getNodeList()) {
            if (graphNode != null && !graphNode.isVisited() && graphNode.getId() != 0) {
                findArtPoints(graphNode);
            }
        }
        graph.reset();
        Visualizer.visualizeWithArtPoints(ArticulationPointFinder.graph, artPointList);
        return artPointList;
    }

    private static void findArtPoints(Graph.GraphNode graphNode) {
        graphNode.setVisited(true);
        int children = 0;
        counter++;
        graphNode.setStartTime(counter);
        graphNode.setLow(counter);
        for (int i = 0; i < graphNode.getNeighbours().size(); i++) {
            Graph.GraphNode neighbour = graph.getNodeList().get(graphNode.getNeighbours().get(i));
            if (!neighbour.isVisited()) {
                children++;
                neighbour.setParent(graphNode);
                findArtPoints(neighbour);
                graphNode.setLow(Math.min(graphNode.getLow(), neighbour.getLow()));
                if (graphNode.getParent() == null && children > 1) {
                    if (!artPointList.contains(graphNode.getId())) {
                        artPointList.add(graphNode.getId());
                    }
                }
                if (graphNode.getParent() != null && neighbour.getLow() >= graphNode.getStartTime()) {
                    if (!artPointList.contains(graphNode.getId())) {
                        artPointList.add(graphNode.getId());
                    }
                }
            } else
                if (neighbour != graphNode.getParent()) {
                    graphNode.setLow(Math.min(graphNode.getLow(), neighbour.getStartTime()));
                }
        }
    }

}
