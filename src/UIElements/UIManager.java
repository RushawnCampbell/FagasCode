package UIElements;

import Security.Authenticator;
import Security.User;
import Security.User.UserType;
import UIElements.UIManager;
import javafx.stage.Stage;

public class UIManager {
    // Attributes
    private Stage window;
    private LoginDisplay loginDisplay;
    private HomeDisplay homeDisplay;
    private User currentUser;
    private Authenticator auth;

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
    public void LoadHomeDisplay(UserType userT) {
        homeDisplay = new HomeDisplay(window);
        homeDisplay.LoadDisplay(userT);
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
            LoadHomeDisplay(result);
        }
    }
}
