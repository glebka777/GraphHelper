package logic.graph;

public class OrientedGraph extends Graph {

    public OrientedGraph(String graph, boolean isFromFile) {
        super(graph, isFromFile);
    }

    public OrientedGraph(Integer[][] edges) {
        super(edges);
    }

    @Override
    public Edge getEdge(int firstNode, int secondNode) {
        for (Edge edge : edgeList) {
            if (edge.getFirstNode() == firstNode && edge.getSecondNode() == secondNode) {
                return edge;
            }
        }
        return null;
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
                        + "---->"
                        + edge.getSecondNode();
            } else {
                string = string
                        + edge.getFirstNode()
                        + "-(" + edge.getWeight() + ")->"
                        + edge.getSecondNode();
            }
            string += "\n";
        }
        return string;
    }

    @Override
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
            if (nodes.length > 2) {
                if (getEdge(firstNode.getId(), secondNode.getId()) == null) {
                    edgeList.add(new Edge(firstNode.getId(), secondNode.getId(), Double.parseDouble(nodes[2]), false));
                }
            } else {
                if (getEdge(firstNode.getId(), secondNode.getId()) == null) {
                    edgeList.add(new Edge(firstNode.getId(), secondNode.getId(), 0, false));
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

}
