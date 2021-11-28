package UIElements;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ServiceListDisplay {

    private Stage window;
    private BorderPane sceneLayout;
    // private UIManager uiManager;

    public ServiceListDisplay(Stage primaryStage, UIManager uiMngr) {
        window = primaryStage;
        // uiManager = uiMngr;
        InitializeAttributes();
    }

    private void InitializeAttributes() {

    }

    // private void ConfigureAttributes() {

    // }

    public void LoadDisplay(BorderPane blayout) {
        sceneLayout = blayout;
        window.setTitle("Service List Generator");
        window.show();
    }
}
