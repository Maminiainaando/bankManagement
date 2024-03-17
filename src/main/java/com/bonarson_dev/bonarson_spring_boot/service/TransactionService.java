package com.bonarson_dev.bonarson_spring_boot.service;

import com.bonarson_dev.bonarson_spring_boot.model.Transaction;
import com.bonarson_dev.bonarson_spring_boot.repository.TransactionRepository;

public class TransactionService implements TransactionRepository {
    @Override
    public void addTransaction(int id, Transaction transaction) {

    }
}
