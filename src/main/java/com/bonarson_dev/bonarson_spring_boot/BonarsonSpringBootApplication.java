package com.bonarson_dev.bonarson_spring_boot;

import com.bonarson_dev.bonarson_spring_boot.dao.DatabaseConfig;
import com.bonarson_dev.bonarson_spring_boot.dao.DbConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication

public class BonarsonSpringBootApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(BonarsonSpringBootApplication.class, args);

        DatabaseConfig databaseConfig = new DatabaseConfig();
        DbConnection dbConnection = new DbConnection();
        dbConnection.conn_db("wallet");


    }

}
