package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a financial transaction in the ledger.
 *
 * This record stores all relevant details of a transaction, including:
 * - Date and time of the transaction
 * - Description of the transaction
 * - Vendor associated with the transaction
 * - Transaction amount (positive for deposits, negative for payments)
 *
 * This record is used for:
 * - Storing transaction data in memory
 * - Writing transactions to a CSV file
 * - Reading transactions from a CSV file
 *
 * @param transactionDate the date the transaction occurred
 * @param transactionTime the time the transaction occurred
 * @param transactionDescription a brief description of the transaction
 * @param vendor the entity associated with the transaction
 * @param transactionAmount the amount of the transaction. positive = deposit, negative = payment
 */
public record Transaction (
    LocalDate transactionDate,
    LocalTime transactionTime,
    String transactionDescription,
    String vendor,
    double transactionAmount
)

{
    /**
     * Returns a pipe-delimited string representation of the transaction.
     *
     * This format is used for writing transactions to the CSV file.
     *
     * @return formatted transaction string -> date|time|description|vendor|amount
     */
    @Override
    public String toString() {
        return transactionDate.format(DateTimeFormats.DATE) + "|"
                + transactionTime.format(DateTimeFormats.TIME) + "|"
                + transactionDescription + "|"
                + vendor + "|"
                + transactionAmount;
    }
}
