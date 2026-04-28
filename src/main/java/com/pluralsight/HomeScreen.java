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
               To get started please select one of the following options below
               
               D) Add Deposit
               P) Make Payemnt (Debit)
               L) Ledger
               X) Exit
               
               Select option:\s""";
        System.out.print(message);
        String userChoice = scanner.next().strip();
        System.out.print("\r ");
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

    private Transaction getTransactionDetails() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.print("Enter in the transaction date using this format (MM/DD/YYYY): ");
        LocalDate transactionDate = LocalDate.parse(scanner.nextLine().strip(),dateFormat);

        System.out.print("Enter in the transaction time using this format (HH:mm:ss): ");
        LocalTime transactionTime = LocalTime.parse(scanner.nextLine().strip());

        System.out.print("Enter in the description of the transaction: ");
        String transactionDescription = scanner.nextLine().strip();

        System.out.print("Enter in the name of the vendor: ");
        String vendor = scanner.nextLine().strip();

        System.out.print("Enter in the transaction amount: $");
        double transactionAmount = Double.parseDouble(scanner.nextLine());

        System.out.println("\nTransaction recorded \uD83E\uDDFE✅\n");

        return new Transaction(transactionDate, transactionTime,
                transactionDescription, vendor, transactionAmount);
    }
}
