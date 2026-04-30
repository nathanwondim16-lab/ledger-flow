package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CustomSearch extends TransactionProcessor {

    private static final Scanner scanner = new Scanner(System.in);
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String vendor;
    private double amount;

    private void filterTransactions() {
        startDate = askForStartDate();
        endDate = askForEndDate();
        List<Transaction> filteredList = transactionList.stream()
                .filter(transaction -> !transaction.getTransactionDate().isBefore(startDate))
                .filter(transaction -> endDate == null || !transaction.getTransactionDate().isAfter(endDate))
                .toList();



        System.out.println("Here are all the transactions based on your search values\n");
        System.out.println(filteredList);
    }


    private static LocalDate askForStartDate() {
        while(true) {
            System.out.print("\nEnter start date (e.g., 04/27/26): ");
            String date = scanner.nextLine().strip();
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
    private static double askForAmount() {
        while(true) {
            System.out.print("\nEnter transaction amount (Optional): $");
            String amount = scanner.nextLine().strip();
            try {
                return Double.parseDouble(amount);
            } catch (Exception e) {
                System.out.println("Invalid amount (e.g., 123.45)");
            }
        }
    }

}
