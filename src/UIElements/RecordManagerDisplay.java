package UIElements;

import LogicManagers.RecordManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RecordManagerDisplay {
    private Stage window;
    private RecordManager recordManager;
    // private UIManager uiManager;

    private Label titleLabel, idLabel, nameLabel, contactLabel;
    private Button createRecord;
    private GridPane recordTable;
    private ScrollPane scroll;
    private VBox centerLayout;

    public RecordManagerDisplay(Stage primaryStage, UIManager uiMngr, RecordManager recMngr) {
        // uiManager = uiMngr;
        window = primaryStage;
        InitializeAttributes();
        recordManager = recMngr;
    }

    private void InitializeAttributes() {
        titleLabel = new Label("Record Manager");
        idLabel = new Label("ID");
        nameLabel = new Label("Customer Name");
        contactLabel = new Label("Contact Number");

        createRecord = new Button("Create New Customer Record");
        recordTable = new GridPane();
        centerLayout = new VBox();
        scroll = new ScrollPane(recordTable);
    }

    private void ConfigureAttributes(BorderPane layout) {
        recordTable.setAlignment(Pos.TOP_CENTER);
        recordTable.setHgap(15);
        recordTable.setVgap(5);
        AddTable();

        scroll.setMaxHeight(500);
        scroll.setPadding(new Insets(10, 10, 10, 10));

        centerLayout.getChildren().addAll(titleLabel, createRecord, scroll);
        centerLayout.setAlignment(Pos.TOP_CENTER);
        centerLayout.setSpacing(20);

        layout.setCenter(centerLayout);
    }

    private void AddTable() {
        recordTable.add(idLabel, 0, 0, 1, 1);
        recordTable.add(nameLabel, 1, 0, 1, 1);
        recordTable.add(contactLabel, 2, 0, 1, 1);

        for (int i = 1; i < 30; i++) {
            Label temp = new Label(String.valueOf(i));
            recordTable.add(temp, 0, i, 1, 1);

            temp = new Label("Customer" + i);
            recordTable.add(temp, 1, i, 1, 1);

            temp = new Label("Contact" + i);
            recordTable.add(temp, 2, i, 1, 1);

            Button viewButton = new Button("View");
            recordTable.add(viewButton, 3, i, 1, 1);

            Button modButton = new Button("Modify");
            recordTable.add(modButton, 4, i, 1, 1);
        }
    }

    public void LoadDisplay(BorderPane blayout) {
        ConfigureAttributes(blayout);
        window.setTitle("Record Manager");
        window.show();
    }

}