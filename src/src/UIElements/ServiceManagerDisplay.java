package UIElements;

import java.util.ArrayList;

import Logic.CustomerRecord;
import Logic.RecordManager;
import Logic.ServiceManager;
import Logic.ServiceRequest;
import PopUpDisplays.CreateServiceDisplay;
import PopUpDisplays.ServiceView;
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

    private UIManager uiManager;
    private ServiceManager serviceManager;
    private Stage window;
    private Label titleLabel, idLabel, nameLabel, typeLabel, statusLabel;
    private Button createService;
    private GridPane table;
    private ScrollPane scroll;
    private VBox centerLayout;
    private RecordManager recordManager;

    public ServiceManagerDisplay(Stage primaryStage, UIManager uiMngr, ServiceManager srvcMngr,
            RecordManager recordManager) {
        window = primaryStage;
        uiManager = uiMngr;
        serviceManager = srvcMngr;
        this.recordManager = recordManager;
        InitializeAttributes();
    }

    private void InitializeAttributes() {
        titleLabel = new Label("Service Request Manager");
        idLabel = new Label("ID");
        nameLabel = new Label("Customer Name");
        typeLabel = new Label("Service Type");
        statusLabel = new Label("Status");

        createService = new Button("Create New Service Request");
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

        createService.setOnAction(e -> {
            CreateServiceDisplay createService = new CreateServiceDisplay(recordManager);
            ArrayList<String> info = createService.LoadDisplay();
            if (info.size() > 0) {
                serviceManager.CreateService(info);
                uiManager.LoadServiceManagerDisplay();
            }
        });

        centerLayout.getChildren().addAll(titleLabel, createService, scroll);
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
            ArrayList<String> info = new ArrayList<String>();
            info.add(String.valueOf(list.get(i).getServiceId()));
            info.add(String.valueOf(list.get(i).getCustomerId()));
            info.add(list.get(i).getType());
            info.add(String.valueOf(list.get(i).getPrice()));
            info.add(String.valueOf(list.get(i).getQuantity()));
            info.add(list.get(i).getComments());
            info.add(list.get(i).getStatus());
            info.add(list.get(i).getDateCreated().toString());

            String name = "";
            for (CustomerRecord rec : recordManager.getRecordList()) {
                if (rec.getId() == list.get(i).getCustomerId()) {
                    name = rec.getFirst() + " " + rec.getLast();
                }
            }

            Label temp = new Label(String.valueOf(list.get(i).getServiceId()));
            table.add(temp, 0, row, 1, 1);

            temp = new Label(name);
            table.add(temp, 1, row, 1, 1);

            temp = new Label(list.get(i).getType());
            table.add(temp, 2, row, 1, 1);

            temp = new Label(list.get(i).getStatus());
            table.add(temp, 3, row, 1, 1);

            ServiceRequest service = list.get(i);
            Button viewButton = new Button("View");
            viewButton.setOnAction(e -> {
                ServiceView serviceView = new ServiceView();
                boolean resp = serviceView.LoadDisplay(service, serviceManager);

                if (resp)
                    uiManager.LoadServiceManagerDisplay();
            });
            table.add(viewButton, 4, row, 1, 1);
            row++;
        }
    }

    public void LoadDisplay(BorderPane blayout) {
        ConfigureAttributes(blayout);
        window.setTitle("Service Request Manager");
        window.show();
    }
}
