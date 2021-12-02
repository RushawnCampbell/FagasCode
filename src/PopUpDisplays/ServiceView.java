package PopUpDisplays;

import java.util.ArrayList;

import Logic.ServiceManager;
import Logic.ServiceRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ServiceView {
    private boolean isModified;

    public boolean LoadDisplay(ServiceRequest service, ServiceManager serviceManager) {
        ArrayList<String> list = new ArrayList<String>();
        isModified = false;
        Stage window = new Stage();
        window.setTitle("Service Request");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        GridPane labelGrid = new GridPane();
        GridPane textGrid = new GridPane();
        Scene viewScene = new Scene(labelGrid);
        Scene textScene = new Scene(textGrid);

        Label l_serviceId = new Label("Service ID: " + service.getServiceId());
        Label l_custId = new Label("Customer ID: " + service.getCustomerId());
        Label l_type = new Label("Service Type: " + service.getType());
        Label l_comments = new Label("Additonal Comments: " + service.getComments());
        Label l_status = new Label("Status: " + service.getStatus());
        Label l_price = new Label("Price: " + service.getPrice());
        Label l_quantity = new Label("Quantity: " + service.getQuantity());
        Label l_date = new Label("Date Created: " + service.getDateCreated());

        Label l_serviceId2 = new Label("Service ID: " + service.getServiceId());
        Label l_custId2 = new Label("Customer ID: " + service.getCustomerId());
        Label l_date2 = new Label("Date Created: " + service.getDateCreated());

        TextField tf_comments = new TextField(service.getComments());
        tf_comments.setPromptText("Additional Comments");
        TextField tf_price = new TextField(String.valueOf(service.getPrice()));
        TextField tf_quantity = new TextField(String.valueOf(service.getQuantity()));

        ObservableList<String> statusOptions = FXCollections.observableArrayList(
                "Incomplete",
                "Complete");
        final ComboBox<String> statusDropdown = new ComboBox<String>(statusOptions);
        statusDropdown.setValue(service.getStatus());

        ObservableList<String> typeOptions = FXCollections.observableArrayList(
                "Gas Delivery",
                "Equipment Repair");
        final ComboBox<String> typeDropdown = new ComboBox<String>(typeOptions);
        typeDropdown.setValue(service.getType());

        Button b_modify = new Button("MODIFY");
        b_modify.setOnAction(e -> window.setScene(textScene));
        Button b_close = new Button("CLOSE");
        b_close.setOnAction(e -> window.close());
        Button b_cancel = new Button("CANCEL");
        b_cancel.setOnAction(e -> window.setScene(viewScene));
        Button b_submit = new Button("SUBMIT");
        b_submit.setOnAction(e -> {
            boolean res = StatusMessage.ConfirmDisplay("Are you sure you want to save these Changes?");

            if (res) {
                list.add(String.valueOf(service.getServiceId()));
                list.add(typeDropdown.getValue());
                list.add(tf_price.getText());
                list.add(tf_quantity.getText());
                list.add(tf_comments.getText());
                list.add(statusDropdown.getValue());
                setModified();
                int resp = serviceManager.ModifyRecord(list);

                if (resp == 1) {
                    StatusMessage.DisplayMessage("Service Request was modified successfully.");
                    window.close();
                }

            }
        });

        labelGrid.add(l_serviceId, 0, 0);
        labelGrid.add(l_custId, 1, 0);
        labelGrid.add(l_type, 0, 1);
        labelGrid.add(l_status, 1, 1);
        labelGrid.add(l_price, 0, 2);
        labelGrid.add(l_quantity, 1, 2);
        labelGrid.add(l_comments, 0, 3);
        labelGrid.add(l_date, 1, 3);
        labelGrid.add(b_modify, 0, 4);
        labelGrid.add(b_close, 1, 4);
        labelGrid.setVgap(15);
        labelGrid.setHgap(30);
        labelGrid.setAlignment(Pos.TOP_CENTER);
        labelGrid.setPadding(new Insets(20));

        textGrid.add(l_serviceId2, 0, 0);
        textGrid.add(l_custId2, 1, 0);
        textGrid.add(typeDropdown, 0, 1);
        textGrid.add(statusDropdown, 1, 1);
        textGrid.add(tf_price, 0, 2);
        textGrid.add(tf_quantity, 1, 2);
        textGrid.add(tf_comments, 0, 3);
        textGrid.add(l_date2, 1, 3);
        textGrid.add(b_submit, 1, 4);
        textGrid.add(b_cancel, 0, 4);
        textGrid.setVgap(15);
        textGrid.setHgap(30);
        textGrid.setAlignment(Pos.TOP_CENTER);
        textGrid.setPadding(new Insets(20));

        window.setScene(viewScene);
        window.showAndWait();
        return isModified;
    }

    private void setModified() {
        isModified = true;
    }
}
