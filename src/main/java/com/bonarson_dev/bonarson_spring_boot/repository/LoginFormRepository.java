package com.bonarson_dev.bonarson_spring_boot.repository;

import java.sql.Connection;

public interface LoginFormRepository {
    boolean authenticateAccount(String name, String password, Connection connection);

}
