package com.bonarson_dev.bonarson_spring_boot.controller;

import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.model.Loans;
import com.bonarson_dev.bonarson_spring_boot.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoansController {
    private final LoansService loansService;

    @Autowired

    public LoansController(LoansService loansService) {
        this.loansService = loansService;
    }
    @GetMapping
    public List<Loans> getAllLoans() {
        return loansService.getAllLoans();
    }
}
