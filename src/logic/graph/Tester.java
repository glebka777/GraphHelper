package logic.graph;

import logic.util.Tools;
import logic.util.Visualizer;

public class Tester {

    public static void main(String[] args) {
        Tools.clearFolders();
        Integer[][] array = {
                {0, 1, 0},
                {0, 0, 1},
                {0, 1, 0}
        };
        Graph graph = new OrientedGraph(array);
        Visualizer.visualizeGraph(graph);
    }

}
