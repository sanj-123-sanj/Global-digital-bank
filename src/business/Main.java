package business;

import java.util.Scanner;

import entities.exceptions.*;
import ui.AccountUI;

/**
 * The main entry point for the Bank of Success (BOS) application.
 * This class initiates the user interface and manages the application's execution flow.
 */
public class Main {

    /**
     * The main method that starts the BOS application. It repeatedly invokes the
     * user interface to handle user operations until the user decides to exit.
     *
     * @param args command-line arguments (not used)
     * @throws Exception if an error occurs during execution
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        AccountUI userInterface = new AccountUI();
        String answer;

        // Continuously start the user interface based on user input
        do {
            userInterface.Start();
            System.out.println("Do you want to continue?");
            answer = scanner.next();
        } while (answer.equalsIgnoreCase("yes"));

        System.out.println("Thanks for using BOS");
        scanner.close();
    }
}
