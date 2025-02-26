package service;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcConnector {
    public static void main(String[] args) {
        var initializeScript = ";INIT=RUNSCRIPT FROM 'jdbc-example/src/main/resources/queries.sql';";
        var url = "jdbc:h2:mem:" + initializeScript;
        try (var connection = DriverManager.getConnection(url);) {
            System.out.println("connection.isValid(0) = " + connection.isValid(0));

            // SELECTS
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employees");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("employee_id") + " - " + resultSet.getString("first_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
