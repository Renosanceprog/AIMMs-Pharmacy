package model.inventory;

import java.time.LocalDate;

public class Batch {

    private final String batchID;
    private final String itemID;
    private final int quantity;
    private final LocalDate expirationDate;
    private final LocalDate dateReceived;
    private final BatchStatus status;

    public Batch(
            String batchID,
            String itemID,
            int quantity,
            LocalDate expirationDate,
            LocalDate dateReceived,
            BatchStatus status) {
        this.batchID = batchID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.dateReceived = dateReceived;
        this.status = status;
    }

    public String getBatchID() {
        return batchID;
    }

    public String getItemID() {
        return itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public LocalDate getDateReceived() {
        return dateReceived;
    }

    public BatchStatus getStatus() {
        return status;
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    public boolean isAvailable() {
        return status == BatchStatus.ACTIVE
                && quantity > 0
                && !isExpired();
    }
}