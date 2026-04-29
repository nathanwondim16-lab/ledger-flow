package com.pluralsight;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionProcessor {
    protected static List<Transaction> transactionList = new ArrayList<>();

    protected static void recordTransactions(Transaction transaction) {
        Path path = Path.of("Transactions.csv");

        List<String> oldTransactions = new ArrayList<>();

        try {
            if(Files.exists(path)) {
                oldTransactions = Files.readAllLines(path);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }


        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Transactions.csv"))) {

            if(oldTransactions.isEmpty()) {
                writer.write("date|time|description|vendor|amount\n");
            } else {
                writer.write(oldTransactions.getFirst() + "\n");
            }

            writer.write(transaction.toString() + "\n");

            // Restoring file with old transactions
            for(int i = 1; i < oldTransactions.size(); i++) {
                writer.write(oldTransactions.get(i) + "\n");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong when writing to the file " + e.getMessage());
        }
    }

    protected static void readTransactions() {
        transactionList.clear();

        try(BufferedReader reader = new BufferedReader(new FileReader("Transactions.csv"))) {
            reader.readLine(); // Skips header
            String line;
            while((line = reader.readLine()) != null) {
                String[] columns = line.split("\\|");
                LocalDate transactionDate = LocalDate.parse(columns[0], DateTimeFormats.DATE);
                LocalTime transactionTime = LocalTime.parse(columns[1], DateTimeFormats.TIME);
                String transactionDescription = columns[2];
                String vendor = columns[3];
                double transactionAmount = Double.parseDouble(columns[4]);
                transactionList.add(new Transaction(transactionDate, transactionTime, transactionDescription,
                        vendor, transactionAmount));
            }

        } catch (Exception e) {
            System.out.println("Something went wrong when reading the file " + e.getMessage());
        }
    }
}
