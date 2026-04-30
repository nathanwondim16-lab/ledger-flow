package com.pluralsight;

import java.util.List;

public class LedgerFormatting {

    protected static void calculateWidth(List<Transaction> transactionList) {
        int dateWidth = "Date".length();
        int timeWidth = "Time".length();
        int descriptionWidth = "Description".length();
        int vendorWidth = "Vendor".length();
        int amountWidth = "Amount".length();

        for(Transaction transaction : transactionList) {
            dateWidth = Math.max(dateWidth, transaction.getTransactionDate().format(DateTimeFormats.DATE).length());
            timeWidth = Math.max(timeWidth, transaction.getTransactionTime().format(DateTimeFormats.TIME).length());
            descriptionWidth = Math.max(descriptionWidth, transaction.getTransactionDescription().length());
            vendorWidth = Math.max(vendorWidth, transaction.getVendor().length());

            String amountText = String.format("$%.2f", transaction.getTransactionAmount());
            amountWidth = Math.max(amountWidth, amountText.length());
        }


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

        // Header format
        String headerFormat = "║ %-" + dateWidth + "s "
                + "║ %-" + timeWidth + "s "
                + "║ %-" + descriptionWidth + "s "
                + "║ %-" + vendorWidth + "s "
                + "║ %" + amountWidth + "s ║";

        // Prints header
        System.out.println(Colors.TRON.printWithColor(topBox));
        System.out.println(Colors.TRON.printWithColor(String.format(headerFormat, "Date", "Time", "Description", "Vendor", "Amount")));
        System.out.println(Colors.TRON.printWithColor(middleBox));

        // Prints rows
        for (Transaction transaction : transactionList) {

            String date = transaction.getTransactionDate().format(DateTimeFormats.DATE);
            String time = transaction.getTransactionTime().format(DateTimeFormats.TIME);
            String description = transaction.getTransactionDescription();
            String vendor = transaction.getVendor();
            String amount = String.format("$%.2f", transaction.getTransactionAmount());

            Colors amountColor = transaction.getTransactionAmount() > 0 ? Colors.GREEN : Colors.CRIMSON;

            // Format the amount before adding color
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

            // Adding color after formatting
            row = row.replace(paddedAmount, amountColor.getCode() + paddedAmount + Colors.TRON.getCode());

            System.out.println(Colors.TRON.getCode() + row + Colors.RESET);

            System.out.println(Colors.TRON.printWithColor(row));
        }


        System.out.println(Colors.TRON.printWithColor(bottomBox));
    }


    private static String repeat(String text, int boxAmount) {
        return text.repeat(boxAmount);
    }
}
