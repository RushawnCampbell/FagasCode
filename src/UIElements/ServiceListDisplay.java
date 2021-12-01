package UIElements;

import java.util.ArrayList;

import Logic.CustomerRecord;
import Logic.RecordManager;
import Logic.ServiceManager;
import Logic.ServiceRequest;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServiceListDisplay {

    // private UIManager uiManager;
    // private ServiceManager serviceManager;
    private Stage window;
    private Label titleLabel, idLabel, nameLabel, typeLabel, statusLabel, instructionLabel;
    private Button createRecord;
    private GridPane table;
    private ScrollPane scroll;
    private VBox centerLayout;
    private ArrayList<Integer> selectedServices;

    public ServiceListDisplay(Stage primaryStage, UIManager uiMngr, ServiceManager srvcMngr) {
        window = primaryStage;
        // uiManager = uiMngr;
        // serviceManager = srvcMngr;
        InitializeAttributes();
    }

    private void InitializeAttributes() {
        titleLabel = new Label("Service List Generator");
        idLabel = new Label("ID");
        nameLabel = new Label("Customer Name");
        typeLabel = new Label("Service Type");
        statusLabel = new Label("Status");
        instructionLabel = new Label(
                "To generate Service List, select multiple Service Requests from the table below and click 'Generate Service List'");

        createRecord = new Button("Generate Service List");
        table = new GridPane();
        centerLayout = new VBox();
        scroll = new ScrollPane(table);
        selectedServices = new ArrayList<Integer>();
    }

    private void ConfigureAttributes(BorderPane layout) {
        table.setAlignment(Pos.TOP_CENTER);
        table.setHgap(15);
        table.setVgap(5);
        AddTable();

        createRecord.setOnAction(e -> PrintCheckBoxes());

        scroll.setMaxHeight(500);
        scroll.setPadding(new Insets(10, 10, 10, 10));

        centerLayout.getChildren().addAll(titleLabel, instructionLabel, createRecord, scroll);
        centerLayout.setAlignment(Pos.TOP_CENTER);
        centerLayout.setSpacing(20);

        layout.setCenter(centerLayout);
    }

    private void AddTable() {
        table.add(idLabel, 0, 0, 1, 1);
        table.add(nameLabel, 1, 0, 1, 1);
        table.add(typeLabel, 2, 0, 1, 1);
        table.add(statusLabel, 3, 0, 1, 1);
        ArrayList<ServiceRequest> list = ServiceManager.serviceReqList;

        int row = 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus().equals("Incomplete")) {
                String name = "";
                for (CustomerRecord rec : RecordManager.recordList) {
                    if (rec.getId() == list.get(i).getCustomerId()) {
                        name = rec.getFirst() + " " + rec.getLast();
                    }
                }
                CheckBox select = new CheckBox(String.valueOf(list.get(i).getServiceId()));
                select.setOnAction(e -> AddCheckBox(select));
                table.add(select, 0, row, 1, 1);

                Label temp = new Label(name);
                table.add(temp, 1, row, 1, 1);

                temp = new Label(list.get(i).getType());
                table.add(temp, 2, row, 1, 1);

                temp = new Label(list.get(i).getStatus());
                table.add(temp, 3, row, 1, 1);
                row++;
            }
        }
    }

    private void AddCheckBox(CheckBox select) {

        if (select.isSelected()) {
            selectedServices.add(Integer.parseInt(select.getText()));
        } else {
            for (int i = 0; i < selectedServices.size(); i++) {
                if (selectedServices.get(i) == Integer.parseInt(select.getText())) {
                    selectedServices.remove(i);
                }
            }
        }
    }

    public void PrintCheckBoxes() {
        String msg = "CheckBoxes: ";
        for (int i = 0; i < selectedServices.size(); i++) {
            msg += selectedServices.get(i) + ", ";
        }
        System.out.println(msg);
    }

    public void LoadDisplay(BorderPane blayout) {
        ConfigureAttributes(blayout);
        window.setTitle("Service List Generator");
        window.show();
    }
}
