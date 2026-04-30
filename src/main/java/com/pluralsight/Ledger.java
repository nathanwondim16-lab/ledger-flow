package com.pluralsight;

import java.awt.*;
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
        ledgerHeader();

        for(Transaction transaction : transactionList) {
            if(filter.test(transaction)) {

                Colors color = transaction.getTransactionAmount() > 0 ? Colors.GREEN : Colors.CRIMSON;

                System.out.println(transaction.ledgerText(color));
            }
        }

        bottomLedgerCover();
    }

    public static void ledgerHeader() {
        System.out.println(Colors.TRON.printWithColor("╔════════════╦════════════╦════════════════════╦════════════════════╦══════════════╗"));
        System.out.println(Colors.TRON.printWithColor("║ Date       ║ Time       ║ Description        ║ Vendor             ║ Amount       ║"));
        System.out.println(Colors.TRON.printWithColor("╠════════════╬════════════╬════════════════════╬════════════════════╬══════════════╣"));
    }

    public static void bottomLedgerCover() {
        System.out.println(Colors.TRON.printWithColor("╚════════════╩════════════╩════════════════════╩════════════════════╩══════════════╝"));
    }
}
