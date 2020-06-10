package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetUpData {

    public static void setUpClients() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS clients (Last_name varchar(20), First_name varchar(20), sex varchar(10), Card_code varchar(10)," +
                                "Pin varchar(5), Balance varchar(20), Currency varchar(20), Bank int, Type varchar(20))";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addPerson(String Last_name, String First_name, String sex, String Card_code, String
                                 Pin, String Balance, String Currency, int Bank, String Type) {

        String insertPersonSql = "INSERT INTO clients(Last_name, First_name, sex, Card_code," +
                "Pin, Balance, Currency, Bank, Type) VALUES('" + Last_name + "', '" + First_name + "', '" + sex + "', '" + Card_code +
                "', '" + Pin + "', '" + Balance + "', '" + Currency + "', " + Bank + ", '" + Type + "')";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeUpdateSql(databaseConnection, insertPersonSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setUpTimestamps() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS timestamps (Action varchar(100), Date varchar(30))";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addTimestamp(String action, String date) {
        String insertPersonSql = "INSERT INTO timestamps(Action, Date) VALUES('" + action + "', '" + date + "')";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeUpdateSql(databaseConnection, insertPersonSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setUpBanks() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS banks (Name varchar(30), Balance varchar(30))";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeSql(databaseConnection, createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addBank(String name, String balance) {
        String insertPersonSql = "INSERT INTO banks(Name, Balance) VALUES('" + name + "', '" + balance + "')";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            repositoryHelper.executeUpdateSql(databaseConnection, insertPersonSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayClients() {
        String selectSql = "SELECT * FROM clients";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Last_name: " + resultSet.getString(1));
                System.out.println("First_name: " + resultSet.getString(2));
                System.out.println("Sex: " + resultSet.getString(3));
                System.out.println("Card_code: " + resultSet.getString(4));
                System.out.println("Pin: " + resultSet.getString(5));
                System.out.println("Balance: " + resultSet.getString(6));
                System.out.println("Currency: " + resultSet.getString(7));
                System.out.println("Bank: " + resultSet.getInt(8));
                System.out.println("Type: " + resultSet.getString(9));
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

