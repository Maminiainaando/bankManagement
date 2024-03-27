package com.bonarson_dev.bonarson_spring_boot.repository;

import com.bonarson_dev.bonarson_spring_boot.model.Loans;

import java.util.List;

public interface LoansRepository {
    List<Loans> getAllLoans();
}
