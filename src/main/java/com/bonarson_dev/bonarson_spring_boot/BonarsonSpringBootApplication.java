package com.bonarson_dev.bonarson_spring_boot;

import com.bonarson_dev.bonarson_spring_boot.dao.DatabaseConfig;
import com.bonarson_dev.bonarson_spring_boot.dao.DbConnection;
import com.bonarson_dev.bonarson_spring_boot.service.Crud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;


@SpringBootApplication

public class BonarsonSpringBootApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(BonarsonSpringBootApplication.class, args);

        DatabaseConfig databaseConfig = new DatabaseConfig();
        DbConnection dbConnection = new DbConnection();
        Connection conn =dbConnection.conn_db("wallet_exam");
        Crud crud = new Crud();
        System.out.println(crud.readBalanceById(conn, 1));
        System.out.println(crud.numberAccountModelX(14));
        int idAccount = crud.readIdAccountEnd(conn);
        System.out.println(crud.numberAccountModelX(idAccount));



    }

}
