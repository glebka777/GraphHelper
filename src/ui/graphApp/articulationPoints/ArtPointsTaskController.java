package ui.graphApp.articulationPoints;

import ui.graphApp.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.util.Tools;

import java.io.File;
import java.util.List;

public class ArtPointsTaskController {

    public static List<Integer> artPoints;

    @FXML
    private ImageView graphViewBox;

    @FXML
    private TextField artPointsField;

    @FXML
    void initialize() {
        if (!artPoints.isEmpty()) {
            artPointsField.setText(artPoints.toString());
        } else {
            artPointsField.setText("Articulation points not found.");
        }
        File imageFile = new File(Tools.getGraphWithArtPointsPngPath());
        Image image = new Image(imageFile.toURI().toString());
        graphViewBox.setImage(image);
    }

    @FXML
    private void close() {
        MainController.taskStage.close();
    }

}
