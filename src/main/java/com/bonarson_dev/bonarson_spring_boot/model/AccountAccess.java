package com.bonarson_dev.bonarson_spring_boot.model;

public class AccountAccess {
    private int idAccount;
    private String dateRegistration;

    public AccountAccess(int idAccount, String dateRegistration) {
        this.idAccount = idAccount;
        this.dateRegistration = dateRegistration;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }
}
