package com.bonarson_dev.bonarson_spring_boot.repository;

import com.bonarson_dev.bonarson_spring_boot.model.Account;

import java.util.List;

public interface AccountRepository {
    void addAccount(int id, Account account);

    List<Account> getAllAccount();

    List<Account> getBalanceNow(int id);

    float readBalanceListByDateTime(String date_registration, int id_account);

    List<String> readBalanceByBetweenDateTime(int id_account, String date_registration_1, String date_registration_2);

}
