package UIElements;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RecordManagerDisplay {
    private Stage window;
    // private UIManager uiManager;

    public RecordManagerDisplay(Stage primaryStage, UIManager uiMngr) {
        // uiManager = uiMngr;
        window = primaryStage;
    }

    // private void InitializeAttributes() {

    // }

    // private void ConfigureAttributes() {

    // }

    public void LoadDisplay(BorderPane blayout) {
        window.setTitle("Record Manager");
        window.show();
    }

}