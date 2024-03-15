package com.bonarson_dev.bonarson_spring_boot.model;

public class TransferHistory {
    private int idAccountSender;
    private int idAccountReceive;
    private String dateRegistration;

    public TransferHistory(int idAccountSender, int idAccountReceive, String dateRegistration) {
        this.idAccountSender = idAccountSender;
        this.idAccountReceive = idAccountReceive;
        this.dateRegistration = dateRegistration;
    }

    public int getIdAccountSender() {
        return idAccountSender;
    }

    public void setIdAccountSender(int idAccountSender) {
        this.idAccountSender = idAccountSender;
    }

    public int getIdAccountReceive() {
        return idAccountReceive;
    }

    public void setIdAccountReceive(int idAccountReceive) {
        this.idAccountReceive = idAccountReceive;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @Override
    public String toString() {
        return "TransferHistory{" +
                "idAccountSender=" + idAccountSender +
                ", idAccountReceive=" + idAccountReceive +
                ", dateRegistration='" + dateRegistration + '\'' +
                '}';
    }
}
