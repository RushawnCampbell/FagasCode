package UIElements;

import java.util.ArrayList;

import Logic.ChangeRequestManager;
import Logic.CustomerRecord;
import Logic.RecordManager;
import PopUpDisplays.CreateChangeReqDisplay;
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

public class ChangeRequestDisplay {

    private UIManager uiManager;
    private ChangeRequestManager changeRequestManager;
    private Stage window;
    private Label titleLabel, idLabel, nameLabel, requestByLabel, descrLabel;
    private GridPane table;
    private ScrollPane scroll;
    private VBox centerLayout;
    private RecordManager recordManager;

    public ChangeRequestDisplay(Stage primaryStage, UIManager uiMngr, ChangeRequestManager chngRqMngr,
            RecordManager recordManager) {
        window = primaryStage;
        uiManager = uiMngr;
        changeRequestManager = chngRqMngr;
        InitializeAttributes();
        this.recordManager = recordManager;
    }

    private void InitializeAttributes() {
        titleLabel = new Label("Change Requests");
        descrLabel = new Label("Select the Customer Record you wish to make a change request for.");
        idLabel = new Label("ID");
        nameLabel = new Label("Customer Name");
        requestByLabel = new Label("Requested by");
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

        centerLayout.getChildren().addAll(titleLabel, descrLabel, scroll);
        centerLayout.setAlignment(Pos.TOP_CENTER);
        centerLayout.setSpacing(20);

        layout.setCenter(centerLayout);
    }

    private void AddTable() {
        ArrayList<CustomerRecord> list = recordManager.getRecordList();
        table.add(idLabel, 0, 0, 1, 1);
        table.add(nameLabel, 1, 0, 1, 1);
        table.add(requestByLabel, 2, 0, 1, 1);

        int row = 1;
        for (int i = 0; i < list.size(); i++) {
            Label temp = new Label(String.valueOf(list.get(i).getId()));
            table.add(temp, 0, row, 1, 1);

            temp = new Label(list.get(i).getFirst() + " " + list.get(i).getLast());
            table.add(temp, 1, row, 1, 1);

            temp = new Label(list.get(i).getPhone());
            table.add(temp, 2, row, 1, 1);

            final int b = i;
            Button selectButton = new Button("Select");
            selectButton.setOnAction(e -> {
                ArrayList<String> info = CreateChangeReqDisplay.LoadDisplay(list.get(b));
                if (info.size() > 0) {
                    int res = changeRequestManager.CreateChangeRequest(info);
                    if (res == 1) {
                        StatusMessage.DisplayMessage("Change request submitted successfully.");
                    } else {
                        StatusMessage.DisplayMessage("Change request was not submittied successfully");
                    }
                }
                uiManager.LoadChangeRequestDisplay();
            });
            table.add(selectButton, 3, row, 1, 1);
            row++;
        }
    }

    public void LoadDisplay(BorderPane blayout) {
        ConfigureAttributes(blayout);
        window.setTitle("Change Requests");
        window.show();
    }
}
