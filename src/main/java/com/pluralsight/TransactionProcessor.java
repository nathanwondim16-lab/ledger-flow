package com.pluralsight;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading and writing transactions from the Transactions.csv file.
 *
 * This class is responsible for:
 * - Saving new transactions to the CSV file
 * - Loading saved transactions into memory
 * - Keeping the newest transactions at the top of the file
 */
public class TransactionProcessor {

    protected static List<Transaction> transactionList = new ArrayList<>();

    /**
     * Writes a new transaction to Transactions.csv file.
     *
     * New transactions are inserted directly below the header so the newest
     * transactions appear first in the file.
     *
     * If the file already exists, the old lines are temporarily stored,
     * then written back after the new transaction.
     *
     * @param transaction the transaction to save
     */
    protected static void recordTransactions(Transaction transaction) {
        Path path = Path.of("Transactions.csv");

        // Temporarily stores existing file lines so they can be rewritten after the new transaction.
        List<String> oldTransactions = new ArrayList<>();


        // Load existing file lines if the CSV already exists.
        try {
            if(Files.exists(path)) {
                oldTransactions = Files.readAllLines(path);
            }
        } catch (IOException e) {
            System.err.println("Failed to process file. " + e.getMessage());
        }


        // Rewrite the file with the header, newest transaction, then previous transactions.
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Transactions.csv"))) {

            // Write header if the file is new or empty.
            if(oldTransactions.isEmpty()) {
                writer.write("date|time|description|vendor|amount\n");
            } else {
                // Preserves existing header if file exists.
                writer.write(oldTransactions.getFirst() + "\n");
            }

            // Insert the newest transaction directly under the header.
            writer.write(transaction.toString() + "\n");

            // Add the previous transactions back after the newest one.
            for(int i = 1; i < oldTransactions.size(); i++) {
                writer.write(oldTransactions.get(i) + "\n");
            }

        } catch (Exception e) {
            System.err.println("Something went wrong when writing to the file " + e.getMessage());
        }
    }

    /**
     * Reads transactions from Transactions.csv and reloads them into transactionList.
     *
     * The list is cleared first to prevent duplicate transactions from being added each
     * time this method is called.
     */
    protected static void readTransactions() {
        transactionList.clear(); // Prevents duplicates when reloading the file.

        try(BufferedReader reader = new BufferedReader(new FileReader("Transactions.csv"))) {

            reader.readLine(); // Skips CSV header row: date|time|description|vendor|amount

            String line;

            while((line = reader.readLine()) != null) {

                // Parse each line into its individual fields using '|' as delimiter
                String[] columns = line.split("\\|");

                // Expected transaction format: date|time|description|vendor|amount|
                // Assumes each line has exactly 5 columns.

                // Convert string values into proper data types
                LocalDate transactionDate = LocalDate.parse(columns[0], DateTimeFormats.DATE);
                LocalTime transactionTime = LocalTime.parse(columns[1], DateTimeFormats.TIME);
                String transactionDescription = columns[2];
                String vendor = columns[3];
                double transactionAmount = Double.parseDouble(columns[4]);

                // Creates Transaction object and stores it in transactionList
                transactionList.add(new Transaction(transactionDate, transactionTime, transactionDescription,
                        vendor, transactionAmount));
            }

        } catch (Exception e) {
            System.err.println("Failed to read Transactions.csv: " + e.getMessage());
        }
    } 
}