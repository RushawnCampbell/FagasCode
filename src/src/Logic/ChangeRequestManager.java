package Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import PopUpDisplays.StatusMessage;

public class ChangeRequestManager {

    private Connection con;
    private String dbUrl = "jdbc:mysql://localhost:3306/fagas?useSSL=true&serverTimezone=UTC";
    public static ArrayList<ChangeRequest> changeReqList;

    public ChangeRequestManager() {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, "root", "");
            setConnection(conn);
        } catch (SQLException e) {
            StatusMessage.DisplayMessage("Unable to connect to database");
        }
        GenerateChangeReqList();
    }

    private void setConnection(Connection conn) {
        this.con = conn;
    }

    public void GenerateChangeReqList() {
        changeReqList = new ArrayList<ChangeRequest>();
        try {
            String sql = "SELECT * FROM changerequests";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                CustomerRecord newRecord = new CustomerRecord(res.getInt("custid"), res.getString("firstname"),
                        res.getString("lastname"), res.getString("phone"), res.getString("address1"),
                        res.getString("address2"), res.getString("parish"), res.getString("email"));

                CustomerRecord oldRecord = RecordManager.getRecord(res.getInt("custid"));

                ChangeRequest temp = new ChangeRequest(newRecord, oldRecord, res.getInt("id"));
                changeReqList.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int CreateChangeRequest(ArrayList<String> info) {
        int res = -2;

        try {
            Statement stmt = con.createStatement();

            String query = "INSERT INTO changerequests (custid,firstname,lastname,email,phone,address1,address2,parish) VALUES('";
            query += info.get(0) + "', '";
            query += info.get(1) + "', '";
            query += info.get(2) + "', '";
            query += info.get(3) + "', '";
            query += info.get(4) + "', '";
            query += info.get(5) + "', '";
            query += info.get(6) + "', '";
            query += info.get(7) + "')";
            res = stmt.executeUpdate(query);
            if (res == 1)
                GenerateChangeReqList();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int AcceptChangeRequest(ArrayList<String> info) {
        int res = -2;
        try {
            String sql = "UPDATE custrecords SET firstname='" + info.get(2) + "', lastname='" + info.get(3);
            sql += "', email='" + info.get(4) + "', phone='" + info.get(5) + "', address1='" + info.get(6);
            sql += "', address2='" + info.get(7) + "', parish='" + info.get(8) + "' WHERE id = "
                    + String.valueOf(info.get(1));

            Statement stmt = con.createStatement();
            res = stmt.executeUpdate(sql);

            if (res == 1) {
                sql = "DELETE FROM changerequests WHERE id=" + info.get(0);
                res = stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            StatusMessage.DisplayMessage("Change request was not accepted successfully");
        }
        GenerateChangeReqList();
        return res;
    }

}
