package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Provides custom search functionality for ledger transactions.
 *
 * This class allows the user to filter transactions by:
 * - Start date
 * - End date
 * - Description
 * - Vendor
 * - Amount
 *
 * All search fields are optional. Blank input is treated as no filter.
 */
public class CustomSearch extends TransactionProcessor {

    private static final Scanner scanner = new Scanner(System.in);

    private static LocalDate startDate;
    private static LocalDate endDate;
    private static String description;
    private static String vendor;
    private static Double amount;

    /**
     * Prompts the user for optional search values, filters the transaction list,
     * and displays teh matching transactions.
     *
     * A blank search field is ignored, meaning that field will not limit the results.
     */
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

                // Include transactions on or after the start date, if provided.
                .filter(t -> startDate == null || !t.transactionDate().isBefore(startDate))

                // Include transactions on or before the end date, if provided.
                .filter(t -> endDate == null || !t.transactionDate().isAfter(endDate))

                // Match description partially and case-insensitively, if provided.
                .filter(t -> description == null || description.isBlank() ||
                        t.transactionDescription().toLowerCase().contains(description.toLowerCase()))

                // Match vendor partially and case-insensitively, if provided.
                .filter(t -> vendor == null || vendor.isBlank() ||
                        t.vendor().toLowerCase().contains(vendor.toLowerCase()))

                // Compare doubles using a small tolerance instead of exact equality.
                .filter(t -> amount == null || Math.abs(t.transactionAmount() - amount) < 0.01)
                .toList();

        LedgerFormatting.calculateWidth(transactions);


    }

    /**
     * Prompts the user for an optional start date.
     *
     * If the user leaves the input blank, no start-date filter is applied.
     * The method continues prompting until the user enters a valid date or leaves it blank.
     *
     * @return the parsed start date, or null if no start date is provided
     */
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

    /**
     * Prompts the user for an optional end date.
     *
     * If the user leaves the input blank, no end-date filter is applied.
     * The method continues prompting until the user enters a valid date or leaves it blank
     *
     * @return the parsed end date, or null if no end date is provided
     */
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

    /**
     * Prompts the user for an optional transaction amount.
     *
     * If the user leaves the input blank, no amount filter is applied.
     * The method continues prompting until the user enters a valid decimal amount
     * or leaves it blank.
     *
     * @return the parsed transaction amount, or null if no amount is provided.
     */
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