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
    public boolean authenticateAccount(String name , String password, Connection connection) {
        String query = "SELECT * FROM account WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return password.equals(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
