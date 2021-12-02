package PopUpDisplays;

import java.util.ArrayList;

import Logic.CustomerRecord;
import Logic.RecordManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateServiceDisplay {
    private Stage window;
    private Scene scene1;
    private ArrayList<String> formData;
    private String selectedCustomer;
    private RecordManager recordManager;

    public CreateServiceDisplay(RecordManager recordManager) {
        this.recordManager = recordManager;
    }

    public ArrayList<String> LoadDisplay() {
        formData = new ArrayList<String>();
        window = new Stage();
        window.setTitle("Create Customer Record");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        Label descLabel = new Label(
                "Select The customer Record you wish to make a service request for or click 'CANCEL'.");
        Label idLabel = new Label("ID");
        Label nameLabel = new Label("Customer Name");
        Label contactLabel = new Label("Contact Number");

        GridPane recordTable = new GridPane();
        recordTable.add(idLabel, 0, 0, 1, 1);
        recordTable.add(nameLabel, 1, 0, 1, 1);
        recordTable.add(contactLabel, 2, 0, 1, 1);
        ArrayList<CustomerRecord> list = recordManager.getRecordList();

        ScrollPane scroll = new ScrollPane(recordTable);
        scroll.setMaxHeight(500);
        scroll.setPadding(new Insets(10, 10, 10, 10));

        int a = 0;
        for (int i = 1; i < list.size() + 1; i++) {
            Label temp = new Label(String.valueOf(list.get(a).getId()));
            recordTable.add(temp, 0, i, 1, 1);

            temp = new Label(list.get(a).getFirst() + list.get(a).getLast());
            recordTable.add(temp, 1, i, 1, 1);

            temp = new Label(list.get(a).getPhone());
            recordTable.add(temp, 2, i, 1, 1);

            final int b = a;
            Button selectButton = new Button("Select");
            selectButton.setOnAction(e -> {
                SelectCustomer(b);
                LoadForm();
            });
            recordTable.add(selectButton, 3, i, 1, 1);
            a++;
        }
        Button b_cancel = new Button("CANCEL");
        b_cancel.setOnAction(e -> window.close());
        recordTable.setVgap(20);
        recordTable.setHgap(20);

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(15);
        layout.getChildren().addAll(descLabel, scroll, b_cancel);
        layout.setAlignment(Pos.CENTER);

        scene1 = new Scene(layout);
        window.setScene(scene1);
        window.showAndWait();
        return formData;
    }

    private void SelectCustomer(int index) {
        selectedCustomer = String.valueOf(recordManager.getRecordList().get(index).getId());
    }

    private void LoadForm() {
        Label title_l = new Label("Create A New Service Request");
        Label descr_l = new Label("Enter the appropriate Information into the fields provided and click 'Create'.");

        TextField tf_comments = new TextField();
        tf_comments.setPromptText("Comments");
        TextField tf_price = new TextField();
        tf_price.setPromptText("Price");
        TextField tf_quantity = new TextField();
        tf_quantity.setPromptText("Quantity");

        ObservableList<String> typeOptions = FXCollections.observableArrayList(
                "Gas Delivery",
                "Equipment Repair");

        final ComboBox<String> typeDropdown = new ComboBox<String>(typeOptions);
        typeDropdown.setPromptText("Service Type");

        Button b_create = new Button("CREATE");
        b_create.setOnAction(e -> {
            boolean res = StatusMessage.ConfirmDisplay("Are you sure you want to create this Service Request?");
            if (res) {
                formData.add(typeDropdown.getValue());
                formData.add(tf_comments.getText());
                formData.add(tf_price.getText());
                formData.add(tf_quantity.getText());
                formData.add(selectedCustomer);
                window.close();
            }
        });

        Button b_cancel = new Button("CANCEL");
        b_cancel.setOnAction(e -> window.setScene(scene1));

        GridPane gridLayout = new GridPane();
        gridLayout.add(typeDropdown, 0, 0, 1, 1);
        gridLayout.add(tf_comments, 1, 0, 1, 1);
        gridLayout.add(tf_price, 0, 1, 1, 1);
        gridLayout.add(tf_quantity, 1, 1, 1, 1);
        gridLayout.add(b_create, 0, 2, 1, 1);
        gridLayout.add(b_cancel, 1, 2, 1, 1);
        gridLayout.setVgap(15);
        gridLayout.setHgap(15);
        gridLayout.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(title_l, descr_l, gridLayout);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(30);
        layout.setPadding(new Insets(20));
        layout.setMinWidth(400);

        Scene scene = new Scene(layout);
        window.setScene(scene);
    }

}
