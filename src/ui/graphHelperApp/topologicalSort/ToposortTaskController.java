package ui.graphHelperApp.topologicalSort;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.util.Tools;
import ui.graphHelperApp.MainController;

import java.io.File;
import java.util.List;

public class ToposortTaskController {

    public static List<Integer> sortedNodes;

    @FXML
    private ImageView toposortBox;

    @FXML
    private TextField sortedNodesField;

    @FXML
    private Button closeButton;

    @FXML
    void initialize() {
        if (sortedNodes != null) {
            sortedNodesField.setText(sortedNodes.toString());

            File imageFile = new File(Tools.getSortedGraphPngPath());
            Image image = new Image(imageFile.toURI().toString());
            toposortBox.setImage(image);

        }
        else {
            sortedNodesField.setText("Topological sort is impossible.");
        }
    }

    @FXML
    private void close() {
        MainController.taskStage.close();
    }

}
