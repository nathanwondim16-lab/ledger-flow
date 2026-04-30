package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CustomSearch extends TransactionProcessor {

    private static final Scanner scanner = new Scanner(System.in);
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static String description;
    private static String vendor;
    private static Double amount;

    protected static void filterTransactions() {
        startDate = askForStartDate();
        endDate = askForEndDate();

        System.out.print("\nEnter description (Optional): ");
        description = scanner.nextLine().strip();

        System.out.print("\nEnter vendor (Optional): ");
        vendor = scanner.nextLine().strip();

        amount = askForAmount();

        System.out.println("\nHere are all the transactions based on your search values\n");

        List<Transaction> transactions = transactionList.stream()
                .filter(t -> startDate == null || !t.getTransactionDate().isBefore(startDate))
                .filter(t -> endDate == null || !t.getTransactionDate().isAfter(endDate))
                .filter(t -> description == null || description.isBlank() ||
                        t.getTransactionDescription().toLowerCase().contains(description.toLowerCase()))
                .filter(t -> vendor == null || vendor.isBlank() ||
                        t.getVendor().toLowerCase().contains(vendor.toLowerCase()))
                .filter(t -> amount == null || Math.abs(t.getTransactionAmount() - amount) < 0.01)
                .toList();

        LedgerFormatting.calculateWidth(transactions);


    }


    private static LocalDate askForStartDate() {
        while(true) {
            System.out.print("\nEnter start date (Optional) (e.g., 04/27/26): ");
            String date = scanner.nextLine().strip();

            if(date.isEmpty()) {
                return null;
            }

            try {
                return LocalDate.parse(date, DateTimeFormats.DATE);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }


    // Allow user to skip this field if they want
    private static LocalDate askForEndDate() {
        while(true) {
            System.out.print("\nEnter end date (Optional) (e.g., 04/27/26): ");
            String date = scanner.nextLine().strip();

            if(date.isEmpty()) {
                return null;
            }

            try {
                return LocalDate.parse(date, DateTimeFormats.DATE);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }


    // Add checker to check once user chooses (P)ayment that they only submit a -$ negative amount of money
    // If they select payment and the amount is positive continue prompting them for the correct negative amount.


    // Allow user to skip this field if they want
    private static Double askForAmount() {
        while(true) {
            System.out.print("\nEnter transaction amount (Optional): $");
            String amount = scanner.nextLine().strip();

            if(amount.isEmpty()) {
                return null;
            }

            try {
                return Double.parseDouble(amount);
            } catch (Exception e) {
                System.out.println("Invalid amount (e.g., 123.45)");
            }
        }
    }

}
