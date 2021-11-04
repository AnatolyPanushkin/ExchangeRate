package sample;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DbConnection {

    public static void DbInsert(String name , BigDecimal rate){
        Scanner sc = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "12345");
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/exchangerate?serverTimezone=UTC", props);

            PreparedStatement stmt = connection.prepareStatement("INSERT INTO exchangerate VALUES(?, ?)"); // SQL query
            stmt.setString(1, name);
            stmt.setBigDecimal(2, rate);
            stmt.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataOfCurrency DbGet(String name) {
        Scanner sc = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "12345");
        DataOfCurrency currency = new DataOfCurrency();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/exchangerate?serverTimezone=UTC", props);

            PreparedStatement stmt = connection.prepareStatement("SELECT "+ name +" FROM exchangerate"); // SQL query
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
            currency.name=rs.getString("name");
            currency.rate = rs.getBigDecimal("value");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currency;
    }

}
