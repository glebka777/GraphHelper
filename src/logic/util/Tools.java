package logic.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Tools {

    public static void clearFolders() {
        try {

            Files.deleteIfExists(new File("_files/dots/graph.dot").toPath());
            Files.deleteIfExists(new File("_files/dots/bfs.dot").toPath());
            Files.deleteIfExists(new File("_files/dots/dfs.dot").toPath());
            Files.deleteIfExists(new File("_files/dots/SCC.dot").toPath());
            Files.deleteIfExists(new File("_files/dots/transposedGraph.dot").toPath());
            Files.deleteIfExists(new File("_files/dots/sortedGraph.dot").toPath());
            Files.deleteIfExists(new File("_files/dots/CSCGraph.dot").toPath());
            Files.deleteIfExists(new File("_files/dots/graphWithArtPoints.dot").toPath());
            Files.deleteIfExists(new File("_files/dots/graphWithShortestPath.dot").toPath());
            Files.deleteIfExists(new File("_files/dots/graphWithTree.dot").toPath());

            Files.deleteIfExists(new File("_files/pngs/graph.png").toPath());
            Files.deleteIfExists(new File("_files/pngs/bfs.png").toPath());
            Files.deleteIfExists(new File("_files/pngs/dfs.png").toPath());
            Files.deleteIfExists(new File("_files/pngs/sortedGraph.png").toPath());
            Files.deleteIfExists(new File("_files/pngs/SCC.png").toPath());
            Files.deleteIfExists(new File("_files/pngs/transposedGraph.png").toPath());
            Files.deleteIfExists(new File("_files/pngs/CSCGraph.png").toPath());
            Files.deleteIfExists(new File("_files/pngs/graphWithArtPoints.png").toPath());
            Files.deleteIfExists(new File("_files/pngs/graphWithShortestPath.png").toPath());
            Files.deleteIfExists(new File("_files/pngs/graphWithTree.png").toPath());

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDFSPngPath() {
        return new File("_files/pngs/dfs.png").getAbsolutePath();
    }

    public static String getBFSPngPath() {
        return new File("_files/pngs/bfs.png").getAbsolutePath();
    }

    public static String getGraphPngPath() {
        return new File("_files/pngs/graph.png").getAbsolutePath();
    }

    public static String getSortedGraphPngPath() {
        return new File("_files/pngs/sortedGraph.png").getAbsolutePath();
    }

    public static String getCscPngPath() {
        return new File("_files/pngs/SCC.png").getAbsolutePath();
    }

    public static String getCscGraphPngPath() {
        return new File("_files/pngs/CSCGraph.png").getAbsolutePath();
    }

    public static String getMatrixGraphPngPath() {
        return new File("_files/pngs/matrixGraph.png").getAbsolutePath();
    }

    public static String getGraphWithTreePngPath() {
        return new File("_files/pngs/graphWithTree.png").getAbsolutePath();
    }

    public static String getGraphWithShortestPathPath() {
        return new File("_files/pngs/graphWithShortestPath.png").getAbsolutePath();
    }

    public static String getGraphWithArtPointsPngPath() {
        return new File("_files/pngs/graphWithArtPoints.png").getAbsolutePath();
    }

    public static String getRandomGraph(boolean weighted) {
        String graph = "";
        int withChars = 1 + (int) (Math.random() * 3 - 1);
        if (withChars == 1) {
            if (weighted) {
                graph = getRandomWeightedGraphWithChars();
            }
            else {
                graph = getRandomUnweightedGraphWithChars();
            }
        }
        else {
            if (weighted) {
                graph = getRandomWeightedGraph();
            }
            else {
                graph = getRandomUnweightedGraph();
            }
        }
        return graph;
    }

    private static String getRandomUnweightedGraph() {
        int numberOfEdges = 1 + (int) (Math.random() * (13 - 1));
        String[] edges = new String[numberOfEdges + 1];
        edges[0] = "Random graph";
        for (int i = 1; i < edges.length; i++) {
            int indexOfFirstNode = 1 + (int) (Math.random() * 10 - 1);
            int indexOfSecondNode = (int) (Math.random() * 10);
            String firstNode, secondNode;
            firstNode = String.valueOf(indexOfFirstNode);
            if (indexOfSecondNode == 0) {
                secondNode = " ";
            }
            else {
                secondNode = String.valueOf(indexOfSecondNode);
            }
            edges[i] = firstNode + " " + secondNode;
        }
        String graph = "";
        for (String edge : edges) {
            graph += edge + "\n";
        }
        return graph;
    }

    private static String getRandomWeightedGraph() {
        int numberOfEdges = 1 + (int) (Math.random() * (13 - 1));
        String[] edges = new String[numberOfEdges + 1];
        edges[0] = "Random graph";
        for (int i = 1; i < edges.length; i++) {
            int indexOfFirstNode = 1 + (int) (Math.random() * 10 - 1);
            int indexOfSecondNode = (int) (Math.random() * 10);
            int weight = (int) (Math.random() * 9);
            String firstNode, secondNode;
            firstNode = String.valueOf(indexOfFirstNode);
            if (indexOfSecondNode == 0) {
                secondNode = " ";
            }
            else {
                secondNode = String.valueOf(indexOfSecondNode);
            }
            if (!secondNode.equals(" ")) {
                edges[i] = firstNode + " " + secondNode + " " + String.valueOf(weight);
            }
            else {
                edges[i] = firstNode;
            }
        }
        String graph = "";
        for (String edge : edges) {
            graph += edge + "\n";
        }
        return graph;
    }

    private static String getRandomUnweightedGraphWithChars() {
        int numberOfEdges = 1 + (int) (Math.random() * (13 - 1));
        String[] edges = new String[numberOfEdges + 1];
        edges[0] = "Random graph";
        for (int i = 1; i < edges.length; i++) {
            int indexOfFirstNode = (int) (Math.random() * 11);
            int indexOfSecondNode = (int) (Math.random() * 11);
            char firstNode, secondNode;
            firstNode = (char) (indexOfFirstNode + 97);
            if (indexOfSecondNode > 9) {
                secondNode = ' ';
            }
            else {
                secondNode = (char) (indexOfSecondNode + 97);
            }
            edges[i] = firstNode + " " + secondNode;
        }
        String graph = "";
        for (String edge : edges) {
            graph += edge + "\n";
        }
        return graph;
    }

    private static String getRandomWeightedGraphWithChars() {
        int numberOfEdges = 1 + (int) (Math.random() * (13 - 1));
        String[] edges = new String[numberOfEdges + 1];
        edges[0] = "Random graph";
        for (int i = 1; i < edges.length; i++) {
            int indexOfFirstNode = (int) (Math.random() * 11);
            int indexOfSecondNode = (int) (Math.random() * 11);
            int weight = (int) (Math.random() * 9);
            char firstNode, secondNode;
            firstNode = (char) (indexOfFirstNode + 97);
            if (indexOfSecondNode > 9) {
                secondNode = ' ';
            }
            else {
                secondNode = (char) (indexOfSecondNode + 97);
            }
            if (secondNode != ' ') {
                edges[i] = firstNode + " " + secondNode + " " + String.valueOf(weight);
            }
            else {
                edges[i] = firstNode + " ";
            }
        }
        String graph = "";
        for (String edge : edges) {
            graph += edge + "\n";
        }
        return graph;
    }

}
