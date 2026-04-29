package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HomeScreen extends UserInput {
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
        while(!userChoice.equalsIgnoreCase("X")) {
            switch(userChoice.toUpperCase()) {
                case "D", "P" -> {
                    processor.recordTransactions(getTransactionDetails()); // Sends transaction details to the processor to be recorded.
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

    // Consider moving this method to another class
    private Transaction getTransactionDetails() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.print("Enter transaction date (e.g., 04/27/26): ");
        LocalDate transactionDate = LocalDate.parse(scanner.nextLine().strip(), dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        System.out.print("Enter transaction time (e.g., 03:15 PM): ");
        LocalTime transactionTime = LocalTime.parse(scanner.nextLine().strip(), timeFormatter);

        System.out.print("Enter description of the transaction: ");
        String transactionDescription = scanner.nextLine().strip();

        System.out.print("Enter name of the vendor: ");
        String vendor = scanner.nextLine().strip();

        System.out.print("Enter transaction amount: $");
        double transactionAmount = Double.parseDouble(scanner.nextLine());

        System.out.println("\nTransaction recorded \uD83E\uDDFE✅\n");

        return new Transaction(transactionDate, transactionTime,
                transactionDescription, vendor, transactionAmount);
    }
}
