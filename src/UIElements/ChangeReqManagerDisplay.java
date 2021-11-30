package UIElements;

import LogicManagers.ChangeRequestManager;
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

    // private UIManager uiManager;
    // private ChangeRequestManager changeRequestManager;
    private Stage window;
    private Label titleLabel, idLabel, nameLabel, requestByLabel;
    private GridPane table;
    private ScrollPane scroll;
    private VBox centerLayout;

    public ChangeReqManagerDisplay(Stage primaryStage, UIManager uiMngr, ChangeRequestManager chngRqMngr) {
        window = primaryStage;
        // uiManager = uiMngr;
        // changeRequestManager = chngRqMngr;
        InitializeAttributes();
    }

    private void InitializeAttributes() {
        titleLabel = new Label("Change Request Manager");
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

        centerLayout.getChildren().addAll(titleLabel, scroll);
        centerLayout.setAlignment(Pos.TOP_CENTER);
        centerLayout.setSpacing(20);

        layout.setCenter(centerLayout);
    }

    private void AddTable() {
        table.add(idLabel, 0, 0, 1, 1);
        table.add(nameLabel, 1, 0, 1, 1);
        table.add(requestByLabel, 2, 0, 1, 1);

        for (int i = 1; i < 30; i++) {
            Label temp = new Label(String.valueOf(i));
            table.add(temp, 0, i, 1, 1);

            temp = new Label("Customer" + i);
            table.add(temp, 1, i, 1, 1);

            if (i % 4 == 0) {
                temp = new Label("Cashier1");
            } else {
                temp = new Label("Cashier2");
            }
            table.add(temp, 2, i, 1, 1);

            Button viewButton = new Button("View");
            table.add(viewButton, 3, i, 1, 1);
        }
    }

    public void LoadDisplay(BorderPane blayout) {
        ConfigureAttributes(blayout);
        window.setTitle("Change Request Manager");
        window.show();
    }
}
