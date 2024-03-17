package com.bonarson_dev.bonarson_spring_boot.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

    public String readTimeById(Connection conn,  int idaccount) {
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

    //pour obtenir le solde account par id

    public float readBalanceById(Connection conn,  int idaccount) {
        Statement statement;
        ResultSet rs = null;
        float q = 0;
        try {
            String query = String.format(" select balance from account where  id_account='%s' ",  idaccount);
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
}
