package com.pluralsight;

import java.util.Scanner;

public class HomeScreen extends ScreenManager {
    private final Scanner scanner = new Scanner(System.in);


    public void displayOptions() {
        welcomeMessage();

        String message = Colors.CHAMPAGNE_SILVER.printWithColor("""
              
              
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
                case "D", "P" -> {
                    TransactionForm.getTransactionDetails();
                }

                case "L" -> {

                    LedgerScreen ledgerScreen = new LedgerScreen(); // Get rid of this and make LedgerScreen class static and displayOptions() static
                    ledgerScreen.displayOptions();
                }

                default -> System.out.println(Colors.CRIMSON.printWithColor("\n\n===== Invalid option. Please try again. ====="));
            }
            System.out.print(message);
            userChoice = scanner.nextLine().strip();
            System.out.println("\n");
        }
        stop();
    }
}