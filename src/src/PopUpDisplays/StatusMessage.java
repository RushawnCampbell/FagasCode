package PopUpDisplays;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StatusMessage {
    static boolean response;

    /*
     * This method creates a new pop up window that displays a message and must be
     * closed before continuing.
     */
    public static void DisplayMessage(String message) {
        // Setting up window properties.
        Stage window = new Stage();
        window.setTitle("ALERT");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        // making label containing message
        Label messageLabel = new Label(message);

        // Creating close button to close window
        Button okButton = new Button("CLOSE");
        okButton.setOnAction(e -> window.close());

        // creating container that holds and displays ui elements vertically.
        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLabel, okButton);
        layout.setAlignment(Pos.CENTER);

        // creating Scene that holds all layout classes to display in the window.
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    // This method create a display with a message that requires a
    // yes or no resonse. returns boolean of response.
    public static boolean ConfirmDisplay(String message) {
        // Setting up window properties.
        Stage window = new Stage();
        window.setTitle("ALERT");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        Label messageLabel = new Label(message);

        // creating yes button and setting its function
        Button yesButton = new Button("YES");
        yesButton.setOnAction(e -> {
            response = true;
            window.close();
        });

        // creating no button and setting its function
        Button noButton = new Button("NO");
        noButton.setOnAction(e -> {
            response = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLabel, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        // creating Scene that holds all layout classes to display in the window.
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return response;
    }

}
