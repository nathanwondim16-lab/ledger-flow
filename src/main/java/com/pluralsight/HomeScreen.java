package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class HomeScreen {
    private final Scanner scanner = new Scanner(System.in);


    public void start() {
        welcomeMessage();
        String message = """
                Select one of the following options below
                
                D) Add Deposit
                P) Make Payemnt (Debit)
                L) Ledger
                X) Exit
                
                Select option:\s""";
        String ledgerOptions = """
                Select one of the following options below
                
                A) Display All Entries
                D) Deposits
                P) Payments
                R) Reports
                
                Select option:\s""";
        String reportsOptions = """
                Select one of the following options below
                
                1) Month to Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                
                Select option:\s""";
        System.out.print(message);
        String userChoice = scanner.nextLine().strip();
        TransactionProcessor processor = new TransactionProcessor();
        while(!userChoice.equalsIgnoreCase("X")) {
            switch(userChoice.toUpperCase()) {
                case "D", "P" -> {
                    processor.recordTransactions(getTransactionDetails()); // Sends transaction details to the processor to be recorded.
                }

                case "L" -> {
                    System.out.print(ledgerOptions);
                    userChoice = scanner.nextLine().strip();
                    switch(userChoice.toUpperCase()) {
                        case "A" -> {
                            processor.displayLedger();
                        }

                        case "D" -> {
                            processor.displayDeposits();
                        }

                        case "P" -> {
                            processor.displayPayments();
                        }

                        case "R" -> {
                            System.out.print(reportsOptions);
                            processor.reports(Integer.parseInt(scanner.nextLine()));

                        }

                        default -> System.out.println("Invalid option. Please try again");
                    }
                }

                default -> System.out.println("Invalid option. Please try again.\n");
            }
            System.out.print(message);
            userChoice = scanner.nextLine().strip();
        }
        stop();
    }

    private Transaction getTransactionDetails() {
        System.out.print("Enter in the transaction date using this format (YYYY-MM-DD): ");
        LocalDate transactionDate = LocalDate.parse(scanner.nextLine().strip());

        System.out.print("Enter in the transaction time using this format (HH:mm:ss): ");
        LocalTime transactionTime = LocalTime.parse(scanner.nextLine().strip());

        System.out.print("Enter in the description of the transaction: ");
        String transactionDescription = scanner.nextLine().strip();

        System.out.print("Enter in the name of the vendor: ");
        String vendor = scanner.nextLine().strip();

        System.out.print("Enter in the transaction amount: $");
        double transactionAmount = Double.parseDouble(scanner.nextLine());

        System.out.println("\nTransaction recorded \uD83E\uDDFE✅\n");

        return new Transaction(transactionDate, transactionTime,
                transactionDescription, vendor, transactionAmount);
    }

    private void welcomeMessage() {
        System.out.print(Colors.TRON.printWithColor("""
                ██╗     ███████╗██████╗  ██████╗ ███████╗██████╗ ███████╗██╗      ██████╗ ██╗    ██╗
                ██║     ██╔════╝██╔══██╗██╔════╝ ██╔════╝██╔══██╗██╔════╝██║     ██╔═══██╗██║    ██║
                ██║     █████╗  ██║  ██║██║  ███╗█████╗  ██████╔╝█████╗  ██║     ██║   ██║██║ █╗ ██║
                ██║     ██╔══╝  ██║  ██║██║   ██║██╔══╝  ██╔══██╗██╔══╝  ██║     ██║   ██║██║███╗██║
                ███████╗███████╗██████╔╝╚██████╔╝███████╗██║  ██║██║     ███████╗╚██████╔╝╚███╔███╔╝
                ╚══════╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚═╝  ╚═╝╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝\s
            
                """));
        String[] systemBoot = {
                "|  > Booting LedgerFlow...                         |",
                "|  > Initializing modules...                       |",
                "|  > Loading transaction engine...                 |",
                "|  > Establishing secure environment...            |",
                "|  > System ready.                                 |"
        };

        for (String line : systemBoot) {
            System.out.print(line);
            try {
                Thread.sleep(1500);
                System.out.print("\r ✅");
            } catch (InterruptedException e) {
                System.out.println("Something went wrong " + e.getMessage()); // Be more specific about what went wrong
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("\r ");
        }

    }

    private void stop() {
        String[] shutdown = {
                "|  > Logging out user...                           |",
                "|  > Archiving session data...                     |",
                "|  > Powering down...                              |",
                "|  > Session Closed                                |",
                "|  > LedgerFlow signing off                        |"
        };

        for (String line : shutdown) {
            System.out.print(line);
            try {
                Thread.sleep(1500);
                System.out.print("\r ✅");
            } catch (InterruptedException e) {
                System.out.println("Something went wrong " + e.getMessage()); // Be more specific about what went wrong
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("\r ");
        }
    }
}
