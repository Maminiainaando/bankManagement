package com.bonarson_dev.bonarson_spring_boot.model;

public class Account {
    private String Name;
    private String firstName;
    private String birthDate;
    private float balance;

    private String accountNumber;
    private String bankType;

    public Account(String name, String firstName, String birthDate, float balance, String accountNumber, String bankType) {
        Name = name;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.bankType = bankType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Name='" + Name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                ", bankType='" + bankType + '\'' +
                '}';
    }
}
