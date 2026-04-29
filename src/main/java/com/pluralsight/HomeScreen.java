package com.pluralsight;

import java.util.Scanner;

public class HomeScreen extends ScreenManager {
    private final Scanner scanner = new Scanner(System.in);


    public void displayOptions() {
        welcomeMessage();
        String message = """
                \nTo get started please select one of the following options below
                
                D) Add Deposit
                P) Make Payment (Debit)
                L) Ledger
                X) Exit
                
                Select option:\s""";
        System.out.print(message);
        String userChoice = scanner.nextLine().strip();
        TransactionProcessor processor = new TransactionProcessor();
        TransactionForm transactionForm = new TransactionForm();
        while (!userChoice.equalsIgnoreCase("X")) {
            switch (userChoice.toUpperCase()) {
                case "D", "P" -> {
                    processor.recordTransactions(transactionForm.getTransactionDetails());
                }

                case "L" -> {
                    LedgerScreen ledgerScreen = new LedgerScreen();
                    ledgerScreen.displayOptions();
                }

                default -> System.out.println("Invalid option. Please try again.\n");
            }
            System.out.print(message);
            userChoice = scanner.nextLine().strip();
        }
        stop();
    }
}