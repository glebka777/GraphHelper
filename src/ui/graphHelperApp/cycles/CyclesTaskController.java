package ui.graphHelperApp.cycles;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import ui.graphHelperApp.MainController;

import java.util.ArrayList;

public class CyclesTaskController {

    public static ArrayList<String> backEdges;
    @FXML
    private TextArea backEdgesTextField;

    @FXML
    private Button closeButton;

    @FXML
    void initialize() {
        if (!backEdges.isEmpty()) {
            backEdgesTextField.appendText("Graph is cyclic." + "\n");
            backEdgesTextField.appendText("Back edges:" + "\n");
            for (String backEdge : backEdges) {
                backEdgesTextField.appendText(backEdge + '\n');
            }
        }
        else {
            backEdgesTextField.setText("Graph is acyclic.");
        }
    }

    @FXML
    private void close() {
        MainController.taskStage.close();
    }

}
