package UIElements;

import LogicManagers.ServiceManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServiceManagerDisplay {

    // private UIManager uiManager;
    // private ServiceManager serviceManager;
    private Stage window;
    private Label titleLabel, idLabel, nameLabel, typeLabel, statusLabel;
    private Button createRecord;
    private GridPane table;
    private ScrollPane scroll;
    private VBox centerLayout;

    public ServiceManagerDisplay(Stage primaryStage, UIManager uiMngr, ServiceManager srvcMngr) {
        window = primaryStage;
        // uiManager = uiMngr;
        // serviceManger = srvcMngr;
        InitializeAttributes();
    }

    private void InitializeAttributes() {
        titleLabel = new Label("Service Request Manager");
        idLabel = new Label("ID");
        nameLabel = new Label("Customer Name");
        typeLabel = new Label("Service Type");
        statusLabel = new Label("Status");

        createRecord = new Button("Create New Service Request");
        table = new GridPane();
        centerLayout = new VBox();
        scroll = new ScrollPane(table);
    }

    private void ConfigureAttributes(BorderPane layout) {
        table.setAlignment(Pos.TOP_CENTER);
        table.setHgap(15);
        table.setVgap(5);
        AddTable();

        scroll.setMaxHeight(500);
        scroll.setPadding(new Insets(10, 10, 10, 10));

        centerLayout.getChildren().addAll(titleLabel, createRecord, scroll);
        centerLayout.setAlignment(Pos.TOP_CENTER);
        centerLayout.setSpacing(20);

        layout.setCenter(centerLayout);
    }

    private void AddTable() {
        table.add(idLabel, 0, 0, 1, 1);
        table.add(nameLabel, 1, 0, 1, 1);
        table.add(typeLabel, 2, 0, 1, 1);
        table.add(statusLabel, 3, 0, 1, 1);

        for (int i = 1; i < 30; i++) {
            Label temp = new Label(String.valueOf(i));
            table.add(temp, 0, i, 1, 1);

            temp = new Label("Customer" + i);
            table.add(temp, 1, i, 1, 1);

            if (i % 2 == 0) {
                temp = new Label("Gas Delivery");
            } else {
                temp = new Label("Equipment Repair");
            }
            table.add(temp, 2, i, 1, 1);

            if (i % 3 == 0) {
                temp = new Label("Complete");
            } else {
                temp = new Label("Incomplete");
            }
            table.add(temp, 3, i, 1, 1);

            Button viewButton = new Button("View");
            table.add(viewButton, 4, i, 1, 1);
        }
    }

    public void LoadDisplay(BorderPane blayout) {
        ConfigureAttributes(blayout);
        window.setTitle("Service Request Manager");
        window.show();
    }
}
