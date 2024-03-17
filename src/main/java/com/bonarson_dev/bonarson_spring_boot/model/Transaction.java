package com.bonarson_dev.bonarson_spring_boot.model;

public class Transaction {
    private int  id_transaction;
    private String label;
    private float transactionAmount;
    private String bankType;
    private String dateEffect;
    private String dateRegistration;
    private String transactionType;
    private int idAccount;
    private int idTransfer;
    private String status;


    public Transaction(int id_transaction, String label, float transactionAmount, String bankType, String dateEffect, String dateRegistration, String transactionType, int idAccount, int idTransfer, String status) {
        this.id_transaction = id_transaction;
        this.label = label;
        this.transactionAmount = transactionAmount;
        this.bankType = bankType;
        this.dateEffect = dateEffect;
        this.dateRegistration = dateRegistration;
        this.transactionType = transactionType;
        this.idAccount = idAccount;
        this.idTransfer = idTransfer;
        this.status = status;
    }

    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getDateEffect() {
        return dateEffect;
    }

    public void setDateEffect(String dateEffect) {
        this.dateEffect = dateEffect;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public int getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(int idTransfer) {
        this.idTransfer = idTransfer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "label='" + label + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", bankType='" + bankType + '\'' +
                ", dateEffect='" + dateEffect + '\'' +
                ", dateRegistration='" + dateRegistration + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", idAccount=" + idAccount +
                ", idTransfer=" + idTransfer +
                ", status='" + status + '\'' +
                '}';
    }
}
