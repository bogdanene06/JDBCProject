# Java JDBC CRUD Operations

## Description
This is the solution to an exercise received in the Java programming course.

## Subject
Create a program in which you highlight the use of CRUD operations on a pre-existing MySQL database using JDBC.

## Author's Note
I added functionalities with constant parameters for creating and deleting the database and the table.

## Requirements
- Java Development Kit (JDK) version 8 or later
- MySQL database
- MySQL Connector/J (JDBC Driver for MySQL)
- XAMPP or other localhost server software

## Setup
1. **Clone the repository:**
   ```sh
   git clone https://github.com/bogdanene06/JDBCProject.git
   cd your-repo-name

2. **Set up MySQL:**
Create a database named exercises.
Update the MySQL user credentials in the config.ConnectionAttributes class.

3. **Features**
Create, Read, Update, Delete (CRUD) operations on a MySQL database.
Handles cases where the database or table does not exist.
Provides feedback when the user ID does not exist in the database.

4. ""Description of the Classes and Methods**

Main
Main class: Entry point of the application. It initializes the main menu options and handles user inputs to perform CRUD operations on the MySQL database using JDBC.

MainMenuOptions
MainMenuOptions class: Contains the main menu options and logic for executing selected operations such as creating/deleting databases and tables, inserting users, displaying all users, updating user details, and deleting users.
displayMainMenuOptions(): Displays the main menu and executes the selected option.

InsertFromKeyboard
InsertFromKeyboard class: Provides methods for taking inputs from the user and displaying data.
insertDatabaseNameFromKeyboard(): Prompts the user to input the name of the database.
insertTableNameFromKeyboard(): Prompts the user to input the name of the table.
insertMyUserDetails(): Prompts the user to input the details of the user.
insertIdFromKeyboard(): Prompts the user to input the ID of the user.
displayEntries(List<MyUser> myUsers): Displays the details of all users.
closeScanner(): Closes the scanner used for taking inputs.

MyUserDAO
MyUserDAO class: Contains methods for performing CRUD operations on the users table.
insertUserIntoTableFromDB(MyUser myUser): Inserts a new user into the users table.
getAllUsersFromDB(): Retrieves all users from the users table.
updateUserByIdFromDB(Integer id, MyUser myUser): Updates user details by ID.
deleteUserByIdFromDB(Integer id): Deletes a user by ID.

DatabaseDAO
DatabaseDAO class: Contains methods for creating and deleting databases.
createDatabase(String databaseName): Creates a new database.
deleteDatabase(String databaseName): Deletes an existing database.

TableDAO
TableDAO class: Contains methods for creating and deleting tables.
createTable(String databaseName, String tableName): Creates a new table in the specified database.
deleteTable(String databaseName, String tableName): Deletes an existing table from the specified database.

MyUser
MyUser class: Represents the user entity with the following attributes:
id: Integer
username: String
password: String
email: String

Config
ConnectionAttributes class: Configures the database connection details.
URL: String
USER: String
PASSWORD: String

5. **How to Use**
Run the application: Follow the setup instructions and run the Main class.

Perform CRUD operations: The application will prompt you for inputs to perform various CRUD operations on the users table.
