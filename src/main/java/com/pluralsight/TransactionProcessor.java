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
    private final List<Transaction> transactionList = new ArrayList<>();

    public void recordTransactions(Transaction transaction) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Transactions.csv", true))) {
            writer.write(transaction.toString() + "\n");
        } catch (Exception e) {
            System.out.println("Something went wrong when writing to the file " + e.getMessage());
        }
    }

    public void readTransactions() {
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


    // Displays all transactions
    public void displayLedger() {
        readTransactions();
        System.out.println("╔════════════╦══════════╦════════════════════╦════════════════════╦════════════╗");
        for(Transaction transaction : transactionList) {
            System.out.printf("║ %-10s ║ %-8s ║ %-18s ║ %-18s ║ $%-10.2f ║\n",
                    transaction.getTransactionDate(), transaction.getTransactionTime(),
                    transaction.getTransactionDescription(), transaction.getVendor(),
                    transaction.getTransactionAmount());
        }
        System.out.println("║════════════║══════════║════════════════════║══════════════║══════════════║");
    }

    // Display deposits only
    public void displayDeposits() {
        readTransactions();
        System.out.println("╔════════════╦══════════╦════════════════════╦════════════════════╦════════════╗");
        for(Transaction transaction : transactionList) {
            if (transaction.getTransactionAmount() > 0) {
                System.out.printf("║ %-10s ║ %-8s ║ %-18s ║ %-18s ║ $%-10.2f ║\n",
                        transaction.getTransactionDate(), transaction.getTransactionTime(),
                        transaction.getTransactionDescription(), transaction.getVendor(),
                        transaction.getTransactionAmount());
            }
        }
        System.out.println("║════════════║══════════║════════════════════║══════════════║══════════════║");
    }

    // Display payments only
    public void displayPayments() {
        readTransactions();
        System.out.println("╔════════════╦══════════╦════════════════════╦════════════════════╦════════════╗");
        for(Transaction transaction : transactionList) {
            if (transaction.getTransactionAmount() < 0) {
                System.out.printf("║ %-10s ║ %-8s ║ %-18s ║ %-18s ║ $%-10.2f ║\n",
                        transaction.getTransactionDate(), transaction.getTransactionTime(),
                        transaction.getTransactionDescription(), transaction.getVendor(),
                        transaction.getTransactionAmount());
            }
        }
        System.out.println("║════════════║══════════║════════════════════║══════════════║══════════════║");
    }

    public void reports(int numberChoice) {
        switch(numberChoice) {
            case 1 -> {
                LocalDate yearStart = LocalDate.now().withDayOfYear(1);
                transactionList.stream()
                        .filter(transaction -> !transaction.getTransactionDate().isBefore(yearStart)
                                && transaction.getTransactionDate().isBefore(LocalDate.now()))
                        .forEach(System.out::println);
            }

            case 2 -> {
                LocalDate previousMonth = LocalDate.now().minusMonths(1);
                transactionList.stream()
                        .filter(transaction -> transaction.getTransactionDate().getMonthValue() == previousMonth.getMonthValue())
                        .forEach(System.out::println);
            }

            case 3 -> {

            }

            case 4 -> {

            }

            case 5 -> {

            }
        }
    }
}
