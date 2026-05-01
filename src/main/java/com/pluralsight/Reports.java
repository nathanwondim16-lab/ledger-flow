package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Handles preset transaction reports for the ledger.
 *
 * This class allows users to view filtered transaction reports, including:
 * - Month to Date
 * - Previous month
 * - Year to date
 * - Previous year
 * - Vendor search
 * - Custom search
 */
public class Reports extends TransactionProcessor {

    /**
     * Displays the selected report based on the user's menu choice.
     *
     * Reloads transactions from the CSV file before filtering to make sure
     * the reports use the most up-to-date transaction data.
     *
     * @param numberChoice the report option selected by the user
     */
    protected static void reportsOptions(int numberChoice) {
        readTransactions();

        switch(numberChoice) {

            // Displays transactions from the beginning of the current month till now.
            case 1 -> {
                LocalDate monthToDate = LocalDate.now().withDayOfMonth(1);


                List<Transaction> transactions = transactionList.stream()
                        .filter(transaction -> !transaction.transactionDate().isBefore(monthToDate) &&
                                !transaction.transactionDate().isAfter(LocalDate.now()))
                        .toList();

                displayFilteredTransactions(transactions);
            }

            // Displays transactions in the previous month
            case 2 -> {
                LocalDate previousMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);

                List<Transaction> transactions = transactionList.stream()
                        .filter(transaction -> transaction.transactionDate().getMonthValue() == previousMonth.getMonthValue()
                        && transaction.transactionDate().getYear() == previousMonth.getYear())
                        .toList();

                displayFilteredTransactions(transactions);
            }

            // Displays transactions from the beginning of the year till now.
            case 3 -> {
                LocalDate yearStart = LocalDate.now().withDayOfYear(1);

                List<Transaction> transactions = transactionList.stream()
                        .filter(transaction -> !transaction.transactionDate().isBefore(yearStart)
                                && !transaction.transactionDate().isAfter(LocalDate.now()))
                        .toList();

                displayFilteredTransactions(transactions);
            }

            // Displays transactions from the previous year
            case 4 -> {
                LocalDate lastYear = LocalDate.now().minusYears(1);

                List<Transaction> transactions =  transactionList.stream()
                        .filter(transaction -> transaction.transactionDate().getYear() == lastYear.getYear())
                        .toList();

                displayFilteredTransactions(transactions);
            }

            // Displays transactions from the vendor specified by the user.
            case 5 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.print("\nEnter in the vendor's name: ");
                String vendor = scanner.nextLine().strip();

                List<Transaction> transactions = transactionList.stream()
                        .filter(transaction -> transaction.vendor().equalsIgnoreCase(vendor))
                        .toList();

                displayFilteredTransactions(transactions);
            }

            case 0 -> {
                LedgerScreen ledgerScreen = new LedgerScreen();
                ledgerScreen.displayOptions();
            }

            case 6 -> {
                CustomSearch.filterTransactions();
            }

            default -> System.out.println(Colors.CRIMSON.colorize("\n===== INVALID OPTION. PLEASE CHOOSE A VALID OPTION ====="));
        }
    }

    /**
     * Displays filtered transactions if any exist.
     *
     * If no transactions match the selected report, an error message is shown instead.
     *
     * @param filteredTransactions the transactions that matched teh report filter
     */
    private static void displayFilteredTransactions(List<Transaction> filteredTransactions) {

        if(filteredTransactions.isEmpty()) {
            System.out.println(Colors.CRIMSON.colorize("\n===== NO TRANSACTIONS WERE FOUND. ====="));
        } else {
            LedgerFormatting.calculateWidth(filteredTransactions);
        }
    }
}