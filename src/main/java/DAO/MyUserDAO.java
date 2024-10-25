/*
Author: Ene Bogdan
Country: Romania
*/
package DAO;

import config.ConnectionAttributes;
import entity.MyUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDAO {
    private ConnectionAttributes connectionAttributes = new ConnectionAttributes();

    public MyUser insertUserIntoTableFromDB(MyUser myUser) {
        System.out.println("Trying to insert a new user...");
        final String checkTableQuery = "SHOW TABLES LIKE 'users'";
        String insertEntryIntoTableQuery = """
                INSERT INTO users (username, password, email)
                VALUES
                (?,?,?)
                """;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/exercises", connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
             PreparedStatement checkPreparedStatement = connection.prepareStatement(checkTableQuery);
             ResultSet checkResultSet = checkPreparedStatement.executeQuery()) {

            while (!checkResultSet.next()) {
                log.info("Table \"users\" does not exist.");
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertEntryIntoTableQuery)) {
                preparedStatement.setString(1, myUser.getUsername());
                preparedStatement.setString(2, myUser.getPassword());
                preparedStatement.setString(3, myUser.getEmail());
                preparedStatement.executeUpdate();
                log.info("Executing query: \"" + insertEntryIntoTableQuery + "\"");
            } catch (SQLException e) {
                log.warning("User cannot be created.");
            }

        } catch (SQLException e) {
            log.info("Database connection failed or table \"users\" does not exist.");
            throw new RuntimeException(e);
        }
        return myUser;
    }

    public List<MyUser> getAllUsersFromDB() {
        System.out.println("Trying to display all users...");
        List<MyUser> myUsers = new ArrayList<>();
        String getAllUsersQuery = "SELECT * FROM users";

        try (Connection connection = DriverManager.getConnection(connectionAttributes.getURL(), connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllUsersQuery)) {

            while (resultSet.next()) {
                MyUser myUser = new MyUser();
                myUser.setId(resultSet.getInt("id"));
                myUser.setUsername(resultSet.getString("username"));
                myUser.setPassword(resultSet.getString("password"));
                myUser.setEmail(resultSet.getString("email"));
                myUsers.add(myUser);
            }
            log.info("Executing query: \"" + getAllUsersQuery + "\"");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return myUsers;
    }

    public MyUser updateUserByIdFromDB(Integer id, MyUser myUser) {
        System.out.println("Trying to update user with id: " + id + "...");
        String checkIdQuery = "SELECT * FROM users WHERE id=" + id + ";";
        String updateUserFromDBQuery = """
            UPDATE users
            SET username = ?, password = ?, email = ?
            WHERE id = ?
            """;
        try (Connection connection = DriverManager.getConnection(connectionAttributes.getURL(), connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
             PreparedStatement checkPreparedStatement = connection.prepareStatement(checkIdQuery);
             ResultSet checkResultSet = checkPreparedStatement.executeQuery()) {

            if (!checkResultSet.next()) {
                log.info("User with ID " + id + " does not exist. Updating failed. Data entered not saved!");
                return null;
            } else {
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateUserFromDBQuery)) {
                    preparedStatement.setString(1, myUser.getUsername());
                    preparedStatement.setString(2, myUser.getPassword());
                    preparedStatement.setString(3, myUser.getEmail());
                    preparedStatement.setInt(4, id);
                    preparedStatement.executeUpdate();
                    log.info("Executing query: \"" + updateUserFromDBQuery + "\"");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myUser.setId(id);
        return myUser;
    }

    public void deleteUserByIdFromDB(Integer id) {
        System.out.println("Trying to delete user with id: " + id + "...");
        String checkIdQuery = "SELECT * FROM users WHERE id=" + id + ";";
        String deleteUserByIdFromDBQuery = """
                DELETE FROM users
                WHERE id = ?;
                """;

        try (Connection connection = DriverManager.getConnection(connectionAttributes.getURL(), connectionAttributes.getUSER(), connectionAttributes.getPASSWORD());
             PreparedStatement checkPreparedStatement = connection.prepareStatement(checkIdQuery);
             ResultSet checkResultSet = checkPreparedStatement.executeQuery();) {
            if (!checkResultSet.next()) {
                log.info("User with ID " + id + " does not exist. Deleting failed.");
            } else {
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteUserByIdFromDBQuery)) {
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                    log.info("Executing query: \"" + deleteUserByIdFromDBQuery + "\" for user_id: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
