package com.pluralsight;

public class LedgerScreen extends ScreenManager {


    @Override
    public void displayOptions() { // Maybe make this protected
        String ledgerOptions = """
                \nSelect one of the following options below
                
                A) Display All Entries
                D) Deposits
                P) Payments
                R) Reports
                H) Home - Go back to the homepage
                
                Select option:\s""";
        Ledger ledger = new Ledger();
        System.out.print(ledgerOptions);
        String userChoice = scanner.nextLine().strip();
        while(!userChoice.equalsIgnoreCase("H")) {
            switch(userChoice.toUpperCase()) {
                case "A" -> {
                    ledger.displayLedger();
                }

                case "D" -> {
                    ledger.displayDeposits();
                }

                case "P" -> {
                    ledger.displayPayments();
                }

                case "R" -> {
                    ReportScreen reportScreen = new ReportScreen();
                    reportScreen.displayOptions();
                }

                default -> System.out.println("Invalid option. Please try again");
            }
            System.out.print(ledgerOptions);
            userChoice = scanner.nextLine().strip();
        }
    }
}
