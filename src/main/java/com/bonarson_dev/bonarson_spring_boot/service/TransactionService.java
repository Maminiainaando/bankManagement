package com.bonarson_dev.bonarson_spring_boot.service;

import com.bonarson_dev.bonarson_spring_boot.dao.DbConnection;
import com.bonarson_dev.bonarson_spring_boot.model.Transaction;
import com.bonarson_dev.bonarson_spring_boot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
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
                System.out.println("update account date_time ok  ✔ ");

                String query2 = String.format("insert into transaction_history(last_balance,date_registration,id_account) values('%s','%s','%s');", balanceNow, timeById, id_account);
                statement = conn.createStatement();
                statement.executeUpdate(query2);
                System.out.println("balance history ok  ✔ ");

            } catch (Exception e) {
                System.out.println(e);
                e.getMessage();
            }

        } else {
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
                transactions.add(new Transaction(resultSet.getInt("id_transaction"), resultSet.getString("label"), resultSet.getFloat("transaction_amount"), resultSet.getString("bank_type"), resultSet.getString("date_effect"), resultSet.getString("date_registration"), resultSet.getString("transaction_type"), resultSet.getInt("id_account"), resultSet.getInt("id_transfer"), resultSet.getString("status")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionById(int id_account) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM transaction WHERE id_account = ?")) {
            preparedStatement.setInt(1, id_account);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    transactions.add(new Transaction(
                            resultSet.getInt("id_transaction"),
                            resultSet.getString("label"),
                            resultSet.getFloat("transaction_amount"),
                            resultSet.getString("bank_type"),
                            resultSet.getString("date_effect"),
                            resultSet.getString("date_registration"),
                            resultSet.getString("transaction_type"),
                            resultSet.getInt("id_account"),
                            resultSet.getInt("id_transfer"),
                            resultSet.getString("status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void addTransfer(int id, Transaction transaction, int id_account_sender, int id_account_receive) {
        Statement statement;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        Crud crud = new Crud();
        String dateTimeNow = crud.readTimeNow(conn, "now()");
        float oldBalanceAccount_1 = crud.readBalanceById(conn, id_account_sender);
        float oldBalanceAccount_2 = crud.readBalanceById(conn, id_account_receive);
        String oldTimeAccount_1 = crud.readTimeById(conn, id_account_sender);
        String oldTimeAccount_2 = crud.readTimeById(conn, id_account_receive);


        if (id_account_sender != id_account_receive) {


            try {
                //insert transaction C1
                String query = String.format("insert into transaction(label,transaction_amount,bank_type,date_effect,date_registration, transaction_type, id_account,id_transfer,status) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');", transaction.getLabel(), transaction.getTransactionAmount(), transaction.getBankType(), dateTimeNow, dateTimeNow, "debit", id_account_sender, transaction.getIdTransfer(), "completed");
                statement = conn.createStatement();
                statement.executeUpdate(query);
                System.out.println("transaction ok ✔ ");

                //insert transaction C2
                String query1 = String.format("insert into transaction(label,transaction_amount,bank_type,date_effect,date_registration, transaction_type, id_account,id_transfer,status) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');", transaction.getLabel(), transaction.getTransactionAmount(), transaction.getBankType(), dateTimeNow, dateTimeNow, "credit", id_account_receive, transaction.getIdTransfer(), "completed");
                statement = conn.createStatement();
                statement.executeUpdate(query1);
                System.out.println("transaction ok  ✔");

                //mise a jour pour chaque solde du compte
                String query666 = String.format("update account set balance='%s' where balance='%s' and id_account='%s'", (oldBalanceAccount_1 - transaction.getTransactionAmount()), oldBalanceAccount_1, id_account_sender);
                statement = conn.createStatement();
                statement.executeUpdate(query666);
                System.out.println("SOLDE C1 a JOUR ok  ✔");

                String query6 = String.format("update account set balance='%s' where balance='%s' and id_account='%s'", (oldBalanceAccount_2 + transaction.getTransactionAmount()), oldBalanceAccount_2, id_account_receive);
                statement = conn.createStatement();
                statement.executeUpdate(query6);
                System.out.println("SOLDE C2 a JOUR ok  ✔ ");

                //MISE A JOUR HEURE DU SOLDE
                String query3 = String.format("update account set    date_heure='%s' where    date_heure='%s'", dateTimeNow, oldTimeAccount_1);
                statement = conn.createStatement();
                statement.executeUpdate(query3);
                System.out.println("Heure C1 a jour ok   ✔");

                String query33 = String.format("update account set    date_heure='%s' where    date_heure='%s'", dateTimeNow, oldTimeAccount_2);
                statement = conn.createStatement();
                statement.executeUpdate(query33);
                System.out.println("Heure  C2 a jour ok   ✔");

                //solde history
                String query4 = String.format("insert into transaction_history(last_balance,date_registration,id_account) values('%s','%s','%s');", oldBalanceAccount_1, oldTimeAccount_1, id_account_sender);
                statement = conn.createStatement();
                statement.executeUpdate(query4);
                System.out.println("solde_history ok  ✔");

                String query7 = String.format("insert into transaction_history(last_balance,date_registration,id_account) values('%s','%s','%s');", oldBalanceAccount_2, oldTimeAccount_2, id_account_receive);
                statement = conn.createStatement();
                statement.executeUpdate(query7);
                System.out.println("solde_history ok  ✔ ");
                //tranfer_history
                String query8 = String.format("insert into transfer_history(id_send,id_receive,date_registration) values('%s','%s','%s');", id_account_sender, id_account_receive, dateTimeNow);
                statement = conn.createStatement();
                statement.executeUpdate(query8);
                System.out.println("tranfer_history ok  ✔");
            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            System.out.println("transfer error  ");
        }
    }

    @Override
    public void saveTransfer(int id, Transaction transaction, int id_account_sender, String accountNumber) {
        Statement statement;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        Crud crud = new Crud();
        String dateTimeNow = crud.readTimeNow(conn, "now()");
        int id_account_receive = crud.readIdAccount(conn, accountNumber);
        float oldBalanceAccount_1 = crud.readBalanceById(conn, id_account_sender);
        float oldBalanceAccount_2 = crud.readBalanceById(conn, id_account_receive);
        String oldTimeAccount_1 = crud.readTimeById(conn, id_account_sender);
        String oldTimeAccount_2 = crud.readTimeById(conn, id_account_receive);


        if (id_account_sender != id_account_receive) {


            try {
                //insert transaction C1
                String query = String.format("insert into transaction(label,transaction_amount,bank_type,date_effect,date_registration, transaction_type, id_account,id_transfer,status) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');", transaction.getLabel(), transaction.getTransactionAmount(), transaction.getBankType(), dateTimeNow, dateTimeNow, "debit", id_account_sender, transaction.getIdTransfer(), "completed");
                statement = conn.createStatement();
                statement.executeUpdate(query);
                System.out.println("transaction ok ✔ ");

                //insert transaction C2
                String query1 = String.format("insert into transaction(label,transaction_amount,bank_type,date_effect,date_registration, transaction_type, id_account,id_transfer,status) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');", transaction.getLabel(), transaction.getTransactionAmount(), transaction.getBankType(), dateTimeNow, dateTimeNow, "credit", id_account_receive, transaction.getIdTransfer(), "completed");
                statement = conn.createStatement();
                statement.executeUpdate(query1);
                System.out.println("transaction ok  ✔");

                //mise a jour pour chaque solde du compte
                String query666 = String.format("update account set balance='%s' where balance='%s' and id_account='%s'", (oldBalanceAccount_1 - transaction.getTransactionAmount()), oldBalanceAccount_1, id_account_sender);
                statement = conn.createStatement();
                statement.executeUpdate(query666);
                System.out.println("SOLDE C1 a JOUR ok  ✔");

                String query6 = String.format("update account set balance='%s' where balance='%s' and id_account='%s'", (oldBalanceAccount_2 + transaction.getTransactionAmount()), oldBalanceAccount_2, id_account_receive);
                statement = conn.createStatement();
                statement.executeUpdate(query6);
                System.out.println("SOLDE C2 a JOUR ok  ✔ ");

                //MISE A JOUR HEURE DU SOLDE
                String query3 = String.format("update account set    date_heure='%s' where    date_heure='%s'", dateTimeNow, oldTimeAccount_1);
                statement = conn.createStatement();
                statement.executeUpdate(query3);
                System.out.println("Heure C1 a jour ok   ✔");

                String query33 = String.format("update account set    date_heure='%s' where    date_heure='%s'", dateTimeNow, oldTimeAccount_2);
                statement = conn.createStatement();
                statement.executeUpdate(query33);
                System.out.println("Heure  C2 a jour ok   ✔");

                //solde history
                String query4 = String.format("insert into transaction_history(last_balance,date_registration,id_account) values('%s','%s','%s');", oldBalanceAccount_1, oldTimeAccount_1, id_account_sender);
                statement = conn.createStatement();
                statement.executeUpdate(query4);
                System.out.println("solde_history ok  ✔");

                String query7 = String.format("insert into transaction_history(last_balance,date_registration,id_account) values('%s','%s','%s');", oldBalanceAccount_2, oldTimeAccount_2, id_account_receive);
                statement = conn.createStatement();
                statement.executeUpdate(query7);
                System.out.println("solde_history ok  ✔ ");
                //tranfer_history
                String query8 = String.format("insert into transfer_history(id_send,id_receive,date_registration) values('%s','%s','%s');", id_account_sender, id_account_receive, dateTimeNow);
                statement = conn.createStatement();
                statement.executeUpdate(query8);
                System.out.println("tranfer_history ok  ✔");
            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            System.out.println("transfer error  ");
        }

    }

    @Override
    public void saveTransferOtherAccount(int id, Transaction transaction, int id_account_sender, String accountNumber) {
        Statement statement;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        Crud crud = new Crud();
        String dateTimeNow = crud.readTimeNow(conn, "now()");
        int id_account_receive = crud.readIdAccount(conn, accountNumber);
        float oldBalanceAccount_1 = crud.readBalanceById(conn, id_account_sender);
        float oldBalanceAccount_2 = crud.readBalanceById(conn, id_account_receive);
        String oldTimeAccount_1 = crud.readTimeById(conn, id_account_sender);
        String oldTimeAccount_2 = crud.readTimeById(conn, id_account_receive);


        if (id_account_sender != id_account_receive) {


            try {
                //insert transaction C1
                String query = String.format("insert into transaction(label,transaction_amount,bank_type,date_effect,date_registration, transaction_type, id_account,id_transfer,status) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');", transaction.getLabel(), transaction.getTransactionAmount(), transaction.getBankType(), dateTimeNow, dateTimeNow, "debit", id_account_sender, transaction.getIdTransfer(), "completed");
                statement = conn.createStatement();
                statement.executeUpdate(query);
                System.out.println("transaction ok ✔ ");

                // insert transaction C2
                String query1 = String.format("insert into transaction(label,transaction_amount,bank_type,date_effect,date_registration, transaction_type, id_account,id_transfer,status) values('%s','%s','%s','%s','%s','%s','%s','%s','%s');", transaction.getLabel(), transaction.getTransactionAmount(), transaction.getBankType(), dateTimeNow, dateTimeNow, "credit", id_account_receive, transaction.getIdTransfer(), "completed");
                statement = conn.createStatement();
                statement.executeUpdate(query1);
                System.out.println("transaction ok  ✔");

                //mise a jour pour chaque solde du compte
                String query666 = String.format("update account set balance='%s' where balance='%s' and id_account='%s'", (oldBalanceAccount_1 - transaction.getTransactionAmount()), oldBalanceAccount_1, id_account_sender);
                statement = conn.createStatement();
                statement.executeUpdate(query666);
                System.out.println("SOLDE C1 a JOUR ok  ✔");

                // String query6 = String.format("update account set balance='%s' where balance='%s' and id_account='%s'", (oldBalanceAccount_2 + transaction.getTransactionAmount()), oldBalanceAccount_2, id_account_receive);
                // statement = conn.createStatement();
                // statement.executeUpdate(query6);
                // System.out.println("SOLDE C2 a JOUR ok  ✔ ");

                //MISE A JOUR HEURE DU SOLDE
                String query3 = String.format("update account set    date_heure='%s' where    date_heure='%s'", dateTimeNow, oldTimeAccount_1);
                statement = conn.createStatement();
                statement.executeUpdate(query3);
                System.out.println("Heure C1 a jour ok   ✔");

                // String query33 = String.format("update account set    date_heure='%s' where    date_heure='%s'", dateTimeNow, oldTimeAccount_2);
                // statement = conn.createStatement();
                // statement.executeUpdate(query33);
                //System.out.println("Heure  C2 a jour ok   ✔");

                //solde history
                String query4 = String.format("insert into transaction_history(last_balance,date_registration,id_account) values('%s','%s','%s');", oldBalanceAccount_1, oldTimeAccount_1, id_account_sender);
                statement = conn.createStatement();
                statement.executeUpdate(query4);
                System.out.println("solde_history ok  ✔");

                // String query7 = String.format("insert into transaction_history(last_balance,date_registration,id_account) values('%s','%s','%s');", oldBalanceAccount_2, oldTimeAccount_2, id_account_receive);
                // statement = conn.createStatement();
                // statement.executeUpdate(query7);
                // System.out.println("solde_history ok  ✔ ");

                //tranfer_history
                String query8 = String.format("insert into transfer_history(id_send,id_receive,date_registration) values('%s','%s','%s');", id_account_sender, id_account_receive, dateTimeNow);
                statement = conn.createStatement();
                statement.executeUpdate(query8);
                System.out.println("tranfer_history ok  ✔");
            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            System.out.println("transfer error  ");
        }
    }

    @Override
    public void saveIdTransfer() {
        Statement statement;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        Crud crud = new Crud();
        int idTransaction = crud.idTransaction(conn);
        int idTransfer = crud.idTransfer(conn);
        try {
            String query = String.format("update transaction set id_transfer='%s' where id_transaction='%s' ;", idTransfer, idTransaction);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("merci ✔");

            String query1 = String.format("update transaction set id_transfer='%s' where id_transaction='%s' ;", idTransfer, idTransaction - 1);
            statement = conn.createStatement();
            statement.executeUpdate(query1);
            System.out.println("merci ✔");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
