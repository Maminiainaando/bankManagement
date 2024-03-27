package com.bonarson_dev.bonarson_spring_boot.model;

public class Loans {
    private String dateRegistration;
    private float balanceLoans;
    private float balanceRepayment;
    private float percentage;
    private String paymentMethode;
    private int idAccount;

    public Loans(String dateRegistration, float balanceLoans, float balanceRepayment, float percentage, String paymentMethode, int idAccount) {
        this.dateRegistration = dateRegistration;
        this.balanceLoans = balanceLoans;
        this.balanceRepayment = balanceRepayment;
        this.percentage = percentage;
        this.paymentMethode = paymentMethode;
        this.idAccount = idAccount;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public float getBalanceLoans() {
        return balanceLoans;
    }

    public void setBalanceLoans(float balanceLoans) {
        this.balanceLoans = balanceLoans;
    }

    public float getBalanceRepayment() {
        return balanceRepayment;
    }

    public void setBalanceRepayment(float balanceRepayment) {
        this.balanceRepayment = balanceRepayment;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getPaymentMethode() {
        return paymentMethode;
    }

    public void setPaymentMethode(String paymentMethode) {
        this.paymentMethode = paymentMethode;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }
}
