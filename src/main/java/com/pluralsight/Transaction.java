package com.pluralsight;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final LocalDate transactionDate;
    private final LocalTime transactionTime;
    private final String transactionDescription;
    private final String vendor;
    private final double transactionAmount;

    public Transaction(LocalDate transactionDate, LocalTime transactionTime
                       ,String transactionDescription, String vendor, double transactionAmount) {

        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.transactionDescription = transactionDescription;
        this.vendor = vendor;
        this.transactionAmount = transactionAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public String getVendor() {
        return vendor;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return transactionDate.format(dateFormat) + "|" + transactionTime + "|" + transactionDescription + "|"
                + vendor + "|" + transactionAmount;
    }

    public String ledgerText() {
        return String.format(Colors.TRON.printWithColor("║ %-10s ║ %-8s ║ %-18s ║ %-18s ║ $%-12.2f║"),
                transactionDate, transactionTime, transactionDescription, vendor, transactionAmount);
    }
}
