package com.bonarson_dev.bonarson_spring_boot.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Crud {
    public String readTimeNow(Connection conn, String now) {
        Statement statement;
        ResultSet rs = null;
        String h = " ";
        try {
            String query = String.format(" select(%s)", now);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                h = rs.getString("now");
            }


        } catch (Exception e) {
            System.out.println(e);
        }
        return h;

    }

    //Model of numberAccount a bank X

    public String numberAccountModelX(int idAccount) {
        String numberModelX = "00006000040" + idAccount + "01" + idAccount*2;
        return numberModelX;
    }
    public int readIdAccountEnd(Connection conn) {
        Statement statement;
        ResultSet rs = null;
        int id = 0;
        try {
            String query = String.format(" select id_account from account order by id_account desc limit 1; ");
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id_account");
            }

        } catch (Exception e) {
            System.out.println(e);
            e.getMessage();
        }
        return id;
    }

    public String readTimeById(Connection conn, int idaccount) {
        Statement statement;
        ResultSet rs = null;
        String h = " ";
        try {
            String query = String.format(" select date_heure from account where  id_account='%s' ", idaccount);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                // System.out.println(rs.getFloat("solde"));
                h = rs.getString("date_heure");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return h;
    }

    public String readTimeByPassword(Connection conn, String password) {
        Statement statement;
        ResultSet rs = null;
        String h = " ";
        try {
            String query = String.format(" select date_heure from account where  password='%s' ", password);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                // System.out.println(rs.getFloat("solde"));
                h = rs.getString("date_heure");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return h;
    }

    public String readAccountNumber(Connection conn, int idAccount) {
        Statement statement;
        ResultSet rs = null;
        String accountNumber = " ";
        try {
            String query = String.format(" select account_number from account where id_account='%s' ", idAccount);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                accountNumber = rs.getString("account_number");
            }

        } catch (Exception e) {
            System.out.println(e);
            e.getMessage();
        }
        return accountNumber;
    }

    //pour obtenir le solde account par id

    public float readBalanceById(Connection conn, int idaccount) {
        Statement statement;
        ResultSet rs = null;
        float q = 0;
        try {
            String query = String.format(" select balance from account where  id_account='%s' ", idaccount);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                q = rs.getFloat("balance");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return q;
    }

    public float balanceLoans(Connection conn, int idAccount){
        Statement statement;
        ResultSet rs = null;
        float q = 0;
        try {
            String query = String.format(" select balance_loans from loans where id_account ='%s'; ", idAccount);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                q = rs.getFloat("balance_loans");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return q;

    }

    public float balanceRepayment(Connection conn, int idAccount){
        Statement statement;
        ResultSet rs = null;
        float q = 0;
        try {
            String query = String.format(" select balance_repayment from loans where id_account ='%s'; ", idAccount);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                q = rs.getFloat("balance_repayment");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return q;

    }
    public float percentage(Connection conn,int idAccount){
        Statement statement;
        ResultSet rs = null;
        float q = 0;
        try {
            String query = String.format(" select percentage from loans where id_account ='%s'; ", idAccount);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                q = rs.getFloat("percentage");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return q;
    }
    public  String paymentMethode(Connection conn,int idAccount){
        Statement statement;
        ResultSet rs = null;
        String q = " ";
        try {
            String query = String.format(" select payment_methode from loans where id_account ='%s'; ", idAccount);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                q = rs.getString("payment_methode");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return q;
    }
    public String dateRegistrationLoans(Connection conn,int idAccount){
        Statement statement;
        ResultSet rs = null;
        String q = " ";
        try {
            String query = String.format(" select  date_registration from loans where id_account ='%s'; ", idAccount);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                q = rs.getString("date_registration");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return q;
    }
    public float differenceBetweenBalanceLoansAndBalanceRepayment(Connection conn,int  idAccount){
        Statement statement;
        ResultSet rs = null;
        float q = 0;
        try {
            String query = String.format(" select balance_loans - balance_repayment from loans where id_account ='%s'; ", idAccount);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                q = rs.getFloat("?column?");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return q;

    }

    public float readBalanceByPassword(Connection conn, String password) {
        Statement statement;
        ResultSet rs = null;
        float q = 0;
        try {
            String query = String.format(" select balance from account where  password='%s' ", password);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                q = rs.getFloat("balance");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return q;
    }

    public int readIdAccount(Connection conn, String accountNumber) {
        Statement statement;
        ResultSet rs = null;
        int id = 0;
        try {
            String query = String.format(" select id_account from account where  account_number='%s' ", accountNumber);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id_account");
            }

        } catch (Exception e) {
            System.out.println(e);
            e.getMessage();
        }
        return id;
    }


    public int idTransaction(Connection conn) {
        Statement statement;
        ResultSet rs = null;
        int id_transaction = 0;
        try {
            String query = String.format("select  id_transaction from transaction order by  id_transaction  desc  limit 1;");
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id_transaction = rs.getInt("id_transaction");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return id_transaction;
    }

    public int idTransfer(Connection conn) {
        Statement statement;
        ResultSet rs = null;
        int id_transfer = 0;
        try {
            String query = String.format(" select id_transfer from transfer_history order by id_transfer  desc  limit 1;");
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id_transfer = rs.getInt("id_transfer");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return id_transfer;
    }

    public List<Integer> listOfIdAccountAccess(Connection conn) {
        Statement statement;
        ResultSet rs = null;
        List<Integer> id = new ArrayList<>();
        try {
            String query = "SELECT id_account FROM account_access";
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id.add(rs.getInt("id_account"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public List<Integer> listOfIdAccountInLoans(Connection conn) {
        Statement statement;
        ResultSet rs = null;
        List<Integer> id = new ArrayList<>();
        try {
            String query = "SELECT id_account FROM loans";
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id.add(rs.getInt("id_account"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }




}
