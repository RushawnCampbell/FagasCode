package UIElements;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomeDisplay {
    private Stage window;
    // private UIManager uiManager;

    public HomeDisplay(Stage primaryStage, UIManager uiMngr) {
        window = primaryStage;
        // uiManager = uiMngr;
        // InitializeAttributes();
    }

    // private void InitializeAttributes() {

    // }

    // private void ConfigureAttributes() {

    // }

    public void LoadDisplay(BorderPane blayout) {
        window.setTitle("Home");
        window.show();
    }

}
