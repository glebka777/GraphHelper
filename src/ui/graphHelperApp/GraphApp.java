package ui.graphHelperApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.util.Tools;

import java.io.File;

public class GraphApp extends Application {

    public static void main(String[] args) {
        new File("_files/dots/").mkdirs();
        new File("_files/pngs/").mkdir();
        launch(args);
        Tools.clearFolders();
//        new File("_files/dots/").delete();
//        new File("_files/pngs/").delete();
//        new File("_files/").delete();
    }

    @Override
    public void stop() throws Exception {
        Tools.clearFolders();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("graph_app.fxml"));
        primaryStage.setTitle("Graphs application");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
