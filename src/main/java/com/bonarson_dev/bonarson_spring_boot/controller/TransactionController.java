package com.bonarson_dev.bonarson_spring_boot.controller;

import com.bonarson_dev.bonarson_spring_boot.model.Transaction;
import com.bonarson_dev.bonarson_spring_boot.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllPersons() {
        return transactionService.getAllTransaction();
    }

    @GetMapping("/{id_account}")
    public ResponseEntity<List<Transaction>> getTransactionsById(@PathVariable int id_account) {
        List<Transaction> transactions = transactionService.getTransactionById(id_account);
        return transactions != null && !transactions.isEmpty() ? new ResponseEntity<>(transactions, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/{transaction_type}/{id_account}")
    public ResponseEntity<Void> saveTransaction(@RequestBody Transaction transaction, @PathVariable String transaction_type, @PathVariable int id_account) {
        if (!transaction_type.equals("credit") && !transaction_type.equals("debit")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        transactionService.addTransaction(1, transaction_type, id_account, transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/transfer/{id_account_sender}/{id_account_receive}")

    public ResponseEntity<Void> addTransfer(@RequestBody Transaction transaction, @PathVariable int id_account_sender, @PathVariable int id_account_receive) {
        if (id_account_sender != id_account_receive) {
            transactionService.addTransfer(1, transaction, id_account_sender, id_account_receive);
            transactionService.saveIdTransfer();
        }else {
            System.out.println("error transfer");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
