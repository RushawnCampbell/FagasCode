package Logic;

public class CustomerRecord {
    private String firstname, lastname, phone, address1, address2, parish, email;
    public int customerID;

    public CustomerRecord(int id, String firstname, String lastname, String phone, String address1, String address2,
            String parish, String email) {
        this.customerID = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.parish = parish;
        this.email = email;
    }

    public int getId() {
        return this.customerID;
    }

    public String getFirst() {
        return this.firstname;
    }

    public String getLast() {
        return this.lastname;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getAd1() {
        return this.address1;
    }

    public String getAd2() {
        return this.address2;
    }

    public String getParish() {
        return this.parish;
    }

    public String getEmail() {
        return this.email;
    }
}
