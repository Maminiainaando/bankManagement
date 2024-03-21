package com.bonarson_dev.bonarson_spring_boot.service;

import com.bonarson_dev.bonarson_spring_boot.repository.LoginFormRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Service
public class LoginFormService implements LoginFormRepository {

    @Override
    public boolean authenticateAccount(int id_account, String account_number, Connection connection) {
        String query = "SELECT * FROM account WHERE id_account = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id_account);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return account_number.equals(resultSet.getString("account_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
