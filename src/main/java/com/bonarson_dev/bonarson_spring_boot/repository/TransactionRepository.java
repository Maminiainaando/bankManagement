package com.bonarson_dev.bonarson_spring_boot.repository;

import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.model.Transaction;

public interface TransactionRepository {
    void addTransaction(int id, Transaction transaction);
}
