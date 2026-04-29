package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Reports extends TransactionProcessor {

    public void reportsScreen(int numberChoice) {
        readTransactions(); // Calling readtransactions() method to restore the transactionList with the transactions

        switch(numberChoice) {

            // Displays transactions from the beginning of the current month till now.
            case 1 -> {
                LocalDate monthToDate = LocalDate.now().withDayOfMonth(1);


                List<Transaction> transactions = transactionList.stream()
                        .filter(transaction -> !transaction.getTransactionDate().isBefore(monthToDate) &&
                                !transaction.getTransactionDate().isAfter(LocalDate.now()))
                        .toList();

                displayFilteredTransactions(transactions);
            }

            // Displays transactions in the previous month
            case 2 -> {
                LocalDate previousMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);

                List<Transaction> transactions = transactionList.stream()
                        .filter(transaction -> transaction.getTransactionDate().getMonthValue() == previousMonth.getMonthValue())
                        .toList();

                displayFilteredTransactions(transactions);
            }

            // Displays transactions from the beginning of the year till now.
            case 3 -> {
                LocalDate yearStart = LocalDate.now().withDayOfYear(1);

                List<Transaction> transactions = transactionList.stream()
                        .filter(transaction -> !transaction.getTransactionDate().isBefore(yearStart)
                                && transaction.getTransactionDate().isBefore(LocalDate.now()))
                        .toList();

                displayFilteredTransactions(transactions);
            }

            // Displays transactions from the previous year
            case 4 -> {
                LocalDate lastYear = LocalDate.now().minusYears(1);

                List<Transaction> transactions =  transactionList.stream()
                        .filter(transaction -> transaction.getTransactionDate().getYear() == lastYear.getYear())
                        .toList();

                displayFilteredTransactions(transactions);
            }

            // Displays transactions from the vendor specified by the user.
            case 5 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.print("\nEnter in the vendor's name: ");
                String vendor = scanner.nextLine().strip();

                List<Transaction> transactions = transactionList.stream()
                        .filter(transaction -> transaction.getVendor().equalsIgnoreCase(vendor))
                        .toList();

                displayFilteredTransactions(transactions);
            }

            case 6 -> {
                // Insert custom search logic here
            }
        }
    }

    private void displayFilteredTransactions(List<Transaction> filteredTransactions) {

        if(filteredTransactions.isEmpty()) {
            System.out.println(Colors.CRIMSON.printWithColor("\n===== NO TRANSACTIONS WERE FOUND. ====="));
        } else {
            Ledger.ledgerHeader();

            filteredTransactions.forEach(transaction -> System.out.println(transaction.ledgerText()));

            Ledger.bottomLedgerCover();
        }
    }
}
