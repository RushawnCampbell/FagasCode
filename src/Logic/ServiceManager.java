package Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import PopUpDisplays.StatusMessage;

public class ServiceManager {
    private Connection con;
    private String dbUrl = "jdbc:mysql://localhost:3306/fagas?useSSL=true&serverTimezone=UTC";

    public static ArrayList<ServiceRequest> serviceReqList;

    public ServiceManager() {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, "root", "");
            setConnection(conn);
        } catch (SQLException e) {
            StatusMessage.DisplayMessage("Unable to connect to database");
        }

        GenerateServiceReqList();
    }

    private void setConnection(Connection conn) {
        this.con = conn;
    }

    private void GenerateServiceReqList() {
        serviceReqList = new ArrayList<ServiceRequest>();
        try {
            String sql = "SELECT * FROM servicerequests";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                ServiceRequest temp = new ServiceRequest(res.getInt("id"), res.getInt("custid"), res.getString("type"),
                        res.getFloat("price"), res.getInt("quantity"), res.getString("comments"),
                        res.getString("status"),
                        res.getTimestamp("date_created"));
                serviceReqList.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int CreateService(ArrayList<String> list) {
        int res = -2;

        try {
            /*
             * index 0 = Service Type
             * index 1 = comments
             * index 2 = price
             * index 3 = quantity
             * index 4 = customer id
             * Remember to insert "incomplete" as default for status
             */
            Statement stmt = con.createStatement();

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String query = "INSERT INTO servicerequests (custid, type, price, quantity, comments, status, date_created) VALUES('";
            query += list.get(4) + "', '";
            query += list.get(0) + "', '";
            query += list.get(2) + "', '";
            query += list.get(3) + "', '";
            query += list.get(1) + "', '";
            query += "Incomplete', '";
            query += timestamp.toString() + "')";

            res = stmt.executeUpdate(query);
            System.out.println(query);
            if (res == 1)
                GenerateServiceReqList();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public int ModifyRecord(ArrayList<String> service) {
        String sql = "UPDATE servicerequests SET type='" + service.get(1) + "', price='" + service.get(2);
        sql += "', quantity='" + service.get(3) + "', comments='" + service.get(4) + "', status='" + service.get(5);
        sql += "' WHERE id = '" + service.get(0) + "'";

        int res = -2;
        try {
            Statement stmt = con.createStatement();
            res = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        GenerateServiceReqList();
        return res;
    }

    public void DeleteService(int serviceId) {
    }
}
