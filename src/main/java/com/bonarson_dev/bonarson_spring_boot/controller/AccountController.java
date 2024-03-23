package com.bonarson_dev.bonarson_spring_boot.controller;

import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.model.Transaction;
import com.bonarson_dev.bonarson_spring_boot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")

public class AccountController {
    private final AccountService accountService;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/balance/{password}")
    public List<Account> getBalanceNow(@PathVariable String password) {
        return accountService.getBalanceNow(password);
    }

    @GetMapping("/{id_account}/{date_registration}")
    public float readBalanceListByDateTime(@PathVariable String date_registration, @PathVariable int id_account) {
        return accountService.readBalanceListByDateTime(date_registration, id_account);
    }

    @GetMapping("/{id_account}/{date_registration_1}/{date_registration_2}")
    List<String> readBalanceByBetweenDateTime(@PathVariable int id_account, @PathVariable String date_registration_1, @PathVariable String date_registration_2) {
        return accountService.readBalanceByBetweenDateTime(id_account, date_registration_1, date_registration_2);
    }


    @GetMapping
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addAccount(@RequestBody Account account) {
        accountService.addAccount(1, account);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
