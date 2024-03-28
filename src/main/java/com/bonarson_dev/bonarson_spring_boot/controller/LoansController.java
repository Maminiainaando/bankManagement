package com.bonarson_dev.bonarson_spring_boot.controller;

import com.bonarson_dev.bonarson_spring_boot.dao.DbConnection;
import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.model.AccountAccess;
import com.bonarson_dev.bonarson_spring_boot.model.BalanceRepaymentException;
import com.bonarson_dev.bonarson_spring_boot.model.Loans;
import com.bonarson_dev.bonarson_spring_boot.service.Crud;
import com.bonarson_dev.bonarson_spring_boot.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.sql.Connection;
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

    @PostMapping("/add")
    public ResponseEntity<Void> addLoans(@RequestBody Loans loans) {
        try {
            loansService.addLoans(1, loans);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AccessDeniedException e) {
            System.out.println("You do not have access. ❌");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (BalanceRepaymentException e) {
            System.out.println("The balance repayment is not complete. ❌");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            System.out.println("An error occurred while processing the request. ❌");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
