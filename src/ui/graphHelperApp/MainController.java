package ui.graphHelperApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.graph.Graph;
import logic.graph.OrientedGraph;
import logic.tasks.*;
import logic.util.Tools;
import logic.util.Visualizer;
import ui.graphHelperApp._tools.AlertBox;
import ui.graphHelperApp.articulationPoints.ArtPointsTaskController;
import ui.graphHelperApp.cycles.CyclesTaskController;
import ui.graphHelperApp.topologicalSort.ToposortTaskController;

import java.io.File;
import java.io.IOException;

public class MainController {

    public static Stage taskStage = new Stage();

    @FXML
    private TextField spId1Field;

    @FXML
    private TextField spId2Field;

    @FXML
    private ToggleButton weightedButton;

    @FXML
    private Button findCyclesButton;

    @FXML
    private ImageView graphViewBox;

    @FXML
    private TextField nodeIdTextField;

    @FXML
    private ImageView DFSViewBox;

    @FXML
    private TextArea graphTextArea;

    @FXML
    private TextField DFSTextField;

    @FXML
    private ImageView BFSViewBox;

    @FXML
    private TextField BFSTextField;

    @FXML
    private ToggleButton orientedButton;

    @FXML
    void initialize() {
        orientedButton.setSelected(true);
        weightedButton.setSelected(true);
    }

    @FXML
    private void DFSButtonClick() {
        if (nodeIdTextField.getText().isEmpty() || graphTextArea.getText().isEmpty()) {
            return;
        }
        if (!nodeIdTextField.getText().matches("[0-9]+")) {
            return;
        }
        Graph graph = null;
        if (orientedButton.isSelected()) {
            graph = new OrientedGraph(graphTextArea.getText(), false);
        }
        else {
            graph = new Graph(graphTextArea.getText(), false);
        }
        if (!graph.contains(Integer.parseInt(nodeIdTextField.getText()))) {
            AlertBox.display("Wrong ID", "Enter existing ID.");
            return;
        }
        DFSTextField.setText(graph.dfs(Integer.parseInt(nodeIdTextField.getText())).toString());
        File imageFile = new File(Tools.getDFSPngPath());
        Image image = new Image(imageFile.toURI().toString());
        DFSViewBox.setImage(image);
        Tools.clearFolders();
    }

    @FXML
    private void BFSButtonClick() {
        if (nodeIdTextField.getText().isEmpty() || graphTextArea.getText().isEmpty()) {
            return;
        }
        if (!nodeIdTextField.getText().matches("[0-9]+")) {
            return;
        }
        Graph graph;
        if (orientedButton.isSelected()) {
            graph = new OrientedGraph(graphTextArea.getText(), false);
        }
        else {
            graph = new Graph(graphTextArea.getText(), false);
        }
        if (!graph.contains(Integer.parseInt(nodeIdTextField.getText()))) {
            AlertBox.display("Wrong ID", "Enter existing ID.");
            return;
        }
        BFSTextField.setText(graph.bfs(Integer.parseInt(nodeIdTextField.getText())).toString());
        File imageFile = new File(Tools.getBFSPngPath());
        Image image = new Image(imageFile.toURI().toString());
        BFSViewBox.setImage(image);
        Tools.clearFolders();
    }

    @FXML
    private void graphVisualiseButtonClick() {
        if (graphTextArea.getText().isEmpty()) {
            return;
        }
        Graph graph;
        if (orientedButton.isSelected()) {
            graph = new OrientedGraph(graphTextArea.getText(), false);
            Visualizer.visualizeOrientedGraph((OrientedGraph) graph);
        }
        else {
            graph = new Graph(graphTextArea.getText(), false);
            Visualizer.visualizeGraph(graph);
        }
        File imageFile = new File(Tools.getGraphPngPath());
        Image image = new Image(imageFile.toURI().toString());
        graphViewBox.setImage(image);
        Tools.clearFolders();
    }

    @FXML
    private void clear() {
        graphViewBox.setImage(null);
        DFSViewBox.setImage(null);
        BFSViewBox.setImage(null);

        nodeIdTextField.setText("1");
        spId1Field.setText("1");
        spId2Field.setText("1");
        graphTextArea.setText("");
        DFSTextField.setText("");
        BFSTextField.setText("");
    }

    @FXML
    private void setExampleGraph() {
        String graph = "12\n" +
                "1 2\n" +
                "1 3\n" +
                "1 4\n" +
                "3 4\n" +
                "2 4\n" +
                "5 6\n" +
                "5 7\n" +
                "7 6\n" +
                "1 8\n" +
                "8 9\n" +
                "8 10\n" +
                "9 10";
        graphTextArea.setText(graph);
    }

    @FXML
    private void toggleGraph() {
        graphVisualiseButtonClick();
    }

    @FXML
    private void validateInput(MouseEvent event) {
        if (!((TextField) event.getSource()).getText().matches("[0-9]+")) {
            ((TextField) event.getSource()).setText("");
        }
    }

    @FXML
    private void clearField(MouseEvent event) {
        ((TextField) event.getSource()).setText("");
    }

    @FXML
    private void createRandomGraph() {
        String graph = Tools.getRandomGraph(weightedButton.isSelected());
        graphTextArea.setText(graph.trim());
        graphVisualiseButtonClick();
    }

    @FXML
    private void doEverything() {
        DFSButtonClick();
        BFSButtonClick();
        graphVisualiseButtonClick();
    }

    @FXML
    private void findCycles() throws Exception {
        if (graphTextArea.getText().isEmpty() || graphTextArea.getText().split("\n").length == 1) {
            return;
        }
        if (!orientedButton.isSelected()) {
            orientedButton.setSelected(true);
            toggleGraph();
        }
        CyclesTaskController.backEdges = CycleFinder.checkAcyclic(new OrientedGraph(graphTextArea.getText(), false));
        Parent root = FXMLLoader.load(getClass().getResource("cycles/cycles.fxml"));
        taskStage = new Stage();
        taskStage.setTitle("Cycles");
        taskStage.setScene(new Scene(root, 640, 480));
        taskStage.show();
        Tools.clearFolders();
    }

    @FXML
    private void topoSort() throws IOException {
        if (graphTextArea.getText().isEmpty() || graphTextArea.getText().split("\n").length == 1) {
            return;
        }
        if (!orientedButton.isSelected()) {
            orientedButton.setSelected(true);
            toggleGraph();
        }
        ToposortTaskController.sortedNodes = TopoSorter.sortGraph(new OrientedGraph(graphTextArea.getText(), false));
        Parent root = FXMLLoader.load(getClass().getResource("topologicalSort/toposort.fxml"));
        taskStage.setTitle("Topological sortGraph.");
        taskStage.setScene(new Scene(root, 1280, 800));
        taskStage.show();
        Tools.clearFolders();
    }

    @FXML
    private void findArtPoints() throws IOException {
        if (graphTextArea.getText().isEmpty() || graphTextArea.getText().split("\n").length == 1) {
            return;
        }
        if (orientedButton.isSelected()) {
            orientedButton.setSelected(false);
            toggleGraph();
        }
        ArtPointsTaskController.artPoints = ArticulationPointFinder.findArtPoints(new Graph(graphTextArea.getText(), false));
        Parent root = FXMLLoader.load(getClass().getResource("articulationPoints/art_points.fxml"));
        taskStage.setTitle("Articulation points");
        taskStage.setScene(new Scene(root, 1280, 800));
        taskStage.show();
        Tools.clearFolders();
    }

    @FXML
    private void findSCC() throws IOException {
        if (graphTextArea.getText().isEmpty() || graphTextArea.getText().split("\n").length == 1) {
            return;
        }
        if (!orientedButton.isSelected()) {
            orientedButton.setSelected(true);
            toggleGraph();
        }
        SCCFinder.findSCC(new OrientedGraph(graphTextArea.getText(), false));
        Parent root = FXMLLoader.load(getClass().getResource("SCC/SCC.fxml"));
        taskStage.setTitle("SCC'");
        taskStage.setScene(new Scene(root, 1280, 800));
        taskStage.show();
        Tools.clearFolders();
    }

    @FXML
    private void findShortestPath() throws IOException {
        if (graphTextArea.getText().isEmpty() || graphTextArea.getText().split("\n").length == 1) {
            return;
        }
        if (!spId1Field.getText().matches("[0-9]+") || !spId2Field.getText().matches("[0-9]+")) {
            return;
        }
        if (!orientedButton.isSelected()) {
            orientedButton.setSelected(true);
            toggleGraph();
        }
        OrientedGraph graph = new OrientedGraph(graphTextArea.getText(), false);
        if (!graph.contains(Integer.parseInt(spId1Field.getText()))
                || !graph.contains(Integer.parseInt(spId2Field.getText()))) {
            AlertBox.display("Wrong ID", "Enter existing ID.");
            return;
        }
        if (ShortestPathFinder.findShortestPath(graph, Integer.parseInt(spId1Field.getText()),
                Integer.parseInt(spId2Field.getText()))) {
            Parent root = FXMLLoader.load(getClass().getResource("shortestPath/shortest_path.fxml"));
            taskStage.setTitle("Single source shortest path");
            taskStage.setScene(new Scene(root, 1280, 800));
            taskStage.show();
        }
        else {
            AlertBox.display("Path not found", "There is no such path");
        }
        Tools.clearFolders();
    }

    @FXML
    private void findTree() throws IOException {
        if (graphTextArea.getText().isEmpty() || graphTextArea.getText().split("\n").length == 1) {
            return;
        }
        if (orientedButton.isSelected()) {
            orientedButton.setSelected(false);
            toggleGraph();
        }
        Graph graph = new Graph(graphTextArea.getText(), false);
        if (TreeFinder.findTree(graph)) {
            Parent root = FXMLLoader.load(getClass().getResource("tree/tree.fxml"));
            taskStage.setTitle("Tree");
            taskStage.setScene(new Scene(root, 1280, 800));
            taskStage.show();
        }
        else {
            AlertBox.display("Tree not found", "There is no tree in graph :(");
        }
        Tools.clearFolders();
    }

}
