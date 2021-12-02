package Security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import PopUpDisplays.StatusMessage;
import Security.User.UserType;

public class Authenticator {
    private String dbUrl = "jdbc:mysql://localhost:3306/fagas?useSSL=true&serverTimezone=UTC";

    public UserType Verify(String username, String password) {
        UserType result = UserType.NONE;

        try {
            Connection conn = DriverManager.getConnection(dbUrl, "root", "");

            String sql = "SELECT * FROM login";
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                if (username.equals(res.getString("username")) && password.equals(res.getString("password"))) {
                    if (res.getString("type").equals("CEO")) {
                        result = UserType.CEO;
                    } else if (res.getString("type").equals("Cashier")) {
                        result = UserType.CASHIER;
                    } else if (res.getString("type").equals("Secretary")) {
                        result = UserType.SECRETARY;
                    }
                }
            }
        } catch (SQLException e) {
            StatusMessage.DisplayMessage("Unable to connect to database");
        }

        return result;
    }

}
