package com.company;

import java.sql.*;

public class MySQLdemo {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/entertainment?useSSL=false&serverTimezone=UTC";
    private final static String user = "root";
    private final static String pw = "12345";
    private static Connection connection;

    public static void main(String[] args) {
        connectToMySQL();
        insertData();
        showAllRows();

    }


    static void showAllRows() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM movies";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double rating = resultSet.getDouble("rating");
                System.out.println("Name: " + name + "Rating: " + rating);
            }
        } catch (Exception e) {

        }
    }


    static void insertData() {
        try {
            String query = "INSERT INTO movies VALUES (null, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Shooter");
            preparedStatement.setDouble(2, 100);
            preparedStatement.executeUpdate();
            System.out.println("Indsat ny række OK");
        } catch (Exception e) {

        }


    }

    static void connectToMySQL() {
        try {
            connection = DriverManager.getConnection(DB_URL, user, pw);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}




