package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class TransactionForm {
    private static final Scanner scanner = new Scanner(System.in);


    protected static void getTransactionDetails() {
        LocalDate transactionDate = askForDate();
        LocalTime transactionTime = askForTime();

        System.out.print("\nEnter description of the transaction: ");
        String transactionDescription = scanner.nextLine().strip();

        System.out.print("\nEnter name of the vendor: ");
        String vendor = scanner.nextLine().strip();

        double transactionAmount = askForAmount();

        Transaction submitTransaction = new Transaction(transactionDate, transactionTime, transactionDescription, vendor, transactionAmount);
        boolean transactionConfirmed = requestTransactionConfirmation(submitTransaction);

        if(transactionConfirmed) {
            System.out.println("\nTransaction recorded \uD83E\uDDFE✅\n");
            TransactionProcessor.recordTransactions(submitTransaction);
        } else {
            System.out.println("\nTransaction deleted ❌");
        }
    }


    // Method displays confirmation summary prompting user to confirm that they want to submit the transaction
    private static boolean requestTransactionConfirmation(Transaction transaction) {
        System.out.println("\n\nPlease confirm this transaction");

        System.out.printf("Date: %-20s\n", transaction.getTransactionDate().format(DateTimeFormats.DATE));
        System.out.printf("Time: %-20s\n", transaction.getTransactionTime().format(DateTimeFormats.TIME));
        System.out.printf("Description: %-20s\n", transaction.getTransactionDescription());
        System.out.printf("Vendor: %-20s\n", transaction.getVendor());
        System.out.printf("Amount: %-20s\n", transaction.getTransactionAmount());


        System.out.print("\nSave transaction? (Y/N) ");
        return scanner.nextLine().strip().equalsIgnoreCase("Y");
    }


    private static LocalDate askForDate() {
        while(true) {
            System.out.print("\nEnter transaction date (e.g., 04/27/26): ");
            String date = scanner.nextLine().strip();
            try {
                return LocalDate.parse(date, DateTimeFormats.DATE);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }

    private static LocalTime askForTime() {
        while(true) {
            System.out.print("\nEnter transaction time (e.g., 03:15 PM): ");
            String time = scanner.nextLine().strip();
            try {
                return LocalTime.parse(time, DateTimeFormats.TIME);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }

    // Add checker to check once user chooses (P)ayment that they only submit a -$ negative amount of money
    // If they select payment and the amount is positive continue prompting them for the correct negative amount.
    private static double askForAmount() {
        while(true) {
            System.out.print("\nEnter transaction amount: $");
            String amount = scanner.nextLine().strip();
            try {
                return Double.parseDouble(amount);
            } catch (Exception e) {
                System.out.println("Invalid amount (e.g., 123.45)");
            }
        }
    }
}