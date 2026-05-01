package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Handles all user interaction for creating a Transaction.
 *
 * This class is responsible for:
 * - Prompting the user for transaction details.
 * - Validating input formats
 * - Displaying a confirmation screen
 * - Submitting the transaction if confirmed
 */
public class TransactionForm {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Guides the user through the full transaction entry process
     *
     * Prompts for all required fields, constructs a Transaction object,
     * and asks the user to confirm before saving.
     *
     * If confirmed, the transaction is written to storage
     *
     * @param transactionType Is the transaction a deposit or payment?
     */
    protected static void getTransactionDetails(String transactionType) {
        LocalDate transactionDate = askForDate();
        LocalTime transactionTime = askForTime();

        System.out.print(Colors.TRON.colorize("\n===== Enter description of the transaction: "));
        String transactionDescription = scanner.nextLine().strip();

        System.out.print(Colors.TRON.colorize("\n===== Enter name of the vendor: "));
        String vendor = scanner.nextLine().strip();

        double transactionAmount = askForAmount(transactionType);

        // Creates transaction record from user input.
        Transaction submitTransaction = new Transaction(transactionDate, transactionTime, transactionDescription, vendor, transactionAmount);
        boolean transactionConfirmed = requestTransactionConfirmation(submitTransaction);

        if(transactionConfirmed) {
            System.out.println(Colors.GREEN.colorize("\n\n===== TRANSACTION RECORDED ✅\n"));
            TransactionProcessor.recordTransactions(submitTransaction);
        } else {
            System.out.println(Colors.CRIMSON.colorize("\n===== TRANSACTION DELETED ❌ =====\n"));
        }
    }

    /**
     * Displays a formatted summary of the transaction and prompts the user
     * to confirm whether it should be saved.
     *
     * @param transaction the transaction to review
     * @return true if the user confirms "Y", false otherwise
     */
    private static boolean requestTransactionConfirmation(Transaction transaction) {
        System.out.println(Colors.PURPLE.colorize("\n\n===== PLEASE CONFIRM THIS TRANSACTION ====="));

        System.out.printf(Colors.MUTED_GRAY.colorize("""
                
                DATE: %-20s
                TIME: %-20s
                DESCRIPTION: %-20s
                VENDOR: %-20s
                AMOUNT: $%-20.2f
                """),transaction.transactionDate().format(DateTimeFormats.DATE),
                     transaction.transactionTime().format(DateTimeFormats.TIME),
                     transaction.transactionDescription(),
                     transaction.vendor(),
                     transaction.transactionAmount());

        System.out.print(Colors.PURPLE.colorize("\nSave transaction? (Y/N) "));

        // Only "Y" confirms the transaction; all other input cancels.
        return scanner.nextLine().strip().equalsIgnoreCase("Y");
    }

    /**
     * Prompts the user to enter a valid transaction date.
     *
     * Continues prompting until a correctly formatted date is entered.
     *
     * @return a valid LocalDate parsed using DateTimeFormats.DATE
     */
    private static LocalDate askForDate() {
        while(true) {
            System.out.print(Colors.TRON.colorize("\n===== ENTER TRANSACTION DATE (e.g., 04/27/26): "));
            String date = scanner.nextLine().strip();

            try {
                return LocalDate.parse(date, DateTimeFormats.DATE);
            } catch (Exception e) {
                System.out.println(Colors.CRIMSON.colorize("\n===== INVALID DATE FORMAT. PLEASE TRY AGAIN ====="));
            }
        }
    }

    /**
     * Prompts the user to enter a valid transaction time.
     *
     * Continues prompting until a correctly formatted time is entered.
     *
     * @return a valid LocalTime parsed using DateTimeFormats.TIME
     */
    private static LocalTime askForTime() {
        while(true) {
            System.out.print(Colors.TRON.colorize("\n===== ENTER TRANSACTION TIME (e.g., 03:15 PM): "));
            String time = scanner.nextLine().strip();

            try {
                return LocalTime.parse(time, DateTimeFormats.TIME);
            } catch (Exception e) {
                System.out.println(Colors.CRIMSON.colorize("\n===== INVALID TIME FORMAT. PLEASE TRY AGAIN ====="));
            }
        }
    }

    /**
     * Prompts the user to enter a valid transaction amount.
     *
     * Accepts positive or negative decimal values and continues prompting
     * until a valid number is entered.
     *
     * @param transactionType Is the transaction a deposit or payment?
     * @return the parsed transaction amount as a double
     */
    private static double askForAmount(String transactionType) {
        while(true) {
            System.out.print(Colors.TRON.colorize("\n===== ENTER TRANSACTION AMOUNT: $"));
            String amount = scanner.nextLine().strip();

            try {
                if(transactionType.equalsIgnoreCase("Deposit")) {
                    return Math.abs(Double.parseDouble(amount));
                }
                return -Math.abs(Double.parseDouble(amount));
            } catch (Exception e) {
                System.out.println(Colors.CRIMSON.colorize("\n===== INVALID AMOUNT (e.g., 123.45) ====="));
            }
        }
    }
}