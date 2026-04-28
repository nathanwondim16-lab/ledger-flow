package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class TransactionProcessor {
    //private String fileName;

    public void recordTransactions(Transaction transaction) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Transactions.csv", true))) {
            writer.write(transaction.toString() + "\n");
        } catch (Exception e) {
            System.out.println("Something went wrong when writing to the file " + e.getMessage());
        }
    }
}
