/**
 * 
 */
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import entities.accounts.*;


/**
 * 
 */
public class AccountRepositoryJDBC implements IAccountRepository{
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/AccountsDB"; // replace with your DB URL
    private static final String DB_USER = "root"; // replace with your DB
    private static final String DB_PASSWORD = "root"; // replace with your DB password
 
 
       protected static Connection connection = null;
 
        protected void connect() {
            try {
                // Register the JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Open a connection
                System.out.println("Connecting to the database...");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Connection successful!");

            } catch (SQLException se) {
                // Handle JDBC errors
                System.out.println("SQL Exception occurred: " + se.getMessage());
            } catch (ClassNotFoundException e) {
                // Handle general errors
                System.out.println("Exception occurred: " + e.getMessage());
            } 
        }
        
        public boolean storeForOpenAccount(Account account) {
        	connect();
        	String insertSQL = "INSERT INTO Account_Details (Account_Number, Account_Holder_Name, Pin_Number, Balance, Privilege, Activated_Date) VALUES ('"
                    + account.getAccountNumber() + "', '"
                    + account.getName() + "', "
                    + account.getPinNumber() + ", "
                    + account.getBalance() + ", '"
                    + account.getPrivilege().toString() + "', '"
                    + account.getActivatedDate().toString() + "')";
            
            try (Statement statement = connection.createStatement()) {
                // Execute the insert statement
                int rowsAffected = statement.executeUpdate(insertSQL);
                System.out.println(rowsAffected + " row(s) inserted.");

            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
            }
        	closeConnection(connection);
        	return false;
        	
        }
        
        public void closeConnection(Connection connection) {
        	try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException se) {
                System.out.println("Error closing the connection: " + se.getMessage());
            }
        }
        
        
        
}

//           