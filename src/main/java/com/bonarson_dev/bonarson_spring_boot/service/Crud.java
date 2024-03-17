package com.bonarson_dev.bonarson_spring_boot.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Crud {
    public String read_heure_now(Connection conn, String now) {
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
}
