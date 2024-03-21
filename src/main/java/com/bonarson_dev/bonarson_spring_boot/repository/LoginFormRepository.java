package com.bonarson_dev.bonarson_spring_boot.repository;

import java.sql.Connection;

public interface LoginFormRepository {
    boolean authenticateAccount(int id_account, String account_number, Connection connection);

}
