package com.bonarson_dev.bonarson_spring_boot.repository;

import com.bonarson_dev.bonarson_spring_boot.model.Account;

import java.util.List;

public interface AccountRepository {
    void addPerson(int id, Account account);
    List<Account> getAllAccount();
}
