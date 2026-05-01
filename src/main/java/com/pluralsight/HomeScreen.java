package com.pluralsight;

import java.util.Scanner;

public class HomeScreen extends ScreenManager {
    private final Scanner scanner = new Scanner(System.in);


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
            System.out.print(message);
            userChoice = scanner.nextLine().strip();
            System.out.println("\n");
        }
        stop();
    }
}