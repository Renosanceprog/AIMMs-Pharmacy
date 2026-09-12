package model.inventory;

import java.math.BigDecimal;

public class Item {

    private final String itemID;
    private final String itemName;
    private final String category;
    private final BigDecimal price;
    private final boolean prescriptionRequired;
    private final ItemStatus status;

    public Item(
            String itemID,
            String itemName,
            String category,
            BigDecimal price,
            boolean prescriptionRequired,
            ItemStatus status) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.prescriptionRequired = prescriptionRequired;
        this.status = status;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isPrescriptionRequired() {
        return prescriptionRequired;
    }

    public ItemStatus getStatus() {
        return status;
    }
}