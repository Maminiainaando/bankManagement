package com.bonarson_dev.bonarson_spring_boot.service;

import com.bonarson_dev.bonarson_spring_boot.dao.DbConnection;
import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service

public class AccountService implements AccountRepository {
    private final DataSource dataSource;

    public AccountService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addAccount(int id, Account account) {
        Statement statement;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        Crud crud = new Crud();
        String dateTimeNow = crud.readTimeNow(conn, "now()");
        try {
            String query = String.format("insert into account(name, lastname, birthdate, balance, date_heure, account_number, bank_type,password) values ('%s', '%s', '%s', %s, '%s', '%s', '%s','%s');", account.getName(), account.getLastname(), account.getBirthDate(), 0, dateTimeNow, account.getAccountNumber(), account.getBankType(),account.getPassword());
            statement = conn.createStatement();
            statement.executeUpdate(query);

            System.out.println("Account save ✔");

        } catch (Exception e) {
            System.out.println(e);
            e.getMessage();
        }
    }

    @Override
    public List<Account> getAllAccount() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from account ")) {
            while (resultSet.next()) {
                accounts.add(new Account(resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getString("birthdate"), resultSet.getFloat("balance"), resultSet.getString("date_heure"), resultSet.getString("account_number"), resultSet.getString("bank_type"),resultSet.getString("password")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Account> getBalanceNow(String password) {
        List<Account> account = new ArrayList<>();
        Statement statement;
        ResultSet rs = null;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        Crud crud = new Crud();
        String timeNow = crud.readTimeNow(conn, "now()");
        float balance = crud.readBalanceByPassword(conn, password);
        String timeLast = crud.readTimeByPassword(conn, password);
        String hTimeLast = timeLast;

        try {


            String query3 = String.format("update account  set  date_heure='%s' where    date_heure='%s'", timeNow, timeLast);
            statement = conn.createStatement();
            statement.executeUpdate(query3);
            System.out.println("update account date_time ok  ✔ ");


            String query = String.format(" select * from account where password='%s'  ",password);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {

                account.add(new Account(rs.getString("name"), rs.getString("lastname"), rs.getString("birthdate"), rs.getFloat("balance"), rs.getString("date_heure"), rs.getString("account_number"), rs.getString("bank_type"),rs.getString("password")));
                System.out.println("select account ok ✔ ");
            }


            String query1 = String.format("update account  set  date_heure='%s' where    date_heure='%s'", hTimeLast, timeNow);
            statement = conn.createStatement();
            statement.executeUpdate(query1);
            System.out.println("update account date_time ok  ✔ ");


        } catch (Exception e) {
            System.out.println(e);
        }

        return account;
    }

    @Override
    public float readBalanceListByDateTime(String date_registration, int id_account) {
        Statement statement;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        ResultSet rs = null;
        float h = 0;

        try {
            String query = String.format(" select last_balance from transaction_history where  transaction_history.date_registration='%s' and id_account='%s' ", date_registration, id_account);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                // System.out.println(rs.getFloat("solde"));
                h = rs.getFloat("last_balance");


            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return h;

    }

    @Override
    public List<String> readBalanceByBetweenDateTime(int id_account, String date_registration_1, String date_registration_2) {
        List<String>balances = new ArrayList<>();
        Statement statement;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        ResultSet rs = null;
        float h = 0;
        try {
            String query = String.format(" select last_balance from transaction_history where id_account='%s' and transaction_history.date_registration between  '%s' and '%s' ",id_account, date_registration_1, date_registration_2);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                h = rs.getFloat("last_balance");
                balances.add(String.valueOf(h) + " Ar");

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return balances;
    }
}

