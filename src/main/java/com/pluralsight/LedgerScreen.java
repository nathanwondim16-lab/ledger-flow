package com.pluralsight;

public class LedgerScreen extends ScreenManager {


    @Override
    public void displayOptions() { // Maybe make this protected
        String ledgerOptions = Colors.CHAMPAGNE_SILVER.printWithColor("""
               
               
               Select one of the following options below
              
               A) Display All Entries
               D) Deposits
               P) Payments
               R) Reports
               H) Home - Go back to the homepage
               
               Select option:\s""");
        System.out.print(ledgerOptions);
        String userChoice = scanner.nextLine().strip();
        while(!userChoice.equalsIgnoreCase("H")) {
            switch(userChoice.toUpperCase()) {
                case "A" -> {
                    Ledger.displayLedger();
                }

                case "D" -> {
                    Ledger.displayDeposits();
                }

                case "P" -> {
                    Ledger.displayPayments();
                }

                case "R" -> {
                    ReportScreen reportScreen = new ReportScreen();
                    reportScreen.displayOptions();
                }

                default -> System.out.println(Colors.CRIMSON.printWithColor("===== INVALID OPTION. PLEASE CHOOSE A VALID OPTION ====="));
            }
            System.out.print(ledgerOptions);
            userChoice = scanner.nextLine().strip();
            System.out.println("\n");
        }
    }
}
