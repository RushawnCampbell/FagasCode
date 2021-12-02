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
    }
}

/*
 * CREATE TABLE servicerequest(
 * id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 * custid varchar(255) NOT NULL,
 * type varchar(255) NOT NULL,
 * price FLOAT, NOT NULL
 * comments TEXT,
 * status varchar(255) NOT NULL,
 * date_created DATETIME
 * );
 * 
 */