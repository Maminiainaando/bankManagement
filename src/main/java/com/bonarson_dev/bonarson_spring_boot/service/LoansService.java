package com.bonarson_dev.bonarson_spring_boot.service;

import com.bonarson_dev.bonarson_spring_boot.dao.DbConnection;
import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.model.BalanceRepaymentException;
import com.bonarson_dev.bonarson_spring_boot.model.Loans;
import com.bonarson_dev.bonarson_spring_boot.repository.LoansRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.nio.file.AccessDeniedException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service

public class LoansService implements LoansRepository {
    private final DataSource dataSource;

    public LoansService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Loans> getAllLoans() {
        List<Loans> loans = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from loans ")) {
            while (resultSet.next()) {
                loans.add(new Loans(resultSet.getString("date_registration"), resultSet.getFloat("balance_loans"), resultSet.getFloat("balance_repayment"), resultSet.getFloat("percentage"), resultSet.getString("payment_methode"), resultSet.getInt("id_account")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public void addLoans(int id, Loans loans) throws AccessDeniedException, BalanceRepaymentException {
        Statement statement;
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.conn_db("wallet_exam");
        Crud crud = new Crud();
        String dateTimeNow = crud.readTimeNow(conn, "now()");
        int idAccount = loans.getIdAccount();
        List<Integer> idListAccountAccess = crud.listOfIdAccountAccess(conn);
        List<Integer> idAccountListInLoans = crud.listOfIdAccountInLoans(conn);

        if (!idListAccountAccess.contains(idAccount)) {
            throw new AccessDeniedException("You do not have access. ❌");
        }

        if (idAccountListInLoans.contains(idAccount)) {
            if (crud.differenceBetweenBalanceLoansAndBalanceRepayment(conn, idAccount) != 0) {
                throw new BalanceRepaymentException("The balance repayment is not complete. ❌");
            }

            try {
                String delete = String.format("delete from loans where id_account='%s';", idAccount);
                statement = conn.createStatement();
                statement.executeUpdate(delete);
                System.out.println("delete done ✔");

                String query = String.format("insert into loans(date_registration, balance_loans, balance_repayment, percentage,payment_methode,id_account) values ('%s', '%s', '%s', %s, '%s', '%s');", dateTimeNow, loans.getBalanceLoans(), 0, loans.getPercentage(), loans.getPaymentMethode(), loans.getIdAccount());
                statement = conn.createStatement();
                statement.executeUpdate(query);

                System.out.println("loans save ✔");

            } catch (Exception e) {
                System.out.println(e);
                e.getMessage();
            }
        } else {
            try {
                String query = String.format("insert into loans(date_registration, balance_loans, balance_repayment, percentage,payment_methode,id_account) values ('%s', '%s', '%s', %s, '%s', '%s');", dateTimeNow, loans.getBalanceLoans(), 0, loans.getPercentage(), loans.getPaymentMethode(), loans.getIdAccount());
                statement = conn.createStatement();
                statement.executeUpdate(query);

                System.out.println("loans save ✔");

            } catch (Exception e) {
                System.out.println(e);
                e.getMessage();
            }
        }
    }

}
