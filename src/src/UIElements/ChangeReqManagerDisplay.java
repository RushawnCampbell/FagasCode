package UIElements;

import java.util.ArrayList;

import Logic.ChangeRequest;
import Logic.ChangeRequestManager;
import PopUpDisplays.ChangeReqView;
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

public class ChangeReqManagerDisplay {

    private UIManager uiManager;
    private ChangeRequestManager changeRequestManager;
    private Stage window;
    private Label titleLabel, idLabel, nameLabel;
    private GridPane table;
    private ScrollPane scroll;
    private VBox centerLayout;

    public ChangeReqManagerDisplay(Stage primaryStage, UIManager uiMngr, ChangeRequestManager chngRqMngr) {
        window = primaryStage;
        uiManager = uiMngr;
        changeRequestManager = chngRqMngr;
        InitializeAttributes();
    }

    private void InitializeAttributes() {
        titleLabel = new Label("Change Request Manager");
        idLabel = new Label("ID");
        nameLabel = new Label("Customer Name");

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

        centerLayout.getChildren().addAll(titleLabel, scroll);
        centerLayout.setAlignment(Pos.TOP_CENTER);
        centerLayout.setSpacing(20);

        layout.setCenter(centerLayout);
    }

    private void AddTable() {
        ArrayList<ChangeRequest> list = ChangeRequestManager.changeReqList;
        table.add(idLabel, 0, 0, 1, 1);
        table.add(nameLabel, 1, 0, 1, 1);

        int row = 1;
        for (int i = 0; i < list.size(); i++) {
            ChangeRequest changeReq = list.get(i);
            Label temp = new Label(String.valueOf(changeReq.getId()));
            table.add(temp, 0, row, 1, 1);

            String name = changeReq.getOldRecord().getFirst() + " " + changeReq.getOldRecord().getLast();
            temp = new Label(name);
            table.add(temp, 1, row, 1, 1);

            Button viewButton = new Button("View");
            viewButton.setOnAction(e -> {
                int res = 0;
                ArrayList<String> info = ChangeReqView.LoadDisplay(changeReq);
                if (info.size() > 0) {
                    res = changeRequestManager.AcceptChangeRequest(info);
                }
                if (res == 1) {
                    StatusMessage.DisplayMessage("Change Request was accepted successfully");
                    uiManager.LoadChangeReqManagerDisplay();
                }
            });
            table.add(viewButton, 2, row, 1, 1);
            row++;
        }
    }

    public void LoadDisplay(BorderPane blayout) {
        ConfigureAttributes(blayout);
        window.setTitle("Change Request Manager");
        window.show();
    }
}
