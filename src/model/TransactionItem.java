package model;

import java.math.BigDecimal;

public class TransactionItem {

    private final String transactionItemID;
    private final String transactionID;
    private final String itemID;
    private final int quantity;
    private final BigDecimal unitPrice;
    private final BigDecimal subtotal;

    public TransactionItem(
            String transactionItemID,
            String transactionID,
            String itemID,
            int quantity,
            BigDecimal unitPrice,
            BigDecimal subtotal) {
        this.transactionItemID = transactionItemID;
        this.transactionID = transactionID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public String getTransactionItemID() {
        return transactionItemID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getItemID() {
        return itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}