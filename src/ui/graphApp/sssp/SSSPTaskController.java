package ui.graphApp.sssp;

import ui.graphApp.MainController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.util.Tools;

import java.io.File;

public class SSSPTaskController {

    @FXML
    private ImageView graphViewBox;

    @FXML
    void initialize() {

        File imageFile = new File(Tools.getGraphWithShortestPathPath());
        Image image = new Image(imageFile.toURI().toString());
        graphViewBox.setImage(image);

    }

    @FXML
    private void close() {
        MainController.taskStage.close();
    }

}


