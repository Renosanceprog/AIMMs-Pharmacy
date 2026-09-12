package model.prescription;

public class PrescriptionItem {

    private final String prescriptionItemID;
    private final String prescriptionID;
    private final String itemID;
    private final int quantity;

    public PrescriptionItem(
            String prescriptionItemID,
            String prescriptionID,
            String itemID,
            int quantity) {
        this.prescriptionItemID = prescriptionItemID;
        this.prescriptionID = prescriptionID;
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public String getPrescriptionItemID() {
        return prescriptionItemID;
    }

    public String getPrescriptionID() {
        return prescriptionID;
    }

    public String getItemID() {
        return itemID;
    }

    public int getQuantity() {
        return quantity;
    }
}