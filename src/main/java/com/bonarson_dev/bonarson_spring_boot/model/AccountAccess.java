package com.bonarson_dev.bonarson_spring_boot.model;

public class AccountAccess {
    private int idAccount;

    public AccountAccess(int idAccount) {
        this.idAccount = idAccount;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }
}
