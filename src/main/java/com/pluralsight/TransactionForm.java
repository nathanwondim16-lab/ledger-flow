package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class TransactionForm {
    private final Scanner scanner = new Scanner(System.in);


    protected Transaction getTransactionDetails() {
        LocalDate transactionDate = askForDate();
        LocalTime transactionTime = askForTime();

        System.out.print("Enter description of the transaction: ");
        String transactionDescription = scanner.nextLine().strip();

        System.out.print("Enter name of the vendor: ");
        String vendor = scanner.nextLine().strip();

        double transactionAmount = askForAmount();


        System.out.println("\nTransaction recorded \uD83E\uDDFE✅\n");

        return new Transaction(transactionDate, transactionTime, transactionDescription, vendor, transactionAmount);
    }

    private LocalDate askForDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        while(true) {
            System.out.print("Enter transaction date (e.g., 04/27/26): ");
            String date = scanner.nextLine().strip();
            try {
                return LocalDate.parse(date, dateFormatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }

    private LocalTime askForTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        while(true) {
            System.out.print("Enter transaction time (e.g., 03:15 PM): ");
            String time = scanner.nextLine().strip();
            try {
                return LocalTime.parse(time, timeFormatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }

    private double askForAmount() {
        while(true) {
            System.out.print("Enter transaction amount: $");
            String amount = scanner.nextLine().strip();
            try {
                return Double.parseDouble(amount);
            } catch (Exception e) {
                System.out.println("Invalid amount (e.g., 123.45)");
            }
        }
    }
}
