package PopUpDisplays;

import java.util.ArrayList;

import Logic.ChangeRequest;
import Logic.CustomerRecord;
import javafx.geometry.Insets;
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

public class ChangeReqView {

    public static ArrayList<String> LoadDisplay(ChangeRequest changeReq) {
        ArrayList<String> formData = new ArrayList<String>();
        Stage window = new Stage();
        window.setTitle("Create Change Request");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        CustomerRecord oldRecord = changeReq.getOldRecord();
        CustomerRecord newRecord = changeReq.getNewRecord();

        Label l_oldRec = new Label("Record Information");
        Label l_newRec = new Label("Modified Information");
        Label l_id = new Label("Customer ID: " + String.valueOf(oldRecord.getId()));
        Label l_fName = new Label("First Name: " + oldRecord.getFirst());
        Label l_lName = new Label("Last Name: " + oldRecord.getLast());
        Label l_email = new Label("Email: " + oldRecord.getEmail());
        Label l_contact = new Label("Contact Number: " + oldRecord.getPhone());
        Label l_address1 = new Label("Address Line 1: " + oldRecord.getAd1());
        Label l_address2 = new Label("Address Line 2: " + oldRecord.getAd2());
        Label l_parish = new Label("Parish: " + oldRecord.getParish());

        TextField tf_fName = new TextField();
        tf_fName.setPromptText("First Name");
        tf_fName.setText(newRecord.getFirst());
        TextField tf_lName = new TextField();
        tf_lName.setPromptText("Last Name");
        tf_lName.setText(newRecord.getLast());
        TextField tf_email = new TextField();
        tf_email.setPromptText("Email");
        tf_email.setText(newRecord.getEmail());
        TextField tf_contact = new TextField();
        tf_contact.setPromptText("Contact Number");
        tf_contact.setText(newRecord.getPhone());
        TextField tf_address1 = new TextField();
        tf_address1.setPromptText("Address Line 2");
        tf_address1.setText(newRecord.getAd1());
        TextField tf_address2 = new TextField();
        tf_address2.setPromptText("Address Line 2");
        tf_address2.setText(newRecord.getAd2());
        TextField tf_parish = new TextField();
        tf_parish.setPromptText("Parish");
        tf_parish.setText(newRecord.getParish());

        Button b_accept = new Button("ACCEPT");
        b_accept.setOnAction(e -> {
            boolean res = StatusMessage.ConfirmDisplay("Are you sure you want to create this Customer Record?");
            if (res) {
                formData.add(String.valueOf(changeReq.getId()));
                formData.add(String.valueOf(oldRecord.getId()));
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

        GridPane oldRecGrid = new GridPane();
        oldRecGrid.add(l_fName, 0, 0, 1, 1);
        oldRecGrid.add(l_lName, 1, 0, 1, 1);
        oldRecGrid.add(l_email, 0, 1, 1, 1);
        oldRecGrid.add(l_contact, 1, 1, 1, 1);
        oldRecGrid.add(l_address1, 0, 2, 1, 1);
        oldRecGrid.add(l_address2, 1, 2, 1, 1);
        oldRecGrid.add(l_parish, 0, 3, 1, 1);
        oldRecGrid.setVgap(15);
        oldRecGrid.setHgap(15);
        oldRecGrid.setAlignment(Pos.CENTER);

        GridPane newRecGrid = new GridPane();
        newRecGrid.add(tf_fName, 0, 0, 1, 1);
        newRecGrid.add(tf_lName, 1, 0, 1, 1);
        newRecGrid.add(tf_email, 0, 1, 1, 1);
        newRecGrid.add(tf_contact, 1, 1, 1, 1);
        newRecGrid.add(tf_address1, 0, 2, 1, 1);
        newRecGrid.add(tf_address2, 1, 2, 1, 1);
        newRecGrid.add(tf_parish, 0, 3, 1, 1);
        newRecGrid.setVgap(15);
        newRecGrid.setHgap(15);
        newRecGrid.setAlignment(Pos.CENTER);

        HBox labels = new HBox();
        labels.getChildren().addAll(l_oldRec, l_newRec);
        labels.setAlignment(Pos.CENTER);
        labels.setSpacing(200);
        HBox grids = new HBox();
        grids.getChildren().addAll(oldRecGrid, newRecGrid);
        grids.setAlignment(Pos.CENTER);
        grids.setSpacing(20);
        HBox buttons = new HBox();
        buttons.getChildren().addAll(b_accept, b_cancel);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(200);

        VBox layout = new VBox();
        layout.getChildren().addAll(l_id, labels, grids, buttons);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(20);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();
        return formData;
    }
}