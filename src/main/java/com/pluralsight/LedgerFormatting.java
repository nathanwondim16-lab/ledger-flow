package com.pluralsight;

import java.util.List;

/**
 * Formats and displays transactions in a dynamic ledger table.
 *
 * Column widths are calculated based on the longest value in each column
 * so the table adjusts automatically to the transaction data.
 */

public class LedgerFormatting {

    /**
     * Calculates column widths and prints transactions in a formatted table.
     *
     * Amount values are right-aligned and color-coded:
     * - Green for deposits
     * - Crimson for paynments
     *
     * @param transactionList the transactions to format and display
     */
    protected static void calculateWidth(List<Transaction> transactionList) {
        int dateWidth = "Date".length();
        int timeWidth = "Time".length();
        int descriptionWidth = "Description".length();
        int vendorWidth = "Vendor".length();
        int amountWidth = "Amount".length();

        // Find the widest value in each column.
        for(Transaction transaction : transactionList) {
            dateWidth = Math.max(dateWidth, transaction.transactionDate().format(DateTimeFormats.DATE).length());
            timeWidth = Math.max(timeWidth, transaction.transactionTime().format(DateTimeFormats.TIME).length());
            descriptionWidth = Math.max(descriptionWidth, transaction.transactionDescription().length());
            vendorWidth = Math.max(vendorWidth, transaction.vendor().length());

            String amountText = String.format("$%.2f", transaction.transactionAmount());
            amountWidth = Math.max(amountWidth, amountText.length());
        }

        // Build table borders based on calculated column widths.
        String topBox = "╔" + repeat("═", dateWidth + 2)
                + "╦" + repeat("═", timeWidth + 2)
                + "╦" + repeat("═", descriptionWidth + 2)
                + "╦" + repeat("═", vendorWidth + 2)
                + "╦" + repeat("═", amountWidth + 2) + "╗";

        String middleBox = "╠" + repeat("═", dateWidth + 2)
                + "╬" + repeat("═", timeWidth + 2)
                + "╬" + repeat("═", descriptionWidth + 2)
                + "╬" + repeat("═", vendorWidth+ 2)
                + "╬" + repeat("═", amountWidth + 2) + "╣";

        String bottomBox = "╚" + repeat("═", dateWidth + 2)
                + "╩" + repeat("═", timeWidth + 2)
                + "╩" + repeat("═", descriptionWidth + 2)
                + "╩" + repeat("═", vendorWidth + 2)
                + "╩" + repeat("═", amountWidth + 2) + "╝";

        // Format string used for the table header.
        String headerFormat = "║ %-" + dateWidth + "s "
                + "║ %-" + timeWidth + "s "
                + "║ %-" + descriptionWidth + "s "
                + "║ %-" + vendorWidth + "s "
                + "║ %" + amountWidth + "s ║";

        System.out.println(Colors.TRON.colorize(topBox));
        System.out.println(Colors.TRON.colorize(String.format(headerFormat, "Date", "Time", "Description", "Vendor", "Amount")));
        System.out.println(Colors.TRON.colorize(middleBox));

        for (Transaction transaction : transactionList) {

            String date = transaction.transactionDate().format(DateTimeFormats.DATE);
            String time = transaction.transactionTime().format(DateTimeFormats.TIME);
            String description = transaction.transactionDescription();
            String vendor = transaction.vendor();
            String amount = String.format("$%.2f", transaction.transactionAmount());

            Colors amountColor = transaction.transactionAmount() > 0 ? Colors.GREEN : Colors.CRIMSON;

            // Pad amount before coloring so it doesn't affect alignment.
            String paddedAmount = String.format("%" + amountWidth + "s", amount);

            // Formatting row
            String row = String.format(
                    "║ %-" + dateWidth + "s "
                            + "║ %-" + timeWidth + "s "
                            + "║ %-" + descriptionWidth + "s "
                            + "║ %-" + vendorWidth + "s "
                            + "║ %s ║",
                    date,
                    time,
                    description,
                    vendor,
                    paddedAmount
            );

            // Apply color only to the amount, then return the rest of the row to TRON color.
            row = row.replace(paddedAmount, amountColor.getCode() + paddedAmount + Colors.TRON.getCode());

            System.out.println(Colors.TRON.getCode() + row + Colors.RESET);
        }

        System.out.println(Colors.TRON.colorize(bottomBox));
    }

    /**
     * Repeats a text value a specified number of times.
     *
     * @param text the text to repeat
     * @param boxAmount the number of times to repeat the text
     * @return the repeated text
     */
    private static String repeat(String text, int boxAmount) {
        return text.repeat(boxAmount);
    }
}