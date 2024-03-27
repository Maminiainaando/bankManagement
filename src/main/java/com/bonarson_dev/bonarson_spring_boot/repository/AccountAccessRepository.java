package com.bonarson_dev.bonarson_spring_boot.repository;

import com.bonarson_dev.bonarson_spring_boot.model.AccountAccess;

import java.util.List;

public interface AccountAccessRepository {
    List<AccountAccess> getAllAccountAccess();

}
