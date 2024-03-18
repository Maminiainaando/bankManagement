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
    public void addPerson(int id, Account account) {
        Statement statement;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        Crud crud = new Crud();
       String dateTimeNow = crud.readTimeNow(conn,"now()");
        try {
            String query = String.format("insert into account(name, lastname, birthdate, balance, date_heure, account_number, bank_type) values ('%s', '%s', '%s', %s, '%s', '%s', '%s');", account.getName(), account.getLastname(), account.getBirthDate(), 0, dateTimeNow, account.getAccountNumber(), account.getBankType());
            statement = conn.createStatement();
            statement.executeUpdate(query);

            System.out.println("Account save ✔");

        }catch (Exception e){
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
                accounts.add(new Account(resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getString("birthdate"),resultSet.getFloat("balance"),resultSet.getString("date_heure"),resultSet.getString("account_number"),resultSet.getString("bank_type")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Account> getBalanceNow(int id) {
        List<Account> account = new ArrayList<>();
        Statement statement;
        ResultSet rs = null;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        Crud crud = new Crud();
        String timeNow = crud.readTimeNow(conn,"now()");
        float balance = crud.readBalanceById(conn,id);
        String timeLast = crud.readTimeById(conn, id);
        String hTimeLast = timeLast;

        try {


            String query3 = String.format("update account  set  date_heure='%s' where    date_heure='%s'", timeNow, timeLast);
            statement = conn.createStatement();
            statement.executeUpdate(query3);
            System.out.println("update account date_time ok  ✔ ");


            String query = String.format(" select * from account where id_account='%s'  ", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){

                account.add(new Account(rs.getString("name"),rs.getString("lastname"),rs.getString("birthdate"),rs.getFloat("balance"),rs.getString("date_heure"),rs.getString("account_number"),rs.getString("bank_type")));
                System.out.println("select account ok ✔ ");
            }


            String query1 = String.format("update account  set  date_heure='%s' where    date_heure='%s'",hTimeLast, timeNow);
            statement = conn.createStatement();
            statement.executeUpdate(query1);
            System.out.println("update account date_time ok  ✔ ");


        }catch (Exception e){
            System.out.println(e);
        }

        return account;
    }


}

