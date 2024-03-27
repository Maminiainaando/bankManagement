package com.bonarson_dev.bonarson_spring_boot.service;

import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.model.Loans;
import com.bonarson_dev.bonarson_spring_boot.repository.LoansRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
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
}
