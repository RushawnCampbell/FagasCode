package UIElements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginDisplay {
    // Attributes
    private Stage window;
    private Button loginButton;
    private VBox vlayout;
    private HBox hLayout1;
    private HBox hLayout2;
    private Scene scene;

    private Label usernameLabel;
    private Label passwordLabel;
    private TextArea usernameText;
    private PasswordField passwordText;
    private UIManager uiManager;
    private Button closeButton;

    /*
     * This class uses window object (Stage) from uimanager to set the scene to
     * the one it has created containing layouts of ui elements. The scene, layouts
     * and ui elements are configured then added to the scene to be display in the
     * window
     */
    public LoginDisplay(Stage primaryStage, UIManager uiMnger) {
        window = primaryStage;
        initializeAttributes();
        ConfigureAttributes();
        uiManager = uiMnger;
    }

    /*
     * This class configures the layouts and ui elements before adding to scene
     */
    private void ConfigureAttributes() {
        usernameText.setMaxHeight(10);
        usernameText.setMaxWidth(100);

        passwordText.setMaxHeight(10);
        passwordText.setMaxWidth(100);

        closeButton.setOnAction(e -> window.close());

        loginButton.setOnAction(e -> {
            String username = usernameText.getText();
            String password = passwordText.getText();
            uiManager.Login(username, password);
        });

        hLayout1.getChildren().add(usernameLabel);
        hLayout1.getChildren().add(usernameText);
        hLayout1.setAlignment(Pos.CENTER);
        hLayout1.setPadding(new Insets(0, 0, 0, 10));

        hLayout2.getChildren().add(passwordLabel);
        hLayout2.getChildren().add(passwordText);
        hLayout2.setAlignment(Pos.CENTER);
        hLayout2.setPadding(new Insets(10, 0, 0, 20));

        vlayout.getChildren().add(hLayout1);
        vlayout.getChildren().add(hLayout2);
        vlayout.getChildren().add(loginButton);
        vlayout.getChildren().add(closeButton);
        vlayout.setAlignment(Pos.CENTER);
        vlayout.setSpacing(10);
    }

    /*
     * This class instantiates necessary attributes to be used in window
     */
    private void initializeAttributes() {
        loginButton = new Button("Login");
        closeButton = new Button("CLOSE");
        vlayout = new VBox();
        hLayout1 = new HBox();
        hLayout2 = new HBox();
        scene = new Scene(vlayout, 600, 300);
        usernameLabel = new Label("Username");
        usernameText = new TextArea();
        passwordLabel = new Label("Password");
        passwordText = new PasswordField();
    }

    /*
     * This class sets the scene of the window to the one created and displays it in
     * the window.
     */
    public void LoadDisplay() {
        window.setTitle("Login");
        window.setScene(scene);
        window.show();
    }
}
