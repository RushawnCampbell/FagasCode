package UIElements;

import Logic.ChangeRequestManager;
import Logic.RecordManager;
import Logic.ServiceManager;
import PopUpDisplays.StatusMessage;
import Security.Authenticator;
import Security.User;
import Security.User.UserType;
import UIElements.UIManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UIManager {
    // Attributes
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private VBox vLayout;
    private Button manageRecords, manageRequest, manageChangeReq, logout, reports, serviceList, changeReq, homeButton;

    private LoginDisplay loginDisplay;
    private HomeDisplay homeDisplay;
    private RecordManagerDisplay recordManagerDisplay;
    private ServiceManagerDisplay serviceManagerDisplay;
    private ReportGeneratorDisplay reportGeneratorDisplay;
    private ChangeReqManagerDisplay changeReqManagerDisplay;
    private ChangeRequestDisplay changeRequestDisplay;
    private ServiceListDisplay serviceListDisplay;

    private User currentUser;
    private Authenticator auth;
    private RecordManager recordManager;
    private ServiceManager serviceManager;
    private ChangeRequestManager changeRequestManager;

    public UIManager(Stage primaryStage) {
        window = primaryStage;
        auth = new Authenticator();
        recordManager = new RecordManager();
        serviceManager = new ServiceManager();
        changeRequestManager = new ChangeRequestManager();
    }

    // creates login display class and sets the scene to the login display.
    public void LoadLoginDisplay() {
        loginDisplay = new LoginDisplay(window, this);
        loginDisplay.LoadDisplay();
    }

    // creates home display class and sets the scene to the home display.
    public void LoadHomeDisplay() {
        ConfigureWindow();
        homeDisplay = new HomeDisplay(window, this);
        homeDisplay.LoadDisplay(layout);
    }

    public void LoadRecordManagerDisplay() {
        recordManagerDisplay = new RecordManagerDisplay(window, this, recordManager);
        recordManagerDisplay.LoadDisplay(layout);
    }

    public void LoadServiceManagerDisplay() {
        serviceManagerDisplay = new ServiceManagerDisplay(window, this, serviceManager);
        serviceManagerDisplay.LoadDisplay(layout);
    }

    public void LoadReportGeneratorDisplay() {
        reportGeneratorDisplay = new ReportGeneratorDisplay(window, this);
        reportGeneratorDisplay.LoadDisplay(layout);
    }

    public void LoadChangeReqManagerDisplay() {
        changeReqManagerDisplay = new ChangeReqManagerDisplay(window, this, changeRequestManager);
        changeReqManagerDisplay.LoadDisplay(layout);
    }

    public void LoadChangeRequestDisplay() {
        changeRequestDisplay = new ChangeRequestDisplay(window, this, changeRequestManager);
        changeRequestDisplay.LoadDisplay(layout);
    }

    public void LoadServiceListDisplay() {
        serviceListDisplay = new ServiceListDisplay(window, this, serviceManager);
        serviceListDisplay.LoadDisplay(layout);
    }

    // this class receive username and password from login and seds it to
    // authenticator to verify the credentials and loads appropriate home page
    // depending on the type of user tht logged in.
    public void Login(String username, String password) {
        UserType result = auth.Verify(username, password);
        if (result == UserType.NONE) {
            currentUser = new User("username", "password");
            StatusMessage.DisplayMessage("Incorrect username or password. Please try again.");
        } else {
            currentUser = new User(username, password);
            currentUser.setUserType(result);
            LoadHomeDisplay();
        }
    }

    private void Logout() {
        boolean response = StatusMessage.ConfirmDisplay("Are you sure you want to Logout?");

        if (response) {
            currentUser = null;
            LoadLoginDisplay();
        }

    }

    private void ConfigureWindow() {
        vLayout = new VBox();
        vLayout.setAlignment(Pos.TOP_CENTER);
        vLayout.setSpacing(10);
        vLayout.setMaxWidth(250);
        vLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        layout = new BorderPane();
        layout.setLeft(vLayout);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        scene = new Scene(layout, 600, 600);

        Label sidebarLabel = new Label("NAVIGATION MENU");
        sidebarLabel.setPadding(new Insets(15, 0, 0, 15));

        manageRecords = new Button("Manage Customer Records");
        manageRequest = new Button("Manage Service Request");
        manageChangeReq = new Button("Manage Change Requests");
        logout = new Button("Logout");
        reports = new Button("Generate Reports");
        serviceList = new Button("Generate Service List");
        changeReq = new Button("Change Requests");
        homeButton = new Button("Home");

        homeButton.setOnAction(e -> LoadHomeDisplay());
        homeButton.setMaxSize(vLayout.getMaxWidth(), 15);

        manageRecords.setOnAction(e -> LoadRecordManagerDisplay());
        manageRecords.setMaxSize(vLayout.getMaxWidth(), 15);

        reports.setOnAction(e -> LoadReportGeneratorDisplay());
        reports.setMaxSize(vLayout.getMaxWidth(), 15);

        manageRequest.setOnAction(e -> LoadServiceManagerDisplay());
        manageRequest.setMaxSize(vLayout.getMaxWidth(), 15);

        manageChangeReq.setOnAction(e -> LoadChangeReqManagerDisplay());
        manageChangeReq.setMaxSize(vLayout.getMaxWidth(), 15);

        serviceList.setOnAction(e -> LoadServiceListDisplay());
        serviceList.setMaxSize(vLayout.getMaxWidth(), 15);

        changeReq.setOnAction(e -> LoadChangeRequestDisplay());
        changeReq.setMaxSize(vLayout.getMaxWidth(), 15);

        logout.setOnAction(e -> Logout());
        logout.setMaxSize(vLayout.getMaxWidth(), 15);

        vLayout.getChildren().add(sidebarLabel);
        vLayout.getChildren().add(homeButton);
        switch (currentUser.getUserType().toString()) {
            case ("CEO"):
                vLayout.getChildren().add(reports);
                vLayout.getChildren().add(manageRecords);
                vLayout.getChildren().add(manageRequest);
                vLayout.getChildren().add(manageChangeReq);
                vLayout.getChildren().add(serviceList);
                vLayout.getChildren().add(logout);
                break;
            case ("SECRETARY"):
                vLayout.getChildren().add(manageRecords);
                vLayout.getChildren().add(manageRequest);
                vLayout.getChildren().add(manageChangeReq);
                vLayout.getChildren().add(serviceList);
                vLayout.getChildren().add(logout);
                break;
            case ("CASHIER"):
                vLayout.getChildren().add(manageRequest);
                vLayout.getChildren().add(changeReq);
                vLayout.getChildren().add(logout);
                break;
        }

        window.setScene(scene);
    }
}
