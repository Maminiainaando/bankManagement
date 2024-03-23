package com.bonarson_dev.bonarson_spring_boot.model;

public class Account {
    private String Name;
    private String lastname;
    private String birthDate;
    private float balance;
    private String date_heure;

    private String accountNumber;
    private String bankType;
    private String password;

    public Account(String name, String lastname, String birthDate, float balance, String date_heure, String accountNumber, String bankType, String password) {
        Name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.balance = balance;
        this.date_heure = date_heure;
        this.accountNumber = accountNumber;
        this.bankType = bankType;
        this.password = password;
    }

    public void setDate_heure(String date_heure) {
        this.date_heure = date_heure;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate_heure() {
        return date_heure;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
                ", firstName='" + lastname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                ", bankType='" + bankType + '\'' +
                '}';
    }
}
