package com.bonarson_dev.bonarson_spring_boot.repository;

import com.bonarson_dev.bonarson_spring_boot.model.BalanceRepaymentException;
import com.bonarson_dev.bonarson_spring_boot.model.Loans;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface LoansRepository {
    List<Loans> getAllLoans();
    void addLoans(int id, Loans loans) throws AccessDeniedException, BalanceRepaymentException;
}
