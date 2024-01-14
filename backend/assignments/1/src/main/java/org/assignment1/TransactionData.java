package org.assignment1;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionData {
    private String coin;
    private long quantity;
    @JsonProperty("wallet_address")
    private String walletAddress;
    private double price;
    private long volume;

    // get coin
    public String getCoin() {
        return coin;
    }

    // get quantity
    public long getQuantity() {
        return quantity;
    }

    // get wallet address
    public String getWalletAddress() {
        return walletAddress;
    }


    // get price
    public double getPrice() {
        return price;
    }

    // get volume
    public long getVolume() {
        return volume;
    }
}
