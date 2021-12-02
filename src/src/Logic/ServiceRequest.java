package Logic;

import java.sql.Timestamp;

public class ServiceRequest {
    private String type, comments, status;
    private float price;
    private int serviceId, customerId;
    private Timestamp date_created;
    private int quantity;

    public ServiceRequest(int serviceId, int customerId, String type, float price, int quantity, String comments,
            String status,
            Timestamp date_created) {
        this.type = type;
        this.comments = comments;
        this.serviceId = serviceId;
        this.customerId = customerId;
        this.price = price;
        this.status = status;
        this.date_created = date_created;
        this.quantity = quantity;
    }

    public String getType() {
        return this.type;
    }

    public String getComments() {
        return this.comments;
    }

    public int getServiceId() {
        return this.serviceId;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public String getStatus() {
        return this.status;
    }

    public float getPrice() {
        return this.price;
    }

    public Timestamp getDateCreated() {
        return this.date_created;
    }

    public int getQuantity() {
        return this.quantity;
    }
}