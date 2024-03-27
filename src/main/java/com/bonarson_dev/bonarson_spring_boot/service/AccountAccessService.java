package com.bonarson_dev.bonarson_spring_boot.service;

import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.model.AccountAccess;
import com.bonarson_dev.bonarson_spring_boot.repository.AccountAccessRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service

public class AccountAccessService implements AccountAccessRepository {
    private final DataSource dataSource;

    public AccountAccessService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AccountAccess> getAllAccountAccess() {
        List<AccountAccess> accounts = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from account_access; ")) {
            while (resultSet.next()) {
                accounts.add(new AccountAccess(resultSet.getInt("id_account")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
