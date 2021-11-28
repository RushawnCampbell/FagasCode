import UIElements.UIManager;
import javafx.application.Application;
import javafx.stage.Stage;

/*
App class contains main method that starts the application.
extends Appliction to use JavaFX classes.
*/
public class App extends Application {
    // UI Element variables
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        UIManager uiManager = new UIManager(window);
        uiManager.LoadLoginDisplay();

        // Set up database
    }

}
