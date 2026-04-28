package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class HomeScreen {
    private final Scanner scanner = new Scanner(System.in);


    public void start() {
        System.out.println("Welcome to LedgerFlow\n");
        String message = """
                Select one of the following options below
                
                D) Add Deposit
                P) Make Payemnt (Debit)
                L) Ledger
                X) Exit
                
                Select option:\s""";
        System.out.print(message);
        String userChoice = scanner.nextLine().strip();
        while(!userChoice.equalsIgnoreCase("X")) {
            switch(userChoice.toUpperCase()) {
                case "D", "P" -> {
                    TransactionProcessor processor = new TransactionProcessor();
                    processor.recordTransactions(getTransactionDetails());
                }

                case "L" -> {
                }

                default -> System.out.println("Invalid option. Please try again.\n");
            }
            System.out.print(message);
            userChoice = scanner.nextLine().strip();
        }
    }

    private Transaction getTransactionDetails() {
        System.out.print("Enter in the transaction date using this format (YYYY-MM-DD): ");
        LocalDate transactionDate = LocalDate.parse(scanner.nextLine().strip());

        System.out.print("Enter in the transaction time using this format (HH:mm:ss): ");
        LocalTime transactionTime = LocalTime.parse(scanner.nextLine().strip());

        System.out.print("Enter in the description of the transaction: ");
        String transactionDescription = scanner.nextLine().strip();

        System.out.print("Enter in the name of the vendor: ");
        String vendor = scanner.nextLine().strip();

        System.out.print("Enter in the transaction amount: $");
        double transactionAmount = Double.parseDouble(scanner.nextLine());

        return new Transaction(transactionDate, transactionTime,
                transactionDescription, vendor, transactionAmount);
    }
}
