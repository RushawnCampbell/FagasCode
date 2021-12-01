package Logic;

import java.util.ArrayList;

import PopUpDisplays.StatusMessage;

import java.sql.*;
import java.sql.ResultSet;

public class RecordManager {
    private Connection con;
    private String dbUrl = "jdbc:mysql://localhost:3306/fagas?useSSL=true&serverTimezone=UTC";

    public static ArrayList<CustomerRecord> recordList;

    public RecordManager() {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, "root", "");
            setConnection(conn);
        } catch (SQLException e) {
            StatusMessage.DisplayMessage("Unable to connect to database");
        }

        GenerateRecordList();
    }

    private void setConnection(Connection conn) {
        this.con = conn;
    }

    private void GenerateRecordList() {
        recordList = new ArrayList<CustomerRecord>();
        try {
            String sql = "SELECT * FROM custrecords";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {

                CustomerRecord temp = new CustomerRecord(res.getInt(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5), res.getString(6),
                        res.getString(7), res.getString(8));
                recordList.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int ModifyRecord(ArrayList<String> record) {
        String sql = "UPDATE custrecords SET firstname='" + record.get(1) + "', lastname='" + record.get(2);
        sql += "', email='" + record.get(3) + "', phone='" + record.get(4) + "', address1='" + record.get(5);
        sql += "', address2='" + record.get(6) + "', parish='" + record.get(7) + "' WHERE id = "
                + String.valueOf(record.get(0));

        int res = -2;
        try {
            Statement stmt = con.createStatement();
            res = stmt.executeUpdate(sql);
            GenerateRecordList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public int CreateRecord(ArrayList<String> list) {
        int res = -2;

        try {
            Statement stmt = con.createStatement();

            String query = "INSERT INTO custrecords (firstname,lastname,phone,address1,address2,parish,email) VALUES('";
            query += list.get(0) + "', '";
            query += list.get(1) + "', '";
            query += list.get(3) + "', '";
            query += list.get(4) + "', '";
            query += list.get(5) + "', '";
            query += list.get(6) + "', '";
            query += list.get(2) + "')";
            res = stmt.executeUpdate(query);

            if (res == 1)
                GenerateRecordList();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }
}

/*
 ***** SQL Queries*****
 * 
 * CREATE TABLE servicerequests (
 * id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 * custid INT NOT NULL,
 * type varchar(255) NOT NULL,
 * comments TEXT
 * );
 * 
 *
 * CREATE TABLE changerequests (
 * id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 * custid INT NOT NULL,
 * firstname varchar(255),
 * lastname varchar(255),
 * phone varchar(30),
 * address1 varchar(255),
 * address2 varchar(255),
 * parish varchar(255),
 * email varchar(255)
 * );
 * 
 */