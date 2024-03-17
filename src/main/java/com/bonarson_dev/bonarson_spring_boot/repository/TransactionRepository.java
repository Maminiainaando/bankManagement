package com.bonarson_dev.bonarson_spring_boot.repository;

import com.bonarson_dev.bonarson_spring_boot.model.Account;
import com.bonarson_dev.bonarson_spring_boot.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    void addTransaction(int id,String transaction_type ,int id_account, Transaction transaction);
    List<Transaction> getAllTransaction();
}
