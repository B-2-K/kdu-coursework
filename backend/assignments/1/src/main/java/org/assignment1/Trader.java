package org.assignment1;

public class Trader {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String walletAddress;

    public Trader(String firstName, String lastName, String phoneNumber, String walletAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.walletAddress = walletAddress;
    }

    // Get First Name
    public String getFirstName() {
        return this.firstName;
    }

    //Get Last Name
    public String getLastName() {
        return this.lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getWalletAddress() {
        return this.walletAddress;
    }
}
