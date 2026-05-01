package com.pluralsight;

import java.util.List;
import java.util.function.Predicate;

/**
 * Provides ledger display options for transactions.
 *
 * This class allows the user to view:
 * - All transactions
 * - Deposits only
 * - Payments only
 *
 * Filtering is handled with Predicate objects so the shared formatting logic
 * can be reused across multiple display methods.
 */
public class Ledger extends TransactionProcessor {

    // Displays all transactions in the ledger
    protected static void displayLedger() {
        formatTransactions(transaction -> true);
    }

    // Displays only deposits
    public static void displayDeposits() {
        formatTransactions(transaction -> transaction.transactionAmount() > 0);
    }

    // Displays only payments
    public static void displayPayments() {
        formatTransactions(transaction -> transaction.transactionAmount() < 0);
    }

    /**
     * Reloads transactions, applies the requested filter, and displays the matching
     * results using teh ledger formatter.
     *
     * @param filter the condition used to select which transactions to display
     */
    private static void formatTransactions(Predicate<Transaction> filter) {
        readTransactions();

        List<Transaction> transactions = transactionList.stream()
                .filter(filter)
                .toList();

        LedgerFormatting.calculateWidth(transactions);
    }
}
