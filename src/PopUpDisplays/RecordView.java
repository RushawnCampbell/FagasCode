package PopUpDisplays;

import java.util.ArrayList;

import Logic.RecordManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RecordView {
    private boolean isModified;

    public boolean LoadDisplay(ArrayList<String> record, RecordManager recManager) {
        isModified = false;
        ArrayList<String> list = new ArrayList<String>();
        Stage window = new Stage();
        window.setTitle("Customer Record");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        GridPane labelGrid = new GridPane();
        GridPane textGrid = new GridPane();
        Scene viewScene = new Scene(labelGrid);
        Scene textScene = new Scene(textGrid);

        Label l_id = new Label("Record ID: " + record.get(0));
        Label l_id2 = new Label("Record ID: " + record.get(0));
        Label l_fName = new Label("First Name: " + record.get(1));
        Label l_lName = new Label("Last Name: " + record.get(2));
        Label l_email = new Label("Email: " + record.get(7));
        Label l_contact = new Label("Contact Number: " + record.get(3));
        Label l_address1 = new Label("Address Line 1: " + record.get(4));
        Label l_address2 = new Label("Address Line 2: " + record.get(5));
        Label l_parish = new Label("Parish: " + record.get(6));

        TextField tf_fName = new TextField(record.get(1));
        TextField tf_lName = new TextField(record.get(2));
        TextField tf_email = new TextField(record.get(7));
        TextField tf_contact = new TextField(record.get(3));
        TextField tf_address1 = new TextField(record.get(4));
        TextField tf_address2 = new TextField(record.get(5));
        TextField tf_parish = new TextField(record.get(6));

        Button b_modify = new Button("MODIFY");
        b_modify.setOnAction(e -> window.setScene(textScene));
        Button close_b = new Button("CLOSE");
        close_b.setOnAction(e -> window.close());

        Button b_submit = new Button("SUBMIT");
        b_submit.setOnAction(e -> {
            boolean res = StatusMessage.ConfirmDisplay("Are you sure you want to save these Changes?");

            if (res) {
                list.add(record.get(0));
                list.add(tf_fName.getText());
                list.add(tf_lName.getText());
                list.add(tf_email.getText());
                list.add(tf_contact.getText());
                list.add(tf_address1.getText());
                list.add(tf_address2.getText());
                list.add(tf_parish.getText());
                recManager.ModifyRecord(list);
                setModified();
                window.close();
            }

        });
        Button cancel_b = new Button("CANCEL");
        cancel_b.setOnAction(e -> window.setScene(viewScene));

        textGrid.add(tf_fName, 0, 0, 1, 1);
        textGrid.add(tf_lName, 1, 0, 1, 1);
        textGrid.add(tf_email, 0, 1, 1, 1);
        textGrid.add(tf_contact, 1, 1, 1, 1);
        textGrid.add(tf_address1, 0, 2, 1, 1);
        textGrid.add(tf_address2, 1, 2, 1, 1);
        textGrid.add(tf_parish, 0, 3, 1, 1);
        textGrid.add(l_id, 1, 3, 1, 1);
        textGrid.add(b_submit, 1, 4, 1, 1);
        textGrid.add(cancel_b, 0, 4, 1, 1);
        textGrid.setVgap(15);
        textGrid.setHgap(15);

        labelGrid.add(l_fName, 0, 0, 1, 1);
        labelGrid.add(l_lName, 1, 0, 1, 1);
        labelGrid.add(l_email, 0, 1, 1, 1);
        labelGrid.add(l_contact, 1, 1, 1, 1);
        labelGrid.add(l_address1, 0, 2, 1, 1);
        labelGrid.add(l_address2, 1, 2, 1, 1);
        labelGrid.add(l_parish, 0, 3, 1, 1);
        labelGrid.add(l_id2, 1, 3, 1, 1);
        labelGrid.add(b_modify, 0, 4, 1, 1);
        labelGrid.add(close_b, 1, 4, 1, 1);
        labelGrid.setVgap(15);
        labelGrid.setHgap(15);

        window.setScene(viewScene);
        window.showAndWait();
        return isModified;
    }

    private void setModified() {
        isModified = true;
    }
}
