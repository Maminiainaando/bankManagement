package com.bonarson_dev.bonarson_spring_boot.model;

public class LoginForm {
    private int id_account;
    private String account_number;

    public LoginForm(int id_account, String account_number) {
        this.id_account = id_account;
        this.account_number = account_number;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }
}
