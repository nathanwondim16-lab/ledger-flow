package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class TransactionForm {
    private static final Scanner scanner = new Scanner(System.in);


    protected static void getTransactionDetails() {
        LocalDate transactionDate = askForDate();
        LocalTime transactionTime = askForTime();

        System.out.print(Colors.TRON.printWithColor("\n===== Enter description of the transaction: "));
        String transactionDescription = scanner.nextLine().strip();

        System.out.print(Colors.TRON.printWithColor("\n===== Enter name of the vendor: "));
        String vendor = scanner.nextLine().strip();

        double transactionAmount = askForAmount();

        Transaction submitTransaction = new Transaction(transactionDate, transactionTime, transactionDescription, vendor, transactionAmount);
        boolean transactionConfirmed = requestTransactionConfirmation(submitTransaction);

        if(transactionConfirmed) {
            System.out.println(Colors.GREEN.printWithColor("\n===== TRANSACTION RECORDED ✅\n"));
            TransactionProcessor.recordTransactions(submitTransaction);
        } else {
            System.out.println(Colors.CRIMSON.printWithColor("\n===== TRANSACTION DELETED ❌ =====\n"));
        }
    }


    // Method displays confirmation summary prompting user to confirm that they want to submit the transaction
    private static boolean requestTransactionConfirmation(Transaction transaction) {
        System.out.println(Colors.PURPLE.printWithColor("\n\n===== PLEASE CONFIRM THIS TRANSACTION ====="));
        System.out.printf(Colors.MUTED_GRAY.printWithColor("""
                
                DATE: %-20s
                TIME: %-20s
                DESCRIPTION: %-20s
                VENDOR: %-20s
                AMOUNT: $%-20.2f
                """),transaction.getTransactionDate().format(DateTimeFormats.DATE),
                     transaction.getTransactionTime().format(DateTimeFormats.TIME),
                     transaction.getTransactionDescription(),
                     transaction.getVendor(),
                     transaction.getTransactionAmount());

        System.out.print(Colors.PURPLE.printWithColor("\nSave transaction? (Y/N) "));
        return scanner.nextLine().strip().equalsIgnoreCase("Y");
    }


    private static LocalDate askForDate() {
        while(true) {
            System.out.print(Colors.TRON.printWithColor("\n===== ENTER TRANSACTION DATE (e.g., 04/27/26): "));
            String date = scanner.nextLine().strip();
            try {
                return LocalDate.parse(date, DateTimeFormats.DATE);
            } catch (Exception e) {
                System.out.println(Colors.CRIMSON.printWithColor("\n===== INVALID DATE FORMAT. PLEASE TRY AGAIN ====="));
            }
        }
    }

    private static LocalTime askForTime() {
        while(true) {
            System.out.print(Colors.TRON.printWithColor("\n===== ENTER TRANSACTION TIME (e.g., 03:15 PM): "));
            String time = scanner.nextLine().strip();
            try {
                return LocalTime.parse(time, DateTimeFormats.TIME);
            } catch (Exception e) {
                System.out.println(Colors.CRIMSON.printWithColor("\n===== INVALID DATE FORMAT. PLEASE TRY AGAIN ====="));
            }
        }
    }

    // Add checker to check once user chooses (P)ayment that they only submit a -$ negative amount of money
    // If they select payment and the amount is positive continue prompting them for the correct negative amount.
    private static double askForAmount() {
        while(true) {
            System.out.print(Colors.TRON.printWithColor("\n===== ENTER TRANSACTION AMOUNT: $"));
            String amount = scanner.nextLine().strip();
            try {
                return Double.parseDouble(amount);
            } catch (Exception e) {
                System.out.println(Colors.CRIMSON.printWithColor("\n===== INVALID AMOUNT (e.g., 123.45) ====="));
            }
        }
    }
}