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
    private final AccountService accountService ;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/balance/{id}")
    public List<Account> getBalanceNow(@PathVariable int id) {
        return accountService.getBalanceNow(id);
    }



    @GetMapping
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addAccount(@RequestBody Account account){
        accountService.addPerson(1,account);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
