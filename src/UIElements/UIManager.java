package UIElements;

import Security.Authenticator;
import Security.User;
import Security.User.UserType;
import UIElements.UIManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIManager {
    // Attributes
    private Stage window;
    private LoginDisplay loginDisplay;
    private HomeDisplay homeDisplay;
    private RecordManagerDisplay recordManagerDisplay;
    private User currentUser;
    private Authenticator auth;

    private Scene scene;
    private BorderPane layout;
    private VBox vLayout;
    private Button manageRecords, manageReq, manageChangeReq, reports, serviceList, changeReq, logout;

    public UIManager(Stage primaryStage) {
        window = primaryStage;
        auth = new Authenticator();

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
        recordManagerDisplay = new RecordManagerDisplay(window, this);
        recordManagerDisplay.LoadDisplay(layout);
    }

    public void LoadServiceManagerDisplay() {

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

    private void ConfigureWindow() {
        layout = new BorderPane();
        scene = new Scene(layout, 900, 750);
        vLayout = new VBox();
        manageRecords = new Button("Manage Customer Records");
        manageReq = new Button("Manage Service Request");
        manageChangeReq = new Button("Manage Change Requests");
        logout = new Button("Logout");
        reports = new Button("Generate Reports");
        serviceList = new Button("Generate Service List");
        changeReq = new Button("Create Change Request");
        layout = new BorderPane();
        scene = new Scene(layout, 600, 600);
        Button homeButton = new Button("Home");

        layout.setLeft(vLayout);
        vLayout.setAlignment(Pos.CENTER);
        vLayout.getChildren().add(homeButton);
        switch (currentUser.getUserType().toString()) {
            case ("CEO"):
                vLayout.getChildren().add(reports);
                vLayout.getChildren().add(manageRecords);
                vLayout.getChildren().add(manageReq);
                vLayout.getChildren().add(manageChangeReq);
                vLayout.getChildren().add(serviceList);
                vLayout.getChildren().add(logout);
                break;
            case ("Secretary"):
                vLayout.getChildren().add(manageRecords);
                vLayout.getChildren().add(manageReq);
                vLayout.getChildren().add(manageChangeReq);
                vLayout.getChildren().add(serviceList);
                vLayout.getChildren().add(logout);
                break;
            case ("Cashier"):
                vLayout.getChildren().add(manageReq);
                vLayout.getChildren().add(changeReq);
                vLayout.getChildren().add(logout);
                System.out.println("CEO");
                break;
        }
        manageRecords.setOnAction(e -> LoadRecordManagerDisplay());
        homeButton.setOnAction(e -> LoadHomeDisplay());

        window.setScene(scene);
    }
}
