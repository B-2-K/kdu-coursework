package org.example;

public class Transaction {
    private TransactionType type;
    private TransactionData data;

    // Set TransactionType
    public void setType(TransactionType type) {
        this.type = type;
    }

    // Get TransactionType
    public TransactionType getType() {
        return type;
    }

    // Set TransactionData
    public void setData(TransactionData data) {
        this.data = data;
    }
}
