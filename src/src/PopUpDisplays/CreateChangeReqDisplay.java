package PopUpDisplays;

import java.util.ArrayList;

import Logic.CustomerRecord;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateChangeReqDisplay {

    public static ArrayList<String> LoadDisplay(CustomerRecord selectedCustomer) {
        ArrayList<String> formData = new ArrayList<String>();
        Stage window = new Stage();
        window.setTitle("Create Change Request");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        Label title_l = new Label("Create A New Change Request");
        Label descr_l = new Label("Enter the appropriate Information into the fields provided and click 'Create'.");

        TextField tf_fName = new TextField();
        tf_fName.setPromptText("First Name");
        tf_fName.setText(selectedCustomer.getFirst());
        TextField tf_lName = new TextField();
        tf_lName.setPromptText("Last Name");
        tf_lName.setText(selectedCustomer.getLast());
        TextField tf_email = new TextField();
        tf_email.setPromptText("Email");
        tf_email.setText(selectedCustomer.getEmail());
        TextField tf_contact = new TextField();
        tf_contact.setPromptText("Contact Number");
        tf_contact.setText(selectedCustomer.getPhone());
        TextField tf_address1 = new TextField();
        tf_address1.setPromptText("Address Line 2");
        tf_address1.setText(selectedCustomer.getAd1());
        TextField tf_address2 = new TextField();
        tf_address2.setPromptText("Address Line 2");
        tf_address2.setText(selectedCustomer.getAd2());
        TextField tf_parish = new TextField();
        tf_parish.setPromptText("Parish");
        tf_parish.setText(selectedCustomer.getParish());

        GridPane textFieldLayout = new GridPane();
        textFieldLayout.add(tf_fName, 0, 0, 1, 1);
        textFieldLayout.add(tf_lName, 1, 0, 1, 1);
        textFieldLayout.add(tf_email, 2, 0, 1, 1);
        textFieldLayout.add(tf_contact, 0, 1, 1, 1);
        textFieldLayout.add(tf_address1, 0, 2, 1, 1);
        textFieldLayout.add(tf_address2, 1, 2, 1, 1);
        textFieldLayout.add(tf_parish, 2, 2, 1, 1);
        textFieldLayout.setVgap(15);
        textFieldLayout.setHgap(15);
        textFieldLayout.setAlignment(Pos.CENTER);

        Label l_id = new Label("Record ID: " + String.valueOf(selectedCustomer.getId()));
        Label l_fName = new Label("First Name: " + selectedCustomer.getFirst());
        Label l_lName = new Label("Last Name: " + selectedCustomer.getLast());
        Label l_email = new Label("Email: " + selectedCustomer.getEmail());
        Label l_contact = new Label("Contact Number: " + selectedCustomer.getPhone());
        Label l_address1 = new Label("Address Line 1: " + selectedCustomer.getAd1());
        Label l_address2 = new Label("Address Line 2: " + selectedCustomer.getAd2());
        Label l_parish = new Label("Parish: " + selectedCustomer.getParish());

        GridPane labelGrid = new GridPane();
        labelGrid.add(l_fName, 0, 0, 1, 1);
        labelGrid.add(l_lName, 1, 0, 1, 1);
        labelGrid.add(l_email, 0, 1, 1, 1);
        labelGrid.add(l_contact, 1, 1, 1, 1);
        labelGrid.add(l_address1, 0, 2, 1, 1);
        labelGrid.add(l_address2, 1, 2, 1, 1);
        labelGrid.add(l_parish, 0, 3, 1, 1);
        labelGrid.add(l_id, 1, 3, 1, 1);
        labelGrid.setVgap(15);
        labelGrid.setHgap(15);
        labelGrid.setAlignment(Pos.CENTER);

        HBox grids = new HBox();
        grids.getChildren().addAll(labelGrid, textFieldLayout);
        grids.setAlignment(Pos.CENTER);

        Button b_create = new Button("CREATE");
        b_create.setOnAction(e -> {
            boolean res = StatusMessage.ConfirmDisplay("Are you sure you want to create this Customer Record?");
            if (res) {
                formData.add(String.valueOf(selectedCustomer.getId()));
                formData.add(tf_fName.getText());
                formData.add(tf_lName.getText());
                formData.add(tf_email.getText());
                formData.add(tf_contact.getText());
                formData.add(tf_address1.getText());
                formData.add(tf_address2.getText());
                formData.add(tf_parish.getText());
                window.close();
            }
        });

        Button b_cancel = new Button("CANCEL");
        b_cancel.setOnAction(e -> window.close());

        HBox buttonGrid = new HBox();
        buttonGrid.getChildren().addAll(b_create, b_cancel);
        buttonGrid.setAlignment(Pos.CENTER);

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(title_l, descr_l, grids, buttonGrid);
        layout2.setAlignment(Pos.CENTER);
        layout2.setSpacing(40);
        layout2.setMinWidth(400);

        Scene scene = new Scene(layout2);
        window.setScene(scene);
        window.showAndWait();
        return formData;
    }

}
