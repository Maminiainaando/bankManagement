package com.bonarson_dev.bonarson_spring_boot.controller;


import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.model.AccountAccess;
import com.bonarson_dev.bonarson_spring_boot.service.AccountAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account-access")

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


}
