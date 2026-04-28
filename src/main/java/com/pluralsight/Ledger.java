package com.pluralsight;

public class Ledger extends TransactionProcessor {

    // Since logic is being repeated in both methods create one method that accepts a param based on the user's display choice and display the ledger based on the param
    protected void displayLedger() {
        readTransactions();
        topLedgerCover();
        for(Transaction transaction : transactionList) {
            System.out.println(transaction.ledgerText());
        }
        bottomLedgerCover();
    }

    public void displayDeposits() {
        readTransactions();
        topLedgerCover();
        for(Transaction transaction : transactionList) {
            if (transaction.getTransactionAmount() > 0) {
                System.out.println(transaction.ledgerText());
            }
        }
        bottomLedgerCover();
    }

    public void displayPayments() {
        readTransactions();
        topLedgerCover();
        for(Transaction transaction : transactionList) {
            if (transaction.getTransactionAmount() < 0) {
                System.out.println(transaction.ledgerText());
            }
        }
        bottomLedgerCover();
    }

    public void topLedgerCover() {
        System.out.println(Colors.TRON.printWithColor("╔════════════╦══════════╦════════════════════╦════════════════════╦══════════════╗"));
        System.out.println(Colors.TRON.printWithColor("║ Date       ║ Time     ║ Description        ║ Vendor             ║ Amount       ║"));
        System.out.println(Colors.TRON.printWithColor("╠════════════╬══════════╬════════════════════╬════════════════════╬══════════════╣"));
    }

    public void bottomLedgerCover() {
        System.out.println(Colors.TRON.printWithColor("╚════════════╩══════════╩════════════════════╩════════════════════╩══════════════╝"));
    }

}
