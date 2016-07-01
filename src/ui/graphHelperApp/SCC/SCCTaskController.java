package ui.graphHelperApp.SCC;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.util.Tools;
import ui.graphHelperApp.MainController;

import java.io.File;

public class SCCTaskController {

    @FXML
    private ImageView cscViewBox;

    @FXML
    private ImageView graphViewBox;

    @FXML
    void initialize() {

        File imageFile = new File(Tools.getCscPngPath());
        Image image = new Image(imageFile.toURI().toString());
        cscViewBox.setImage(image);

        imageFile = new File(Tools.getCscGraphPngPath());
        image = new Image(imageFile.toURI().toString());
        graphViewBox.setImage(image);

    }

    @FXML
    private void close() {
        MainController.taskStage.close();
    }

}
