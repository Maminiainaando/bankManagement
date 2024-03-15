package com.bonarson_dev.bonarson_spring_boot.model;

public class TransactionHistory {
    private float lastBalance;
    private String dateRegistration;
    private int idAccount;

    public TransactionHistory(float lastBalance, String dateRegistration, int idAccount) {
        this.lastBalance = lastBalance;
        this.dateRegistration = dateRegistration;
        this.idAccount = idAccount;
    }

    public float getLastBalance() {
        return lastBalance;
    }

    public void setLastBalance(float lastBalance) {
        this.lastBalance = lastBalance;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "lastBalance=" + lastBalance +
                ", dateRegistration='" + dateRegistration + '\'' +
                ", idAccount=" + idAccount +
                '}';
    }
}
