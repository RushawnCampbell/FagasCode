/*import UIElements.UIManager;
import javafx.application.Application;
import javafx.stage.Stage;

/*
App class contains main method that starts the application.
extends Appliction to use JavaFX classes.
public class App extends Application {
    // UI Element variables
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        UIManager uiManager = new UIManager(window);
        uiManager.LoadLoginDisplay();

        // Set up database
    }

}*/
import UIElements.UIManager;
import java.sql.*;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.stage.Stage;

/*
App class contains main method that starts the application.
extends Appliction to use JavaFX classes.
*/
public class App extends Application {

    // UI Element variables
    private Stage window;

    // private Connection con;

    public static void main(String[] args) {
        // launch(args);
        String dbUrl = "jdbc:mysql://localhost:3306/fagas?useSSL=true&serverTimezone=UTC";
        // String sql = "INSERT INTO login (type, username, password)
        // VALUES('Secretary','sectest', 'secpass')";
        // String sql2 = "INSERT INTO login (type, username, password)
        // VALUES('Cashier','cashtest', 'cashpass')";

        try (
                final Connection con = DriverManager.getConnection(dbUrl, "root", "");
                Statement stmt = con.createStatement();) {
            System.out.println("Connected");
            // ResultSet res = stmt.executeQuery(sql2);
            // stmt.executeUpdate(sql);
            // stmt.executeUpdate(sql2);
            // res.next();
            // String username = res.getString("username");
            // System.out.println(username);
        } catch (SQLException e) { // Handle any errors that may have occurred.
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        UIManager uiManager = new UIManager(window);
        uiManager.LoadLoginDisplay();
        // Set up database
    }
}



