package com.bonarson_dev.bonarson_spring_boot;

import com.bonarson_dev.bonarson_spring_boot.dao.DatabaseConfig;
import com.bonarson_dev.bonarson_spring_boot.dao.DbConnection;
import com.bonarson_dev.bonarson_spring_boot.model.AccountAccess;
import com.bonarson_dev.bonarson_spring_boot.repository.AccountAccessRepository;
import com.bonarson_dev.bonarson_spring_boot.service.AccountAccessService;
import com.bonarson_dev.bonarson_spring_boot.service.Crud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


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
        System.out.println("listIdAccess");
        System.out.println(crud.listOfIdAccountAccess(conn));
        List<Integer> id_List = crud.listOfIdAccountAccess(conn);
        int a = 4;
        System.out.println(id_List.contains(a));
        System.out.println("difference balance ");
        System.out.println("============");
        System.out.println(crud.differenceBetweenBalanceLoansAndBalanceRepayment(conn, 1)==0);
        System.out.println(crud.listOfIdAccountInLoans(conn));
        List<Integer>idAccountListInLoans = crud.listOfIdAccountInLoans(conn);
        int b = 1;
        System.out.println(idAccountListInLoans.contains(b));
        System.out.println("percentage");
        System.out.println(crud.percentage(conn, 1));
        System.out.println("payment_methode");
        System.out.println(crud.paymentMethode(conn, 1));
        System.out.println(" date_registration in loans");
        System.out.println(crud.dateRegistrationLoans(conn, 1));


    }

}
