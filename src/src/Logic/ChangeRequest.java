package Logic;

public class ChangeRequest {

    private CustomerRecord oldRecord, newRecord;
    private int id;

    public ChangeRequest(CustomerRecord newRecord, CustomerRecord oldRecord, int id) {
        this.oldRecord = oldRecord;
        this.newRecord = newRecord;
        this.id = id;
    }

    public CustomerRecord getOldRecord() {
        return oldRecord;
    }

    public CustomerRecord getNewRecord() {
        return newRecord;
    }

    public int getId() {
        return id;
    }
}