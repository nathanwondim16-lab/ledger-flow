package com.pluralsight;

import java.util.List;
import java.util.function.Predicate;

public class Ledger extends TransactionProcessor {

    // Since logic is being repeated in both methods create one method that accepts a param based on the user's display choice and display the ledger based on the param
    protected static void displayLedger() {
        formatTransactions(transaction -> true);
    }

    public static void displayDeposits() {
        formatTransactions(transaction -> transaction.getTransactionAmount() > 0);
    }

    public static void displayPayments() {
        formatTransactions(transaction -> transaction.getTransactionAmount() < 0);
    }

    private static void formatTransactions(Predicate<Transaction> filter) {
        readTransactions();

        List<Transaction> transactions = transactionList.stream().filter(filter).toList();

        LedgerFormatting.calculateWidth(transactions);
    }
}
