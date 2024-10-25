/*
Author: Ene Bogdan
Country: Romania
*/
package DAO;

import config.ConnectionAttributes;
import lombok.extern.java.Log;

import java.sql.*;

@Log
public class DatabaseDAO {
    private ConnectionAttributes connectionAttributes = new ConnectionAttributes();

    public void createDatabase(String databaseName) {
        final String createDBQuery = "CREATE DATABASE IF NOT EXISTS " + databaseName;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createDBQuery);
            log.info("Executing query: \"" + createDBQuery + "\"");
            log.info("Database \"" + databaseName + "\" successfully created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteDatabase(String databaseName) {
        final String checkDatabaseQuery = "SHOW DATABASES LIKE ?";
        final String dropDBQuery = "DROP DATABASE " + databaseName;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
             PreparedStatement checkDatabasePreparedStatement = connection.prepareStatement(checkDatabaseQuery)) {

            checkDatabasePreparedStatement.setString(1, databaseName);
            try (ResultSet databaseResultSet = checkDatabasePreparedStatement.executeQuery()) {
                if (!databaseResultSet.next()) {
                    log.info("Database \"" + databaseName + "\" does not exist.");
                } else {
                    try (Statement dropStatement = connection.createStatement()) {
                        dropStatement.executeUpdate(dropDBQuery);
                        log.info("Executing query: \"" + dropDBQuery + "\"");
                        log.info("Database \"" + databaseName + "\" successfully deleted.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

