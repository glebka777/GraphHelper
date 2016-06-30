package logic.graph;

import logic.util.Queue;
import logic.util.Visualizer;

import java.io.*;
import java.util.*;

public class Graph {

    protected Vector<GraphNode> nodeList;
    protected Vector<Edge> edgeList;
    protected int time;

    protected Graph() {

    }

    public Graph(String graph, boolean isFromFile) {
        time = 0;
        nodeList = new Vector<>();
        edgeList = new Vector<>();
        nodeList.add(new GraphNode(0));
        String[] edges;
        String[] lines;
        if (isFromFile) {
            String strFromFile = readEdges(graph);
            lines = strFromFile.split("\n");
        } else {
            lines = graph.split("\n");
        }
        edges = new String[lines.length - 1];
        System.arraycopy(lines, 1, edges, 0, lines.length - 1);
        /*if (edges.length == 1) {
            return;
        }*/
        for (String edge : edges) {
            bunchNodes(edge);
        }
        for (GraphNode graphNode : nodeList) {
            if (graphNode != null && graphNode.getId() != 0) {
                Collections.sort(graphNode.getNeighbours());
            }
        }
    }

    public Graph(Integer[][] edgesArray){
        time = 0;
        nodeList = new Vector<>();
        edgeList = new Vector<>();
        nodeList.add(new GraphNode(0));
        List<String> tempEdges = new LinkedList<>();
        for (int i = 0; i < edgesArray.length; i++) {
            for (int j = 0; j < edgesArray[i].length; j++) {
                if (edgesArray[i][j] == 1){
                    tempEdges.add(Integer.toString(i+1) + " " + Integer.toString(j+1));
                }
            }
        }
        for (String tempEdge : tempEdges) {
            bunchNodes(tempEdge);
        }
        for (GraphNode graphNode : nodeList) {
            if (graphNode != null && graphNode.getId() != 0) {
                Collections.sort(graphNode.getNeighbours());
            }
        }
    }

    public Vector<GraphNode> getNodeList() {
        return nodeList;
    }

    @Override
    public String toString() {
        String string = "Nodes: \n";
        for (int i = 1; i < nodeList.size(); i++) {
            if (nodeList.get(i) != null) {
                string = string
                        + " '" + nodeList.get(i).getData() + "' "
                        + " [id: " + nodeList.get(i).getId() + "] "
                        + nodeList.get(i).getNeighbours()
                        + "\n{ST: " + nodeList.get(i).getStartTime() + "} "
                        + "{ET: " + nodeList.get(i).getEndTime() + "}\n"
                        + "\n";
            }
        }
        string += "Edges: \n";
        for (Edge edge : edgeList) {
            if (edge.getWeight() == 0) {
                string = string
                        + edge.getFirstNode()
                        + "-----"
                        + edge.getSecondNode();
            } else {
                string = string
                        + edge.getFirstNode()
                        + "--(" + edge.getWeight() + ")--"
                        + edge.getSecondNode();
            }
            string += "\n";
        }
        return string;
    }

    public ArrayList dfs(int id) {
        ArrayList<Integer> DFSList = new ArrayList<Integer>(nodeList.size());
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("_files/dots/dfs.dot"));
            bw.write("digraph dfs {");
            bw.newLine();
            dfs(nodeList.get(id), DFSList, bw);
            for (int i = 1; i < nodeList.size(); i++) {
                GraphNode graphNode = nodeList.get(i);
                if (graphNode != null && !graphNode.isVisited()) {
                    dfs(graphNode, DFSList, bw);
                }
            }
            bw.write("}");
            bw.close();
            for (GraphNode graphNode : nodeList) {
                if (graphNode != null) {
                    graphNode.setVisited(false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Visualizer.createPNG("_files/dots/dfs.dot", "dfs");
        time = 0;
        return DFSList;
    }

    public ArrayList bfs(int id) {
        ArrayList<Integer> BFSList = new ArrayList<Integer>(nodeList.size());
        Queue<GraphNode> queue = new Queue<GraphNode>(nodeList.get(0), nodeList.size());
        queue.push(nodeList.get(id));
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("_files/dots/bfs.dot"));
            bw.write("digraph bfs {");
            bw.newLine();
            bfs(queue, BFSList, bw);
            for (int i = 1; i < nodeList.size(); i++) {
                GraphNode graphNode = nodeList.get(i);
                if (graphNode != null && !graphNode.isVisited()) {
                    queue.push(graphNode);
                    bfs(queue, BFSList, bw);
                }
            }
            bw.write("}");
            bw.close();
            for (GraphNode graphNode : nodeList) {
                if (graphNode != null) {
                    graphNode.setVisited(false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Visualizer.createPNG("_files/dots/bfs.dot", "bfs");
        return BFSList;
    }

    public List<Integer> sortNodesByEndTime() {
        List<Graph.GraphNode> sortedNodes = new ArrayList<Graph.GraphNode>(this.getNodeList());
        sortedNodes.remove(0);
        List<Integer> sortedIds = new ArrayList<>();
        sortedNodes.sort(new Comparator<GraphNode>() {
            @Override
            public int compare(Graph.GraphNode o1, Graph.GraphNode o2) {
                if ((o1 == null) && (o2 != null)) {
                    return 1;
                } else
                    if ((o1 != null) && (o2 == null)) {
                        return -1;
                    } else
                        if ((o1 == null)) {
                            return 0;
                        }
                return o1.getEndTime() > o2.getEndTime() ? -1 : o1.getEndTime() < o2.getEndTime() ? 1 : 0;
            }
        });
        for (GraphNode sortedNode : sortedNodes) {
            if (sortedNode != null) {
                sortedIds.add(sortedNode.getId());
            }
        }
        return sortedIds;
    }

    public boolean contains(int id) {
        if (id == 0) {
            return false;
        }
        for (GraphNode graphNode : nodeList) {
            if (graphNode != null && graphNode.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String data) {
        for (GraphNode graphNode : nodeList) {
            if (graphNode.getData() != null && graphNode.getData().equals(data)) {
                return true;
            }
        }
        return false;
    }

    public Edge getEdge(int firstNode, int secondNode) {
        for (Edge edge : edgeList) {
            if (edge.getFirstNode() == firstNode && edge.getSecondNode() == secondNode) {
                return edge;
            }
            if (edge.getFirstNode() == secondNode && edge.getSecondNode() == firstNode) {
                return edge;
            }
        }
        return null;
    }

    public Integer getGraphNodeIdByData(String data) {
        for (GraphNode graphNode : nodeList) {
            if (graphNode.getData() != null && graphNode.getData().equals(data)) {
                return graphNode.getId();
            }
        }
        return 0;
    }

    public void reset() {
        time = 0;
        for (GraphNode graphNode : nodeList) {
            if (graphNode != null) {
                graphNode.setParent(null);
                graphNode.setLow(-1);
                graphNode.setVisited(false);
                graphNode.setStartTime(0);
                graphNode.setEndTime(0);
            }
        }
    }

    public Vector<Edge> getEdgeList() {
        return edgeList;
    }

    protected void bunchNodes(String edge) {
        String[] nodes = (edge.split(" "));
        if (nodes.length > 1) {
            GraphNode firstNode = null;
            GraphNode secondNode = null;
            if (!this.contains(nodes[0])) {
                firstNode = new GraphNode(nodeList.size(), nodes[0]);
                nodeList.add(firstNode);
            } else {
                firstNode = this.getNodeList().get(this.getGraphNodeIdByData(nodes[0]));
            }
            if (!this.contains(nodes[1])) {
                secondNode = new GraphNode(nodeList.size(), nodes[1]);
                nodeList.add(secondNode);
            } else {
                secondNode = this.getNodeList().get(this.getGraphNodeIdByData(nodes[1]));
            }
            firstNode.addNeighbour(secondNode.getId());
            secondNode.addNeighbour(firstNode.getId());
            if (nodes.length > 2) {
                if (getEdge(firstNode.getId(), secondNode.getId()) == null) {
                    edgeList.add(new Edge(firstNode.getId(), secondNode.getId(), Double.parseDouble(nodes[2]), true));
                }
            } else {
                if (getEdge(firstNode.getId(), secondNode.getId()) == null) {
                    edgeList.add(new Edge(firstNode.getId(), secondNode.getId(), 0, true));
                }
            }
        } else
            if (nodes.length == 1) {
                if (!this.contains(nodes[0])) {
                    GraphNode firstNode = new GraphNode(nodeList.size(), nodes[0]);
                    nodeList.add(firstNode);
                }
            }
    }

    protected int getCurrentTime() {
        return ++time;
    }

    protected void dfs(GraphNode tempNode, ArrayList<Integer> DFSList, BufferedWriter bw) throws IOException {
        DFSList.add(tempNode.getId());
        tempNode.setVisited(true);
        tempNode.setStartTime(getCurrentTime());
        for (int i = 0; i < tempNode.neighbours.size(); i++) {
            if (nodeList.get(tempNode.neighbours.get(i)) != null &&
                    !nodeList.get(tempNode.neighbours.get(i)).isVisited()) {
                dfs(nodeList.get(tempNode.neighbours.get(i)), DFSList, bw);
                bw.write(tempNode.getId() + "->" + nodeList.get(tempNode.neighbours.get(i)).getId());
                bw.newLine();
            }
        }
        bw.write(tempNode.getId() + " [label = \"" + tempNode.getData() +
                " \\n [" + tempNode.getId() + "]\"]");
        bw.newLine();
        tempNode.setEndTime(getCurrentTime());

    }

    protected void bfs(Queue<GraphNode> queue, ArrayList<Integer> BFSList, BufferedWriter bw) throws IOException {
        while (!queue.isEmpty()) {
            GraphNode tempNode = queue.pop();
            BFSList.add(tempNode.getId());
            tempNode.setVisited(true);
            ArrayList<GraphNode> nonVisitedNeighbours = tempNode.getNonVisitedNeighbours();
            for (GraphNode nonVisitedNeighbour : nonVisitedNeighbours) {
                queue.push(nonVisitedNeighbour);
                nonVisitedNeighbour.setVisited(true);
                bw.write(tempNode.getId() + "->" + nonVisitedNeighbour.getId());
                bw.newLine();
            }
            bw.write(tempNode.getId() + " [label = \"" + tempNode.getData() +
                    " \\n [" + tempNode.getId() + "]\"]");
            bw.newLine();
        }
    }

    private String readEdges(String fileName) {
        String edges = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                edges += line;
                edges += "\n";
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return edges;
    }

    public class GraphNode {

        public double distance;
        protected int id;
        protected String data;
        protected List<Integer> neighbours;
        protected boolean visited;
        protected int low;
        protected int startTime;
        protected int endTime;
        protected GraphNode parent;

        GraphNode(int id) {
            this.neighbours = new ArrayList<Integer>();
            this.id = id;
        }

        GraphNode(int id, String data) {
            this.neighbours = new ArrayList<Integer>();
            this.id = id;
            this.data = data;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getLow() {
            return low;
        }

        public void setLow(int low) {
            this.low = low;
        }

        public GraphNode getParent() {
            return parent;
        }

        public void setParent(GraphNode parent) {
            this.parent = parent;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Integer> getNeighbours() {
            return neighbours;
        }

        public void setNeighbours(List<Integer> neighbours) {
            this.neighbours = neighbours;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public void addNeighbour(int neighbour) {
            if (!this.neighbours.contains(neighbour)) {
                this.neighbours.add(neighbour);
            }
        }

        public void removeNeighbour(int neighbour) {
            if (this.neighbours.contains(neighbour)) {
                this.neighbours.remove(Integer.valueOf(neighbour));
            }
        }

        protected ArrayList<GraphNode> getNonVisitedNeighbours() {
            ArrayList<GraphNode> nonVisitedNeighbours = new ArrayList<GraphNode>();
            for (Integer neighbour : this.neighbours) {
                if (!nodeList.get(neighbour).isVisited()) {
                    nonVisitedNeighbours.add(nodeList.get(neighbour));
                }
            }
            return nonVisitedNeighbours;
        }

    }

    public class Edge {
        private int firstNode;
        private int secondNode;
        private double weight;
        private boolean inPath;
        private boolean written;
        private boolean biderected;

        public Edge(int firstNode, int secondNode, double weight, boolean biderected) {
            this.firstNode = firstNode;
            this.secondNode = secondNode;
            this.weight = weight;
            this.biderected = biderected;
        }

        public boolean isInPath() {
            return inPath;
        }

        public void setInPath(boolean inPath) {
            this.inPath = inPath;
        }

        public boolean isWritten() {
            return written;
        }

        public void setWritten(boolean written) {
            this.written = written;
        }

        public int getFirstNode() {
            return firstNode;
        }

        public int getSecondNode() {
            return secondNode;
        }

        public double getWeight() {
            return weight;
        }

        public boolean isBiderected() {
            return biderected;
        }
    }

}
