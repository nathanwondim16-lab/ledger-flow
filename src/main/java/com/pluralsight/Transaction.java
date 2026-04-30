package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


// Consider making this class a record
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
        return transactionDate.format(DateTimeFormats.DATE) + "|" + transactionTime.format(DateTimeFormats.TIME) + "|" + transactionDescription + "|"
                + vendor + "|" + transactionAmount;
    }

    // Consider moving this method outside transaction class
    public String ledgerText(Colors color) {
        return String.format(Colors.TRON.printWithColor("║ %-10s ║ %-10s ║ %-18s ║ %-18s ║"),
                transactionDate.format(DateTimeFormats.DATE), transactionTime.format(DateTimeFormats.TIME), transactionDescription, vendor)
                + String.format(color.printWithColor(" $%-12.2f"), transactionAmount) + String.format(Colors.TRON.printWithColor("║\n")
                + String.format(Colors.TRON.printWithColor("╢════════════║════════════║════════════════════║════════════════════║══════════════╢")));

    }
}
