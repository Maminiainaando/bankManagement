package com.bonarson_dev.bonarson_spring_boot.controller;

import com.bonarson_dev.bonarson_spring_boot.model.LoginForm;
import com.bonarson_dev.bonarson_spring_boot.repository.LoginFormRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class AuthController {
    private final LoginFormRepository loginFormRepository;
    private final DataSource dataSource;

    public AuthController(LoginFormRepository loginFormRepository, DataSource dataSource) {
        this.loginFormRepository = loginFormRepository;
        this.dataSource = dataSource;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        int idAccount = loginForm.getId_account();
        String accountNumber = loginForm.getAccount_number();

        try (Connection connection = dataSource.getConnection()) {
            if (loginFormRepository.authenticateAccount(idAccount, accountNumber, connection)) {
                return ResponseEntity.ok("Information correct");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(" bad Information ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error base");
        }
    }
}
