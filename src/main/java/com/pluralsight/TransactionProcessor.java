package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionProcessor {
    protected List<Transaction> transactionList = new ArrayList<>();

    protected static void recordTransactions(Transaction transaction) {
        Path path = Path.of("Transactions.csv");
        boolean doesFileExist = Files.exists(path);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Transactions.csv", true))) {
            if(!doesFileExist) {
                writer.write("date|time|description|vendor|amount\n");
            }
            writer.write(transaction.toString() + "\n");
        } catch (Exception e) {
            System.out.println("Something went wrong when writing to the file " + e.getMessage());
        }
    }

    protected void readTransactions() {
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
