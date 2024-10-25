/*
Author: Ene Bogdan
Country: Romania
*/
package utils;

import DAO.DatabaseDAO;
import DAO.MyUserDAO;
import DAO.TableDAO;

import java.util.Scanner;

public class MainMenuOptions {
    final String databaseName = "exercises";
    final String tableName = "users";
    Scanner scanner = new Scanner(System.in);
    InsertFromKeyboard insertFromKeyboard = new InsertFromKeyboard();
    DatabaseDAO databaseDAO = new DatabaseDAO();
    TableDAO tableDAO = new TableDAO();
    MyUserDAO myUserDAO = new MyUserDAO();

    public void displayMainMenuOptions() {

        String applicationMenu = """
                \n\n
                Choose an option:
                1. Create database "exercises";
                2. Delete database "exercises";
                3. Create table "users";
                4. Delete table "users";
                5. Insert user;
                6. Display all users;
                7. Modify user's details;
                8. Delete user;
                0. Exit application.
                """;

        System.out.println(applicationMenu);
        int optionMenu;
        do {
            System.out.print("Insert value: ");
            optionMenu = scanner.nextInt();
            switch (optionMenu) {
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                case 1:
                    databaseDAO.createDatabase(databaseName);
                    System.out.println(applicationMenu);
                    break;
                case 2:
                    databaseDAO.deleteDatabase(databaseName);
                    System.out.println(applicationMenu);
                    break;
                case 3:
                    tableDAO.createTable(databaseName, tableName);
                    System.out.println(applicationMenu);
                    break;
                case 4:
                    tableDAO.deleteTable(databaseName, tableName);
                    System.out.println(applicationMenu);
                    break;
                case 5:
                    myUserDAO.insertUserIntoTableFromDB(insertFromKeyboard.insertMyUserDetails());
                    System.out.println(applicationMenu);
                    break;
                case 6:
                    insertFromKeyboard.displayEntries(myUserDAO.getAllUsersFromDB());
                    System.out.println(applicationMenu);
                    break;
                case 7:
                    myUserDAO.updateUserByIdFromDB(insertFromKeyboard.insertIdFromKeyboard(),
                            insertFromKeyboard.insertMyUserDetails());
                    insertFromKeyboard.displayEntries(myUserDAO.getAllUsersFromDB());
                    System.out.println(applicationMenu);
                    break;
                case 8:
                    myUserDAO.deleteUserByIdFromDB(insertFromKeyboard.insertIdFromKeyboard());
                    System.out.println(applicationMenu);
                    break;
                default:
                    System.out.println("Invalid option. Please, enter a value between 0 and 8!");
                    break;
            }
        } while (optionMenu != 0);
        insertFromKeyboard.closeScanner();
    }
}
