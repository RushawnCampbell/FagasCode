package Logic;

import java.util.ArrayList;
import java.sql.*;
import java.sql.ResultSet;

public class RecordManager {
    private Connection con;
    private String dbUrl = "jdbc:mysql://localhost:3306/fagas?useSSL=true&serverTimezone=UTC";
    private String firstname, lastname, phone, address1, address2, parish, email;
    private int id;
    private int res = -2;
    private ResultSet result;

    public static ArrayList<CustomerRecord> recordList;

    public RecordManager() {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, "root", "");
            setConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // getRecord();

        recordList = new ArrayList<CustomerRecord>();
        GenerateRecordList();
    }

    private void setConnection(Connection conn) {
        this.con = conn;
    }

    private void GenerateRecordList() {
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

            // CustomerRecord temp = new CustomerRecord(res.getInt(0), res.getString(1),
            // res.getString(2),
            // res.getString(3), res.getString(4), res.getString(5), res.getString(6),
            // res.getString(7));
            // recordList.add(temp);
            // res.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int CreateRecord(ArrayList<String> list) {
        // Setting up Connection with database try {

        try {
            this.con = DriverManager.getConnection(dbUrl, "root", "");
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
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public void getRecord() {

        try {
            Statement stmt = con.createStatement();

            String query = "SELECT * FROM custrecords";
            result = stmt.executeQuery(query);

            while (result.next()) {
                this.id = result.getInt("id");
                this.firstname = result.getString("firstname");
                this.lastname = result.getString("lastname");
                this.phone = result.getString("phone");
                this.address1 = result.getString("address1");
                this.address2 = result.getString("address2");
                this.parish = result.getString("parish");
                this.email = result.getString("email");
                CustomerRecord custrecObj = new CustomerRecord(id, firstname, lastname, phone, address1, address2,
                        parish, email);
                recordList.add(custrecObj);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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