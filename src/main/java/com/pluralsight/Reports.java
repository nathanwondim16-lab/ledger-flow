package com.pluralsight;

import java.time.LocalDate;
import java.util.Scanner;

public class Reports extends TransactionProcessor {

    public void reportsScreen(int numberChoice) {
        readTransactions(); // Calling readtransactions() method to restore the transactionList with the transactions
        switch(numberChoice) {

            // Displays transactions from the beginning of the current month till now.
            case 1 -> {
                LocalDate monthToDate = LocalDate.now().withDayOfMonth(1);
                transactionList.stream()
                        .filter(transaction -> !transaction.getTransactionDate().isBefore(monthToDate) &&
                                !transaction.getTransactionDate().isAfter(LocalDate.now()))
                        .forEach(transaction -> System.out.println(transaction.ledgerText()));
            }

            // Displays transactions in the previous month
            case 2 -> {
                LocalDate previousMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                transactionList.stream()
                        .filter(transaction -> transaction.getTransactionDate().getMonthValue() == previousMonth.getMonthValue())
                        .forEach(transaction -> System.out.println(transaction.ledgerText()));
            }

            // Displays transactions from the beginning of the year till now.
            case 3 -> {
                LocalDate yearStart = LocalDate.now().withDayOfYear(1);
                transactionList.stream()
                        .filter(transaction -> !transaction.getTransactionDate().isBefore(yearStart)
                                && transaction.getTransactionDate().isBefore(LocalDate.now()))
                        .forEach(transaction -> System.out.println(transaction.ledgerText()));
            }

            // Displays transactions from the previous year
            case 4 -> {
                LocalDate lastYear = LocalDate.now().minusYears(1);
                transactionList.stream()
                        .filter(transaction -> transaction.getTransactionDate().getYear() == lastYear.getYear())
                        .forEach(transaction -> System.out.println(transaction.ledgerText()));
            }

            // Displays transactions from the vendor specified by the user.
            case 5 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter in the vendor's name: ");
                String vendor = scanner.nextLine().strip();
                System.out.println("Size: " + transactionList.size());
                transactionList.stream()
                        .filter(transaction -> transaction.getVendor().equalsIgnoreCase(vendor))
                        .forEach(transaction -> System.out.println(transaction.ledgerText()));
            }

            case 6 -> {
                // Insert custom search logic here
            }
        }
    }
}
