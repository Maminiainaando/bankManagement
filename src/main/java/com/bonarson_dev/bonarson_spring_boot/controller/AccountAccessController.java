package com.bonarson_dev.bonarson_spring_boot.controller;


import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.model.AccountAccess;
import com.bonarson_dev.bonarson_spring_boot.service.AccountAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/access")

public class AccountAccessController {
    private final AccountAccessService accountAccessService;

    @Autowired
    public AccountAccessController(AccountAccessService accountAccessService) {
        this.accountAccessService = accountAccessService;
    }

    @GetMapping
    public List<AccountAccess> getAllAccountAccess() {
        return accountAccessService.getAllAccountAccess();

    }

    @PostMapping("/add")
    public ResponseEntity<Void> addAccountAccess(@RequestBody AccountAccess accountAccess) {
        accountAccessService.addAccountAccess(1,accountAccess);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
