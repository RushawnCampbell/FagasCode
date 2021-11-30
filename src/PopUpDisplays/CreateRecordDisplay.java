package PopUpDisplays;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateRecordDisplay {

    public static void LoadDisplay() {
        // Setting up window properties.
        Stage window = new Stage();
        window.setTitle("ALERT");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        Label title_l = new Label("Create A New Customer Record");

        Label descr_l = new Label("Enter the appropriate Information into the fields provided and click 'Create'.");

        TextField tf_fName = new TextField();
        tf_fName.setPromptText("First Name");
        TextField tf_lName = new TextField();
        tf_lName.setPromptText("Last Name");
        TextField tf_email = new TextField();
        tf_email.setPromptText("Email");
        TextField tf_contact = new TextField();
        tf_contact.setPromptText("Contact Number");
        TextField tf_address1 = new TextField();
        tf_address1.setPromptText("Address Line 2");
        TextField tf_address2 = new TextField();
        tf_address2.setPromptText("Address Line 2");
        TextField tf_parish = new TextField();
        tf_parish.setPromptText("parish");

        VBox layout = new VBox();

        Button b_create = new Button("CREATE");

        Button cancel_b = new Button("CANCEL");
        cancel_b.setOnAction(e -> window.close());

        GridPane gridLayout = new GridPane();
        gridLayout.add(tf_fName, 0, 0, 1, 1);
        gridLayout.add(tf_lName, 1, 0, 1, 1);
        gridLayout.add(tf_email, 2, 0, 1, 1);
        gridLayout.add(tf_contact, 0, 1, 1, 1);
        gridLayout.add(tf_address1, 0, 2, 1, 1);
        gridLayout.add(tf_address2, 1, 2, 1, 1);
        gridLayout.add(tf_parish, 2, 2, 1, 1);
        gridLayout.add(b_create, 0, 3, 1, 1);
        gridLayout.add(cancel_b, 2, 3, 1, 1);
        gridLayout.setVgap(15);
        gridLayout.setHgap(15);

        layout.getChildren().addAll(title_l, descr_l, gridLayout);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(30);
        layout.setMinWidth(400);

        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.show();
    }
}