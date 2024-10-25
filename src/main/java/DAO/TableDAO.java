/*
Author: Ene Bogdan
Country: Romania
*/
package DAO;

import config.ConnectionAttributes;
import lombok.extern.java.Log;

import java.sql.*;

@Log
public class TableDAO {

    private ConnectionAttributes connectionAttributes = new ConnectionAttributes();

    public void createTable(String databaseName, String tableName) {
        final String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName +
                                        "(id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), password VARCHAR(255), email VARCHAR(255))";
        String databaseURL = "jdbc:mysql://localhost/" + databaseName;

        try (Connection dbConnection = DriverManager.getConnection(databaseURL, connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
             Statement createStatement = dbConnection.createStatement()) {
            createStatement.executeUpdate(createTableQuery);
            log.info("Executing query: \"" + createTableQuery + "\"");
            log.info("Table \"" + tableName + "\" successfully created.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //    public void createTable(String databaseName, String tableName) throws SQLException {
//        final String checkDatabaseQuery = "SHOW DATABASES LIKE ?";
//        final String checkTableQuery = "SHOW TABLES LIKE ?";
//        final String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName +
//                                        "(id int, username varchar(255), password varchar(255), email varchar(255))";
//        String databaseURL = "jdbc:mysql://localhost/" + databaseName;
//
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
//             PreparedStatement checkDatabasePreparedStatement = connection.prepareStatement(checkDatabaseQuery)) {
//
//            checkDatabasePreparedStatement.setString(1, databaseName);
//            try (ResultSet databaseResultSet = checkDatabasePreparedStatement.executeQuery()) {
//                if (!databaseResultSet.next()) {
//                    log.info("Database \"" + databaseName + "\" does not exist.");
//                    return;
//                }
//            }
//
//            try (Connection dbConnection = DriverManager.getConnection(databaseURL, connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
//                 PreparedStatement checkTablePreparedStatement = dbConnection.prepareStatement(checkTableQuery)) {
//
//                checkTablePreparedStatement.setString(1, tableName);
//                try (ResultSet tableResultSet = checkTablePreparedStatement.executeQuery()) {
//                    if (tableResultSet.next()) {
//                        log.info("Table \"" + tableName + "\" already exists.");
//                    } else {
//                        try (PreparedStatement createTablePreparedStatement = dbConnection.prepareStatement(createTableQuery)) {
//                            createTablePreparedStatement.executeUpdate();
//                            log.info("Executing query: \"" + createTableQuery + "\"");
//                            log.info("Table \"" + tableName + "\" successfully created.");
//                        }
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void deleteTable(String databaseName, String tableName) {
//        final String checkDatabaseQuery = "SHOW DATABASES LIKE ?";
//        final String checkTableQuery = "SHOW TABLES LIKE ?";
//        final String dropTableQuery = "DROP TABLE " + tableName;
//
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
//             PreparedStatement checkDatabasePreparedStatement = connection.prepareStatement(checkDatabaseQuery)) {
//
//            checkDatabasePreparedStatement.setString(1, databaseName);
//            try (ResultSet databaseResultSet = checkDatabasePreparedStatement.executeQuery()) {
//                if (!databaseResultSet.next()) {
//                    log.info("Database \"" + databaseName + "\" does not exist.");
//                    return; // Baza de date nu există, ieșim din metodă
//                }
//            }
//
//            // Conexiune la baza de date existentă
//            String databaseURL = "jdbc:mysql://localhost/" + databaseName;
//            try (Connection dbConnection = DriverManager.getConnection(databaseURL, connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
//                 PreparedStatement checkTablePreparedStatement = dbConnection.prepareStatement(checkTableQuery)) {
//
//                checkTablePreparedStatement.setString(1, tableName);
//                try (ResultSet tableResultSet = checkTablePreparedStatement.executeQuery()) {
//                    if (!tableResultSet.next()) {
//                        log.info("Table \"" + tableName + "\" does not exist.");
//                    } else {
//                        try (Statement dropStatement = dbConnection.createStatement()) {
//                            dropStatement.executeUpdate(dropTableQuery);
//                            log.info("Executing query: \"" + dropTableQuery + "\"");
//                            log.info("Table \"" + tableName + "\" successfully deleted.");
//                        }
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    }

    public void deleteTable(String databaseName, String tableName) {
        final String checkDatabaseQuery = "SHOW DATABASES LIKE ?";
        final String checkTableQuery = "SHOW TABLES LIKE ?";
        final String dropTableQuery = "DROP TABLE " + tableName;
        String databaseURL = "jdbc:mysql://localhost/" + databaseName;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
             PreparedStatement checkDatabasePreparedStatement = connection.prepareStatement(checkDatabaseQuery)) {

            checkDatabasePreparedStatement.setString(1, databaseName);
            try (ResultSet databaseResultSet = checkDatabasePreparedStatement.executeQuery()) {
                if (!databaseResultSet.next()) {
                    log.info("Database \"" + databaseName + "\" does not exist.");
                    return; // Baza de date nu există, ieșim din metodă
                }
            }

            try (Connection dbConnection = DriverManager.getConnection(databaseURL, connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
                 PreparedStatement checkTablePreparedStatement = dbConnection.prepareStatement(checkTableQuery)) {

                checkTablePreparedStatement.setString(1, tableName);
                try (ResultSet tableResultSet = checkTablePreparedStatement.executeQuery()) {
                    if (!tableResultSet.next()) {
                        log.info("Table \"" + tableName + "\" does not exist.");
                    } else {
                        try (Statement dropStatement = dbConnection.createStatement()) {
                            dropStatement.executeUpdate(dropTableQuery);
                            log.info("Executing query: \"" + dropTableQuery + "\"");
                            log.info("Table \"" + tableName + "\" successfully deleted.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
