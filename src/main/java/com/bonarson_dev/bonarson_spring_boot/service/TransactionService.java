package com.bonarson_dev.bonarson_spring_boot.service;

import com.bonarson_dev.bonarson_spring_boot.dao.DbConnection;
import com.bonarson_dev.bonarson_spring_boot.model.Transaction;
import com.bonarson_dev.bonarson_spring_boot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService implements TransactionRepository {
    private final DataSource dataSource;

    @Autowired
    public TransactionService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addTransaction(int id, String transaction_type, int id_account, Transaction transaction) {
        Statement statement;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        Crud crud = new Crud();
        String dateTimeNow = crud.readTimeNow(conn, "now()");
        Float balanceNow = crud.readBalanceById(conn, id_account);
        String timeById = crud.readTimeById(conn, id_account);
        if (transaction_type.equals("credit")) {
            try {

                String query = String.format("insert into transaction(label,transaction_amount,bank_type,date_effect,date_registration, transaction_type, id_account,id_transfer,status) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');", transaction.getLabel(), transaction.getTransactionAmount(), transaction.getBankType(), dateTimeNow, dateTimeNow, transaction_type, id_account, transaction.getIdTransfer(), "complete");
                statement = conn.createStatement();
                statement.executeUpdate(query);
                System.out.println(" Transaction saved successfully ✔ ");


                String query1 = String.format("update account set balance='%s' where balance='%s' and  id_account='%s'", (balanceNow + transaction.getTransactionAmount()), balanceNow, id_account);
                statement = conn.createStatement();
                statement.executeUpdate(query1);
                System.out.println(transaction.getTransactionAmount());
                System.out.println("update account balance ok  ✔ ");


                String query3 = String.format("update account  set  date_heure='%s' where    date_heure='%s'", dateTimeNow, timeById);
                statement = conn.createStatement();
                statement.executeUpdate(query3);
                System.out.println("update account date_heure ok  ✔ ");

                String query2 = String.format("insert into transaction_history(last_balance,date_registration,id_account) values('%s','%s','%s');", balanceNow, timeById, id_account);
                statement = conn.createStatement();
                statement.executeUpdate(query2);
                System.out.println("balance history ok  ✔ ");


            } catch (Exception e) {
                System.out.println(e);
                e.getMessage();
            }
        } else if (transaction_type.equals("debit")) {
            try {

                String query = String.format("insert into transaction(label,transaction_amount,bank_type,date_effect,date_registration, transaction_type, id_account,id_transfer,status) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');", transaction.getLabel(), transaction.getTransactionAmount(), transaction.getBankType(), dateTimeNow, dateTimeNow, transaction_type, id_account, transaction.getIdTransfer(), "complete");
                statement = conn.createStatement();
                statement.executeUpdate(query);
                System.out.println(" Transaction saved successfully ✔ ");


                String query1 = String.format("update account set balance='%s' where balance='%s' and  id_account='%s'", (balanceNow - transaction.getTransactionAmount()), balanceNow, id_account);
                statement = conn.createStatement();
                statement.executeUpdate(query1);
                System.out.println("update account balance ok  ✔ ");


                String query3 = String.format("update account  set  date_heure='%s' where    date_heure='%s'", dateTimeNow, timeById);
                statement = conn.createStatement();
                statement.executeUpdate(query3);
                System.out.println("update account date_heure ok  ✔ ");

                String query2 = String.format("insert into transaction_history(last_balance,date_registration,id_account) values('%s','%s','%s');", balanceNow, timeById, id_account);
                statement = conn.createStatement();
                statement.executeUpdate(query2);
                System.out.println("balance history ok  ✔ ");

            } catch (Exception e) {
                System.out.println(e);
                e.getMessage();
            }

        }else {
            System.out.println("error transaction , use  debit or credit type ");
        }
    }

    @Override
    public List<Transaction> getAllTransaction() {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from transaction ")) {
            while (resultSet.next()) {
                transactions.add(new Transaction(resultSet.getInt("id_transaction"), resultSet.getString("label"), resultSet.getFloat("transaction_amount"), resultSet.getString("bank_type"), resultSet.getString("date_effect"), resultSet.getString("date_registration"), resultSet.getString("transaction_type"), resultSet.getInt("id_account"), resultSet.getInt("id_transfer"),resultSet.getString("status")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
