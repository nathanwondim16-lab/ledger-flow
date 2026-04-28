package com.pluralsight;

public class Ledger extends TransactionProcessor {

    // Since logic is being repeated in both methods create one method that accepts a param based on the user's display choice and display the ledger based on the param
    protected void displayLedger() {
        readTransactions();
        System.out.println(Colors.TRON.printWithColor("╔════════════╦══════════╦════════════════════╦════════════════════╦══════════════╗"));
        for(Transaction transaction : transactionList) {
            System.out.println(transaction.ledgerText());
        }
        System.out.println(Colors.TRON.printWithColor("║════════════║══════════║════════════════════║════════════════════║══════════════║"));
    }

    public void displayDeposits() {
        readTransactions();
        System.out.println(Colors.TRON.printWithColor("╔════════════╦══════════╦════════════════════╦════════════════════╦══════════════╗"));
        for(Transaction transaction : transactionList) {
            if (transaction.getTransactionAmount() > 0) {
                System.out.println(transaction.ledgerText());
            }
        }
        System.out.println(Colors.TRON.printWithColor("║════════════║══════════║════════════════════║════════════════════║══════════════║"));
    }

    public void displayPayments() {
        readTransactions();
        System.out.println(Colors.TRON.printWithColor("╔════════════╦══════════╦════════════════════╦════════════════════╦══════════════╗"));
        for(Transaction transaction : transactionList) {
            if (transaction.getTransactionAmount() < 0) {
                System.out.println(transaction.ledgerText());
            }
        }
        System.out.println(Colors.TRON.printWithColor("║════════════║══════════║════════════════════║════════════════════║══════════════║"));
    }
}
