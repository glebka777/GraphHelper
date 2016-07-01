package ui.hugeMatrixGraphApp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.graph.Graph;
import logic.graph.OrientedGraph;
import logic.util.Tools;
import logic.util.Visualizer;
import ui.graphHelperApp._tools.AlertBox;

import java.io.File;

public class SingleGraphApp extends Application {

    public static double WIDTH;
    public static double HEIGHT;
    private final ImageView imgBox = new ImageView();
    private Scene mainScene;
    private TextArea textArea;
    private TextField pngPathField;
    private ToggleButton orientedButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
//        super.init();
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        WIDTH = visualBounds.getWidth();
        HEIGHT = visualBounds.getHeight();

        Button button = new Button("Visualise");
        button.setOnAction(event->visualiseMatrixGraph());
        orientedButton = new ToggleButton("Oriented");
        textArea = new TextArea();
        textArea.setMaxWidth(WIDTH / 100 * 25);
        textArea.setPrefHeight(HEIGHT / 100 * 75);
        pngPathField = new TextField();

        VBox right = new VBox(imgBox);
        imgBox.setFitHeight(WIDTH);
        imgBox.setFitWidth(WIDTH / 100 * 70);
        right.setPrefWidth(WIDTH / 100 * 70);
        right.setStyle("-fx-border-color: black");

        VBox left = new VBox(textArea, new HBox(button, orientedButton) {{
            setAlignment(Pos.CENTER);
            setSpacing(WIDTH / 100 * 2);
        }}, pngPathField);
        left.setAlignment(Pos.CENTER);
        left.setSpacing(HEIGHT / 100 * 2);
        left.setStyle("-fx-border-color: black");
        left.setPrefWidth(WIDTH / 100 * 30);

        HBox main = new HBox(left, new ScrollPane(right));

        mainScene = new Scene(main, WIDTH, HEIGHT);
    }

    private void visualiseMatrixGraph() {
        if (textArea.getText().isEmpty()) {
            return;
        }
        Graph graph;
        Integer[][] edgesArray = readArray();
        if (edgesArray == null) {
            return;
        }
        if (orientedButton.isSelected()) {
            graph = new OrientedGraph(edgesArray);
        }
        else {
            graph = new Graph(edgesArray);
        }
        Visualizer.visualizeMatrixGraph(graph);

        File imageFile = new File(Tools.getMatrixGraphPngPath());
        Image image = new Image(imageFile.toURI().toString());
        imgBox.setImage(image);

        Tools.clearFolders();
        pngPathField.setText(Tools.getMatrixGraphPngPath());
    }

    private Integer[][] readArray() {
        String[] lines = textArea.getText().split("\n");
        Integer[][] edgesArray = new Integer[lines.length][];
        for (int i = 0; i < edgesArray.length; i++) {
            String[] temp = lines[i].split("\\s+");
            edgesArray[i] = new Integer[temp.length];
            for (int j = 0; j < edgesArray[i].length; j++) {
                try {
                    edgesArray[i][j] = Integer.parseInt(temp[j].trim());
                } catch(NumberFormatException e) {
                    AlertBox.display("Wrong input!", "Oops!");
                    pngPathField.setText("");
                    return null;
                }
            }
        }
        return edgesArray;
    }


}
