package logic.tasks;

import logic.graph.Graph;
import logic.graph.OrientedGraph;
import logic.util.Visualizer;

import java.util.List;

public class TopoSorter {

    private static OrientedGraph graph;
    private static List<Integer> sortedIds;

    public static List<Integer> sort(OrientedGraph graph) {
        if (CycleFinder.checkAcyclic(graph).isEmpty()) {
            TopoSorter.graph = graph;
            Visualizer.visualizeOrientedGraph(graph);
            sortedIds = graph.sortNodesByEndTime();
            sortGraph();
            Visualizer.visualizeSpecialGraph(TopoSorter.graph, "_files/dots/sortedgraph.dot");
            return sortedIds;
        } else {
            return null;
        }
    }

    private static void sortGraph() {
        String[] datas = new String[graph.getNodeList().size()];
        int i = 0;
        for (Graph.GraphNode graphNode : graph.getNodeList()) {
            if (graphNode.getId() != 0) {
                datas[i] = graphNode.getData();
                i++;
            }
        }
        i = 0;
        for (Integer sortedId : sortedIds) {
            graph.getNodeList().get(sortedId).setData(datas[i]);
            i++;
        }
    }

    private static void printResult() {
        System.out.println(sortedIds);
    }

}
