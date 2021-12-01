package UIElements;

import java.util.ArrayList;

import Logic.CustomerRecord;
import Logic.RecordManager;
import PopUpDisplays.CreateRecordDisplay;
import PopUpDisplays.RecordView;
import PopUpDisplays.StatusMessage;
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
    private ArrayList<String> list;
    private Stage window;
    private RecordManager recordManager;
    private UIManager uiManager;

    private Label titleLabel, idLabel, nameLabel, contactLabel;
    private Button createRecord;
    private GridPane recordTable;
    private ScrollPane scroll;
    private VBox centerLayout;
    private RecordView recordView;

    public RecordManagerDisplay(Stage primaryStage, UIManager uiMngr, RecordManager recMngr) {
        uiManager = uiMngr;
        recordManager = recMngr;
        window = primaryStage;
        InitializeAttributes();
        recordView = new RecordView();
    }

    private void InitializeAttributes() {
        titleLabel = new Label("Customer Record Manager");
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

        // ArrayList<String> list;
        createRecord.setOnAction(e -> {
            this.list = CreateRecordDisplay.LoadDisplay();
            if (this.list.size() > 0)
                SubmitRecord(list);
        });

    }

    private void SubmitRecord(ArrayList<String> list) {
        int res = recordManager.CreateRecord(list);

        if (res == 1) {
            StatusMessage.DisplayMessage("Customer Record was created successfully.");
        } else {
            StatusMessage.DisplayMessage("Customer Record was not successfully created.");
        }
        uiManager.LoadRecordManagerDisplay();
    }

    private void AddTable() {
        recordTable.add(idLabel, 0, 0, 1, 1);
        recordTable.add(nameLabel, 1, 0, 1, 1);
        recordTable.add(contactLabel, 2, 0, 1, 1);
        ArrayList<CustomerRecord> list = RecordManager.recordList;

        int a = 0;
        for (int i = 1; i < list.size() + 1; i++) {
            ArrayList<String> info = new ArrayList<String>();
            info.add(String.valueOf(list.get(a).getId()));
            info.add(list.get(a).getFirst());
            info.add(list.get(a).getLast());
            info.add(list.get(a).getPhone());
            info.add(list.get(a).getAd1());
            info.add(list.get(a).getAd2());
            info.add(list.get(a).getParish());
            info.add(list.get(a).getEmail());

            Label temp = new Label(String.valueOf(list.get(a).getId()));
            recordTable.add(temp, 0, i, 1, 1);

            temp = new Label(list.get(a).getFirst() + " " + list.get(a).getLast());
            recordTable.add(temp, 1, i, 1, 1);

            temp = new Label(list.get(a).getPhone());
            recordTable.add(temp, 2, i, 1, 1);

            Button viewButton = new Button("View");
            viewButton.setOnAction(e -> {
                boolean isModed = recordView.LoadDisplay(info, recordManager);
                if (isModed)
                    uiManager.LoadRecordManagerDisplay();
            });
            recordTable.add(viewButton, 3, i, 1, 1);
            a++;
        }
    }

    public void LoadDisplay(BorderPane blayout) {
        ConfigureAttributes(blayout);
        window.setTitle("Record Manager");
        window.show();
    }

}