package ui.graphHelperApp.shortestPath;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.util.Tools;
import ui.graphHelperApp.MainController;

import java.io.File;

public class SPTaskController {

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


