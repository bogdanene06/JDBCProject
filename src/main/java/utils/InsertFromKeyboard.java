package utils;/*
Author: Ene Bogdan
Country: Romania
*/

import entity.MyUser;

import java.util.List;
import java.util.Scanner;


public class InsertFromKeyboard {

    private Scanner scanner;

    public InsertFromKeyboard() {
        this.scanner = new Scanner(System.in);
    }

    public String insertDatabaseNameFromKeyboard() {
        System.out.print("Insert database's name: ");
        return scanner.nextLine();
    }

    public String insertTableNameFromKeyboard() {
        System.out.print("Insert table's name: ");
        return scanner.nextLine();
    }

    public MyUser insertMyUserDetails() {
        MyUser myUser = new MyUser();
        System.out.print("Insert the user's username: ");
        myUser.setUsername(scanner.nextLine());
        System.out.print("Insert the user's password: ");
        myUser.setPassword(scanner.nextLine());
        System.out.print("Insert the user's email: ");
        myUser.setEmail(scanner.nextLine());
        return myUser;
    }



    public void displayEntries(List<MyUser> myUsers) {
        for (MyUser myUser : myUsers) {
            System.out.println(myUser + " ");
        }

    }

    public Integer insertIdFromKeyboard() {
        System.out.print("Insert the user's ID: ");
        return Integer.parseInt(scanner.nextLine());

    }


    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
