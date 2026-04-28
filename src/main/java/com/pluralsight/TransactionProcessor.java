package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionProcessor {
    protected List<Transaction> transactionList = new ArrayList<>();

    protected void recordTransactions(Transaction transaction) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Transactions.csv", true))) {
            writer.write(transaction.toString() + "\n");
        } catch (Exception e) {
            System.out.println("Something went wrong when writing to the file " + e.getMessage());
        }
    }

    protected void readTransactions() {
        transactionList.clear();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        try(BufferedReader reader = new BufferedReader(new FileReader("Transactions.csv"))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] columns = line.split("\\|");
                LocalDate transactionDate = LocalDate.parse(columns[0], dateFormat);
                LocalTime transactionTime = LocalTime.parse(columns[1], timeFormat);
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
