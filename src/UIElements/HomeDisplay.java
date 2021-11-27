package UIElements;

import Security.User.UserType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HomeDisplay {
    Stage window;
    Scene scene;
    Label label;
    StackPane layout;

    public HomeDisplay(Stage primaryStage) {
        window = primaryStage;
        InitializeAttributes();
        ConfigureAttributes();
    }

    private void ConfigureAttributes() {
        layout.getChildren().add(label);
        layout.setAlignment(Pos.CENTER);
    }

    private void InitializeAttributes() {
        layout = new StackPane();
        label = new Label();
        scene = new Scene(layout, 600, 300);
    }

    public void LoadDisplay(UserType userT) {
        window.setTitle("Login");
        label.setText(userT.toString());
        window.setScene(scene);
        window.show();
    }

}
