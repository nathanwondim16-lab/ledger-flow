package com.pluralsight;

import java.util.Scanner;

/**
 * Represents the main entry screen of the application.
 *
 * This screen allows the user to:
 * - Add deposits
 * - Make payments
 * - Navigate to the ledger screen
 * - Exit the application
 *
 * It acts as the primary navigation hub for the system.
 */

public class HomeScreen extends ScreenManager {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the home menu, processes user input, and routes
     * the user to teh appropriate feature based on their selection.
     */
    public void displayOptions() {
        welcomeMessage();

        // Display stylized screen title
        printScreenTitle("""
                ██╗  ██╗ ██████╗ ███╗   ███╗███████╗    ███████╗ ██████╗██████╗ ███████╗███████╗███╗   ██╗
                ██║  ██║██╔═══██╗████╗ ████║██╔════╝    ██╔════╝██╔════╝██╔══██╗██╔════╝██╔════╝████╗  ██║
                ███████║██║   ██║██╔████╔██║█████╗      ███████╗██║     ██████╔╝█████╗  █████╗  ██╔██╗ ██║
                ██╔══██║██║   ██║██║╚██╔╝██║██╔══╝      ╚════██║██║     ██╔══██╗██╔══╝  ██╔══╝  ██║╚██╗██║
                ██║  ██║╚██████╔╝██║ ╚═╝ ██║███████╗    ███████║╚██████╗██║  ██║███████╗███████╗██║ ╚████║
                ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝    ╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝  ╚═══╝
                """);
        String message = Colors.CHAMPAGNE_SILVER.colorize("""
              
              
              To get started please select one of the following options below
              
              D) Add Deposit
              P) Make Payment (Debit)
              L) Ledger
              X) Exit
              
              Select option:\s""");

        System.out.print(message);
        String userChoice = scanner.nextLine().strip();

        // Continue looping until the user chooses to exit
        while (!userChoice.equalsIgnoreCase("X")) {
            switch (userChoice.toUpperCase()) {
                case "D" -> {
                    TransactionForm.getTransactionDetails("Deposit");
                }

                case "P" -> {
                    TransactionForm.getTransactionDetails("Payment");
                }

                case "L" -> {
                    LedgerScreen ledgerScreen = new LedgerScreen();
                    ledgerScreen.displayOptions();
                }

                default -> System.out.println(Colors.CRIMSON.colorize("\n\n===== Invalid option. Please try again. ====="));
            }

            // Re-display menu after each action
            System.out.print(message);
            userChoice = scanner.nextLine().strip();
            System.out.println("\n");
        }

        // Exits application
        System.out.println("\n");
        stop();
    }
}